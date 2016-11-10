/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.books;

import actions.BaseAction;
import java.util.List;
import models.Book;

/**
 *
 * @author sergio
 */
public class BooksAction extends BaseAction {
    
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    public String execute() throws Exception {
        return SUCCESS;
    }
    
    
}
