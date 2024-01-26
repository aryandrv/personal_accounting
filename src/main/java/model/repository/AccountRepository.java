package model.repository;

import model.entity.Account;
import model.repository.impl.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class AccountRepository implements Repository<Account> ,AutoCloseable{

    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Account save(Account account) throws Exception {
        return null;
    }

    @Override
    public Account edit(Account account) throws Exception {
        return null;
    }

    @Override
    public Account remove(int id) throws Exception {
        return null;
    }

    @Override
    public List<Account> findAll() throws Exception {
        return null;
    }

    @Override
    public Account findById(int id) throws Exception {
        return null;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
