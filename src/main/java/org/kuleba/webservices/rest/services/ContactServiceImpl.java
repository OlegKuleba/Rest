package org.kuleba.webservices.rest.services;

import org.kuleba.webservices.rest.BadPatternSyntaxException;
import org.kuleba.webservices.rest.entities.Contact;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactServiceImpl implements ContactService {

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_QUERY = "SELECT * FROM CONTACTS";
    private static final String CONTACT_ID = "ID";
    private static final String CONTACT_NAME = "NAME";

    public ContactServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Contact> findFilteredContacts(String nameFilter) {

        List<Contact> contactList = jdbcTemplate.query(SQL_QUERY, resultSet -> {

            Pattern pattern;
            try {
                pattern = Pattern.compile(nameFilter);
            } catch (Exception e) {
                throw new BadPatternSyntaxException();
            }
            Matcher matcher;
            matcher = pattern.matcher("");

            List<Contact> contacts = new ArrayList<>();
            while (resultSet.next()) {
                matcher.reset(resultSet.getString(CONTACT_NAME));
                if (!matcher.find()) {
                    contacts.add(new Contact(resultSet.getInt(CONTACT_ID), resultSet.getString(CONTACT_NAME)));
                }
            }
            return contacts;
        });

        return contactList;
    }
}
