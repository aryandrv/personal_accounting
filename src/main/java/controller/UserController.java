package controller;

import model.entity.User;
import model.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;

import lombok.extern.log4j.Log4j;


@Log4j

public class UserController {

    private static UserController controller = new UserController();

    private UserController() {
    }

    public static UserController getController() {
        return controller;
    }

    public User save(Integer id, String name, String family, String username, String password, LocalDateTime creationDate) throws Exception {
        if (Pattern.matches("^[a-z\\d\\S\\._]{3,30}$", username) &&
                (Pattern.matches("^[\\w\\S]{5,30}$", password))) {
            User user =
                    User
                            .builder()
                            .id(id)
                            .name(name)
                            .family(family)
                            .username(username)
                            .password(password)
                            .creationDate(creationDate)
                            .build();
            UserService.getService().save(user);
            log.info("save");
            return user;
        } else {
            log.error("can not save");
            throw new Exception("Invalid Data");
        }
    }

    public User edit(Integer id, String name, String family, String username, String password, LocalDateTime creationDate) throws Exception {
        if (Pattern.matches("^[a-z\\d\\S\\._]{3,30}$", username) &&
                (Pattern.matches("^[\\w\\S]{5,30}$", password))) {
            User user =
                    User
                            .builder()
                            .id(id)
                            .name(name)
                            .family(family)
                            .username(username)
                            .password(password)
                            .creationDate(creationDate)
                            .build();
            UserService.getService().edit(user);
            log.info("edit");

            return user;
        } else {
            throw new Exception("Invalid Data");
        }
    }

    public User remove(Integer id) {
        try {
            User user = UserService.getService().findById(id);
            if (user != null) {
                UserService.getService().remove(id);
                log.info("remove");
                return user;
            } else {
                System.out.println("user not find");
                return null;
            }
        } catch (Exception e) {
            log.error("eror to remove");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<User> findAll() {
        try {
            log.info("find all");
            return UserService.getService().findAll();

        } catch (Exception e) {
            log.error("eror to find");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public User findById(Integer id) {
        try {
            log.info("find by id");
            return UserService.getService().findById(id);

        } catch (Exception e) {
            log.error("eror to find");
            System.out.println("Error : " + e.getMessage());
            return null;

        }
    }

    public User findByUsername(String username) {
        try {
            log.info("find by username");
            return UserService.getService().findByUsername(username);

        } catch (Exception e) {
            log.error("eror to find");
            System.out.println("Error : " + e.getMessage());
            return null;

        }

    }

    public User findByUsernameAndPassword(String username, String password) {
        try {
            log.info("find by username and password");
            User user = UserService.getService().findByUsernameAndPassword(username, password);
            if (user != null) {
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("eror to find");
            System.out.println("Invalid Username/Password /n" + e.getMessage());
            return null;

        }
    }

}
