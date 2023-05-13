package it.uninsubria.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<Entity> implements RepositoryImpl<Entity> {

    public static <T> List<T> resultSetToList(ResultSet rs, Class<T> clazz) throws SQLException {
        List<T> list = new ArrayList<>();
        ResultSetMetaData metadata = rs.getMetaData();
        int numColumns = metadata.getColumnCount();

        while (rs.next()) {
            try {
                T obj = clazz.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= numColumns; i++) {
                    String columnName = metadata.getColumnName(i);
                    Object columnValue = rs.getObject(i);
                    String setterName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method setter = clazz.getDeclaredMethod(setterName, columnValue.getClass());
                    setter.invoke(obj, columnValue);
                }
                list.add(obj);
            } catch (InstantiationException | IllegalAccessException
                     | InvocationTargetException | NoSuchMethodException e) {
                throw new SQLException("Error creating object from resultset", e);
            }
        }

        return list;
    }




}
