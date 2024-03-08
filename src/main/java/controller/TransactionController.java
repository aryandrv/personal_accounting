package controller;

import enums.TypeEnum;
import model.entity.Account;
import model.entity.Titles;
import model.entity.Transaction;
import model.entity.User;
import model.service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

import lombok.extern.log4j.Log4j;
import model.service.TransactionService;


@Log4j

public class TransactionController {

    private static TransactionController controller = new TransactionController();

    private TransactionController() {
    }

    public static TransactionController getController() {
        return controller;
    }

    public Transaction save(Integer id, User user, Account account, Double amount, Titles titles, LocalDateTime transactionDateTime, String description, TypeEnum type) throws Exception {
        if (user != null) {
            Transaction transaction =
                    Transaction
                            .builder()
                            .id(id)
                            .user(user)
                            .account(account)
                            .amount(amount)
                            .titles(titles)
                            .transactionDate(transactionDateTime)
                            .description(description)
                            .type(type)
                            .build();
            TransactionService.getService().save(transaction);
            log.info("save");
            if (transaction != null) {
                AccountController.getController().updateAccount(transaction.getType(),
                        transaction.getAmount(), transaction.getAccount());
            }
            return transaction;
        } else {
            log.error("can not save");
            throw new Exception("Invalid Data");
        }
    }

    public Transaction edit(Integer id, User user, Account account, Double amount, Titles titles, LocalDateTime transactionDateTime, String description, TypeEnum type) throws Exception {
        if (user != null) {
            Transaction transaction =
                    Transaction
                            .builder()
                            .id(id)
                            .user(user)
                            .account(account)
                            .amount(amount)
                            .titles(titles)
                            .transactionDate(transactionDateTime)
                            .description(description)
                            .type(type)
                            .build();
            TransactionService.getService().edit(transaction);
            log.info("edit");
            if (transaction != null) {
                AccountController.getController().updateAccount(transaction.getType(),
                        transaction.getAmount(), transaction.getAccount());
            }
            return transaction;
        } else {
            throw new Exception("Invalid Data");
        }
    }

    public Transaction remove(Integer id) {
        try {
            Transaction transaction = TransactionService.getService().findById(id);
            if (transaction != null) {
                TransactionService.getService().remove(id);
                log.info("remove transaction");
                if (transaction != null) {
                    AccountController.getController().updateAccount(transaction.getType(),
                            transaction.getAmount(), transaction.getAccount());
                }
                return transaction;
            } else {
                System.out.println("Transaction not find");
                return null;
            }
        } catch (Exception e) {
            log.error("error to remove");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<Transaction> findAll() {
        try {
            log.info("find all");
            return TransactionService.getService().findAll();

        } catch (Exception e) {
            log.error("Error to find");
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public Transaction findById(Integer id) {
        try {
            log.info("find by id");
            return TransactionService.getService().findById(id);

        } catch (Exception e) {
            log.error("Error to find");
            System.out.println("Error : " + e.getMessage());
            return null;

        }
    }

    public List<Transaction> findByUserId(Integer id) {
        try {
            log.info("find all by user_id");
            return TransactionService.getService().findByUserId(id);

        } catch (Exception e) {
            log.error("Error to find all by user_id");
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
            return null;

        }
    }

    public List<Transaction> findByDateAndAccountId(Integer id, LocalDateTime from, LocalDateTime to) {
        try {
            log.info("find all by account_id and date");
            return TransactionService.getService().findByDateAndAccountId(id, from, to);

        } catch (Exception e) {
            log.error("Error to find all by account_id and date");
            System.out.println("Error : " + e.getMessage());
            return null;

        }
    }

    public List<Transaction> findByDateAndUserId(Integer id, LocalDateTime from, LocalDateTime to) {
        try {
            log.info("find all by user_id and date");
            return TransactionService.getService().findByDateAndUserId(id, from, to);

        } catch (Exception e) {
            log.error("Error to find all by user_id and date");
            System.out.println("Error : " + e.getMessage());
            return null;

        }
    }

    public Double sumByType(Integer userID, Integer accountId, TypeEnum type) {
        try {
            log.info("find all by user_id and date");
            return TransactionService.getService().sumByType(userID, accountId, type);

        } catch (Exception e) {
            log.error("Error to find all by user_id and date");
            System.out.println("Error : " + e.getMessage());
            return null;

        }
    }

}
