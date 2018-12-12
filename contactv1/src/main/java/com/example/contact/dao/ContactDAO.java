package com.example.contact.dao;

import com.example.contact.domain.Contact;

import java.util.List;

public interface ContactDAO {

    public void addContact(Contact contact);

    public Contact updateContact(Contact contact);

    public void deleteContact(Contact contact);

    public Contact getContact(Long idContact);

    public Contact getContactByEmail(String email);

    public List<Contact> getAll();

}
