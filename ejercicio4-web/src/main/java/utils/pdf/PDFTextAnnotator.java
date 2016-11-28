/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.pdf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationTextMarkup;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

public class PDFTextAnnotator extends PDFTextStripper {

  private float verticalTolerance = 0;
  private float heightModifier = (float) 2.250;

  private class Match { 
    public final String str;
    public final List<TextPosition> positions;

    public Match(String str, List<TextPosition> positions) {
      this.str = str;
      this.positions = positions; 
    }
  }
  
  /**
   * Internal class that keeps a mapping from the text contents to their
   * TextPositions. This is needed to compute bounding boxes. The data is stored
   * on a per-page basis (keyed on the 1-based pageNo)
   */
  private class TextCache {
    private final Map<Integer, StringBuilder> texts = new HashMap<Integer, StringBuilder>();
    private final Map<Integer, ArrayList<TextPosition>> positions = new HashMap<Integer, ArrayList<TextPosition>>();

    public StringBuilder obtainStringBuilder(Integer pageNo) {
      StringBuilder sb = texts.get(pageNo);
      if (sb == null) {
        sb = new StringBuilder();
        texts.put(pageNo, sb);
      }
      return sb;
    }

    public ArrayList<TextPosition> obtainTextPositions(Integer pageNo) {
      ArrayList<TextPosition> textPositions = positions.get(pageNo);
      if (textPositions == null) {
        textPositions = new ArrayList<TextPosition>();
        positions.put(pageNo, textPositions);
      }
      return textPositions;
    }

    public String getText(Integer pageNo) {
      return obtainStringBuilder(pageNo).toString();
    }

    public void append(String str, TextPosition pos) {
      int currentPage = getCurrentPageNo();
      ArrayList<TextPosition> positions = obtainTextPositions(currentPage);
      StringBuilder sb = obtainStringBuilder(currentPage);

      for (int i = 0; i < str.length(); i++) {
        sb.append(str.charAt(i));
        positions.add(pos);
      }
    }

    public List<TextPosition> getTextPositions(Integer pageNo) {
      return obtainTextPositions(pageNo);
    }

    public List<Match> getTextPositions(Integer pageNo, Pattern pattern) {
      Matcher matcher = pattern.matcher(getText(pageNo));
      List<Match> matches = new ArrayList<Match>();

      while (matcher.find()) {
        List<TextPosition> elements = this.getTextPositions(pageNo).subList(matcher.start(), matcher.end());
        matches.add(new Match(matcher.group(), elements));
      }
      return matches;
    }
  }

  private TextCache textCache;
  private PDGamma defaultColor;

  /**
   * Instantiate a new PDFTextAnnotator object. This object will load properties
   * from PDFTextAnnotator.properties and will apply encoding-specific
   * conversions to the output text.
   *
   * @param encoding
   *          The encoding that the output will be written in.
   * @throws IOException
   *           If there is an error reading the properties.
   */
  public PDFTextAnnotator(final String encoding) throws IOException {
    super(encoding);
  }

  /**
   * Computes a series of bounding boxes from the TextPositions. It will create
   * a new bounding box if the vertical tolerance is exceeded
   * 
   * @param matches
   * @throws IOException
   */
  private List<PDRectangle> getTextBoundingBoxes(List<TextPosition> matches) {
    List<PDRectangle> boundingBoxes = new ArrayList<PDRectangle>();

    float lowerLeftX = 0, lowerLeftY = 0, upperRightX = 0, upperRightY = 0;
    boolean first = true;
    for (int i = 0; i < matches.size(); i++) {
      TextPosition position = matches.get(i);
      if (position == null) {
        continue;
      }
      Matrix textPos = position.getTextPos();
      float height = (float) (position.getHeight() * getHeightModifier());
      if (first) {
        lowerLeftX = textPos.getXPosition();
        upperRightX = lowerLeftX + position.getWidth();

        lowerLeftY = textPos.getYPosition();
        upperRightY = lowerLeftY + height;
        first = false;
        continue;
      }

      // we are still on the same line
      if (Math.abs(textPos.getYPosition() - lowerLeftY) <= getVerticalTolerance()) { 
        upperRightX = textPos.getXPosition() + position.getWidth();
        upperRightY = textPos.getYPosition() + height;
      } else {
        PDRectangle boundingBox = boundingBox(lowerLeftX, lowerLeftY, upperRightX, upperRightY);
        boundingBoxes.add(boundingBox);

        // new line
        lowerLeftX = textPos.getXPosition();
        upperRightX = lowerLeftX + position.getWidth();

        lowerLeftY = textPos.getYPosition();
        upperRightY = lowerLeftY + height;
      }
    }
    if (!(lowerLeftX == 0 && lowerLeftY == 0 && upperRightX == 0 && upperRightY == 0)) {
      PDRectangle boundingBox = boundingBox(lowerLeftX, lowerLeftY, upperRightX, upperRightY);
      boundingBoxes.add(boundingBox);
    }
    return boundingBoxes;
  }

