/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.books;

import actions.BaseAction;
import com.google.inject.Inject;
import facade.BookFacadeLocal;
import java.util.List;
import models.Book;

/**
 *
 * @author sergio
 */
public class BooksAction extends BaseAction {
    
    @Inject
    private BookFacadeLocal booksFacade;
    
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }
    
    public String execute() throws Exception {
        // get all books
        books = booksFacade.findAll();
        return SUCCESS;
    }
}
