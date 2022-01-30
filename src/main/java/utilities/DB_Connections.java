package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class DB_Connections {

	public static ResultSet rs;
	public static String SECURITY_LEVEL;
	public static String ASSIGNED_TO;
	public static Connection conn = null;
	public static String url;
	public ExtentTest test;
	public ExtentReports reports;
	public String Result;
	public static String userName;
	public static String password;
	public static String s;

	public static String Declaration_Clear_Status(String decno, String Environment)
			throws ClassNotFoundException, InterruptedException, SQLException {

		String s2 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01:1525:DPPSIT";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {

			url = "jdbc:oracle:thin:@utordb-scan.dubaiworld.ae:1525/UT1_DPP.dubaiworld.ae";
			userName = "ptuser";
			password = "pt1u$er";
			Class.forName("oracle.jdbc.OracleDriver");

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select DD1.STATUS_ID STATUS FROM dppdec.dec_declaration DD1 WHERE DD1.DECLARATION_NO ='"
					+ decno + "' AND DD1.IS_ACTIVE = '1'";
			Statement stmt = conn.createStatement();
			System.out.println(query);
			rs = stmt.executeQuery(query);
			for (int i = 0; i <= 10; i++) {

				while (rs.next()) {
					if (rs.getString("STATUS").equals("6") || rs.getString("STATUS").equals("7")) {
						s2 = rs.getString("STATUS");
						continue;
					} else if (i == 10) {
						if (rs.getString("STATUS") != null) {
							s2 = rs.getString("STATUS");
							continue;
						}

					}
					Thread.sleep(10000);
					conn.close();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s2;

	}

	public static String Read_Store_Status(String decno, String Environment)
			throws SQLException, ClassNotFoundException, InterruptedException {

		String s1 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01:1525:DPPSIT";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordb-scan.dubaiworld.ae:1525/UT1_DPP.dubaiworld.ae";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "SELECT DECLARATION_NO FROM READ_STORE.RS_DECLARATION WHERE DECLARATION_NO = '" + decno
					+ "'";
			Statement stmt = conn.createStatement();

			for (int i = 0; i <= 5; i++) {
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					if (rs.getObject("DECLARATION_NO") != null) {
						s1 = (rs.getString("DECLARATION_NO"));
						continue;
					}
					Thread.sleep(10000);
				}

				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s1;

	}

	public static String Declaration_Siebel_Registry(String decno, String Environment)
			throws SQLException, ClassNotFoundException, InterruptedException {

		String s3 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01:1525:DPPSIT";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "SELECT X_DECLARATION_NUMBER FROM SIEBEL.S_INCIDENT WHERE X_DECLARATION_NUMBER = '" + decno
					+ "'";
			Statement stmt = conn.createStatement();
			{
				for (int i = 0; i <= 5; i++) {

					rs = stmt.executeQuery(query);
					while (rs.next()) {
						if (rs.getObject("X_DECLARATION_NUMBER") != null) {
							s3 = rs.getString("X_DECLARATION_NUMBER");
							break;
						}
						Thread.sleep(10000);
					}
				}
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s3;

	}

	public static String BooKingRequest_Declaration_No(String decno, String Environment)
			throws SQLException, ClassNotFoundException, InterruptedException {

		String s4 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sittest_r";
			password = "welcome123";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select srv.X_INS_SERIAL_NUMBER from siebel.s_srv_req srv,siebel.s_incident inc where srv.row_id = inc.X_INS_SR_ID and inc.X_DECLARATION_NUMBER = '"
					+ decno + "'";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			for (int i = 0; i <= 5; i++) {

				while (rs.next()) {

					if (rs.getObject("X_INS_SERIAL_NUMBER") != null) {
						s4 = rs.getString("X_INS_SERIAL_NUMBER");
						break;
					}
					Thread.sleep(10000);

				}
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s4;

	}

	public static String Booking_Request_Owner(String Booking_Req_No, String Environment)
			throws SQLException, ClassNotFoundException {

		String s4 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sittest_r";
			password = "welcome123";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select LOGIN From Siebel.s_user Where row_id in (select OWNER_EMP_ID from siebel.s_srv_req where x_ins_serial_number ='"
					+ Booking_Req_No + "')";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			s4 = rs.getString("LOGIN");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return s4;

	}

	public static String Booking_Request_Status(String Booking_Req_No, String Environment)
			throws SQLException, ClassNotFoundException, InterruptedException {

		String s5 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sittest_r";
			password = "welcome123";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select X_CLIENT_STATUS from siebel.s_srv_req where x_ins_serial_number ='" + Booking_Req_No
					+ "'";
			Statement stmt = conn.createStatement();

			for (int i = 0; i <= 10; i++) {
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					if (rs.getObject("X_CLIENT_STATUS") != null) {
						s5 = rs.getString("X_CLIENT_STATUS");
						continue;
					}
					Thread.sleep(10000);
				}

			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s5;

	}

	public static String Booking_Request_Sub_Status(String Booking_Req_No, String Environment)
			throws SQLException, ClassNotFoundException, InterruptedException {

		String s6 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sittest_r";
			password = "welcome123";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select X_INS_SUB_STATUS from siebel.s_srv_req where x_ins_serial_number ='" + Booking_Req_No
					+ "'";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				for (int i = 0; i <= 5; i++) {
					if (rs.getObject("X_INS_SUB_STATUS") != null) {
						// rs.getObject("X_INS_SUB_STATUS")!= null
						// rs.getObject("X_INS_SUB_STATUS") != null
						s6 = rs.getString("X_INS_SUB_STATUS");
						continue;
					}
					Thread.sleep(10000);

				}
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s6;

	}

	public static ResultSet Reschedule_Booking_Status(String Booking_Req_No, String Environment)
			throws SQLException, ClassNotFoundException {

		String s7 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sittest_r";
			password = "welcome123";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select X_CLIENT_STATUS from siebel.s_srv_req where x_ins_serial_number ='" + Booking_Req_No
					+ "'";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.close();
		return rs;

	}

	public static ResultSet Reschedule_Booking_Sub_Status(String Booking_Req_No, String Environment)
			throws SQLException, ClassNotFoundException {

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sittest_r";
			password = "welcome123";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select X_CLIENT_SUB_STATUS from siebel.s_srv_req where x_ins_serial_number ='"
					+ Booking_Req_No + "'";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;

	}

	public static ResultSet Demand_Notice_Sent_Status(String demandNoticeNo, String Environment)
			throws SQLException, ClassNotFoundException {

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select X_SEND_TO_M1 from  siebel.cx_dec_charges WHERE X_DN_NUMBER in (" + demandNoticeNo
					+ ")";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;

	}

	public static ResultSet Demand_Notice_Paid_Status(String demandNoticeNo, String Environment)
			throws SQLException, ClassNotFoundException {

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select DISTINCT X_RESPONSE_STATUS from  siebel.cx_decl_chg_det a,siebel.cx_dec_charges b where a.X_CHRG_ID = b.row_id and b.X_DN_NUMBER in ("
					+ demandNoticeNo + ")";
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;

	}

	public static String Siebel_Planning_Queue_Status(String Booking_Req_No, String Environment)
			throws SQLException, ClassNotFoundException, InterruptedException {

		String s10 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sittest_r";
			password = "welcome123";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select * From Siebel.s_user Where row_id in (select OWNER_EMP_ID from siebel.s_srv_req where x_ins_serial_number = '"
					+ Booking_Req_No + "')";
			Statement stmt = conn.createStatement();

			for (int i = 0; i <= 5; i++) {

				rs = stmt.executeQuery(query);
				while (rs.next()) {

					if (rs.getObject("LOGIN") != null) {
						s10 = rs.getString("LOGIN");
						continue;
					}
					Thread.sleep(10000);

				}
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s10;

	}

	public static String ServiceRequest_No(String decno, String Environment)
			throws SQLException, ClassNotFoundException, InterruptedException {

		String s11 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sittest_r";
			password = "welcome123";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select x_ins_serial_number from siebel.s_srv_req where X_INS_DOC_REF_NUM = '" + decno + "'";
			Statement stmt = conn.createStatement();

			for (int i = 0; i <= 5; i++) {

				rs = stmt.executeQuery(query);
				while (rs.next()) {
					if (rs.getObject("X_INS_SERIAL_NUMBER") != null) {
						s11 = rs.getString("X_INS_SERIAL_NUMBER");
						break;
					}
					Thread.sleep(10000);

				}
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s11;

	}

	public static String Inspection_Payment_status(String DimandNotice_No, String Environment)
			throws SQLException, ClassNotFoundException, InterruptedException {

		String s12 = null;

		if (Environment.equals("SIT1")) {

			url = "jdbc:oracle:thin:@cusvlsit2db01:1525:DPPDQA";
			userName = "sit_test";
			password = "newtest";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("SIT2")) {

			url = "jdbc:oracle:thin:@cusvlsit2dbs01.group.root.ad:1526:SIT2SBLEX";
			userName = "sittest_r";
			password = "welcome123";
			Class.forName("oracle.jdbc.driver.OracleDriver");

		} else if (Environment.equals("UAT1")) {
			url = "jdbc:oracle:thin:@utordbx1.dubaiworld.ae:1525:USBL1";
			userName = "ptuser";
			password = "pt1u$er";

		} else if (Environment.equals("UAT2")) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, userName, password);
			String query = "Select X_RESPONSE_STATUS from siebel.cx_dec_charges where x_dn_number ='" + DimandNotice_No
					+ "'";
			System.out.println(query);
			Statement stmt = conn.createStatement();
			for (int i = 0; i <= 10; i++) {
				rs = stmt.executeQuery(query);
				rs.next();
				if (rs.getObject("X_RESPONSE_STATUS") != null) {
					s12 = rs.getString("X_RESPONSE_STATUS");
					break;
				}
				Thread.sleep(50000);

			}
			conn.close();
			// X_RESPONSE_STATUS
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return s12;

	}

}