  private PDRectangle boundingBox(float lowerLeftX, float lowerLeftY, float upperRightX, float upperRightY) {
    PDRectangle boundingBox = new PDRectangle();
    boundingBox.setLowerLeftX(lowerLeftX);
    boundingBox.setLowerLeftY(lowerLeftY);
    boundingBox.setUpperRightX(upperRightX);
    boundingBox.setUpperRightY(upperRightY);
    return boundingBox;
  }

  /**
   * Highlights a pattern within the PDF with the default color 
   * Returns the list of added annotations for further modification 
   * Note: it will process every page, but cannot process patterns that span multiple pages 
   * Note: it will not work for top-bottom text (such as Chinese)
   * 
   * @param pdf
   *          PDDocument
   * @param pattern
   *          String that will be converted to Regex pattern
   * @throws Exception
   */
  public List<PDAnnotationTextMarkup> highlight(final PDDocument pdf, final String pattern) throws Exception {
    return highlight(pdf, Pattern.compile(pattern));
  }

  /**
   * Highlights a pattern within the PDF with the default color 
   * Returns the list of added annotations for further modification
   * Note: it will process every page, but cannot process patterns that span multiple pages 
   * Note: it will not work for top-bottom text (such as Chinese)
   * 
   * @param pdf
   *          PDDocument
   * @param pattern
   *          Pattern (regex)
   * @throws Exception
   */
  public List<PDAnnotationTextMarkup> highlight(PDDocument pdf, Pattern pattern) throws Exception {
    if (textCache == null) {
      throw new Exception("TextCache was not initilized, please run initialize on the document first");
    }

    List<PDPage> pages = pdf.getDocumentCatalog().getAllPages();

    ArrayList<PDAnnotationTextMarkup> highligts = new ArrayList<PDAnnotationTextMarkup>();

    for (int pageIndex = getStartPage() - 1; pageIndex < getEndPage() && pageIndex < pages.size(); pageIndex++) {
      PDPage page = pages.get(pageIndex);
      List<PDAnnotation> annotations = page.getAnnotations();

      List<Match> matches = this.textCache.getTextPositions(pageIndex + 1, pattern);

      for (Match match : matches) {
        List<PDRectangle> textBoundingBoxes = getTextBoundingBoxes(match.positions);

        PDAnnotationTextMarkup markup = new PDAnnotationTextMarkup(PDAnnotationTextMarkup.SUB_TYPE_HIGHLIGHT);
        if (textBoundingBoxes.size() > 0) {
          markup.setRectangle(textBoundingBoxes.get(0));

          float[] quads = new float[8 * textBoundingBoxes.size()];
          int cursor = 0;
          for (PDRectangle rect : textBoundingBoxes) {
            float[] tmp = computeQuads(rect);
            for (int i = 0; i < tmp.length; i++) {
              quads[cursor + i] = tmp[i];
            }
            cursor = cursor + 8;
          }

          markup.setQuadPoints(quads);

          markup.setConstantOpacity((float) 0.8);
          markup.setColour(getDefaultColor());
          markup.setPrinted(true);
          markup.setContents(match.str);
                    
          annotations.add(markup);
          highligts.add(markup);
        }
      }
    }
    return highligts;
  }

  private float[] computeQuads(PDRectangle rect) {
    float[] quads = new float[8];
    // top left
    quads[0] = rect.getLowerLeftX(); // x1
    quads[1] = rect.getUpperRightY(); // y1
    // bottom left
    quads[2] = rect.getUpperRightX(); // x2
    quads[3] = quads[1]; // y2
    // top right
    quads[4] = quads[0]; // x3
    quads[5] = rect.getLowerLeftY(); // y3
    // bottom right
    quads[6] = quads[2]; // x4
    quads[7] = quads[5]; // y5
    return quads;
  }

  public void getDefaultColor(PDGamma color) {
    this.defaultColor = color;
  }

  public PDGamma getDefaultColor() {
    if (this.defaultColor != null) {
      return this.defaultColor;
    } else { // #fbe85a
      PDGamma c = new PDGamma();
      c.setR((float) 0.9843);
      c.setG((float) 0.9098);
      c.setB((float) 0.3879);
      return c;
    }
  }

  public float getVerticalTolerance() {
    return this.verticalTolerance;
  }

