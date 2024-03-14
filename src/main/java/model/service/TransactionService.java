package model.service;

import enums.TypeEnum;
import model.entity.Transaction;
import model.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

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


}
