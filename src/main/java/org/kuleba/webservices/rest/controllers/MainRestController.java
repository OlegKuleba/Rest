package org.kuleba.webservices.rest.controllers;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.kuleba.webservices.rest.BadPatternSyntaxException;
import org.kuleba.webservices.rest.entities.Contact;
import org.kuleba.webservices.rest.services.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/hello")
public class MainRestController {

    private static final int PAGE_SIZE = 15;
    private static final String NAME_REGEX = " ";

    @Autowired
    ContactRepository contactRepository;

    @RequestMapping("contacts/{page}")
    Iterable<Contact> findContacts(@PathVariable("page") int page) {
        if (page < 0) {
            page = 0;
        }
        return contactRepository.findAll(new PageRequest(page, PAGE_SIZE));
    }

    @RequestMapping("contacts")
    public List<Contact> findContactsByRegex(@RequestParam(defaultValue = NAME_REGEX) String nameFilter) {

        Pattern pattern = null;
        try {
            pattern = Pattern.compile(nameFilter);
        } catch (Exception e) {
            throw new BadPatternSyntaxException();
        }
        Matcher matcher;
        matcher = pattern.matcher("");
        Predicate<Contact> matchesWithRegex = new Predicate<Contact>() {
            @Override
            public boolean apply(Contact contact) {
                matcher.reset(contact.getName());
                return !matcher.find();
            }
        };

        return Lists.newArrayList(Iterables.filter(contactRepository.findAll(), matchesWithRegex));
    }

}
