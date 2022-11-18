package com.example.contact.repository;

import com.example.contact.entity.Contact;
import com.example.contact.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findAllByUserOrderByContactName(User user);

    boolean existsContactByUserAndContactName(User user, String contactName);

    @Modifying
    @Query(value = "delete from Contact c where c.contactId = ?1 and c.user.userId = ?2")
    void deleteContactByContactId(Long contactId, Long userId);

    Contact findContactByUserAndContactName(User user, String contactName);

    Contact findContactByContactId(Long id);

    @Query(value = "select c from Contact c where c.user = ?1 and  c.contactName like %?2% or c.user = ?1 and c.phone like %?2% or c.user = ?1 and c.homePhone like %?2% or c.user = ?1 and c.workPhone like %?2% or c.user = ?1 and c.tags like %?2% or c.user = ?1 and c.groupName like %?2% order by c.contactName asc")
    List<Contact> searchContactsByAllRecord(User user, String search);

    @Modifying
    @Query("update Contact c set c.contactId = ?1 , c.contactName = ?2 , c.phone = ?3 , c.homePhone = ?4 , c.workPhone = ?5  , c.groupName = ?6 , c.tags = ?7 , c.user.userId = ?8 where c.contactId = ?1")
    void updateContactByAllElements(Long contactId, String contactName,
                                    String phone, String homePhone,
                                    String workPhone, String groupName, String tags, Long userId);

    @Modifying
    @Query("update Contact c set c.contactId = ?1 ,c.phone = ?2 , c.homePhone = ?3 , c.workPhone = ?4  , c.groupName = ?5 , c.tags = ?6 , c.user.userId = ?7 where c.contactId = ?1")
    void updateContactWithoutContactName(Long contactId, String phone, String homePhone,
                                         String workPhone, String groupName, String tags, Long userId);


    @Modifying
    @Query("update Contact c set c.contactId = ?1 , c.isBlock = ?2 where c.contactId = ?1")
    void updateIsBlockProperty(Long contactId, boolean isBlock);


    Page<Contact> findAllByUser(User user, Pageable pageable);

}
