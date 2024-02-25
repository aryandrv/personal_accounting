package model.repository;

import model.entity.Account;
import model.entity.User;
import model.repository.impl.Repository;
import model.repository.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements Repository<Account>, AutoCloseable {

    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Account save(Account account) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT ACOUNT_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        account.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO ACCOUNT_TBL(ID,NAME,BALANCE,USER_ID) VALUES (?,?,?,?)"
        );
        preparedStatement.setInt(1, account.getId());
        preparedStatement.setString(2, account.getName());
        preparedStatement.setDouble(3, account.getBalance());
        preparedStatement.setInt(4, account.getUser().getId());
        preparedStatement.execute();
        return account;
    }

    @Override
    public Account edit(Account account) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE ACCOUNT_TBL SET NAME=?,BALANCE=?,USER_ID=? WHERE ID=?"
        );
        preparedStatement.setString(1, account.getName());
        preparedStatement.setDouble(2, account.getBalance());
        preparedStatement.setInt(3, account.getUser().getId());
        preparedStatement.setInt(4, account.getId());
        preparedStatement.execute();

        return account;
    }

    @Override
    public Account remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ACCOUNT_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        return null;
    }

    @Override
    public List<Account> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ACCOUNT_REPORT"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Account> accountsList = new ArrayList<>();

        while (resultSet.next()) {
            Account account = Account.builder()
                    .id(resultSet.getInt("account_id"))
                    .name(resultSet.getString("account_name"))
                    .balance(resultSet.getDouble("account_balance"))
                    .user(User.builder()
                            .id(resultSet.getInt("user_id"))
                            .name(resultSet.getString("user_name"))
                            .family(resultSet.getString("user_family"))
                            .username(resultSet.getString("user_username"))
                            .password(resultSet.getString("user_password"))
                            .creationDate(resultSet.getTimestamp("user_creationdate").toLocalDateTime())
                            .build())
                    .build();

            accountsList.add(account);

        }
        return accountsList;
    }

    @Override
    public Account findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ACCOUNT_REPORT WHERE ACCOUNT_ID=?"
        );

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Account account = null;

        while (resultSet.next()) {
            account = Account.builder()
                    .id(resultSet.getInt("account_id"))
                    .name(resultSet.getString("account_name"))
                    .balance(resultSet.getDouble("account_balance"))
                    .user(User.builder()
                            .id(resultSet.getInt("user_id"))
                            .name(resultSet.getString("user_name"))
                            .family(resultSet.getString("user_family"))
                            .username(resultSet.getString("user_username"))
                            .password(resultSet.getString("user_password"))
                            .creationDate(resultSet.getTimestamp("user_creationdate").toLocalDateTime())
                            .build())
                    .build();
        }
        return account;
    }

    public List<Account> findByUserId(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ACCOUNT_REPORT WHERE USER_ID=?"
        );

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Account> accountsList = new ArrayList<>();

        while (resultSet.next()) {
            Account account = Account.builder()
                    .id(resultSet.getInt("account_id"))
                    .name(resultSet.getString("account_name"))
                    .balance(resultSet.getDouble("account_balance"))
                    .user(User.builder()
                            .id(resultSet.getInt("user_id"))
                            .name(resultSet.getString("user_name"))
                            .family(resultSet.getString("user_family"))
                            .username(resultSet.getString("user_username"))
                            .password(resultSet.getString("user_password"))
                            .creationDate(resultSet.getTimestamp("user_creationdate").toLocalDateTime())
                            .build())
                    .build();

            accountsList.add(account);
        }

        return accountsList;

    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
