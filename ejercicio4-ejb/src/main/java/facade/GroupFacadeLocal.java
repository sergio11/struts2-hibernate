/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.ejb.Local;
import models.Group;

/**
 *
 * @author sergio
 */
@Local
public interface GroupFacadeLocal {

    void create(Group group);

    void edit(Group group);

    void remove(Group group);

    Group find(Object id);

    List<Group> findAll();

    List<Group> findRange(int offset, int count);

    int count();
    
}
