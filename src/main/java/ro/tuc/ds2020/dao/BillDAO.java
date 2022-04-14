package ro.tuc.ds2020.dao;

import ro.tuc.ds2020.connection.ConnectionFactory;
import ro.tuc.ds2020.model.Bill;
import ro.tuc.ds2020.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class BillDAO extends AbstractDAO<Bill>{

    public List<Bill> getBillsByClientId( int id ){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Bill> finalC = null;
        String query = "SELECT * from " +
                " bill " + "where clientid =" + id;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            finalC = createObjects(resultSet);
            return finalC;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "BillDAO:getBillsByClientId " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
}
}
