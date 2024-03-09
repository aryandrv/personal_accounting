import controller.AccountController;
import controller.TitlesController;
import controller.TransactionController;
import controller.UserController;
import enums.TypeEnum;
import model.entity.Account;
import model.entity.Titles;
import model.entity.User;
import model.service.TitlesService;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
//            User user = UserController.getController().save(2, "radin", "darvishzadeh",
//                    "radin_drv", "12345678", LocalDateTime.now());
//            User user = UserController.getController().findById(1);
//            List<Account> account = AccountController.getController().findByUserId(1);
//            System.out.println(TransactionController.getController().sumByType(1,41, TypeEnum.INCOME));
//            System.out.println(account);
//            Account account1 = AccountController.getController().edit(1, "Saman", 150.5, user);
//            Titles titles = TitlesController.getController().findById(6);
//            Titles titles = TitlesService.getService().remove(11);
//            System.out.println(titles);
//            System.out.println(UserController.getController().edit(user.getId(), user.getUsername(), user.getPassword(), "aryan jan"), user.getFamily(), user.getCreationDate());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
