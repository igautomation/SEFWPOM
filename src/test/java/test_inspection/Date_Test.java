package test_inspection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Date_Test {

	public static ResultSet rs;
	public static String SECURITY_LEVEL;
	public static String ASSIGNED_TO;
	public static String url;
	public static String userName;
	public static String password;
	public static String s;
	
	public static void  main(String[] args) throws  ClassNotFoundException, SQLException, InterruptedException {		
		String s10 = null;
		url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
		userName = "mgimran_r"; 
		password = "mg1mran_usbl";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        
        
        java.sql.Connection conn = DriverManager.getConnection(url, userName, password);
        String query = "Select LOGIN From Siebel.s_user Where row_id in (select OWNER_EMP_ID from siebel.s_srv_req where x_ins_serial_number = '202-BR-106747')";
				 	 
		 java.sql.Statement stmt = conn.createStatement();	
		 
		 for (int i = 0 ; i<=10; i++) {
			 
			 
			 
			 rs = stmt.executeQuery(query);
			 
		 while(rs.next()) {
		         if(rs.getString("LOGIN")!= null){
					 s10 = rs.getString("LOGIN");
					 break;
		         }
					 else {
						 System.out.println("Failed");
					 }

		 }
		 Thread.sleep(20000);
		 
		 
		 }
		 

		
		 // closing DB Connection		
	 	conn.close();
	}


}

