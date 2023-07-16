package com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
    	Connection connection;
    	
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
        	connection=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/agenda?serverTimeZone=UTC&useSSL=false",
                "root",
                "");
    	}catch(SQLException|ClassNotFoundException ex) {
    		ex.printStackTrace();
    		connection=null;
    	}
    	return connection;
    }
}