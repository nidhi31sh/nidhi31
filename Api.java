package restapi;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.AssertJUnit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class Api {

    private FileOutputStream data1;
    private XSSFWorkbook wb;

    //to check response code of API
    public void testresponse()
   
    {
       
    Response resp=RestAssured.get("http://dev.admin.letsmd.com/api/v1/decision-engine");
    int code=resp.getStatusCode();
    System.out.println("status code is "+code);
    AssertJUnit.assertEquals(code, 200);
    AssertJUnit.assertEquals(code, 500);
   
   
   
    }
   
    
	 //To read data from excel sheet
	 @Test
    public void readDataFile() throws Exception{
		
	 
    	File src=new File("C:\\TestExcelData\\leadcheckdata.xlsx");
     	
     	
     	FileInputStream fis=new FileInputStream(src);
     	XSSFWorkbook wb1=new XSSFWorkbook(fis);
     	
     	 XSSFSheet sheet1= wb1.getSheetAt(0);
     	
     	//int data0=(int) sheet1.getRow(1).getCell(0).getNumericCellValue();
     	
     	//System.out.println("Data from excel is "+data0);
     	
     	//int rowcount=sheet1.getLastRowNum();
     
     	//System.out.println("last row is" +rowcount+1);
     	
     	 
     	 for(int i=1;i<200;i++)
     		 
     	 {
     		int data0=(int)sheet1.getRow(i).getCell(0).getNumericCellValue(); 
     		
     		System.out.println(data0);
     		
     		api(data0+"");
     		
     	 }
	
		
	}
	 
    //To save response in txt file
     public void api(String s) throws IOException{
           String url="https://instant.letsmd.com/api/v1/decision-engine/"+s;
           Response resp=RestAssured.get(url);
           String data=resp.asString();
            System.out.println("data  "+data);
            System.out.println("Response time "+resp.getTime());
           
            
            
                 
                File f = new File("C:\\Users\\User\\jsonoutput\\result.txt");
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
                    CharSequence line ="lead id is " +s+data;
                   
                    
                  
                    System.getProperty("line.separator");
                    System.out.println("data  "+s);
                     bw.newLine();
                     bw.write(" ");
					bw.append(line);
                    bw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    
                    
                    
                    
                   
                }
                 
          
     
    }
     
     
    
 	
//To write json response in excel
public void getIdSaveResponse (String data) throws Exception {
	

     

	  
      
            		File file = new File("C:\\Users\\User\\ExcelOutPutJson\\result.xlsx");
            		
            		//XSSFWorkbook wk = new XSSFWorkbook(fs);
            		Workbook wk = WorkbookFactory.create(file);

            		
            		
            		
            		
            		
            		Sheet sheet = wk.getSheetAt(0);
            		

            		int rowId = 0;
					Row row = sheet.getRow(rowId);		
            		//String data = null;
					//String response = data;
					
					Cell cell = row.getCell(1);
					
					if (cell == null)
				        cell = row.createCell(2);
					
					Date s = null;
					cell.setCellValue(s);
					
					
					
					
					
					FileOutputStream fileOut = new FileOutputStream("existing-spreadsheet.xlsx");
				    
					
					
					wk.write(fileOut);
				    fileOut.close();

				    // Closing the workbook
				    wk.close();
					
            	}
                
                
           
         
     
   
}



