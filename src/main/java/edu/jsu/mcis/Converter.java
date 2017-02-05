package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;
import org.json.simple.*;
import org.json.simple.parser.*;


public class Converter {
    
    
    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
		
        JSONObject json_data = new JSONObject();
        JSONArray colHeaders = new JSONArray();
        JSONArray rowHeaders = new JSONArray();
        JSONArray data = new JSONArray();

        colHeaders.add("Total");
        colHeaders.add("Assignment 1");
        colHeaders.add("Assignment 2");
        colHeaders.add("Exam 1");

        json_data.put("colHeaders", colHeaders);
        json_data.put("rowHeaders", rowHeaders);
        json_data.put("data", data);

        CSVParser parser = new CSVParser();
        BufferedReader reader = new BufferedReader(new StringReader(csvString));
        
        try {
            String csvline = reader.readLine();
            while ((csvline = reader.readLine()) != null) {
                String[] parseString = parser.parseLine(csvline);
                rowHeaders.add(parseString[0]);
                JSONArray row = new JSONArray();
                row.add(new Long(parseString[1]));
                row.add(new Long(parseString[2]));
                row.add(new Long(parseString[3]));
                row.add(new Long(parseString[4]));
                data.add(row);
            }
            
        } catch (IOException e) {
        }
        
        return json_data.toString();
    }
	
	



	public static String jsonToCsv(String json_String) {
		
        JSONObject json = null;
       
        try { 
            JSONParser jParser = new JSONParser();
            json = (JSONObject) jParser.parse(json_String);
        }    

        catch (Exception e) {}

        String cString = "\"ID\"," + Converter.<String>joinA((JSONArray) json.get("colHeaders")) + "\n";
        
        JSONArray rowHeaders = (JSONArray) json.get("rowHeader");   
        JSONArray data = (JSONArray) json.get("Data");

        for (int a=0; a < rowHeaders.size(); a++) {
            cString = (cString + "\"" +(String)rowHeaders.get(a) + "\"," + Converter.<Long>joinA((JSONArray) data.get(a)) + "\n" );
        }

        return cString;

        }

        @SuppressWarnings("unchecked")
        private static <T> String joinA(JSONArray jarray) {
        
            String fline = "";
        
            for (int i = 0; i < jarray.size(); i++) {
                fline = (fline + "\"" + ((T) jarray.get(i)) + "\"");
                
                if (i < jarray.size() - 1) {
                    fline = fline + ",";
            }
        }

        return fline;
	}

}













