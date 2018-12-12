package com.example.contact.web;

import com.example.contact.domain.Contact;
import com.example.contact.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ContactV2")
public class ContactController {
    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ModelAndView get() {
        List<Contact> listContact = contactService.getAll();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("contacts", listContact);
        logger.info(listContact.size() + " contacts sont récupérés");
        return mv;
    }

    @PostMapping("/Add")
    public String addContact(@ModelAttribute("contact") Contact contact){
        contactService.addContact(contact);
        logger.info("Le contact a bien été ajouté.", contact);
        return "redirect:/ContactV2";
    }

    @GetMapping("/Delete")
    public String removeContact(@RequestParam(name="id")String id){
        Long idContact = Long.parseLong(id);
        contactService.deleteContact(idContact);
        return "redirect:/ContactV2";
    }

    @GetMapping("/Update")
    public ModelAndView updateContact(@RequestParam(name="id")String id){
        Long idContact = Long.parseLong(id);
        Contact c = contactService.getContact(idContact);
        ModelAndView mv = new ModelAndView("update");
        mv.addObject("contact", c);
        return mv;
    }

    @PostMapping("/Update")
    public String updateContact(@ModelAttribute("contact") Contact contact) {
        contactService.updateContact(contact);
        return "redirect:/ContactV2";
    }

}
