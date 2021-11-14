package net.jeremi.studio.test_infracom.service;

import net.jeremi.studio.test_infracom.dto.UserContactBasic;
import net.jeremi.studio.test_infracom.dto.UserContactWithUser;
import net.jeremi.studio.test_infracom.entity.UserContact;

import java.util.List;
import java.util.Set;

public interface UserContactService {

    List<UserContactWithUser> getAllUserContacts();
    Set<UserContactBasic> getUserContactByUserId(String userId);
    UserContactWithUser getUserContactById(String userContactId);

    UserContactWithUser createUserContact(UserContactBasic userContact,String userId);
    UserContactWithUser updateUserContact(UserContactBasic userContact,String userContactId);

    String deleteUserContact(String userContactId);

}
