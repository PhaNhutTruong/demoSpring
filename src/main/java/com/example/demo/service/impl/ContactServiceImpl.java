package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

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

        return contactRepository.findAll();
    }

    @Override
    public void createContact(Contact contact) {

        contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {

        // Contact updateContact = contactRepository.findById(contact.getId())
        // .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id:"
        // + contact.getId()));
        // return contactRepository.save(updateContact);
        return contactRepository.findById(contact.getId())
                .map(contactUpdate -> {
                    contactUpdate.setName(contact.getName());
                    contactUpdate.setBirthDay(contact.getBirthDay());
                    return contactRepository.save(contactUpdate);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id:"
                        + contact.getId()));

    }

    @Override
    public void deleteContact(Long id) {

        contactRepository.deleteById(id);
    }

    @Override
    public Optional<Contact> findById(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact;
    }

}
