package ro.tuc.ds2020.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.tuc.ds2020.connection.ConnectionFactory;
import ro.tuc.ds2020.util.Hasher;


public class AbstractDAO<T> {
        protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

        private final Class<T> type;

        @SuppressWarnings("unchecked")
        public AbstractDAO() {
            this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        }

        private String createSelectQuery(String field) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append(" * ");
            sb.append(" FROM ");
            sb.append(type.getSimpleName());
            sb.append(" WHERE " + field + " =?");
            return sb.toString();
        }

        private String createInsertQuery(T t) {
            StringBuilder query = new StringBuilder("INSERT into " + type.getSimpleName() +
                    " VALUES(");

            try{
            for( Field fieldIt : type.getDeclaredFields() ){
                fieldIt.setAccessible(true);

                if ( fieldIt.getName().equals("timestamp") ){
                    query.append(" current_timestamp ");
                    query.append(",");
                    continue;
                }

                // Cheap
                if ( fieldIt.getName().equals("id") ){
                    query.append( (int)((Math.random() * (999999) )));
                    query.append(",");
                    continue;
                }

                if ( fieldIt.getName().equals("password") ){
                    // Hash passwords
                    query.append("\"").append( Hasher.MD5Hash((String)fieldIt.get(t)) );
                    query.append("\"").append(",");
                    continue;
                }

                if ( fieldIt.getType().equals( String.class ) )
                    query.append("\"").append(fieldIt.get(t)).append("\"");
                else
                    query.append( fieldIt.get(t) );

                query.append(",");
            }}
            catch (IllegalAccessException exp)
            {
                exp.printStackTrace();
            }

            query.deleteCharAt( query.lastIndexOf(",") );

            query.append(");");

            return query.toString();
        }

        // This assumes every ID is the first field in each entity, and it is called id.
        // Kinda cheap but will update later
        private String createUpdateQuery(T t) {
            StringBuilder query = new StringBuilder("UPDATE " + type.getSimpleName() +
                    " SET ");

            try{
                for( Field fieldIt : type.getDeclaredFields() ){
                    fieldIt.setAccessible(true);

                    // Ignore ID;
                    if  ( fieldIt.getName().equals("id") ){
                        continue;
                    }

                    if ( fieldIt.getName().equals("password") ){
                        query.append( fieldIt.getName() ).append(" = ").append("'")
                                        .append( Hasher.MD5Hash( (String)fieldIt.get(t) ) ).append("'");
                        query.append(",");
                        continue;
                    }

                    if ( fieldIt.getType().equals( String.class ) )
                        query.append(fieldIt.getName()).append(" = ")
                                .append("'").append(fieldIt.get(t)).append("'");
                    else
                        query.append(fieldIt.getName()).append(" = ")
                                .append(fieldIt.get(t));

                    query.append(",");
                }}
            catch (IllegalAccessException exp)
            {
                exp.printStackTrace();
            }

            query.deleteCharAt( query.lastIndexOf(",") );

            Field id = type.getDeclaredFields()[0];
            id.setAccessible(true);

            try {
                query.append(" WHERE id = ").append(id.get(t));
            }
            catch ( IllegalAccessException exception){
                exception.printStackTrace();
            }

            query.append(";");

            return query.toString();
        }

        public List<T> findAll() {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String query = "SELECT * FROM " + type.getSimpleName();
            try{
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                // setInt ??
                resultSet = statement.executeQuery();

                return createObjects(resultSet);
            }
            catch (SQLException exp){
                LOGGER.log(Level.WARNING, type.getName() +
                        "DAO:findAll" + exp.getMessage());
            }
            finally {
                ConnectionFactory.close(resultSet);
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
            return null;
        }

        public T findById(int id) {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String query = createSelectQuery("id");
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                return createObjects(resultSet).get(0);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
            } finally {
                ConnectionFactory.close(resultSet);
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
            return null;
        }

        private List<T> createObjects(ResultSet resultSet) {
            List<T> list = new ArrayList<T>();
            Constructor[] ctors = type.getDeclaredConstructors();
            Constructor ctor = null;
            for (int i = 0; i < ctors.length; i++) {
                ctor = ctors[i];
                if (ctor.getGenericParameterTypes().length == 0)
                    break;
            }
            try {
                while (resultSet.next()) {
                    ctor.setAccessible(true);
                    T instance = (T)ctor.newInstance();
                    for (Field field : type.getDeclaredFields()) {
                        String fieldName = field.getName();
                        Object value = resultSet.getObject(fieldName);
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    }
                    list.add(instance);
                }
            } catch (InstantiationException | IllegalAccessException | SecurityException |
                    IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
                e.printStackTrace();
            }
            return list;
        }

        public T insert(T t) {
            Connection connection = null;
            PreparedStatement statement = null;
            String query = createInsertQuery(t);
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                statement.executeUpdate();
                return t;
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
                return null;
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
        }

        public T update(T t) {
            Connection connection = null;
            PreparedStatement statement = null;
            String query = createUpdateQuery(t);
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                statement.executeUpdate();
                return t;
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
                return null;
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
        }


        public T findByName(String name) {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String query = createSelectQuery("name");
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                statement.setString( 1,name);
                resultSet = statement.executeQuery();

                return createObjects(resultSet).get(0);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
            } finally {
                ConnectionFactory.close(resultSet);
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
            return null;
        }

    }
