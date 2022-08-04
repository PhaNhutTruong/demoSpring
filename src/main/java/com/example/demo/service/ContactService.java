package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Contact;

public interface ContactService {

    public List<Contact> getAllContact();

    public void createContact(Contact contact);

    public Contact updateContact(Contact contact);

    public void deleteContact(Long id);

    public Optional<Contact> findById(Long id);
}
