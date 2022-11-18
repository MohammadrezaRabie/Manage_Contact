package com.example.contact.business;


import com.example.contact.entity.Contact;
import com.example.contact.entity.User;
import com.example.contact.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ContactBusinessImpl implements ContactBusiness {

    private final ContactRepository contactRepository;


    public ContactBusinessImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContact(User user) {
        return contactRepository.findAllByUserOrderByContactName(user);
    }

    @Override
    public Page<Contact> findAllByUserByPagination(User user, Pageable pageable) {
        return contactRepository.findAllByUser(user, pageable);
    }

    @Override
    public boolean existContactName(User user, String contactName) {
        return contactRepository.existsContactByUserAndContactName(user, contactName);
    }

    @Override
    public Contact findContact(User user, String contactName) {
        return contactRepository.findContactByUserAndContactName(user, contactName);
    }

    @Override
    @Transactional
    public void deleteContact(Long contactId, Long userId) {
        contactRepository.deleteContactByContactId(contactId, userId);
    }

    @Override
    public Contact findContactById(Long id) {
        return contactRepository.findContactByContactId(id);
    }

    @Override
    public List<Contact> searchAllContact(User user, String search) {
        return contactRepository.searchContactsByAllRecord(user, search);
    }

    @Override
    @Transactional
    public void updateContactByAllElements(Long contactId, String contactName, String phone, String homePhone, String workPhone, String groupName, String tags, Long userId) {
        contactRepository.updateContactByAllElements(contactId, contactName, phone, homePhone, workPhone, groupName, tags, userId);
    }

    @Override
    @Transactional
    public void updateContactByAllElementsWithoutContactName(Long contactId, String phone, String homePhone, String workPhone, String groupName, String tags, Long userId) {
        contactRepository.updateContactWithoutContactName(contactId, phone, homePhone, workPhone, groupName, tags, userId);
    }

    @Override
    @javax.transaction.Transactional
    public void updateIsBlock(Long contactId, boolean isBlock) {
        contactRepository.updateIsBlockProperty(contactId, isBlock);
    }


}
