package net.jeremi.studio.test_infracom.controller;

import net.jeremi.studio.test_infracom.dto.UserContactBasic;
import net.jeremi.studio.test_infracom.dto.UserContactWithUser;
import net.jeremi.studio.test_infracom.service.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class UserContactController {

    @Autowired
    UserContactService userContactService;

    @GetMapping("/user-contacts")
    public ResponseEntity<List<UserContactWithUser>> getAllUserContacts () {
        return new ResponseEntity<>(userContactService.getAllUserContacts(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/user-contacts")
    public ResponseEntity<Set<UserContactBasic>> getUserContactByUserId(@PathVariable("userId") String userId) {
        return new ResponseEntity<>(userContactService.getUserContactByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/user-contacts/{id}")
    public ResponseEntity<UserContactWithUser> getUserContactById(@PathVariable("id") String id) {
        return new ResponseEntity<>(userContactService.getUserContactById(id), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/user-contacts")
    public ResponseEntity<UserContactWithUser> createUserContact(@RequestBody UserContactBasic request, @PathVariable("userId") String userId) {
        return new ResponseEntity<>(userContactService.createUserContact(request,userId), HttpStatus.CREATED);
    }

    @PutMapping("/user-contacts/{id}")
    public ResponseEntity<UserContactWithUser> updateUserContact(@RequestBody UserContactBasic request, @PathVariable("id") String id) {
        return new ResponseEntity<>(userContactService.updateUserContact(request,id),HttpStatus.OK);
    }

    @DeleteMapping("/user-contacts/{id}")
    public ResponseEntity<String> deleteUserContact(@PathVariable("id") String id) {
        return new ResponseEntity<>(userContactService.deleteUserContact(id), HttpStatus.OK);
    }
}
