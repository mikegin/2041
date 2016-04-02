import java.util.*; 
import java.io.*;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.sql.ResultSetMetaData; 
public class MGListArtObj { 
	private static String dbURL = "jdbc:derby://red.eecs.yorku.ca:52041/EECS2041;user=student;password=secret"; 
	private static Connection conn = null; 
	private static Statement stmt = null; 
	private static String id = null; 
	
	public static void main(String[] args) {
		readPar(); 
		createConnection(); 
		printMsg(); 
	} 
	private static void readPar() { 
		Hashtable form_data = cgi_lib.ReadParse(System.in); 
		//System.out.println("test");
		id= (String)form_data.get("aID"); 
		//System.out.println(id);
	} 
	private static void shutdown() { 
		try { 
			if (stmt != null) { 
				stmt.close(); 
			} 
			if (conn != null) { 
				DriverManager.getConnection(dbURL + ";shutdown=true"); 
				conn.close(); 
			} 
		} 
		catch (SQLException sqlExcept) { 
			// do nothing for now 
		}
	} 
	private static ResultSet selectTable() { 
		ResultSet results = null; 
		try { 
			stmt = conn.createStatement(); 
			stmt.executeUpdate("set schema course"); 
			results = stmt.executeQuery("select artid, artyear, arttitle, artepoch, artprice from artobj where artistid in (select artistid from artist where artistid='"+id+"')"); 
		} catch (SQLException sqlExcept) { sqlExcept.printStackTrace(); } 
		return results; 
	} 
	private static void createConnection() { 
		try { 
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance(); 
			//Get a connection 
			conn = DriverManager.getConnection(dbURL); 
		} catch (Exception except) { except.printStackTrace(); } 
	} 
	public static void printMsg() { 
	// Output the header(s). 
	System.out.print("Content-type: text/html \r\n");
	System.out.print("\r\n"); 
	// -------------------------------------------------------------------- 
	// Set up HTML template. 
	String htmlHead = ""
		+ "<!DOCTYPE html>\n"
		+ "<html>\n"
		+ "<head>\n"
		+ " <title>\n"
		+ " Assignment 6 from Java!\n"
		+ " </title>\n"
		+ "</head>\n"
		+ " \n"
			+"<style type=\"text/css\">\n"
			+"table, td, th\n"
			+"{border:1px solid black; background-color:yellow;}\n"
		+"</style >\n" 
	+"<body>\n";
	System.out.print(htmlHead); 
	/*createConnection(); */ 
	ResultSet results = selectTable(); 

	try { 
		// get column names from the database 
		ResultSetMetaData rsmd = results.getMetaData(); 
		int numberCols = rsmd.getColumnCount(); 

		// begin table 
		System.out.println("<table>");

		// header row 
		System.out.println("<tr>");
		
		for(int i=1; i<=numberCols; i++) { 
			//print column names 
			System.out.println("<th>" + rsmd.getColumnLabel(i) + "</th>"); 
		} 

		System.out.println("</tr>");
		// table data rows 
		while(results.next()) { 
			// get the results from the db 
			System.out.println("<tr>"); 
			for (int i = 1; i <= numberCols; i++) { 
				String col = results.getString(i); 
				System.out.println("<td>" + col + "</td>"); 
		} 
		System.out.println("</tr>"); 
	} 

	// end table 
	System.out.println("</table>"); 

	// clean up the connection to database 
	results.close(); 
	stmt.close(); 
	} 
	catch (Exception e) { e.printStackTrace(); } 
	shutdown();
	// finish up html 	
	String htmlFoot = "\n"
		+ "</body> \n"
		+ "</html> \n";
	System.out.print(htmlFoot); 
	
	} 
	
	} 
