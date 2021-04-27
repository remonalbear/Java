
/**
 * Write a description of BirthsProject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class BirthsProject {
      public void totalBirths(){
           int total=0;
           int girls=0;
           int boys=0;
           FileResource fr =new FileResource();
           CSVParser parser=fr.getCSVParser(false);
           for (CSVRecord record:parser){
                total+=Integer.parseInt(record.get(2));
                if(record.get(1).equals("F")){
                    girls+=1;
                }
                else{
                     boys+=1;
                }
            }
            System.out.println("Total number of births :" +total);
            System.out.println("Total number of boys :" +boys);
            System.out.println("Total number of girls :" +girls);
       
       }
       
       private int getRank(int year ,String name ,String gender){
           int rank=-1;
           int rankCounter=0;
           FileResource fr =new FileResource("us_babynames_by_year\\yob"+ year+".csv");
           CSVParser parser=fr.getCSVParser(false);
           for (CSVRecord record:parser){
                if(record.get(1).equals(gender)){
                   rankCounter++; 
                   if(record.get(0).equals(name)){
                       rank=rankCounter;
                       break;
                   }
                }
           }
           return rank;
       }
       
        private String getName(int year ,int rank ,String gender){
           String name="NO NAME";
           int rankCounter=1;
           FileResource fr =new FileResource("us_babynames_by_year\\yob"+ year+".csv");
           CSVParser parser=fr.getCSVParser(false);
           for (CSVRecord record:parser){
                if(record.get(1).equals(gender)){
                   if(rank == rankCounter){
                       name=record.get(0);
                       break;
                   }
                   rankCounter++; 
                }
           }
           return name;
       }
       
       private String whatIsNameInYear(String name,int year,int newYear,String gender){
           int rank=getRank(year,name,gender);
           if (rank>0){
           String newName=getName(newYear,rank,gender);
           return newName;}
           else{
           return "NOT FOUND"; 
           }
       
       }
       
       private int yearOfHighestRank(String name,String gender){
           int highestRank=1000000000;
           int highestYear=-1;
           DirectoryResource dr = new DirectoryResource();
           for(File f : dr.selectedFiles()){
            String fileName=f.getName();
            int year=Integer.parseInt(fileName.substring(3,7));
            int rank=getRank(year,name,gender);
            if(rank != -1 && rank < highestRank){
                highestRank=rank;
                highestYear=year;
            }
            }
            return highestYear;
      }
      
      private double getAverageRank(String name,String gender){
           double sumRank=0.0;
           int numberOfFiles=0;
           DirectoryResource dr = new DirectoryResource();
           for(File f : dr.selectedFiles()){
            String fileName=f.getName();
            int year=Integer.parseInt(fileName.substring(3,7));
            int rank=getRank(year,name,gender);
            sumRank+=rank;
            numberOfFiles++;
            
            }
            
            return sumRank/numberOfFiles;
      }
      
      private int getTotalBirthsRankedHigher (int year,String name, String gender){
        int rank=getRank(year,name,gender);
        int sum=0;
        FileResource fr =new FileResource("us_babynames_by_year\\yob"+year+".csv");
        CSVParser parser=fr.getCSVParser(false);
        for (CSVRecord record:parser){
                if(record.get(1).equals(gender) && !record.get(0).equals(name)){
                   if(getRank(year,record.get(0),record.get(1)) < rank){
                       sum+=Integer.parseInt(record.get(2));
                   }
                }
            
         }
        return sum;    
      }
       
       public void test(){
           System.out.println("=========================================");
           //System.out.println("rank of Emily 1960="+getRank(1960,"Emily","F"));
           //System.out.println("rank of Frank 1971="+getRank(1971,"Frank","M"));
           //System.out.println("name of rank 350 ="+getName(1980,350,"F"));
           //System.out.println("name of rank 450 ="+getName(1982,450,"M"));
           //System.out.println("Susan born in 1972 would be " + whatIsNameInYear("Susan",1972,2014,"F")+" if she was born in 2014.");
           //System.out.println("Owen born in 1974 would be " + whatIsNameInYear("Owen",1974,2014,"M")+" if she was born in 2014.");
           //System.out.println("year of the highest rank Genevieve:" +yearOfHighestRank("Genevieve","F"));
           System.out.println("year of the highest rank Mish:" +yearOfHighestRank("Mich","M"));
           /*System.out.println("Average rank of Susan :" +getAverageRank("Susan","F"));
           System.out.println("Average rank of Robert:" +getAverageRank("Robert","M"));*/
           //System.out.println("total birth higher of rank of Emily :" +getTotalBirthsRankedHigher(1990,"Emily","F"));
           //System.out.println("total birth higher of rank of Drew :" +getTotalBirthsRankedHigher(1990,"Drew","M"));
       }
    
}
