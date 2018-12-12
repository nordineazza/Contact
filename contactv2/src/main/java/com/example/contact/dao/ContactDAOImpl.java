package com.example.contact.dao;

import com.example.contact.domain.Contact;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContactDAOImpl implements ContactDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addContact(Contact contact) {
        em.persist(contact);
    }

    @Override
    public Contact updateContact(Contact contact) {
        return em.merge(contact);
    }

    @Override
    public void deleteContact(Contact contact) {
        em.remove(contact);
    }

    @Override
    public Contact getContact(Long idContact) {
        return em.find(Contact.class, idContact);
    }

    @Override
    public Contact getContactByEmail(String email) {
        return em.createQuery("FROM Contact c WHERE c.mail =:email", Contact.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<Contact> getAll() {
        return em.createQuery("FROM Contact", Contact.class).getResultList();
    }


}
