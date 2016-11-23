/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.ejb.Local;
import models.Author;

/**
 *
 * @author sergio
 */
@Local
public interface AuthorFacadeLocal {

    void create(Author author);

    void edit(Author author);

    void remove(Author author);

    Author find(Object id);

    List<Author> findAll();

    List<Author> findRange(int offset, int count);

    int count();
    
}
