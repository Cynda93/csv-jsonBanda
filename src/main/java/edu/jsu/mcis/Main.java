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
        StringBuffer csvContents = new StringBuffer();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("grades.csv")))) {
            String line;
            while((line = reader.readLine()) != null) {
                csvContents.append(line + '\n');
            }
        }

        catch(IOException e) {}

        String testCsv = csvContents.toString();
        
        StringBuilder jsonContents = new StringBuilder();
        
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("grades.json")))) {
            String line;
            while((line = reader.readLine()) != null) {
                jsonContents.append(line + '\n');
            }
        }
        
        catch(IOException e) {}
        
        String testJson = jsonContents.toString();
        json = Converter.csvToJson(testCsv);
        System.out.println(json);
        System.out.println("\n----------------\n");
        String csv = Converter.jsonToCsv(testJson);
        System.out.println(csv);
    }
}