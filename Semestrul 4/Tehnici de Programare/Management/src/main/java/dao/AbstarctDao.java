package dao;


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

import connection.ConnectionFactory;


public abstract class AbstarctDao<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstarctDao.class.getName());

    private final Class<T> type;

    public AbstarctDao() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    private String createselectquery(String field) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT ");
        str.append(" * ");
        str.append(" FROM ");
        str.append(type.getSimpleName());
        str.append(" WHERE " + field + " =?");
        return str.toString();
    }
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createselectquery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createobject(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findbyId " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    private List<T> createobject(ResultSet resultset) {
        List<T> list = new ArrayList<T>();
        Constructor[] constrs = type.getDeclaredConstructors();
        Constructor constr = null;
        for (int i = 0; i < constrs.length; i++) {
            constr = constrs[i];
            if (constr.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultset.next()) {
                constr.setAccessible(true);
                T instance = (T) constr.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultset.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    private String createfindallquery(String field) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT ");
        str.append(" * ");
        str.append(" FROM `");
        str.append(type.getSimpleName());
        str.append("`");
        return str.toString();
    }
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createfindallquery("");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createobject(resultSet);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findall" + e.getMessage());
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;

    }
    private String createinsertquery(String field) {
        StringBuilder str = new StringBuilder();
        str.append("insert into ");
        str.append(type.getSimpleName());
        str.append(" values (");
        return str.toString();
    }
    public void insert(T t)
    {
        String query=createinsertquery("");
        Connection conn=null;
        PreparedStatement stm= null;

        try
        {
            for(Field fld : type.getDeclaredFields())
            {
                fld.setAccessible(true);
                Object val= fld.get(t);
                if(val instanceof String)
                {
                    query= query + "'" + val.toString() + "',";
                }
                else
                {
                    query= query + val.toString() + ",";
                }
            }
            query=query.substring(0,query.length()-1)+")";
            conn= ConnectionFactory.getConnection();
            stm = conn.prepareStatement(query);
            stm.execute();
        }catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(stm);
            ConnectionFactory.close(conn);
        }
    }
    private String createupdatequery(String field) {
        StringBuilder str = new StringBuilder();
        str.append("update ");
        str.append(type.getSimpleName());
        str.append(" set ");
        return str.toString();
    }
    public void update(T t)
    {
        String query=createupdatequery("");
        Connection conn=null;
        PreparedStatement stm= null;

        int id=0;
        try
        {
            for(Field fld : type.getDeclaredFields())
            {
                fld.setAccessible(true);
                Object val= fld.get(t);
                if(fld.getName().equals("id"))
                {
                    id=(int) val;
                }
                else
                {
                    if (val instanceof String)
                        query = query + fld.getName() + "='" + val.toString() + "',";
                    else
                        query = query + fld.getName() + "=" + val.toString() + ",";
                }
            }
            query=query.substring(0,query.length()-1)+" where id="+ id;
            conn= ConnectionFactory.getConnection();
            stm = conn.prepareStatement(query);
            stm.execute();
        }catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(stm);
            ConnectionFactory.close(conn);
        }
    }
    private String createdeletequery(String field) {
        StringBuilder str = new StringBuilder();
        str.append("delete from `");
        str.append(type.getSimpleName());
        str.append("` where id=");
        return str.toString();
    }
    public void delete(T t)
    {
        String query=createdeletequery("");
        Connection conn=null;
        PreparedStatement stm= null;
        int id=0;
        try
        {
            for(Field fld : type.getDeclaredFields())
            {
                fld.setAccessible(true);
                Object val= fld.get(t);
                if(fld.getName().equals("id"))
                {
                    id=(int) val;
                }
            }
            query=query + id;
            conn= ConnectionFactory.getConnection();
            stm = conn.prepareStatement(query);
            stm.execute();
        }catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(stm);
            ConnectionFactory.close(conn);
        }
    }

    public Object[] getheaderoftable()
    {
        Field[] fields = type.getDeclaredFields();
        Object[] hdr = new Object[fields.length];
        int i=0;
        while(i< fields.length)
        {
            String nm= fields[i].getName();
            hdr[i]= nm;
            i++;
        }
        return hdr;
    }

    public Object[][] gettable(List<T> lists)
    {
        Field[] fld =type.getDeclaredFields();
        Object[][] tbl= new Object[lists.size()][fld.length];
        try
        {
            for(int i=0;i< lists.size();i++)
            {
                for(int j=0;j<fld.length;j++)
                {
                    fld[j].setAccessible(true);
                    tbl[i][j]=fld[j].get(lists.get(i));
                }
            }
            return tbl;
        }catch (IllegalAccessException e)
        {
            LOGGER.log(Level.WARNING,type.getName() + "DAO gettable"+ e.getMessage());
        }
        return  null;
    }
}






