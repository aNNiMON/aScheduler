package com.annimon.scheduler.util;

import com.annimon.scheduler.data.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Подключение к базе данных.
 * @author aNNiMON
 */
public class DBConnection {

    private static DBConnection instance;
    
    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        
        return instance;
    }
    
    
    private static final String PROPERTY_RESOURCE = "db.properties";
    
    private Connection connection;
    private PreparedStatement statement;
    private final Properties dbProperties;
    
    private DBConnection() {
        dbProperties = new Properties();
        readProperties();
        initDatabase();
    }
    
    public int executeUpdate(String sql, Object[] params) {
        statement = getStatement(connection, sql, params);
        int resultNum = executeUpdate(statement);
        return resultNum;
    }
    
    public int executeUpdate(PreparedStatement pstmt) {
        int generatedKey = -1;
        try {
            int resultNum = pstmt.executeUpdate();
            if (resultNum > 0) {
                PreparedStatement stm = getStatement(connection, "SELECT last_insert_id()", null);
                ResultSet rs = null;
                try {
                    rs = stm.executeQuery();
                    rs.next();
                    generatedKey = rs.getInt(1);
                } catch (SQLException ex) {
                    ExceptionHandler.handle(ex, "sql query");
                } finally {
                    close(stm);
                    close(rs);
                }
            }
        } catch (SQLException ex) {
            ExceptionHandler.handle(ex, "sql update");
        } finally {
            close(pstmt);
        }
        return generatedKey;
    }
    
    public List<Entity> executeQuery(String sql, Object[] params, IResultSetHandler handler) {
        statement = getStatement(connection, sql, params);
        List<Entity> resultList = executeQuery(statement, handler);
        return resultList;
    }
    
    public Object[][] executeQuery(String sql, Object[] params) {
        List<Object[]> resultList = new ArrayList<>();
        
        PreparedStatement stm = getStatement(connection, sql, params);
        ResultSet rs = null;
        try {
            rs = stm.executeQuery();
            while (rs.next()) {
                ResultSetMetaData meta = rs.getMetaData();
                int cols = meta.getColumnCount();
                Object[] item = new Object[cols];
                for (int i = 0; i < cols; i++) {
                    item[i] = rs.getObject(i + 1);
                }
                resultList.add(item);
            }
        } catch (SQLException ex) {
            ExceptionHandler.handle(ex, "sql object[] query");
        } finally {
            close(stm);
            close(rs);
        }
        Object[][] result = new Object[resultList.size()][];
        result = resultList.toArray(result);
        
        return result;
    }
    
    public List<Entity> executeQuery(PreparedStatement pstmt, IResultSetHandler handler) {
        List<Entity> resultList = new ArrayList<>();
        
        ResultSet rs = null;
        try {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Entity entity = null;
                try {
                    entity = handler.process(rs);
                } catch (Exception ex) {
                    ExceptionHandler.handle(ex, "sql handle resultset");
                }
                resultList.add(entity);
            }
        } catch (SQLException ex) {
            ExceptionHandler.handle(ex, "sql query " + pstmt.toString());
        } finally {
            close(rs);
            close(pstmt);
        }
        return resultList;
    }

    public PreparedStatement getStatement(Connection connection, String sql, Object[] params) {
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            if (params != null && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
        } catch (SQLException ex) {
            ExceptionHandler.handle(ex, "Get statement error");
        }
        return pstmt;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void close(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                ExceptionHandler.handle(ex);
            }
        }
    }
    
    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
               ExceptionHandler.handle(ex);
            }
        }
    }

    public void destroy() {
        close(statement);
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ExceptionHandler.handle(ex);
            }
        }
    }
    
    private void initDatabase() {
        try {
            Class.forName(dbProperties.getProperty("JDBC_DRIVER", "com.mysql.jdbc.Driver"));
            
            connection = DriverManager.getConnection(
                    dbProperties.getProperty("DB_URL", "jdbc:mysql://localhost/scheduler"),
                    dbProperties.getProperty("DB_USER", "root"),
                    dbProperties.getProperty("DB_PASSWORD", ""));
        } catch (ClassNotFoundException | SQLException ex) {
            ExceptionHandler.handle(ex, "init database");
        }
    }
    
    private void readProperties() {
        // Если рядом с приложением нет файла db.properties, то читаем из ресурсов.
        InputStream stream = getInputStreamFromFile();
        if (stream == null) {
            stream = getClass().getResourceAsStream("/" + PROPERTY_RESOURCE);
        }
        try {
            dbProperties.load(stream);
            stream.close();
        } catch (IOException ex) {
            ExceptionHandler.handle(ex, "read db property");
        }
    }
    
    private InputStream getInputStreamFromFile() {
        File file = new File(PROPERTY_RESOURCE);
        if (file.exists()) {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                return null;
            }
        }
        return null;
    }
}
