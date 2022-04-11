package ro.tuc.ds2020.dao;

import ro.tuc.ds2020.connection.ConnectionFactory;
import ro.tuc.ds2020.model.Client;
import ro.tuc.ds2020.util.Hasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ClientDAO extends AbstractDAO<Client>{

    public Client findByNameAndPassword( String name, String password ){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Client> finalC = null;
        String query = createCustomSelectQuery(name, password);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            finalC = createObjects(resultSet);
            if ( !finalC.isEmpty() )
                return finalC.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findByNameAndPassword " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    String createCustomSelectQuery( String name, String password ){
        return "SELECT * from client where name=" + "\"" + name +
                "\"" + "AND password=" + "\"" + Hasher.MD5Hash(password) + "\"";
    }

    public Client findByEmailAndPassword( String email, String password ){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Client> finalC = null;
        String query = createCustomSelectQuery2(email, password);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            finalC = createObjects(resultSet);
            if ( !finalC.isEmpty() )
                return finalC.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findByEmailAndPassword " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    String createCustomSelectQuery2( String email, String password ){
        return "SELECT * from client where email=" + "\"" + email +
                "\"" + "AND password=" + "\"" + Hasher.MD5Hash(password) + "\"";
    }

    String createEmailSelectQuery( String email ){
        return "SELECT * from client where email=" + "\"" + email + "\"";
    }


    public Client findByEmail( String email ){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Client> finalC = null;
        String query = createEmailSelectQuery( email );
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            finalC = createObjects(resultSet);
            if ( !finalC.isEmpty() )
                return finalC.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findByEmail " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

}
