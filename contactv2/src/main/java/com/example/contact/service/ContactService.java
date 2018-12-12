package com.example.contact.service;

import com.example.contact.domain.Contact;

import java.util.List;

public interface ContactService {

    void addContact(Contact contact);

    Contact updateContact(Contact contact);

    void deleteContact(Long idContact);

    Contact getContact(Long idContact);

    Contact getContactByEmail(String email);

    List<Contact> getAll();
}
