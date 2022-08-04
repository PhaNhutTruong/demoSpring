package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import com.example.demo.service.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContact() {
        // TODO Auto-generated method stub
        return contactRepository.findAll();
    }

    @Override
    public void createContact(Contact contact) {
        // TODO Auto-generated method stub
        contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        // TODO Auto-generated method stub
        Contact updateContact = contactRepository.findById(contact.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id: " + contact.getId()));
        return contactRepository.save(updateContact);
    }

    @Override
    public void deleteContact(Long id) {
        // TODO Auto-generated method stub
        contactRepository.deleteById(id);
    }

}
