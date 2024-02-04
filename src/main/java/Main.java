import controller.AccountController;
import controller.UserController;
import model.entity.Account;
import model.entity.User;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        try {
//            User user = UserController.getController().save(2, "radin", "darvishzadeh",
//                    "radin_drv", "12345678", LocalDateTime.now());
            User user = UserController.getController().findById(1);
            Account account = AccountController.getController().save(4, "Ayandeh", 0.0, user);
            System.out.println(account);
            Account account1 = AccountController.getController().edit(1, "Saman", 150.5, user);
            System.out.println(account1);
//            System.out.println(UserController.getController().edit(user.getId(), user.getUsername(), user.getPassword(), "aryan jan"), user.getFamily(), user.getCreationDate());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
