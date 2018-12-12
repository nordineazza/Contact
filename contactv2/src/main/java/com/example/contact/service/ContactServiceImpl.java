package com.example.contact.service;

import com.example.contact.dao.ContactDAO;
import com.example.contact.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    ContactDAO contactDAO;

    @Transactional
    @Override
    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    @Transactional
    @Override
    public Contact updateContact(Contact contact) {
        return contactDAO.updateContact(contact);
    }

    @Transactional
    @Override
    public void deleteContact(Long idContact) {
        Contact c = getContact(idContact);
        contactDAO.deleteContact(c);
    }

    @Transactional(readOnly = true)
    @Override
    public Contact getContact(Long idContact) {
        return contactDAO.getContact(idContact);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contact> getAll() {
        return contactDAO.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Contact getContactByEmail(String email) {
        return contactDAO.getContactByEmail(email);
    }

}
