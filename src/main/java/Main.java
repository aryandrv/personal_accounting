import controller.UserController;
import model.entity.User;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        try {
//            User user = UserController.getController().save(2, "radin", "darvishzadeh",
//                    "radin_drv", "12345678", LocalDateTime.now());
            System.out.println(UserController.getController().findById(2));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
