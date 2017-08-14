package org.kuleba.webservices.rest.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    public Contact() {
    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Contact)) {
            return false;
        }

        Contact contact = (Contact) obj;
        return id == contact.id && Objects.equals(name, contact.name);
    }

}
