package com.pewpew.pewpew.main;

import com.pewpew.pewpew.model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface AccountService {

    List<User> getTop();

    void addToken(String token, User user);

    Boolean addUser(User user);

    User getUserByToken(String token);

    User getUser(String login, String password);

    User getUserById(String userId);

    Boolean updateUser(String token, @Nullable User editedUser);

    Boolean userExists(User newUser);

    void deleteUser(User user);

    Boolean closeToken(String token);

    void delete(User user);


}
