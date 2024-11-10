package model.repository;

import model.entity.User;
import model.repository.impl.Repository;
import model.repository.tools.JdbcProvider;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User>, AutoCloseable {

    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public User save(User user) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT USER_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        user.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO USER_TBL(ID,NAME,FAMILY,USERNAME,PASSWORD,CREATIONDATE) VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1,user.getId());
        preparedStatement.setString(2,user.getName());
        preparedStatement.setString(3,user.getFamily());
        preparedStatement.setString(4,user.getUsername());
        preparedStatement.setString(5,user.getPassword());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(user.getCreationDate()));
        preparedStatement.execute();

        return user;
    }

    @Override
    public User edit(User user) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE USER_TBL SET NAME=?,FAMILY=?,USERNAME=?,PASSWORD=?,CREATIONDATE=? WHERE ID=?"
        );
        preparedStatement.setString(1,user.getName());
        preparedStatement.setString(2,user.getFamily());
        preparedStatement.setString(3,user.getUsername());
        preparedStatement.setString(4,user.getPassword());
        preparedStatement.setTimestamp(5, Timestamp.valueOf(user.getCreationDate()));
        preparedStatement.setInt(6,user.getId());
        preparedStatement.execute();

        return user;
    }

    @Override
    public User remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM USER_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1,id);
        preparedStatement.execute();

        return null;
    }

    @Override
    public List<User> findAll() throws Exception {
        connection =JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<User> userList = new ArrayList<>();

        while(resultSet.next()){
            User user = User.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                    .build();

            userList.add(user);

        }
        return userList;
    }

    @Override
    public User findById(int id) throws Exception {
        connection =  JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;

        while (resultSet.next()){
            user = User.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                    .build();
        }
        return user;
    }

    public User findByUsernameAndPassword(String username ,String password)  throws Exception{
        connection = JdbcProvider.getJdbcProvider().getConnection();

        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USERNAME=? AND PASSWORD = ?"
        );

        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;

        while (resultSet.next()){
            user = User.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                    .build();
        }
        return user;
    }

    public User findByUsername(String username) throws Exception {
        connection =  JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM USER_TBL WHERE USERNAME=?"
        );

        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();

        User user = null;

        while (resultSet.next()){
            user = User.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .family(resultSet.getString("FAMILY"))
                    .username(resultSet.getString("USERNAME"))
                    .password(resultSet.getString("PASSWORD"))
                    .creationDate(resultSet.getTimestamp("CREATIONDATE").toLocalDateTime())
                    .build();
        }
        return user;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
