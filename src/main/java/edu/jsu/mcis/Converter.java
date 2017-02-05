package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Converter {
    
    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
   
        JSONObject ctoJ = new JSONObject();
       
        JSONArray data = new JSONArray();
        JSONArray colHeaders = new JSONArray();
        JSONArray rowHeaders = new JSONArray();
        
        colHeaders.add("Total");
        colHeaders.add("Assignment 1");
        colHeaders.add("Assignment 2");
        colHeaders.add("Exam 1");

        ctoJ.put("colHeaders", colHeaders);
        ctoJ.put("rowHeaders", rowHeaders);
        ctoJ.put("data", data);

        CSVParser parser = new CSVParser();
        BufferedReader reader = new BufferedReader(new StringReader(csvString));
        
        try {

            String line = reader.readLine();

            while ((line = reader.readLine()) != null){

                String[] record = parser.parseLine(line);
                rowHeaders.add(record[0]);
                
                JSONArray row = new JSONArray();
                row.add(new Long(record[1]));
                row.add(new Long(record[2]));
                row.add(new Long(record[3]));
                row.add(new Long(record[4]));
                data.add(row);
          }
            
        } 
        
        catch (IOException e) {
        }
        
        return ctoJ.toString();

    }
  
    
    public static String jsonToCsv(String jsonString) 
    {
        JSONObject jtoC = null;

        try {
            JSONParser jParser = new JSONParser();
            jtoC = (JSONObject) jParser.parse(jsonString);
        } 
        
        catch (Exception e) {}
        
       String csvString = "\"ID\"," + Converter.<String>joinArray((JSONArray) jtoC.get("colHeaders")) + "\n";

       JSONArray rowHeaders = (JSONArray) jtoC.get("rowHeaders");
       JSONArray data = (JSONArray) jtoC.get("data");        

    for (int n = 0; n < rowHeaders.size(); n++) {
           
            csvString += ( "\""+ (String) rowHeaders.get(n) + "\"," + Converter.<Long>joinArray((JSONArray) data.get(n)) + "\n");
       
        }

        return csvString;
    }


     @SuppressWarnings("unchecked")

     
    private static <T> String joinArray(JSONArray jsarray)
    {
        
        String fLine = "";
        
        for (int n = 0; n < jsarray.size(); n++)
     {
           
            fLine = (fLine + "\"" + ((T) jsarray.get(n)) + "\"");
                if (n < jsarray.size() - 1) {
               
                fLine = fLine + ",";
               }
        }

        return fLine;
    }
}