package net.jeremi.studio.test_infracom.service;

import net.jeremi.studio.test_infracom.dto.UserContactBasic;
import net.jeremi.studio.test_infracom.dto.UserContactWithUser;
import net.jeremi.studio.test_infracom.entity.User;
import net.jeremi.studio.test_infracom.entity.UserContact;
import net.jeremi.studio.test_infracom.exception.IdAlreadyUsedException;
import net.jeremi.studio.test_infracom.exception.ResourceNotFoundException;
import net.jeremi.studio.test_infracom.repository.UserContactRepository;
import net.jeremi.studio.test_infracom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserContactServiceImpl implements UserContactService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserContactRepository userContactRepository;

    @Override
    public List<UserContactWithUser> getAllUserContacts() {

        List<UserContact> findUserContacts = userContactRepository.findAll();

        List<UserContactWithUser> dto = new ArrayList<>();

        findUserContacts.forEach((UserContact userContact)-> dto.add(new UserContactWithUser(userContact)));

        return dto;
    }

    @Override
    public Set<UserContactBasic> getUserContactByUserId(String userId) {

        Optional<User> findUser = userRepository.findById(userId);

        if (findUser.isEmpty()) {
            throw new ResourceNotFoundException("User with ID : "+userId+" is not found");
        }

        Set<UserContact> findUserContact = userContactRepository.findUserContactByUser(findUser.get());

        Set<UserContactBasic> dto = new HashSet<>();

        findUserContact.forEach((UserContact userContact)-> dto.add(new UserContactBasic(userContact)));

        return dto;
    }

    @Override
    public UserContactWithUser getUserContactById(String userContactId) {

        Optional<UserContact> findUserContact = userContactRepository.findById(userContactId);

        if (findUserContact.isEmpty()) {
            throw new ResourceNotFoundException("User Contact with ID : "+userContactId+" is not found");
        }

        return new UserContactWithUser(findUserContact.get());
    }

    @Override
    public UserContactWithUser createUserContact(UserContactBasic request, String userId) {

        Optional<User> findUser = userRepository.findById(userId);

        if (findUser.isEmpty()) {
            throw new ResourceNotFoundException("User with ID : "+userId+" is not found");
        }

        Boolean checkProposedUserContactId = userContactRepository.existsById(request.getId());

        if (checkProposedUserContactId.equals(true)) {
            throw new IdAlreadyUsedException("User Contact ID : "+request.getId()+" already used");
        }

        UserContact createdUserContact = new UserContact();
        createdUserContact.setId(request.getId());
        createdUserContact.setAddress(request.getAddress());
        createdUserContact.setUser(findUser.get());

        createdUserContact = userContactRepository.save(createdUserContact);

        return new UserContactWithUser(createdUserContact);
    }

    @Override
    public UserContactWithUser updateUserContact(UserContactBasic request, String userContactId) {

        Optional<UserContact> findUserContact = userContactRepository.findById(userContactId);

        if(findUserContact.isEmpty()) {
            throw new ResourceNotFoundException("User Contact with ID : "+userContactId+" is not found");
        }

        UserContact userContactInDB = findUserContact.get();
        userContactInDB.setAddress(request.getAddress());

        userContactRepository.save(userContactInDB);

        return new UserContactWithUser(userContactInDB);
    }

    @Override
    public String deleteUserContact(String userContactId) {

        Optional<UserContact> findUserContact = userContactRepository.findById(userContactId);

        if (findUserContact.isEmpty()) {
            throw new ResourceNotFoundException("User Contact ID : "+userContactId+" is not found");
        }

        userContactRepository.delete(findUserContact.get());

        return "User Contact with ID : "+userContactId+" already deleted";
    }
}
