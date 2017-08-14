package org.kuleba.webservices.rest.services;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kuleba.webservices.rest.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    @Test
    public void testFindAllContacts() {
        long expectedAmount = 100;
        Page<Contact> contacts = contactRepository.findAll(new PageRequest(0, 15));
        assertThat(contacts.getTotalElements()).isEqualTo(expectedAmount);
    }

}
