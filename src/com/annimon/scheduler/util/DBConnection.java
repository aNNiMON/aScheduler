package com.annimon.scheduler.util;

import com.annimon.scheduler.data.Entity;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    
    private static final String PROPERTY_RESOURCE = "/db.properties";
    
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
        int resultNum = 0;
        try {
            resultNum = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ExceptionHandler.handle(ex, "sql update");
        } finally {
            close(statement);
        }
        return resultNum;
    }
    
    public ResultSet executeQuery(String sql, Object[] params) {
        statement = getStatement(connection, sql, params);
        ResultSet rs = executeQuery(statement);
        return rs;
    }
    
    public ResultSet executeQuery(PreparedStatement pstmt) {
        ResultSet rs = null;
        try {
            rs = pstmt.executeQuery();
        } catch (SQLException ex) {
            ExceptionHandler.handle(ex, "sql query");
        } finally {
            close(pstmt);
        }
        return rs;
    }
    
    public List<Entity> executeQuery(String sql, Object[] params, IResultSetHandler handler) {
        statement = getStatement(connection, sql, params);
        List<Entity> resultList = executeQuery(statement, handler);
        return resultList;
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

    public void close(Connection connection) {
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
        InputStream stream = getClass().getResourceAsStream(PROPERTY_RESOURCE);
        try {
            dbProperties.load(stream);
            stream.close();
        } catch (IOException ex) {
            ExceptionHandler.handle(ex, "read db property");
        }
    }
}
