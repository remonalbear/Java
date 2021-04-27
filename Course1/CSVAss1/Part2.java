
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Part2 {
    
    
    private CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldest=null;
        for(CSVRecord record:parser){
            if(Double.parseDouble(record.get("TemperatureF"))<-273 ){
            continue;
            }
            if(coldest==null){
                
                coldest =record;
            }
            else
            {
                double tempVal=Double.parseDouble(record.get("TemperatureF"));
                double coldestVal=Double.parseDouble(coldest.get("TemperatureF"));
                if(tempVal<coldestVal ){
                    
                    coldest=record;
           
                }
            }
        }
        
       return coldest;
    }
    
    private String fileWithColdestTemperature(){
        String fileName=null;
        DirectoryResource dr=new DirectoryResource();
        CSVRecord coldest=null;
        for(File f : dr.selectedFiles()){
            FileResource fr=new FileResource(f) ;
            CSVRecord temp=coldestHourInFile(fr.getCSVParser());
            if(coldest == null ){
                
                coldest=temp;
                fileName=f.getName();
            
            }
            else{
            
                double tempVal=Double.parseDouble(temp.get("TemperatureF"));
                double coldestVal=Double.parseDouble(coldest.get("TemperatureF"));
                if(tempVal<coldestVal ){
                    
                    coldest=temp;
                    fileName=f.getName();
           
                }
            }
        }
        
        return fileName;
    }
    
    private CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowest=null;
        for(CSVRecord record:parser){
            if(record.get("Humidity").equals("N/A")){
            continue;
            }
            if(lowest==null){
                
                lowest =record;
            }
            else
            {
                double tempVal=Double.parseDouble(record.get("Humidity"));
                double lowestVal=Double.parseDouble(lowest.get("Humidity"));
                if(tempVal<lowestVal ){
                    
                    lowest=record;
           
                }
            }
        }
        
       return lowest;
    }
    
    private String lowestHumidityInManyFiles (){
        String fileName=null;
        DirectoryResource dr=new DirectoryResource();
        CSVRecord lowest=null;
        for(File f : dr.selectedFiles()){
            FileResource fr=new FileResource(f) ;
            CSVRecord temp=lowestHumidityInFile(fr.getCSVParser());
            if(lowest == null ){
                
                lowest=temp;
                fileName=f.getName();
            
            }
            else{
            
                double tempVal=Double.parseDouble(temp.get("Humidity"));
                double lowestVal=Double.parseDouble(lowest.get("Humidity"));
                if(tempVal<lowestVal ){
                    
                    lowest=temp;
                    fileName=f.getName();
           
                }
            }
        }
        
        return fileName;
    }
    
    private double averageTemperatureInFile (CSVParser parser){
        double sum=0.0;
        int count=0;
        for(CSVRecord record:parser){
            if(Double.parseDouble(record.get("TemperatureF"))<-273 ){
            continue;
            }
            sum+=Double.parseDouble(record.get("TemperatureF"));
            count++;
        }
        
       return sum/count;
    }
    
    private double averageTemperatureWithHighHumidityInFile(CSVParser parser ,int value){
        double sum=0.0;
        int count=0;
        for(CSVRecord record:parser){
            if(Double.parseDouble(record.get("TemperatureF"))<-273 ){
                continue;
            }
            if(Double.parseDouble(record.get("Humidity")) >= value){
                sum+=Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
            
        }
        
       return sum/count;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        System.out.println("--------------------------------");
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        double avg=averageTemperatureWithHighHumidityInFile(parser,80);
        if(avg>0){
            System.out.println("Average Temp when high Humidity is "+avg);
        }
        else{
            System.out.println("No temperatures with that humidity");
        }
    }
    
    public void testAverageTemperatureInFile(){
        System.out.println("--------------------------------");
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        double avg=averageTemperatureInFile(parser);
        System.out.println("Average temperate is "+avg);
    }
    
    public void testLowestHumidity(){
        System.out.println("--------------------------------");
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord record=lowestHumidityInFile(parser);
        System.out.println("lowest humidity was "+record.get("Humidity")+"at " +record.get("DateUTC"));
    }
    
    public void testLowestHumidityInManyFiles(){
    System.out.println("--------------------------------");
    String fileName=lowestHumidityInManyFiles ();
    FileResource fr=new FileResource("nc_weather/2013/"+fileName);
    CSVParser parser=fr.getCSVParser();
    CSVRecord record=lowestHumidityInFile(parser);
    System.out.println("Lowest Humidity was"+record.get("Humidity")+"at "+record.get("DateUTC"));
}
    
    public void testColdestHour(){
        System.out.println("--------------------------------");
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord record=coldestHourInFile(parser);
        System.out.println(record.get("TemperatureF"));
    }
    
    public void testColdestFile(){
    System.out.println("--------------------------------");
    String fileName=fileWithColdestTemperature();
    System.out.println("Coldest day was in file "+ fileName);
    FileResource fr=new FileResource("nc_weather/2013 git reset --soft HEAD~1/"+fileName);
    CSVParser parser=fr.getCSVParser();
    for(CSVRecord record : parser){
        System.out.println("temp:" +record.get("TemperatureF"));
    }
    parser=fr.getCSVParser();
    CSVRecord record=coldestHourInFile(parser);
    System.out.println("Coldest temprature on this file "+record.get("TemperatureF"));
}

}
