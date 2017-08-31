package org.kuleba.webservices.rest.services;

import org.kuleba.webservices.rest.entities.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactService {

    List<Contact> findFilteredContacts(String nameFilter);

}
