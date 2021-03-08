package com.pantry.app.user.microserver.usermicroserver.service.interfaces;

import com.pantry.app.user.microserver.usermicroserver.model.User;

public interface IUserService {

    User findById(Long id);
    User findByUsername(String username);
    User add(User user);

}
