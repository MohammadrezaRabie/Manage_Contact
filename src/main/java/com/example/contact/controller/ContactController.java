package com.example.contact.controller;


import com.example.contact.business.ContactBusiness;
import com.example.contact.entity.Contact;
import com.example.contact.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/user-contacts")
public class ContactController {

    private final ContactBusiness contactBusiness;


    public ContactController(ContactBusiness contactBusiness) {
        this.contactBusiness = contactBusiness;
    }

    @GetMapping()
    public ModelAndView listContact(HttpSession session) {
        ModelAndView model = new ModelAndView("contact");
        User user = (User) session.getAttribute("user");

        if (user == null) {
            model.setViewName("home");
            model.addObject("userNotLogin", "Please login to your account first !");
            return model;
        } else {
            int currentPage = 1;

            Sort sort = Sort.by("contactName");

            Pageable pageable = PageRequest.of(0, 3, sort);

            Page<Contact> contacts = contactBusiness.findAllByUserByPagination(user, pageable);

            long totlaItems = contacts.getTotalElements();

            int totalPages = contacts.getTotalPages();

            List<Contact> contactList = contacts.getContent();


            model.addObject("currentPage", currentPage);
            model.addObject("contactList", contactList);
            model.addObject("totalItems", totlaItems);
            model.addObject("totalPages", totalPages);
            return model;

        }
    }

    @GetMapping("/contact-list")
    public ModelAndView showContactByPaginationAndNumberOfShow(@RequestParam int page, HttpSession session) {

        ModelAndView model = new ModelAndView("contact");
        User user = (User) session.getAttribute("user");


        if (user == null) {
            model.setViewName("home");
            model.addObject("userNotLogin", "Please login to your account first !");
            return model;
        } else {

            Sort sort = Sort.by("contactName");
            Pageable pageable = PageRequest.of(page - 1, 3, sort);
            Page<Contact> contacts = contactBusiness.findAllByUserByPagination(user, pageable);

            long totlaItems = contacts.getTotalElements();
            int totalPages = contacts.getTotalPages();
            List<Contact> contactList = contacts.getContent();

            model.addObject("currentPage", page);
            model.addObject("contactList", contactList);
            model.addObject("totalItems", totlaItems);
            model.addObject("totalPages", totalPages);
            return model;
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/contact";
    }

    @GetMapping("/show-black-list")
    public ModelAndView showBlackList(HttpSession session, ModelAndView modelAndView) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            modelAndView.setViewName("home");
            modelAndView.addObject("userNotLogin", "Please login to your account first !");
            return modelAndView;
        } else {
            modelAndView.setViewName("black_list");
            modelAndView.addObject("contactList", contactBusiness.getAllContact(user));
            return modelAndView;
        }


    }

    @GetMapping("/edit-page")
    public String editContactPage() {
        return "edit_contact_form";
    }


    @PostMapping("/add-contacts")
    public String addContacts(HttpSession session, @Valid Contact contact, Errors errors, ModelMap model, RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        contact.setUser(user);


        if (contact.getUser() == null) {
            model.put("userNotLogin", "Please login to your account first !");
            return "home";
        } else {

            if (errors.hasErrors() || contactBusiness.existContactName(user, contact.getContactName())) {
                redirectAttributes.addFlashAttribute("errors", errors.getAllErrors());
                redirectAttributes.addFlashAttribute("errorContactExist", "Error : Name ' " + contact.getContactName() + " ' has Exist !");
                return "redirect:/user-contacts";
            } else {
                contactBusiness.saveContact(contact);
                redirectAttributes.addFlashAttribute("addContactOk", "Add Contact Successfully");
                return "redirect:/user-contacts";
            }
        }
    }

    @GetMapping("/delete-contact")
    public String deleteContact(HttpSession session, Contact contact, @RequestParam Long id, RedirectAttributes redirectAttributes, ModelMap model) {

        User user = (User) session.getAttribute("user");
        contact.setUser(user);

        if (contact.getUser() == null) {
            model.put("userNotLogin", "Please login to your account first !");
            return "home";
        } else {

            contactBusiness.deleteContact(id, user.getUserId());

            redirectAttributes.addFlashAttribute("deleteOk", "Delete Contact Successfully");

            return "redirect:/user-contacts";
        }
    }

    @GetMapping("/edit")
    public String editContact(Contact contact, HttpSession session, @RequestParam Long id, ModelMap model) {

        User user = (User) session.getAttribute("user");
        contact.setUser(user);

        if (contact.getUser() == null) {
            model.put("userNotLogin", "Please login to your account first !");
            return "home";
        } else {

            Contact contactById = contactBusiness.findContactById(id);

            model.addAttribute("cont", contactById);

            return "forward:/user-contacts/edit-page";
        }
    }

    @PostMapping("/update-contact")
    public String updateContacts(HttpSession session, @Valid Contact contact, Errors errors, ModelMap model, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        contact.setUser(user);


        if (contact.getUser() == null) {
            model.put("userNotLogin", "Please login to your account first !");
            return "home";
        } else {

            if (errors.hasErrors()) {
                redirectAttributes.addFlashAttribute("errors", errors.getAllErrors());
                return "redirect:/user-contacts";
            } else {
                if (contactBusiness.existContactName(user, contact.getContactName())) {
                    contactBusiness.updateContactByAllElementsWithoutContactName(contact.getContactId()
                            , contact.getPhone(), contact.getHomePhone(), contact.getWorkPhone(),
                            contact.getGroupName(), contact.getTags(), user.getUserId());

                    redirectAttributes.addFlashAttribute("updateOk", "Update Contact Successfully");
                    redirectAttributes.addFlashAttribute("errorContactExist", "Attention : Update name unsuccessful because The name entered in the 'Name' field has exist !");

                    return "redirect:/user-contacts";
                } else {
                    contactBusiness.saveContact(contact);
                    redirectAttributes.addFlashAttribute("addContactOk", "Add Contact Successfully");
                    return "redirect:/user-contacts";
                }
            }
        }

    }

    @GetMapping("/search-contacts")
    public ModelAndView searchAllContacts(@RequestParam String search, ModelAndView modelAndView, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            modelAndView.setViewName("home");
            modelAndView.addObject("userNotLogin", "Please login to your account first !");
            return modelAndView;
        } else {
            modelAndView.setViewName("search_contacts");
            String search2 = search.trim();
            List<Contact> searchAllContact = contactBusiness.searchAllContact(user, search2);
            modelAndView.addObject("contactSearchList", searchAllContact);
            return modelAndView;
        }

    }

    @GetMapping("/block-contact")
    public String blockContacts(@RequestParam Long id, @ModelAttribute("contact") Contact contact, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        contact.setUser(user);

        contactBusiness.updateIsBlock(id, true);

        Contact contactById = contactBusiness.findContactById(id);

        redirectAttributes.addFlashAttribute("blockOk", contactById.getContactName() + " was Blocked");

        return "redirect:/user-contacts";

    }


    @GetMapping("/unblock-contact")
    public String unBlockContacts(@RequestParam Long id, @ModelAttribute("contact") Contact contact, HttpSession session, RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        contact.setUser(user);

        contactBusiness.updateIsBlock(id, false);

        Contact contactById = contactBusiness.findContactById(id);

        redirectAttributes.addFlashAttribute("unBlockOk", contactById.getContactName() + " was UnBlocked");

        return "redirect:/user-contacts/show-black-list";
    }
}
