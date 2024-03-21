package proiect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {
	public static void main(String[] args) {
		PrimaPagina f = new PrimaPagina();
	}

	public static void main2() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {
			System.err.println("An Exception occured during JDBC Driver loading." + " Details are provided below:");
			ex.printStackTrace(System.err);
		}
		Connection connection = null;
		Statement selectStatement = null, insertStatement = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/proiect?user=root&password=17052002Da");

		} catch (SQLException sqlex) {
			System.err.println("An SQL Exception occured. Details are provided below:");
			sqlex.printStackTrace(System.err);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
				rs = null;
			}
			if (selectStatement != null) {
				try {
					selectStatement.close();
				} catch (SQLException e) {
				}
				selectStatement = null;
			}
			if (insertStatement != null) {
				try {
					insertStatement.close();
				} catch (SQLException e) {
				}
				insertStatement = null;
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
				connection = null;
			}
		}
	}
}
