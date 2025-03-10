package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public User getUser(int id);
    void deleteUser(int id);
    void addUser(User user);
    void updateUser(User user);
    List<User> listUsers();
}
