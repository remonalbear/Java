
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records=new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         WebLogParser parser=new WebLogParser();
         FileResource f=new FileResource(filename);
         for(String line : f.lines()){
             LogEntry l = parser.parseEntry(line);
             records.add(l);
         }
     }
     
     public int countUniqueIPs(){
        int count=0;
        ArrayList<String> IPs=new ArrayList<String>();
        for (LogEntry l: records ){
            if (!IPs.contains(l.getIpAddress())){
                count++;
                IPs.add(l.getIpAddress());
            }
        }
        return count;
     }
     
     public int countUniqueIPsInRange(int low , int high){
        int count=0;
        ArrayList<String> IPs=new ArrayList<String>();
        for (LogEntry l: records ){
            if (l.getStatusCode() >= low && l.getStatusCode() <= high )
                if (!IPs.contains(l.getIpAddress())){
                    count++;
                    IPs.add(l.getIpAddress());
                }
            }    
        return count;
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String date){
        ArrayList<String> IPs=new ArrayList<String>();
        
        for (LogEntry l: records ){
            if (l.getAccessTime().toString().substring(4,10).equals(date)){
                if (!IPs.contains(l.getIpAddress())){
                    IPs.add(l.getIpAddress());
                }
            }
            
        }
        return IPs;
     }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry l: records ){
            if (l.getStatusCode() > num){
                System.out.println(l);
            }
        }
    }
    
    public HashMap<String,Integer> countVisitsPerIP() {
        HashMap<String,Integer> counts=new HashMap<String,Integer>(); 
        for (LogEntry le : records) {
             if(!counts.containsKey(le.getIpAddress())){
                 counts.put(le.getIpAddress(),1);
             }
             else{
                  counts.put(le.getIpAddress(),counts.get(le.getIpAddress()) + 1);
                }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
         int maxCount=0;
         for (String ip : counts.keySet()) {
             if(counts.get(ip) > maxCount){
                maxCount=counts.get(ip);
             }
         }
         return maxCount;
     }
     
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map ){
        String mostDay="";
        for (String day: map.keySet()){
            if(! map.containsKey(mostDay)){
                mostDay=day;
            }
            
            if (map.get(day).size() > map.get(mostDay).size() ){
                mostDay=day;
            }
        }
        
        return mostDay;
     }
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map ,String day){
        HashMap<String,Integer> oldMap= countVisitsPerIP();
        HashMap<String,Integer> newMap= new HashMap<String,Integer>();
        
        for (String ip :map.get(day)){
               if(oldMap.containsKey(ip)){
                   newMap.put(ip,oldMap.get(ip));
               }
        }
 
        return iPsMostVisits(newMap);
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> dateMap=new HashMap<String,ArrayList<String>>();
         for (LogEntry le : records) {
             String date=le.getAccessTime().toString().substring(4,10);
             if (dateMap.containsKey(date)){
                 dateMap.get(date).add(le.getIpAddress());
             }
             else 
             {
                 ArrayList<String> IPs=new ArrayList<String>();
                 IPs.add(le.getIpAddress());
                 dateMap.put(date,IPs);
                
             }
         }
         return dateMap;
     }
     
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String> IPs=new ArrayList<String>();
         int maxCount=mostNumberVisitsByIP(counts);
         System.out.println("Max count: "+ maxCount);
         for (String ip : counts.keySet()) {
             System.out.println("IP : "+ip+" has count: "+counts.get(ip));
             if(counts.get(ip) == maxCount){
                 IPs.add(ip);
             }
         }
         return IPs;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
