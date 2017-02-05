package keywords;

import edu.jsu.mcis.*;
import org.json.simple.parser.*;

public class ConverterKeywords {
	
	private Converter converting;
	
	public ConverterKeywords() {
		converting = new Converter();
	}
    
    public String convertToJson(String csv) {
        return converting.csvToJson(csv);
    }
    
    public String convertToCsv(String json) {
        return converting.jsonToCsv(json);
    }
    
    public boolean jsonStringsAreEqual(String w, String x) {
        JSONParser parser = new JSONParser();
		try{
			Object wObj = parser.parse(w);
			Object xObj = parser.parse(x);
			return wObj.equals(xObj);
		}
		
		catch(ParseException e) {
			return false;
		}
    }
}