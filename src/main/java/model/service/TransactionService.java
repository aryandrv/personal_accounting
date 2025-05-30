package model.service;

import enums.TypeEnum;
import model.entity.Transaction;
import model.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionService {
    private static TransactionService service = new TransactionService();

    private TransactionService() {
    }

    public static TransactionService getService() {
        return service;
    }
    public Transaction save(Transaction transaction) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.save(transaction);
        }
    }

    public Transaction edit(Transaction transaction) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.edit(transaction);
        }
    }

    public Transaction remove(int id) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            Transaction transaction = transactionRepository.findById(id);
            if (transaction != null){
                transactionRepository.remove(id);
                return transaction;
            }
            else {
                return null;
            }
        }
    }

    public List<Transaction> findAll() throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findAll();
        }
    }

    public Transaction findById(int id) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findById(id);
        }
    }
    public List<Transaction> findByUserId(int id) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findByUserId(id);
        }
    }

    public List<Transaction> findByAccountId(int id) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findByAccountId(id);
        }
    }
    public List<Transaction> findByDateAndAccountId(int id, LocalDateTime from, LocalDateTime to) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findByDateAndAccountId(id,from,to);
        }
    }
    public List<Transaction> findByDateAndUserId(int id, LocalDateTime from, LocalDateTime to) throws Exception {
        try (TransactionRepository transactionRepository = new TransactionRepository()) {
            return transactionRepository.findByDateAndUserId(id,from,to);
        }
    }

    public Map<String, Double> getMonthlyIncomeExpense(YearMonth yearMonth, int userId) {
        try (TransactionRepository repo = new TransactionRepository()) {
            return repo.getIncomeExpenseByMonth(yearMonth, userId);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Double> emptyMap = new HashMap<>();
            emptyMap.put("income", 0.0);
            emptyMap.put("cost", 0.0);
            return emptyMap;
        }
    }

    public Map<String, Double> getIncomeExpenseByDateRange(int userId, LocalDate fromDate, LocalDate toDate) {
        try (TransactionRepository repo = new TransactionRepository()) {
            return repo.getIncomeExpenseByDateRange(userId, fromDate, toDate);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Double> emptyMap = new HashMap<>();
            emptyMap.put("income", 0.0);
            emptyMap.put("cost", 0.0);
            return emptyMap;
        }
    }



}
