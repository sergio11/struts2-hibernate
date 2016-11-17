/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actions.books;

import actions.FileProcessAction;
import com.google.inject.Inject;
import facade.BookFacadeLocal;
import java.io.IOException;
import models.Book;

/**
 *
 * @author sergio
 */
public class PostPersistBookAction extends FileProcessAction{
    
    @Inject
    private BookFacadeLocal bookFacade;
    private Book book;
    
  
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
   
    public String execute() throws Exception{
        try{
            if(upload != null){
                uploadFile();
                book.setExcerpt(uploadFileName);
            }
            bookFacade.create(book);
            addActionMessage(getText("success.book.save"));
            return SUCCESS;
        } catch (IOException e){
            e.printStackTrace();
            return ERROR;
        }
    }

}
