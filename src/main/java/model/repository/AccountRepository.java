package model.repository;

import model.entity.Account;
import model.repository.impl.Repository;
import model.repository.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public class AccountRepository implements Repository<Account> ,AutoCloseable{

    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Account save(Account account) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT ACCOUNT_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        account.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO USER_TBL(ID,NAME,BALANCE,USER_ID) VALUES (?,?,?,?)"
        );
        preparedStatement.setInt(1,account.getId());
        preparedStatement.setString(2,account.getName());
        preparedStatement.setDouble(3,account.getBalance());
        preparedStatement.setInt(4,account.getUser().getId());
        preparedStatement.execute();
        return account;
    }

    @Override
    public Account edit(Account account) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE ACCOUNT_TBL SET NAME=?,BALANCE=?,USER_ID=? WHERE ID=?"
        );
        preparedStatement.setString(1,account.getName());
        preparedStatement.setDouble(2,account.getBalance());
        preparedStatement.setInt(3,account.getUser().getId());
        preparedStatement.setInt(4,account.getId());
        preparedStatement.execute();

        return account  ;
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
