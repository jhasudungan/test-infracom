package net.jeremi.studio.test_infracom.controller;

import net.jeremi.studio.test_infracom.dto.UserBasic;
import net.jeremi.studio.test_infracom.dto.UserWithContacts;
import net.jeremi.studio.test_infracom.entity.User;
import net.jeremi.studio.test_infracom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserBasic>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserWithContacts> getUserById(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserBasic> createUser(@RequestBody UserBasic request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserBasic> updateUser(@RequestBody UserBasic request,@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.updateUser(request,id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}
