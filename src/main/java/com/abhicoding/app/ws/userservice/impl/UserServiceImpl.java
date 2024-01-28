package com.abhicoding.app.ws.userservice.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhicoding.app.ws.shared.Utils;
import com.abhicoding.app.ws.ui.model.request.UpdateUserRequestDetailsModel;
import com.abhicoding.app.ws.ui.model.request.UserRequestDetailsModel;
import com.abhicoding.app.ws.ui.model.response.UserRest;
import com.abhicoding.app.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {

    HashMap<String, UserRest> users;
    Utils utils;

    public UserServiceImpl() {
    }

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserRequestDetailsModel userDetails) {
        if (users == null) {
            users = new HashMap<>();
        }

        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());
        String userId = utils.generateUserId();
        returnValue.setUserId(userId);

        users.put(userId, returnValue);
        return returnValue;
    }

    @Override
    public UserRest getUser(String userId) {
        if (users == null) {
            users = new HashMap<>();
        }
        return users.get(userId);
    }

    @Override
    public UserRest updateUser(String userId, UpdateUserRequestDetailsModel userDetails) {
        if (users == null) {
            users = new HashMap<>();
        }
        if (users.get(userId) == null) {
            return null;
        }

        UserRest storedUser = users.get(userId);
        storedUser.setFirstName(userDetails.getFirstName());
        storedUser.setLastName(userDetails.getLastName());
        users.put(userId, storedUser);
        return storedUser;
    }

    @Override
    public void deleteUser(String userId) {
        if (users == null) {
            users = new HashMap<>();
        }
        if (users.get(userId) == null) {
            return;
        }
        users.remove(userId);
    }

}
