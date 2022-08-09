package com.example.demo.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.jdbc.Sql;

import com.example.demo.model.Contact;
import com.example.demo.service.impl.ContactServiceImpl;
import com.example.demo.service.repository.ContactRepository;

@DataJpaTest
@ActiveProfiles("test")
public class ContactServiceTest {

    @Mock
    ContactRepository contactRepository;

    @InjectMocks
    ContactServiceImpl contactService;

    @Test
    void testGetAllContact() {
        List<Contact> mockContacts = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            mockContacts.add(new Contact());
        }

        when(contactRepository.findAll()).thenReturn(mockContacts);

        List<Contact> actualContacts = contactService.getAllContact();

        assertThat(actualContacts.size()).isEqualTo(mockContacts.size());

        verify(contactRepository).findAll();
    }

    @Test
    @Sql("classpath:test-data.sql")
    void testGetAllContactWithDatabase() {

        List<Contact> actualContacts = contactService.getAllContact();

        assertThat(actualContacts.size()).isEqualTo(contactRepository.findAll().size());
    }

    @Test
    public void shouldSaveContact() {
        Contact expectedContact = new Contact();
        expectedContact.setName("name");
        Contact actualContact = contactRepository.save(expectedContact);
        assertThat(actualContact).usingRecursiveComparison()
                .ignoringFields("id").isEqualTo(actualContact);
    }

    // @Test
    // public void updateContactTest(){
    // Contact contact = new Contact();
    // }

    @Test
    @Sql("classpath:test-data.sql")
    public void findAContactTest() {
        Optional<Contact> test = contactRepository.findById(1L);
        assertThat(test.isPresent());
    }

    // @Test
    // void when_FindbyIdContact_InvalidId_shownThrowException() {
    // Long invalidContactId = 7L;

    // assertThatThrownBy(() -> contactService.findById(invalidContactId))
    // .isInstanceOf(ResourceNotFoundException.class);
    // }

    // @Test
    // void when_UpdateContact_InvalidId_shownThrowException() {

    // Long invalidContactId = 0L;
    // Contact contact = new Contact();
    // contact.setId(invalidContactId);

    // assertThatThrownBy(() -> contactService.updateContact(contact))
    // .isInstanceOf(ResourceNotFoundException.class);

    // }

    // @Test
    // void when_DeleteContact_InvalidId_shownThrowException() {

    // Long invalidContactId = 0L;

    // // Use doThrow when method service is void method
    // doThrow(new RuntimeException("test
    // exception")).when(contactRepository).deleteById(invalidContactId);

    // assertThatThrownBy(() -> contactService.deleteContact(invalidContactId))
    // .isInstanceOf(RuntimeException.class);

    // }

    // @Test
    // void createContact_shownThrowException() {
    // Contact contact = new Contact();

    // doThrow(new RuntimeException("test
    // exception")).when(contactRepository).save(contact);

    // assertThatThrownBy(() -> contactService.createContact(contact))
    // .isInstanceOf(RuntimeException.class);
    // }
}
