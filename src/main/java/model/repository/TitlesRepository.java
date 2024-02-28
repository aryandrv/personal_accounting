package model.repository;

import enums.TypeEnum;
import model.entity.Titles;
import model.entity.User;
import model.repository.impl.Repository;
import model.repository.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TitlesRepository implements Repository<Titles>, AutoCloseable {
    private PreparedStatement preparedStatement;
    private Connection connection;

    @Override
    public Titles save(Titles titles) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT TITLES_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        titles.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TITLES_TBL(ID,NAME,TYPE) VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, titles.getId());
        preparedStatement.setString(2, titles.getName());
        preparedStatement.setString(3, titles.getType().toString());
        preparedStatement.execute();

        return titles;
    }

    @Override
    public Titles edit(Titles titles) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE TITLES_TBL SET NAME=?,TYPE=? WHERE ID=?"
        );
        preparedStatement.setString(1, titles.getName());
        preparedStatement.setString(2, titles.getType().toString());
        preparedStatement.setInt(3, titles.getId());
        preparedStatement.execute();

        return titles;
    }

    @Override
    public Titles remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TITLES_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

        return null;
    }

    @Override
    public List<Titles> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM TITLES_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Titles> titlesList = new ArrayList<>();

        while (resultSet.next()) {
            Titles titles = Titles.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .type(TypeEnum.toEnum(resultSet.getString("TYPE")))
                    .build();

            titlesList.add(titles);

        }
        return titlesList;
    }

    @Override
    public Titles findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM TITLES_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Titles titles = null;

        while (resultSet.next()){
            titles = Titles.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .type(TypeEnum.toEnum(resultSet.getString("TYPE")))
                    .build();
        }
        return titles;

    }

    public List<Titles> findByType(String type) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM TITLES_TBL WHERE TYPE=?"
        );
        preparedStatement.setString(1, type);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Titles> titlesList = new ArrayList<>();

        while (resultSet.next()) {
            Titles titles = Titles.builder()
                    .id(resultSet.getInt("ID"))
                    .name(resultSet.getString("NAME"))
                    .type(TypeEnum.toEnum(resultSet.getString("TYPE")))
                    .build();

            titlesList.add(titles);

        }
        return titlesList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();

    }
}
