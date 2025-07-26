package controller;

import enums.TypeEnum;
import model.entity.*;
import model.service.TransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

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
                Account account1 = transaction.getAccount();
                AccountController.getController().edit(account1.getId(),
                        account1.getName(), account1.getBalance() + transaction.getAmount(), account1.getUser());
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
            Transaction oldTransaction = TransactionController.getController().findById(transaction.getId());
            TransactionService.getService().edit(transaction);
            log.info("edit");
            if (transaction != null && oldTransaction != null) {
                if (transaction.getAmount() == oldTransaction.getAmount()) {
                    return transaction;
                } else {
                    Account account1 = transaction.getAccount();
                    AccountController.getController().edit(account1.getId(),
                            account1.getName(), account1.getBalance() + (oldTransaction.getAmount() * -1) + transaction.getAmount(), account1.getUser());
                }
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
                transaction = TransactionService.getService().remove(id);
                log.info("remove transaction");
                if (transaction != null) {
                    Account account1 = transaction.getAccount();
                    AccountController.getController().edit(account1.getId(),
                            account1.getName(), account1.getBalance() + (transaction.getAmount() * -1), account1.getUser());
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

    // This methods after remove one account is called
    public Boolean remove(List<Transaction> transactionList) {
        try {
            if (transactionList != null && !transactionList.isEmpty()) {
                for (Transaction transaction : transactionList) {
                    if (transaction != null) {
                        Transaction transaction1 = remove(transaction.getId());
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("error to remove all transaction");
            System.out.println("Error : " + e.getMessage());
            return false;
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
            log.error("Error to find All");
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

    public List<Transaction> findByAccountId(Integer id) {
        try {
            log.info("find all by account_id");
            return TransactionService.getService().findByAccountId(id);

        } catch (Exception e) {
            log.error("Error to find all by account_id");
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

    public Map<String, Double> getMonthlyIncomeExpense(YearMonth yearMonth, int userId) {
        return TransactionService.getService().getMonthlyIncomeExpense(yearMonth, userId);
    }

    public Map<String, Double> getIncomeExpenseByDateRange(int userId, LocalDate fromDate, LocalDate toDate) {
        return TransactionService.getService().getIncomeExpenseByDateRange(userId, fromDate, toDate);
    }
    public List<TitleSummary> getTitleSummariesByType(int userId, LocalDate from, LocalDate to) throws Exception {
        return TransactionService.getService().getTitleSummariesByType(userId, from, to);
    }


}

