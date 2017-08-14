package org.kuleba.webservices.rest.services;

import org.kuleba.webservices.rest.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    Page<Contact> findAll(Pageable pageable);

}
