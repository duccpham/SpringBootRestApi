package com.thang.springboot.service;

import java.util.List;

import com.thang.springboot.dto.UserDTO;
import com.thang.springboot.entity.User;

public interface UserService {
    void addUser(User user);

    List<User> getUsers();

    User getUser(Integer id);

    void updateUser(Integer id, User user);

    void deleteUser(Integer id);

    void updateName(Integer id, UserDTO userDTO);
    Boolean checkLogin(String name, Integer password);
}