/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.books;

import com.google.inject.Inject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import facade.BookFacadeLocal;
import java.util.logging.Logger;
import models.Book;
import models.Level;

/**
 *
 * @author sergio
 */
public class PrePersistBookAction extends ActionSupport implements Preparable {
    @Inject
    private BookFacadeLocal bookFacade;
    private Integer idBook;
    private Book book;
    private Level[] levels;

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Level[] getLevels() {
        levels = Level.values();
        return levels;
    }
    
    public String execute() throws Exception{
        return SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        if(idBook != null){
            book = bookFacade.find(idBook);
        }else{
            book = new Book();
        }
    }
    
}