  public void setVerticalTolerance(float tolerance) {
    this.verticalTolerance = tolerance;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void resetEngine() {
    super.resetEngine();
    this.textCache = null;
  }

  public void initialize(final PDDocument pdf) throws IOException {
    this.resetEngine();

    this.textCache = new TextCache();

    if (this.getAddMoreFormatting()) {
      this.setParagraphEnd(this.getLineSeparator());
      this.setPageStart(this.getLineSeparator());
      this.setArticleStart(this.getLineSeparator());
      this.setArticleEnd(this.getLineSeparator());
    }
    this.startDocument(pdf);
    this.processPages(pdf.getDocumentCatalog().getAllPages());
    this.endDocument(pdf);
  }

  /**
   * Start a new article, which is typically defined as a column on a single
   * page (also referred to as a bead). Default implementation is to do nothing.
   * Subclasses may provide additional information.
   *
   * @param isltr
   *          true if primary direction of text is left to right.
   * @throws IOException
   *           If there is any error writing to the stream.
   */
  @Override
  protected void startArticle(final boolean isltr) throws IOException {
    String articleStart = this.getArticleStart();
    this.textCache.append(articleStart, null);

  }

  /**
   * End an article. Default implementation is to do nothing. Subclasses may
   * provide additional information.
   *
   * @throws IOException
   *           If there is any error writing to the stream.
   */
  @Override
  protected void endArticle() throws IOException {
    String articleEnd = this.getArticleEnd();
    this.textCache.append(articleEnd, null);

  }

  /**
   * Start a new page. Default implementation is to do nothing. Subclasses may
   * provide additional information.
   *
   * @param page
   *          The page we are about to process.
   *
   * @throws IOException
   *           If there is any error writing to the stream.
   */
  @Override
  protected void startPage(final PDPage page) throws IOException {
    // default is to do nothing.
  }

  /**
   * End a page. Default implementation is to do nothing. Subclasses may provide
   * additional information.
   *
   * @param page
   *          The page we are about to process.
   *
   * @throws IOException
   *           If there is any error writing to the stream.
   */
  @Override
  protected void endPage(final PDPage page) throws IOException {
    // default is to do nothing
  }

  /**
   * Write the page separator value to the text cache.
   *
   * @throws IOException
   *           If there is a problem writing out the pageseparator to the
   *           document.
   */
  @Override
  protected void writePageSeperator() {
    String pageSeparator = this.getPageSeparator();
    this.textCache.append(pageSeparator, null);

  }

  /**
   * Write the line separator value to the text cache.
   *
   * @throws IOException
   *           If there is a problem writing out the lineseparator to the
   *           document.
   */
  @Override
  protected void writeLineSeparator() {
    String lineSeparator = this.getLineSeparator();
    this.textCache.append(lineSeparator, null);

  }

  /**
   * Write the word separator value to the text cache.
   *
   * @throws IOException
   *           If there is a problem writing out the wordseparator to the
   *           document.
   */
  @Override
  protected void writeWordSeparator() {
    String wordSeparator = this.getWordSeparator();
    this.textCache.append(wordSeparator, null);

  }

  /**
   * Write the string in TextPosition to the text cache.
   *
   * @param text
   *          The text to write to the stream.
   * @throws IOException
   *           If there is an error when writing the text.
   */
  @Override
  protected void writeCharacters(final TextPosition text) {
    String character = text.getCharacter();
    this.textCache.append(character, text);

  }

  /**
   * Write a string to the text cache. The default implementation will ignore
   * the <code>text</code> and just calls {@link #writeCharacters(TextPosition)}
   * .
   *
   * @param text
   *          The text to write to the stream.
   * @param textPositions
   *          The TextPositions belonging to the text.
   * @throws IOException
   *           If there is an error when writing the text.
   */
  @Override
  protected void writeString(final String text, final List<TextPosition> textPositions) {
    for (final TextPosition textPosition : textPositions) {
      this.writeCharacters(textPosition);
    }
  }

  private boolean inParagraph;

  /**
   * writes the paragraph separator string to the text cache.
   *
   * @throws IOException
   *           if something went wrong
   */
  @Override
  protected void writeParagraphSeparator() {
    this.writeParagraphEnd();
    this.writeParagraphStart();
  }

  /**
   * Write something (if defined) at the start of a paragraph.
   *
   * @throws IOException
   *           if something went wrong
   */
  @Override
  protected void writeParagraphStart() {
    if (this.inParagraph) {
      this.writeParagraphEnd();
      this.inParagraph = false;
    }

    String paragraphStart = this.getParagraphStart();
    this.textCache.append(paragraphStart, null);
    this.inParagraph = true;
  }

  /**
   * Write something (if defined) at the end of a paragraph.
   *
   * @throws IOException
   *           if something went wrong
   */
  @Override
  protected void writeParagraphEnd() {
    String paragraphEnd = this.getParagraphEnd();
    this.textCache.append(paragraphEnd, null);

    this.inParagraph = false;
  }

  /**
   * Write something (if defined) at the start of a page.
   *
   * @throws IOException
   *           if something went wrong
   */
  @Override
  protected void writePageStart() {
    String pageStart = this.getPageStart();
    this.textCache.append(pageStart, null);
  }

  /**
   * Write something (if defined) at the start of a page.
   *
   * @throws IOException
   *           if something went wrong
   */
  @Override
  protected void writePageEnd() {
    String pageEnd = this.getPageEnd();
    this.textCache.append(pageEnd, null);
  }

  public float getHeightModifier() {
    return heightModifier;
  }

  public void setHeightModifier(float heightModifier) {
    this.heightModifier = heightModifier;
  }

}
