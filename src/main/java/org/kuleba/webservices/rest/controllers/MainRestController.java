package org.kuleba.webservices.rest.controllers;

import org.kuleba.webservices.rest.entities.Contact;
import org.kuleba.webservices.rest.services.ContactRepository;
import org.kuleba.webservices.rest.services.ContactService;
import org.kuleba.webservices.rest.services.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class MainRestController {

    private static final int PAGE_SIZE = 15;
    private static final String NAME_REGEX = " ";

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ContactService contactService;

    @RequestMapping("contacts/{page}")
    Iterable<Contact> findContacts(@PathVariable("page") int page) {
        if (page < 0) {
            page = 0;
        }
        return contactRepository.findAll(new PageRequest(page, PAGE_SIZE));
    }

    @RequestMapping("contacts")
    public List<Contact> findContactsByRegex(@RequestParam(defaultValue = NAME_REGEX) String nameFilter) {
        contactService = new ContactServiceImpl(jdbcTemplate);
        return contactService.findFilteredContacts(nameFilter);
    }
}