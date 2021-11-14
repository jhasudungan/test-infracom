package net.jeremi.studio.test_infracom.service;

import net.jeremi.studio.test_infracom.dto.UserBasic;
import net.jeremi.studio.test_infracom.dto.UserWithContacts;
import net.jeremi.studio.test_infracom.entity.User;

import java.util.List;

public interface UserService {

    List<UserBasic> getAllUsers();
    UserWithContacts getUserById(String userId);

    UserBasic createUser(UserBasic request);
    UserBasic updateUser(UserBasic request,String userId);

    String deleteUser(String userId);
}
