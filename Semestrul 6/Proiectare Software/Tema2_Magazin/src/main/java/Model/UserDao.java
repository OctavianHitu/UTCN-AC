package Model;

import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;

import javax.swing.*;
import java.util.logging.Logger;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {

    private  String table = "users";
    protected Logger LOGGER = Logger.getLogger(table);


    private String createfindallquery() {
        StringBuilder str = new StringBuilder();
        str.append("SELECT ");
        str.append(" * ");
        str.append(" FROM ");
        str.append(table);
        return str.toString();
    }

    public List<User> findallusers()
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createfindallquery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            List<User> list = new ArrayList<>();
            while(resultSet.next())
            {
                String username = resultSet.getObject("username").toString();
                String password = resultSet.getObject("password").toString();
                String rol = resultSet.getObject("rol").toString();
                User user = new BuilderUser().setusername(username).setpassword(password).setrol(rol).build();
                list.add(user);
            }
            return list;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, table + "DAO:findall " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    public void insertusersdao(User user) {
        Connection connection=null;
        PreparedStatement statement = null;
        String query = "INSERT INTO users VALUE ('";

        query += user.getUsername();
        query += "','";
        query += user.getPassword();
        query += "','";
        query += user.getRol();
        query +="')";

        connection =ConnectionFactory.getConnection();
        try
        {
            statement=connection.prepareStatement(query);
            statement.execute();

        }catch (SQLException e)
        {
            JOptionPane.showConfirmDialog(new JFrame(),"Nu am putut insera in tabela un user"+e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void deleteuserdao(User user)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM users WHERE username =";
        query += "'";
        query += user.getUsername();
        System.out.println(user.getUsername());
        query += "'";

        System.out.println(query);
        connection = ConnectionFactory.getConnection();

        try {
            statement = connection.prepareStatement(query);
            statement.execute();
        }catch (SQLException e)
        {
            JOptionPane.showMessageDialog(new JFrame(),"Nu am putut sterge userul din tabela"+e.getMessage());

        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);

        }
    }
}
