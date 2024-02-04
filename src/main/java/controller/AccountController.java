package controller;

import model.entity.Account;
import model.entity.User;
import model.service.AccountService;
import model.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j;


@Log4j

public class AccountController {
    private static AccountController controller = new AccountController();

    private AccountController() {
    }

    public static AccountController getController() {
        return controller;
    }

    public Account save(Integer id, String name, Double balance, User user) throws Exception {
        if (!name.isEmpty()) {
            Account account =
                    Account
                            .builder()
                            .id(id)
                            .name(name)
                            .balance(balance)
                            .user(user)
                            .build();
            AccountService.getService().save(account);
            log.info("save");
            return account;
        } else {
            log.error("can not save");
            throw new Exception("Invalid Data");
        }
    }

    public Account edit(Integer id, String name, Double balance, User user) throws Exception {
        if (!name.isEmpty()) {
            Account account =
                    Account
                            .builder()
                            .id(id)
                            .name(name)
                            .balance(balance)
                            .user(user)
                            .build();
            AccountService.getService().edit(account);
            log.info("edit");

            return account;
        } else {
            throw new Exception("Invalid Data");
        }
    }

    public Account remove(Integer id) {
        try {
            Account account = AccountService.getService().findById(id);
            if (account != null) {
                AccountService.getService().remove(id);
                log.info("remove");
                return account;
            } else {
                System.out.println("user not find");
                return null;
            }
        } catch (Exception e) {
            log.error("error to remove");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<Account> findAll() {
        try {
            log.info("find all");
            return AccountService.getService().findAll();

        } catch (Exception e) {
            log.error("Error to find");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Account findById(Integer id) {
        try {
            log.info("find by id");
            return AccountService.getService().findById(id);

        } catch (Exception e) {
            log.error("Error to find");
            System.out.println("Error : " + e.getMessage());
            return null;

        }
    }    public List<Account> findByUserId(Integer id) {
        try {
            log.info("find all by user_id");
            return AccountService.getService().findByUserId(id);

        } catch (Exception e) {
            log.error("Error to find all by user_id");
            System.out.println("Error : " + e.getMessage());
            return null;

        }
    }

}
