package utilities;

import java.util.HashMap;

public class ReadData_DubaiTrade {
	
	
	public HashMap<String, String> HashMap_Data_Inspection_DubaiTrade(int j , String Test_Data_Path , String Sheet) throws Exception {
		ExcelApiTest eat = new ExcelApiTest(Test_Data_Path);

		HashMap<String, String> hmap = new HashMap<String, String>();

		//Test Case Variables
		hmap.put("Execute", eat.getCellData(Sheet, "Execute", j));
		hmap.put("TestCaseNo", eat.getCellData(Sheet, "TestCaseNo", j));
		hmap.put("TestCaseDescription", eat.getCellData(Sheet, "TestCaseDescription", j));
		hmap.put("IterationNo", eat.getCellData(Sheet, "IterationNo", j));
	
		//Flag Variables
		hmap.put("Sieze_Flag", eat.getCellData(Sheet, "Sieze_Flag", j));
		hmap.put("Auto_FollowUp", eat.getCellData(Sheet, "Auto_FollowUp", j));
		hmap.put("Seal_Flag", eat.getCellData(Sheet, "Seal_Flag", j));
		hmap.put("Payment_Mode", eat.getCellData(Sheet, "Payment_Mode", j));
		hmap.put("Multiple_Declaration_Flag", eat.getCellData(Sheet, "Multiple_Declaration_Flag", j));
		hmap.put("Suspend_Flag", eat.getCellData(Sheet, "Suspend_Flag", j));
		
		//Common Variables		
		hmap.put("Declaration_No", eat.getCellData(Sheet, "Declaration_No", j));
		hmap.put("Booking_Ref_No", eat.getCellData(Sheet, "Booking_Ref_No", j));
		hmap.put("Seal_Quantity", eat.getCellData(Sheet, "Seal_Quantity", j));
		hmap.put("Virtual_Corridor_Type", eat.getCellData(Sheet, "Virtual_Corridor_Type", j));
		hmap.put("Seal_Number", eat.getCellData(Sheet, "Seal_Number", j));
		hmap.put("Seal_Remarks", eat.getCellData(Sheet, "Seal_Remarks", j));
		hmap.put("Seal_Truck_Number", eat.getCellData(Sheet, "Seal_Truck_Number", j));
		hmap.put("Contact_Email", eat.getCellData(Sheet, "Contact_Email", j));
		hmap.put("Contact_Name", eat.getCellData(Sheet, "Contact_Name", j));
		hmap.put("Representative_Name", eat.getCellData(Sheet, "Representative_Name", j));
		hmap.put("Document_Reference", eat.getCellData(Sheet, "Document_Reference", j));
		hmap.put("Document_Reference_Number", eat.getCellData(Sheet, "Document_Reference_Number", j));
		hmap.put("Location", eat.getCellData(Sheet, "Location", j));
		hmap.put("Transfer_Center", eat.getCellData(Sheet, "Transfer_Center", j));
		hmap.put("Transfer_Section", eat.getCellData(Sheet, "Transfer_Section", j));
		hmap.put("Contact_Number", eat.getCellData(Sheet, "Contact_Number", j));
		hmap.put("Mobile_Number", eat.getCellData(Sheet, "Mobile_Number", j));
		hmap.put("Inspection_Centre", eat.getCellData(Sheet, "Inspection_Centre", j));
		hmap.put("Inspection_Section", eat.getCellData(Sheet, "Inspection_Section", j));
		hmap.put("Preferred_Date_1", eat.getCellData(Sheet, "Preferred_Date_1", j));
		hmap.put("Preferred_Slot_1", eat.getCellData(Sheet, "Preferred_Slot_1", j));
		hmap.put("Additional_Preferred_Slot", eat.getCellData(Sheet, "Additional_Preferred_Slot", j));
		hmap.put("Business_Code", eat.getCellData(Sheet, "Business_Code", j));
		hmap.put("Credit_Account", eat.getCellData(Sheet, "Credit_Account", j));
		hmap.put("CreditCard_No", eat.getCellData(Sheet, "CreditCard_No", j));
		hmap.put("Expiry_Month", eat.getCellData(Sheet, "Expiry_Month", j));
		hmap.put("Expiry_Year", eat.getCellData(Sheet, "Expiry_Year", j));
		hmap.put("CVV", eat.getCellData(Sheet, "CVV", j));
		
		
		//Siebel Base & eServices Variables
		hmap.put("Shift", eat.getCellData(Sheet, "Shift", j));
		hmap.put("Section_Centre", eat.getCellData(Sheet, "Section_Centre", j));
		hmap.put("Detained_Receipt_Number", eat.getCellData(Sheet, "Detained_Receipt_Number", j));
		hmap.put("Method_Of_Shipment", eat.getCellData(Sheet, "Method_Of_Shipment", j));
		hmap.put("Method_Of_Concealing", eat.getCellData(Sheet, "Method_Of_Concealing", j));
		hmap.put("Pattern_of_Defraud", eat.getCellData(Sheet, "Pattern_of_Defraud", j));
		hmap.put("Detection_Aid", eat.getCellData(Sheet, "Detection_Aid", j));
		hmap.put("Method_Of_Detection", eat.getCellData(Sheet, "Method_Of_Detection", j));
		hmap.put("Inspection_Source", eat.getCellData(Sheet, "Inspection_Source", j));
		hmap.put("Seizure_Information_Source", eat.getCellData(Sheet, "Seizure_Information_Source", j));
		hmap.put("Upload_File", eat.getCellData(Sheet, "Upload_File", j));
		hmap.put("Document_Type", eat.getCellData(Sheet, "Document_Type", j));
		hmap.put("Search_Keyword", eat.getCellData(Sheet, "Search_Keyword", j));
		hmap.put("Involvement_Type", eat.getCellData(Sheet, "Involvement_Type", j));
		hmap.put("Full_Name", eat.getCellData(Sheet, "Full_Name", j));
		hmap.put("Type_Of_Visa", eat.getCellData(Sheet, "Type_Of_Visa", j));
		hmap.put("No_Of_Previous_Offences", eat.getCellData(Sheet, "No_Of_Previous_Offences", j));
		hmap.put("Statements", eat.getCellData(Sheet, "Statements", j));
		hmap.put("Report_Initiator_Name", eat.getCellData(Sheet, "Report_Initiator_Name", j));
		hmap.put("Employee_Number", eat.getCellData(Sheet, "Employee_Number", j));
		hmap.put("Role_In_Seizure_Report", eat.getCellData(Sheet, "Role_In_Seizure_Report", j));
		hmap.put("Witness_Statement", eat.getCellData(Sheet, "Witness_Statement", j));
		hmap.put("Fact_Details", eat.getCellData(Sheet, "Fact_Details", j));
		hmap.put("OutCome_Reason", eat.getCellData(Sheet, "OutCome_Reason", j));
		hmap.put("Outbound_Declaration_Status", eat.getCellData(Sheet, "Outbound_Declaration_Status", j));		
		hmap.put("AOR_Results", eat.getCellData(Sheet, "AOR_Results", j));
		hmap.put("AOR_Notes", eat.getCellData(Sheet, "AOR_Notes", j));
		hmap.put("Means_of_Transport", eat.getCellData(Sheet, "Means_of_Transport", j));
		hmap.put("Number_Transport", eat.getCellData(Sheet, "Number_Transport", j));
		hmap.put("Flight_#", eat.getCellData(Sheet, "Flight_#", j));
		hmap.put("Nature_Of_Transport", eat.getCellData(Sheet, "Nature_Of_Transport", j));
		hmap.put("Nature_of_Customs_Center", eat.getCellData(Sheet, "Nature_of_Customs_Center", j));
		
		
		
		return hmap;
	}

}
