/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converters;

import com.google.inject.Inject;
import com.opensymphony.xwork2.conversion.TypeConversionException;
import facade.AuthorFacadeLocal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Author;
import org.apache.struts2.util.StrutsTypeConverter;

/**
 *
 * @author sergio
 */
public class AuthorConverter extends StrutsTypeConverter {
    
    @Inject
    private AuthorFacadeLocal authorFacade;

    @Override
    public Object convertFromString(Map map, String[] values, Class type){
        try{
            Set<Author> authors = new HashSet();
            for(String value: values){
                Author author = authorFacade.find(Long.valueOf(value));
                authors.add(author);
            }
            return authors;
        } catch(Exception e){
            Logger.getLogger(AuthorConverter.class.getName()).log(Level.INFO, "El campo autor no es válido");
            throw new TypeConversionException("El campo autor no es válido");
        }
        
    }

    @Override
    public String convertToString(Map map, Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
