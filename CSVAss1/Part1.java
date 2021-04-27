
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {

    
    private String getCountryInfo(CSVParser parser,String country){
        String countryInfo="NOT FOUND";
            for(CSVRecord record:parser){
            String countryName =record.get("Country");
            if(countryName.equals(country)){
                countryInfo=countryName+":"+record.get("Exports");
            }
        }
        return countryInfo;
    } 

    private void listExportsTwoProducts(CSVParser parser,String product1,String product2){
        for(CSVRecord record:parser){
            String exports =record.get("Exports");
            if(exports.contains(product1) && exports.contains(product2) ){
                String country=record.get("Country");
                System.out.println("Country:"+country);
             }
        }
    }
    
     private int numberOfExports(CSVParser parser,String item){
         int count=0;   
         for(CSVRecord record:parser){
                String exports =record.get("Exports");
                if(exports.contains(item) ){
                    count++;
                 }
            }
         return count;
        }
        
     private void bigExporters(CSVParser parser,String amount){  
         for(CSVRecord record:parser){
                String value =record.get("Value (dollars)");
                if(value.length()>amount.length()){
                    System.out.println(record.get("Country")+value);
                 }
            }
        }    

    public void tester(){
        System.out.println("--------------------------------");
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        System.out.println("export cotton && flowers");
        listExportsTwoProducts(parser,"cotton","flowers");
        parser=fr.getCSVParser();
        System.out.println("#countries exports cocoa: "+numberOfExports(parser,"cocoa"));
        parser=fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
