package net.jeremi.studio.test_infracom.service;

import net.jeremi.studio.test_infracom.dto.UserBasic;
import net.jeremi.studio.test_infracom.dto.UserWithContacts;
import net.jeremi.studio.test_infracom.entity.User;
import net.jeremi.studio.test_infracom.exception.IdAlreadyUsedException;
import net.jeremi.studio.test_infracom.exception.ResourceNotFoundException;
import net.jeremi.studio.test_infracom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserBasic> getAllUsers() {

        List<User> findUsers = userRepository.findAll();

        List<UserBasic> dto = new ArrayList<>();

        findUsers.forEach((User user)-> {
            dto.add(new UserBasic(user));
        });

        return dto ;
    }

    @Override
    public UserWithContacts getUserById(String userId) {

        Optional<User> findUser = userRepository.findById(userId);

        if (findUser.isEmpty()) {
            throw new ResourceNotFoundException("User with ID : "+userId+" is not found");
        }

        return new UserWithContacts(findUser.get());
    }

    @Override
    public UserBasic createUser(UserBasic request) {

        Boolean checkProposedId = userRepository.existsById(request.getId());

        if(checkProposedId.equals(true)) {
            throw new IdAlreadyUsedException("Proposed ID : "+request.getId()+" already used");
        }

        User createdUser = new User();
        createdUser.setId(request.getId());
        createdUser.setName(request.getName());
        createdUser.setAge(request.getAge());

        createdUser = userRepository.save(createdUser);

        return new UserBasic(createdUser);
    }

    @Override
    public UserBasic updateUser(UserBasic request,String userId) {

        Optional<User> findUser = userRepository.findById(userId);

        if(findUser.isEmpty()) {
            throw new ResourceNotFoundException("User with ID : "+userId+" is not found");
        }

        User userInDb = findUser.get();
        userInDb.setName(request.getName());
        userInDb.setAge(request.getAge());

        userInDb = userRepository.save(userInDb);

        return new UserBasic(userInDb);

    }

    @Override
    public String deleteUser(String userId) {

        Optional<User> findUser = userRepository.findById(userId);

        if(findUser.isEmpty()) {
            throw new ResourceNotFoundException("User with ID : "+userId+" is not found");
        }

        userRepository.delete(findUser.get());

        return "User with ID : "+userId+" is deleted";
    }
}
