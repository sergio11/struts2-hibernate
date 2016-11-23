/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.books;

import actions.BaseAction;
import com.google.inject.Inject;
import com.opensymphony.xwork2.Preparable;
import facade.BookFacadeLocal;
import java.util.List;
import models.Book;

/**
 * @author sergio
 */
public class BooksAction extends BaseAction implements Preparable {
    
    private final static Integer DEFAULT_OFFSET = 0;
    private final static Integer DEFAULT_COUNT = 10;
    
    @Inject
    private BookFacadeLocal booksFacade;
    private String query;
    private Integer offset = DEFAULT_OFFSET;
    private Integer count = DEFAULT_COUNT;
    private List<Book> books;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        if(query != null){
            books = booksFacade.search(query);
        }else{
            // get all books
            books = booksFacade.findRange(offset, count);
        }
    }
}
