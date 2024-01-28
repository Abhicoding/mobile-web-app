package com.abhicoding.app.ws.userservice;

import com.abhicoding.app.ws.ui.model.request.UpdateUserRequestDetailsModel;
import com.abhicoding.app.ws.ui.model.request.UserRequestDetailsModel;
import com.abhicoding.app.ws.ui.model.response.UserRest;

public interface UserService {
    public UserRest getUser(String userId);

    public UserRest createUser(UserRequestDetailsModel userDetails);

    public UserRest updateUser(String userId, UpdateUserRequestDetailsModel userDetails);

    public void deleteUser(String userId);
}
