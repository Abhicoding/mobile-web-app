package com.abhicoding.app.ws.ui.controller;

import org.springframework.web.bind.annotation.RestController;

import com.abhicoding.app.ws.ui.model.request.UpdateUserRequestDetailsModel;
import com.abhicoding.app.ws.ui.model.request.UserRequestDetailsModel;
import com.abhicoding.app.ws.ui.model.response.UserRest;
import com.abhicoding.app.ws.userservice.UserService;

import jakarta.validation.Valid;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    Map<String, UserRest> users;

    @GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        UserRest user = userService.getUser(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "25") int pageSize,
            @RequestParam(value = "sort", defaultValue = "desc") String sort) {
        return "get user was called for page = " + page + " pageSize = " + pageSize + " sort = " + sort;
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserRequestDetailsModel userDetails) {
        UserRest returnValue = userService.createUser(userDetails);
        return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId,
            @Valid @RequestBody UpdateUserRequestDetailsModel userDetails) {
        UserRest storedUser = userService.updateUser(userId, userDetails);
        if (storedUser != null) {
            return new ResponseEntity<>(storedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
