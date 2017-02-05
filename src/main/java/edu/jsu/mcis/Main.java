package edu.jsu.mcis;

import java.io.*;

public class Main {
    
    public static String json;
    public static String csv;
    
    public static void main(String[] args) {
        runConversions();
    }
    
    public static void runConversions(){
         ClassLoader loader = ClassLoader.getSystemClassLoader();
         StringBuilder csvMsg = new StringBuilder();
         
         try(BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("grades.csv")))) {
             String cline;
             
             while((cline = reader.readLine()) != null) {
                 csvMsg.append(cline).append('\n');
             }
         }
         
         catch(IOException e) {}
         
         String testCsv = csvMsg.toString();
         StringBuilder jsonContents = new StringBuilder();
         
         try(BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("grades.json")))) {
            String jline;
            
            while((jline = reader.readLine()) != null) {
                jsonContents.append(jline).append('\n');
            }
        }
        
         catch(IOException e) {}
         
         String testJson = jsonContents.toString();
         json = Converter.csvToJson(testCsv);
         
         System.out.println(json);
         System.out.println("\n----------------\n");
         
         csv = Converter.jsonToCsv(testJson);
         System.out.println(csv);
    }
}