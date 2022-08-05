package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ContactController {
    @Autowired
    ContactService contactService;

    @GetMapping("/")
    public @ResponseBody List<Contact> getAllContact() {
        return contactService.getAllContact();
    }

    @PostMapping("/create-contact")
    public ResponseEntity<String> createContact(@RequestBody Contact contact) {
        try {
            contactService.createContact(contact);
        } catch (Exception e) {

            return new ResponseEntity<>("Something went wrong!!" + e, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Create contact sucessfully!!", HttpStatus.OK);
    }

    @PutMapping("/update-contact")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        Contact contactUpdate = contactService.updateContact(contact);
        return ResponseEntity.ok(contactUpdate);
    }

    @DeleteMapping("/delete-contact-{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        try {
            contactService.deleteContact(id);
        } catch (Exception e) {

            return new ResponseEntity<>("Not Found Contact!!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Delete Contact Successfully", HttpStatus.OK);
    }

}
