package com.example.contact.web;

import com.example.contact.domain.Contact;
import com.example.contact.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.*;
import java.util.List;

@Controller
@RequestMapping("/ContactV3")
public class ContactController {
    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    Validator validator;

    @GetMapping
    public ModelAndView get() {
        List<Contact> listContact = contactService.getAll();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("contacts", listContact);
        mv.addObject("contact", new Contact());
        logger.info(listContact.size() + " contacts sont récupérés");
        return mv;
    }

    @PostMapping("/Add")
    public ModelAndView addContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult){
        ModelAndView mv = new ModelAndView("redirect:/ContactV3");
        if (bindingResult.hasErrors()) {
            logger.error("errors = " + bindingResult.getAllErrors());
            mv.setViewName("index");
            mv.addObject("contacts", contactService.getAll());
        } else {
            contactService.addContact(contact);
        }
        return mv;
    }

    @GetMapping("/Delete")
    public String removeContact(@RequestParam(name="id") String id){
        Long idContact = Long.parseLong(id);
        contactService.deleteContact(idContact);
        return "redirect:/ContactV3";
    }

    @GetMapping("/Update")
    public ModelAndView updateContact(@RequestParam(name="id") String id){
        Long idContact = Long.parseLong(id);
        Contact c = contactService.getContact(idContact);
        ModelAndView mv = new ModelAndView("update");
        mv.addObject("contact", c);
        return mv;
    }

    @PostMapping("/Update")
    public ModelAndView updateContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("redirect:/ContactV3");
        if (bindingResult.hasErrors()) {
            logger.error("errors = " + bindingResult.getAllErrors());
            mv.setViewName("update");
        } else {
            contactService.updateContact(contact);
        }
        return mv;
    }

}
