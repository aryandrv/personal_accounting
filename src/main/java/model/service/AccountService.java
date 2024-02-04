package model.service;

import model.entity.Account;
import model.entity.Account;
import model.repository.AccountRepository;
import model.repository.AccountRepository;

import java.util.List;

public class AccountService {
    private static AccountService service = new AccountService();

    private AccountService() {
    }

    public static AccountService getService() {
        return service;
    }

    public Account save(Account account) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
                return accountRepository.save(account);
        }
    }

    public Account edit(Account account) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            return accountRepository.edit(account);
        }
    }

    public Account remove(int id) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            Account account= accountRepository.findById(id);
            if (account != null){
                accountRepository.remove(id);
                return account;
            }
            else {
                return null;
            }
        }
    }

    public List<Account> findAll() throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            return accountRepository.findAll();
        }
    }

    public Account findById(int id) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            return accountRepository.findById(id);
        }
    }
    public List<Account> findByUserId(int id) throws Exception {
        try (AccountRepository accountRepository = new AccountRepository()) {
            return accountRepository.findByUserId(id);
        }
    }

}
