package it.uninsubria.emotionalsongs.repository;

import it.uninsubria.emotionalsongs.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Repository<T> implements RepositoryImpl<T> {

    /**
     * Converte un ResultSet in una lista di oggetti di tipo T.
     * @param rs ResultSet da convertire in lista
     * @param clazz classe generica T che rappresenta il tipo di oggetto da creare
     * @return una lista di oggetti di tipo T popolata dai dati del ResultSet
     * @throws SQLException se si verifica un errore durante l'accesso al ResultSet o durante la creazione degli oggetti T
     */
    public static <T> List<T> resultSetToList(ResultSet rs, Class<T> clazz) throws SQLException {
        List<T> list = new ArrayList<>(); // crea una lista vuota di oggetti T
        ResultSetMetaData metadata = rs.getMetaData(); // ottiene i metadati del ResultSet
        int numColumns = metadata.getColumnCount(); // ottiene il numero di colonne nel ResultSet

        // attraversa il ResultSet riga per riga
        while (rs.next()) {
            try {
                // crea un nuovo oggetto T per ogni riga del ResultSet
                T obj = clazz.getDeclaredConstructor().newInstance();
                // imposta i valori degli attributi dell'oggetto T con i dati della riga corrente del ResultSet
                for (int i = 1; i <= numColumns; i++) {
                    String columnName = metadata.getColumnName(i); // ottiene il nome della colonna
                    Object columnValue = rs.getObject(i); // ottiene il valore della colonna
                    String setterName = "set" + Utils.convertToCamelCase(columnName); // costruisce il nome del metodo set per l'attributo corrente
                    Method setter = clazz.getDeclaredMethod(setterName, columnValue.getClass()); // ottiene il metodo set corrispondente all'attributo corrente
                    setter.invoke(obj, columnValue); // chiama il metodo set per impostare il valore dell'attributo corrente
                }
                list.add(obj); // aggiunge l'oggetto T alla lista
            } catch (InstantiationException | IllegalAccessException
                     | InvocationTargetException | NoSuchMethodException e) {
                throw new SQLException("Errore conversione resultSet to List: ", e.getMessage()); // gestisce eventuali errori di creazione degli oggetti T
            }
        }

        return list; // restituisce la lista di oggetti T creata dai dati del ResultSet
    }


    public static String replaceNamedParams(String query, Map<String, Object> params) {
        for (Map.Entry<String, Object> param : params.entrySet()) {
            String paramName = param.getKey();
            Object paramValue = param.getValue();
            String replacement;
            if (paramValue instanceof String) {
                replacement = "'" + ((String) paramValue).replace("'", "''") + "'";
            } else {
                replacement = String.valueOf(paramValue);
            }
            query = query.replace(":" + paramName, replacement);
        }
        return query;
    }



}
