package com.example.contact.business;

import com.example.contact.entity.Contact;
import com.example.contact.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ContactBusiness {

    Contact saveContact (Contact contact);

    List<Contact> getAllContact (User user);

    Page<Contact> findAllByUserByPagination(User user , Pageable pageable);

    boolean existContactName (User user , String contactName);

    Contact findContact (User user , String contactName);

    void deleteContact(Long contactId , Long userId);

    Contact findContactById(Long id);

    List<Contact> searchAllContact(User user , String srh);

    void updateContactByAllElements(Long contactId , String contactName ,
                                       String phone , String homePhone ,
                                       String workPhone , String groupName , String tags ,  Long userId);

    void updateContactByAllElementsWithoutContactName(Long contactId , String phone , String homePhone ,
                                    String workPhone , String groupName , String tags ,  Long userId);

    void updateIsBlock (Long contactId , boolean isBlock);
}
