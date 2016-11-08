/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author sergio
 */
@Indexed
@Entity
@NamedQueries({
    @NamedQuery(name="Author.findAll", query="SELECT a FROM Author a ")
})
public class Author implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Field
    private String name;
    @Field
    private String lastname;
    @Transient
    private String fullname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return this.name + " - " + this.lastname;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", name=" + name + ", lastname=" + lastname + ", fullname=" + fullname + '}';
    }
    
}
