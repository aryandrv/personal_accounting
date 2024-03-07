package model.repository;

import enums.TypeEnum;
import model.entity.Account;
import model.entity.Titles;
import model.entity.Transaction;
import model.entity.User;
import model.repository.impl.Repository;
import model.repository.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements Repository<Transaction>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Transaction save(Transaction transaction) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT TRANSACTION_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        transaction.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TRANSACTION_TBL(ID,USER_ID,ACCOUNT_ID,AMOUNT,TITLES_ID,TRANSACTIONDATE,DESCRIPTION,TYPE) VALUES (?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, transaction.getId());
        preparedStatement.setInt(2, transaction.getUser().getId());
        preparedStatement.setInt(3, transaction.getAccount().getId());
        preparedStatement.setDouble(4, transaction.getAmount());
        preparedStatement.setInt(5, transaction.getTitles().getId());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(transaction.getTransactionDate()));
        preparedStatement.setString(7, transaction.getDescription());
        preparedStatement.setString(8, transaction.getType().toString());
        preparedStatement.execute();

        return transaction;
    }

    @Override
    public Transaction edit(Transaction transaction) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE TRANSACTION_TBL SET USER_ID=?,ACCOUNT_ID=?,AMOUNT=?,TITLES_ID=?,TRANSACTIONDATE=?, DESCRIPTION=?, TYPE=? WHERE ID=?"
        );
        preparedStatement.setInt(1, transaction.getUser().getId());
        preparedStatement.setInt(2, transaction.getAccount().getId());
        preparedStatement.setDouble(3, transaction.getAmount());
        preparedStatement.setInt(4, transaction.getTitles().getId());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(transaction.getTransactionDate()));
        preparedStatement.setString(6, transaction.getDescription());
        preparedStatement.setString(7, transaction.getType().toString());
        preparedStatement.setInt(8, transaction.getId());
        preparedStatement.execute();

        return transaction;
    }

    @Override
    public Transaction remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TRANSACTION_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        return null;
    }

    @Override
    public List<Transaction> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM TRANSACTION_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Transaction> transactionList = new ArrayList<>();

        while (resultSet.next()) {
            Transaction transaction = Transaction.builder()
                    .id(resultSet.getInt("ID"))
                    .user(User.builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                            .build())
                    .account(Account.builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .balance(resultSet.getDouble("BALANCE"))
                            .user(User.builder()
                                    .id(resultSet.getInt("ID"))
                                    .name(resultSet.getString("NAME"))
                                    .family(resultSet.getString("FAMILY"))
                                    .username(resultSet.getString("USERNAME"))
                                    .password(resultSet.getString("PASSWORD"))
                                    .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                                    .build())
                            .build())
                    .amount(resultSet.getDouble("AMOUNT"))
                    .titles(Titles.builder()
                            .id(resultSet.getInt("ID"))
                            .name("NAME")
                            .type(TypeEnum.valueOf(resultSet.getString("TYPE")))
                            .build())
                    .transactionDate(resultSet.getTimestamp("TRANSACTIONDATE").toLocalDateTime())
                    .description(resultSet.getString("DESCRIPTION"))
                    .type(TypeEnum.valueOf(resultSet.getString("TYPE")))
                    .build();

            transactionList.add(transaction);

        }
        return transactionList;
    }

    @Override
    public Transaction findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM TRANSACTION_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Transaction transaction = null;
        while (resultSet.next()) {
            transaction = Transaction.builder()
                    .id(resultSet.getInt("ID"))
                    .user(User.builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                            .build())
                    .account(Account.builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .balance(resultSet.getDouble("BALANCE"))
                            .user(User.builder()
                                    .id(resultSet.getInt("ID"))
                                    .name(resultSet.getString("NAME"))
                                    .family(resultSet.getString("FAMILY"))
                                    .username(resultSet.getString("USERNAME"))
                                    .password(resultSet.getString("PASSWORD"))
                                    .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                                    .build())
                            .build())
                    .amount(resultSet.getDouble("AMOUNT"))
                    .titles(Titles.builder()
                            .id(resultSet.getInt("ID"))
                            .name("NAME")
                            .type(TypeEnum.valueOf(resultSet.getString("TYPE")))
                            .build())
                    .transactionDate(resultSet.getTimestamp("TRANSACTIONDATE").toLocalDateTime())
                    .description(resultSet.getString("DESCRIPTION"))
                    .type(TypeEnum.valueOf(resultSet.getString("TYPE")))
                    .build();
        }
        return transaction;
    }

    public List<Transaction> findByUserId(int userId) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM TRANSACTION_REPORT WHERE TRANSACTION_USERID=?"
        );

        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Transaction> transactionList = new ArrayList<>();

        while (resultSet.next()) {
            Transaction transaction = Transaction.builder()
                    .id(resultSet.getInt("transaction_id"))
                    .user(User.builder()
                            .id(resultSet.getInt("user_id"))
                            .name(resultSet.getString("user_name"))
                            .family(resultSet.getString("user_family"))
                            .username(resultSet.getString("user_username"))
                            .password(resultSet.getString("user_password"))
                            .creationDate(resultSet.getTimestamp("user_creationdate").toLocalDateTime())
                            .build())
                    .account(Account.builder()
                            .id(resultSet.getInt("account_id"))
                            .name(resultSet.getString("account_name"))
                            .balance(resultSet.getDouble("account_balance"))
                            .user(User.builder()
                                    .id(resultSet.getInt("account_userid"))
                                    .name(resultSet.getString("user_name"))
                                    .family(resultSet.getString("user_family"))
                                    .username(resultSet.getString("user_username"))
                                    .password(resultSet.getString("user_password"))
                                    .creationDate(resultSet.getTimestamp("user_creationdate").toLocalDateTime())
                                    .build())
                            .build())
                    .amount(resultSet.getDouble("transaction_amount"))
                    .titles(Titles.builder()
                            .id(resultSet.getInt("titles_id"))
                            .name(resultSet.getString("titles_name"))
                            .type(TypeEnum.toEnum(resultSet.getString("titles_type")))
                            .build())
                    .transactionDate(resultSet.getTimestamp("transaction_date").toLocalDateTime())
                    .description(resultSet.getString("transaction_description"))
                    .type(TypeEnum.toEnum(resultSet.getString("transaction_type")))
                    .build();

            transactionList.add(transaction);
        }

        return transactionList;
    }

    public List<Transaction> findByDateAndAccountId(int accountID, LocalDateTime from, LocalDateTime to) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM TRANSACTION_TBL WHERE " +
                        "ACCOUNT_ID = ? And TRANSACTIONDATE >= ? AND TRANSACTIONDATE <= ?");

        preparedStatement.setInt(1, accountID);
        preparedStatement.setTimestamp(2, Timestamp.valueOf(from));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(to));
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Transaction> transactionList = new ArrayList<>();

        while (resultSet.next()) {
            Transaction transaction = Transaction.builder()
                    .id(resultSet.getInt("ID"))
                    .user(User.builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                            .build())
                    .account(Account.builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .balance(resultSet.getDouble("BALANCE"))
                            .user(User.builder()
                                    .id(resultSet.getInt("ID"))
                                    .name(resultSet.getString("NAME"))
                                    .family(resultSet.getString("FAMILY"))
                                    .username(resultSet.getString("USERNAME"))
                                    .password(resultSet.getString("PASSWORD"))
                                    .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                                    .build())
                            .build())
                    .amount(resultSet.getDouble("AMOUNT"))
                    .titles(Titles.builder()
                            .id(resultSet.getInt("ID"))
                            .name("NAME")
                            .type(TypeEnum.valueOf(resultSet.getString("TYPE")))
                            .build())
                    .transactionDate(resultSet.getTimestamp("TRANSACTIONDATE").toLocalDateTime())
                    .description(resultSet.getString("DESCRIPTION"))
                    .type(TypeEnum.valueOf(resultSet.getString("TYPE")))
                    .build();

            transactionList.add(transaction);
        }


        return transactionList;

    }
    public List<Transaction> findByDateAndUserId(int userId, LocalDateTime from, LocalDateTime to) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM TRANSACTION_TBL WHERE " +
                        "USER_ID = ? And TRANSACTIONDATE >= ? AND TRANSACTIONDATE <= ?");

        preparedStatement.setInt(1, userId);
        preparedStatement.setTimestamp(2, Timestamp.valueOf(from));
        preparedStatement.setTimestamp(3, Timestamp.valueOf(to));
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Transaction> transactionList = new ArrayList<>();

        while (resultSet.next()) {
            Transaction transaction = Transaction.builder()
                    .id(resultSet.getInt("ID"))
                    .user(User.builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                            .build())
                    .account(Account.builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .balance(resultSet.getDouble("BALANCE"))
                            .user(User.builder()
                                    .id(resultSet.getInt("ID"))
                                    .name(resultSet.getString("NAME"))
                                    .family(resultSet.getString("FAMILY"))
                                    .username(resultSet.getString("USERNAME"))
                                    .password(resultSet.getString("PASSWORD"))
                                    .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                                    .build())
                            .build())
                    .amount(resultSet.getDouble("AMOUNT"))
                    .titles(Titles.builder()
                            .id(resultSet.getInt("ID"))
                            .name("NAME")
                            .type(TypeEnum.valueOf(resultSet.getString("TYPE")))
                            .build())
                    .transactionDate(resultSet.getTimestamp("TRANSACTIONDATE").toLocalDateTime())
                    .description(resultSet.getString("DESCRIPTION"))
                    .type(TypeEnum.valueOf(resultSet.getString("TYPE")))
                    .build();

            transactionList.add(transaction);
        }


        return transactionList;

    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
