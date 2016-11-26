/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.ejb.Local;
import models.PdfFile;

/**
 *
 * @author sergio
 */
@Local
public interface FileStorageFacadeLocal {

    void create(PdfFile fileStorage);

    void edit(PdfFile fileStorage);

    void remove(PdfFile fileStorage);

    PdfFile find(Object id);

    List<PdfFile> findAll();

    List<PdfFile> findRange(int offset, int count);

    int count();
    
}
