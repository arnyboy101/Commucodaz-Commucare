import java.io.*;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.poi.xssf.usermodel.*;



public class Rishi_Integ 
{
	static String disease[];
	static FileInputStream file;
	static XSSFWorkbook workbook;
	public static int[] backEnd(String symptoms[])throws IOException
	{
		file= new FileInputStream(new File("Disease_Database.xlsx"));
		workbook= new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int length = sheet.getLastRowNum()+1;
		disease = new String[length];
		int weightage[]= new int[length];
		int test=1;
		for(int i=0;i<length;i++)
		{
			weightage[i]=0;
			disease[i]=sheet.getRow(i).getCell(0).getStringCellValue();
		}
		for(int i=0; i<10;i++)
		{
			try
			{
				int index = checkSymptom(symptoms[i],1);
				
					for(int j=1;j<=29;j++)
					{
						test++;
						weightage[checkSymptom(workbook.getSheetAt(1).getRow(index).getCell(j).getStringCellValue(),0)]++;
					}
			}
				
		
			catch(Exception e)
			{
				
			}
		}
		
		int bestIndex[] = {0,1,2,3,4,5};
		int max = 0,index;
		for (int j = 0; j < 5; j++) {
	        max = weightage[0];
	        index = 0;
	        for (int i = 1; i < weightage.length; i++) {
	            if (max < weightage[i]) {
	                max = weightage[i];
	                
	                index = i;
	            }
	            bestIndex[j] = index;
	            weightage[index] = Integer.MIN_VALUE;
	        }
		}
		int n = bestIndex.length;  
        int temp = 0;  
         for(int i=0; i < n; i++){  
                 for(int j=1; j < (n-i); j++){  
                          if(weightage[bestIndex[j-1] ]> weightage[bestIndex[j] ]){  
                                 //swap elements  
                                 temp = bestIndex[j-1];  
                                 bestIndex[j-1] = bestIndex[j];  
                                 bestIndex[j] = temp;  
                         }  
                          
                 }  
         }  
		
		workbook.close();
		return bestIndex;
	}
	public static int checkSymptom(String symptom, int ch )throws Exception
	{
		//ch=1 for symptom to disease ch=0 for disease to symptom
			
		
		 file = new FileInputStream(new File("Disease_Database.xlsx"));
		
		
			workbook= new XSSFWorkbook(file);
		 
          XSSFSheet sheet = workbook.getSheetAt(ch);
          
          Row row;
          int i;
          for( i=1;i<=sheet.getLastRowNum();i++)
          {
        	  row = sheet.getRow(i);
          
        	  Cell c1= row.getCell(0);
        	  if(c1.getStringCellValue().equalsIgnoreCase(symptom))
        		  break;
          }
          workbook.close();
          return i;
		}
	public static void main(String args[])throws Exception
	{
		String s[] = {"Feeling hopeless", "Irritable mood", "Tremor","Blackout","Weepiness"};
		int n[] = backEnd(s);
		for(int j = 0; j<5; j++)
			System.out.println(n[j]+1);
	}
	}