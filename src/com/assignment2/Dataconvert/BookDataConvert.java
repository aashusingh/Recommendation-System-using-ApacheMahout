package com.assignment2.Dataconvert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BookDataConvert {

	/**
	 * cat u.data | cut -f1,2,3 | tr "\\t" ","
	 * @throws IOException 
	 * 
	 */
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("data/BX-Book-Ratings.csv"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/books1.csv"));
		
		String line;
		
		while((line = br.readLine()) != null) {
			String withoutQuotes_line1 = line.replaceAll("\"", "");
			String[] values = withoutQuotes_line1.split(";", -1);
			try{
			bw.write(Long.parseLong(values[0]) + "," + Long.parseLong(values[1]) + "," + Long.parseLong(values[2]) + "\n");
			}catch(Exception e){
				continue;
			}
		}
		
		br.close();
		bw.close();

	}

}