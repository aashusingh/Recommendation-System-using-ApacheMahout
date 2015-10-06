package com.assignment2.Dataconvert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MovieDataConvert {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("data/u.data"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("data/movies.csv"));
		
		String line;
		
		while((line = br.readLine()) != null) {
			String[] values = line.split("\\t", -1);
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