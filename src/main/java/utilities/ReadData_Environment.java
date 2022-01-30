package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData_Environment {

	private FileInputStream file;
	private XSSFWorkbook Workbook;
	private XSSFSheet sheet;
	
	
	public HashMap<String, String> HashMap_Data_Environment_Details(String Environment , String Test_Data_Path) throws IOException{
		
		HashMap<String, String> hmap = new HashMap<String, String>();
		

		file = new FileInputStream(Test_Data_Path);
		Workbook = new XSSFWorkbook(file);
		sheet = Workbook.getSheet("Environment_Details");
		XSSFSheet Environment_Details = sheet;

		hmap.put("Browser", Environment_Details.getRow(1).getCell(1).getRichStringCellValue().getString());
		hmap.put("Environment", Environment_Details.getRow(2).getCell(1).getRichStringCellValue().getString());
		hmap.put("DubaiTrade_URL", Environment_Details.getRow(4).getCell(1).getRichStringCellValue().getString());
		hmap.put("DubaiTrade_UserID", Environment_Details.getRow(5).getCell(1).getRichStringCellValue().getString());
		hmap.put("DubaiTrade_Password", Environment_Details.getRow(6).getCell(1).getRichStringCellValue().getString());
		hmap.put("SiebelBase_URL", Environment_Details.getRow(8).getCell(1).getRichStringCellValue().getString());
		hmap.put("Planner_UserID", Environment_Details.getRow(9).getCell(1).getRichStringCellValue().getString());
		hmap.put("Planner_Password", Environment_Details.getRow(10).getCell(1).getRichStringCellValue().getString());
		hmap.put("SiebeleService_URL", Environment_Details.getRow(12).getCell(1).getRichStringCellValue().getString());
		hmap.put("Inspector_UserID", Environment_Details.getRow(13).getCell(1).getRichStringCellValue().getString());
		hmap.put("Inspector_Password", Environment_Details.getRow(14).getCell(1).getRichStringCellValue().getString());
		hmap.put("Bureau_UserID", Environment_Details.getRow(16).getCell(1).getRichStringCellValue().getString());
		hmap.put("Bureau_Password", Environment_Details.getRow(17).getCell(1).getRichStringCellValue().getString());
		hmap.put("ED_UserID", Environment_Details.getRow(19).getCell(1).getRichStringCellValue().getString());
		hmap.put("ED_Password", Environment_Details.getRow(20).getCell(1).getRichStringCellValue().getString());
		hmap.put("Inspector_UserID_Hatta", Environment_Details.getRow(22).getCell(1).getRichStringCellValue().getString());
		hmap.put("Inspector_Password_Hatta", Environment_Details.getRow(23).getCell(1).getRichStringCellValue().getString());
		hmap.put("E-Revenue_URL", Environment_Details.getRow(25).getCell(1).getRichStringCellValue().getString());
		hmap.put("VC_Entry_Inspector_UserID", Environment_Details.getRow(27).getCell(1).getRichStringCellValue().getString());
		hmap.put("VC_Entry_Inspector_Password", Environment_Details.getRow(28).getCell(1).getRichStringCellValue().getString());
		hmap.put("VC_Exit_Inspector_UserID", Environment_Details.getRow(30).getCell(1).getRichStringCellValue().getString());
		hmap.put("VC_Exit_Inspector_Password", Environment_Details.getRow(31).getCell(1).getRichStringCellValue().getString());
		return hmap;
		
		
	}


	
}
