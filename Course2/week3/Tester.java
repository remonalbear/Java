
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testUniqueIPs() {
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog2_log.txt");
        System.out.println("Number of unique IPs: "+l.countUniqueIPs());
    }
    
   public void testStatusHigherThanNum() {
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog1_log.txt");
        l.printAllHigherThanNum(400);
   } 
    
   public void testUniqueIPVisitsOnDay() {
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog2_log.txt");
        System.out.println("Number of uniques Ips visit that day:  "+l.uniqueIPVisitsOnDay("Sep 24").size());

   } 
    
   public void testCountUniqueIPsInRange() {
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog2_log.txt");
        System.out.println("Number of uniques Ips visit with status  200:299 :  "+l.countUniqueIPsInRange(200,299));

   } 
   
   public void testCountVisitsPerIP() {
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog1_log.txt");
        HashMap <String,Integer> counts=l.countVisitsPerIP();
        for (String ip : counts.keySet()){
            System.out.println("number of visits for: "+ip+" are : "+counts.get(ip));
        }
   }
   public void testMostNumberVisitsByIP(){
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog2_log.txt");
        HashMap <String,Integer> counts=l.countVisitsPerIP();
        System.out.println("most number visits by ip: "+l.mostNumberVisitsByIP(counts));
        
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog2_log.txt");
        HashMap <String,Integer> counts=l.countVisitsPerIP();
        ArrayList<String> IPs=l.iPsMostVisits(counts);
        for (String ip : IPs){
            System.out.println("IP: "+ip);
        }
        
    }
   
    public void testIPsForDays(){
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog3-short_log.txt");
        HashMap<String,ArrayList<String>> map =l.iPsForDays();
        for (String date : map.keySet()){
            System.out.println("Date: "+date);
            for(String ip : map.get(date)){
                System.out.println("IPS: ");
                System.out.println(ip);
            }
        }
        
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog2_log.txt");
        HashMap<String,ArrayList<String>> map =l.iPsForDays();
        System.out.println("Day with the most IP visits:" +l.dayWithMostIPVisits(map));
        
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("weblog2_log.txt");
        HashMap<String,ArrayList<String>> map =l.iPsForDays();
        System.out.println("IPs with the most visits:");
        for (String ip : l.iPsWithMostVisitsOnDay(map,"Sep 30")){
            System.out.println(ip);
        }
        
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer l=new LogAnalyzer();
        l.readFile("short-test_log.txt");
        l.printAll();
    }
}
