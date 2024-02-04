package model.service;

import model.entity.Transaction;
import model.repository.TransactionRepository;

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
            Transaction transaction= transactionRepository.findById(id);
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

}