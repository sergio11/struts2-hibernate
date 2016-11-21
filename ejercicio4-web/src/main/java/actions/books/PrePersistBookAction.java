/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.books;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import facade.AuthorFacadeLocal;
import facade.BookFacadeLocal;
import java.util.List;
import models.Author;
import models.Book;
import models.Level;

/**
 *
 * @author sergio
 */
public class PrePersistBookAction extends ActionSupport implements Preparable {
    @Inject
    private BookFacadeLocal bookFacade;
    @Inject
    private AuthorFacadeLocal authorFacade;
    private Long idBook;
    private Book bookEdit;
    private Level[] levels;
    private List<Author> authors;

    public Long getIdBook() {
        return idBook;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public Book getBookEdit() {
        return bookEdit;
    }

    public void setBookEdit(Book bookEdit) {
        this.bookEdit = bookEdit;
    }

    public Level[] getLevels() {
        levels = Level.values();
        return levels;
    }

    public List<Author> getAuthors() {
        authors = authorFacade.findAll();
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
    
    public String execute() throws Exception{
        return SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        if(idBook != null){
            bookEdit = bookFacade.find(idBook);
        }else{
            bookEdit = new Book();
        }
    }
    
}
