package model.service;

import model.entity.User;
import model.repository.UserRepository;

import java.util.List;

public class UserService {

    private static UserService service = new UserService();

    private UserService() {
    }

    public static UserService getService() {
        return service;
    }

    public User save(User user) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            if (userRepository.findByUsername(user.getUsername()) == null) {
                return userRepository.save(user);
            } else {
                throw new Exception("This user exists");
            }
        }
    }

    public User edit(User user) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.edit(user);
        }
    }

    public User remove(int id) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            User user= userRepository.findById(id);
            if (user != null){
                userRepository.remove(id);
                return user;
            }
            else {
                return null;
            }
        }
    }

    public List<User> findAll() throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findAll();
        }
    }

    public User findById(int id) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findById(id);
        }
    }

    public User findByUsername(String username) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findByUsername(username);
        }
    }

    public User findByUsernameAndPassword(String username,String password) throws Exception {
        try (UserRepository userRepository = new UserRepository()) {
            return userRepository.findByUsernameAndPassword(username, password);
        }
    }
}
