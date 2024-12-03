package com.hanshudan.general;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutput {
    public void filewrite(String filename, String result) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename, true);
			
			byte[] buffer1 = result.getBytes();
			fos.write(buffer1);
			
			String str = "\r\n";
			byte[] buffer = str.getBytes();
			fos.write(buffer);
			
			fos.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}
	
	public void filewrite(String filename, double result) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename, true);
			
			String str1 = String.valueOf(result);
			byte[] buffer1 = str1.getBytes();
			fos.write(buffer1);
			
			String str = "\r\n";
			byte[] buffer = str.getBytes();
			fos.write(buffer);
			
			fos.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}
	
	public void filewriteContinuous(String filename, String result) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename, true);
			
			byte[] buffer1 = result.getBytes();
			fos.write(buffer1);
			
			String str = "";
			byte[] buffer = str.getBytes();
			fos.write(buffer);
			
			fos.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		} catch (IOException e1) {
			System.out.println(e1);
		}
	}
	
	public void writeCSV(double longestlinklength, double totallinklength,double numberoflinks, double numberofEDFA1, double numberofEDFA2, double NLI, double Pase, String nodepairname, String nodelistname, String filename) {  
		FileOutputStream fos = null;
			
		try {
			fos = new FileOutputStream(filename, true);
			
			String str1 = String.valueOf(longestlinklength);
			byte[] buffer1 = str1.getBytes();
			fos.write(buffer1);
				
			String str2 = ",";
			byte[] buffer2 = str2.getBytes();
			fos.write(buffer2);
			
			String str3 = String.valueOf(totallinklength);
			byte[] buffer3 = str3.getBytes();
			fos.write(buffer3);
				
			String str4 = ",";
			byte[] buffer4 = str4.getBytes();
			fos.write(buffer4);
			
			String str5 = String.valueOf(numberoflinks);
			byte[] buffer5 = str5.getBytes();
			fos.write(buffer5);
				
			String str6 = ",";
			byte[] buffer6 = str6.getBytes();
			fos.write(buffer6);
			
			String str7 = String.valueOf(numberofEDFA1);
			byte[] buffer7 = str7.getBytes();
			fos.write(buffer7);
				
			String str8 = ",";
			byte[] buffer8 = str8.getBytes();
			fos.write(buffer8);
			
			String str9 = String.valueOf(numberofEDFA2);
			byte[] buffer9 = str9.getBytes();
			fos.write(buffer9);
						
			String str10 = ",";
			byte[] buffer10 = str10.getBytes();
			fos.write(buffer10);
			
			String str11 = String.valueOf(NLI);
			byte[] buffer11 = str11.getBytes();
			fos.write(buffer11);
			
			String str12 = ",";
			byte[] buffer12 = str12.getBytes();
			fos.write(buffer12);
			
			String str13 = String.valueOf(Pase);
			byte[] buffer13 = str13.getBytes();
			fos.write(buffer13);
			
			String str14 = ",";
			byte[] buffer14 = str14.getBytes();
			fos.write(buffer14);
			
			byte[] buffer15 = nodepairname.getBytes();
			fos.write(buffer15);
			
			String str16 = ",";
			byte[] buffer16 = str16.getBytes();
			fos.write(buffer16);
			
			byte[] buffer17 = nodelistname.getBytes();
			fos.write(buffer17);
			
			String str18 = ",";
			byte[] buffer18 = str18.getBytes();
			fos.write(buffer18);
			
//			String str19 = String.valueOf(routeOSNR);
//			byte[] buffer19 = str19.getBytes();
//			fos.write(buffer19);

			
			String str = "\r\n";
			byte[] buffer = str.getBytes();
			fos.write(buffer);
			
			fos.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		} catch (IOException e1) {
			System.out.println(e1);
		}
    }  
	
	public void writeCSV(double margin, double totallinklength, String filename) {  
		FileOutputStream fos = null;
			
		try {
			fos = new FileOutputStream(filename, true);
			
			String str1 = String.valueOf(margin);
			byte[] buffer1 = str1.getBytes();
			fos.write(buffer1);
				
			String str2 = ",";
			byte[] buffer2 = str2.getBytes();
			fos.write(buffer2);
			
			String str3 = String.valueOf(totallinklength);
			byte[] buffer3 = str3.getBytes();
			fos.write(buffer3);
				
			String str4 = ",";
			byte[] buffer4 = str4.getBytes();
			fos.write(buffer4);
			
			String str = "\r\n";
			byte[] buffer = str.getBytes();
			fos.write(buffer);
			
			fos.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		} catch (IOException e1) {
			System.out.println(e1);
		}
    }  
	public void writeCSV(double margin,String filename) {  
		FileOutputStream fos = null;
			
		try {
			fos = new FileOutputStream(filename, true);
			
			String str1 = String.valueOf(margin);
			byte[] buffer1 = str1.getBytes();
			fos.write(buffer1);
				
			String str2 = ",";
			byte[] buffer2 = str2.getBytes();
			fos.write(buffer2);
			
			fos.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		} catch (IOException e1) {
			System.out.println(e1);
		}
    }  
	public void writeCSV(String filename) {  
		FileOutputStream fos = null;
			
		try {
			fos = new FileOutputStream(filename, true);
			
			String str = "\r\n";
			byte[] buffer = str.getBytes();
			fos.write(buffer);
			
			fos.close();
		} catch (FileNotFoundException e1) {
			System.out.println(e1);
		} catch (IOException e1) {
			System.out.println(e1);
		}
    }  
}
