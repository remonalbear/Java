
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.File;
public class WordsInFiles {
    
    private HashMap<String , ArrayList<String>> wordsFiles;
    ArrayList<String> allFiles;
    public WordsInFiles(){
        wordsFiles=new  HashMap<String , ArrayList<String>>();
        allFiles=new ArrayList<String>();
    }
    
    private void addWordsFromFile(File file){
        String fileName = file.getName();
        FileResource f=new FileResource(file);
        
        if(!allFiles.contains(fileName)){
            allFiles.add(fileName);
        }
        
        for(String word: f.words()){
            if(wordsFiles.containsKey(word)){
                ArrayList<String > files= wordsFiles.get(word);
                if(!files.contains(fileName)){
                    files.add(fileName);
                    wordsFiles.put(word,files);
                }
            }
            else{
                ArrayList<String > files= new ArrayList<String>();
                files.add(fileName);
                wordsFiles.put(word,files);
            }
        }
        
    }
 
    private void buildWordFileMap() {
        wordsFiles.clear();
        allFiles.clear();
        DirectoryResource dr=new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
        
    }
    
    private int maxNumber(){
        int maxNum=0;
        
        for (String word: wordsFiles.keySet() ){
            int count=0;
            for(String file:allFiles){
                if(wordsFiles.get(word).contains(file)){
                    count++;
                }
            }
            if(count > maxNum){
                maxNum=count;
            }
        }
        
        return maxNum;
    }
    
    private ArrayList<String> wordsNumFiles(int num){
        ArrayList<String> words=new ArrayList<String>();
        for(String word : wordsFiles.keySet()){
                if(wordsFiles.get(word).size() == num){
                    words.add(word);
                }
        }
        return words;
     }
    
     private void printFiles(String word){
         for(String file:allFiles){
             if(wordsFiles.get(word).contains(file)){
                 System.out.println(file);
             }
         }
     }
    
     
     public void tester(){
      buildWordFileMap();
      int maxNum=maxNumber();
      System.out.println("words in 7 Files "+ wordsNumFiles(7).size());
      System.out.println("words in 4 Files "+ wordsNumFiles(4).size());
      
      /*for (String word : wordsFiles.keySet()){
          if(wordsFiles.get(word).size() == maxNum){
               System.out.println("Word: "+word);
               System.out.println("Files:");
              for(String file : wordsFiles.get(word) ){
                  System.out.println(file);
              }
          }
      }
      
      /*for (String word : wordsFiles.keySet()){
              System.out.println("Word: "+word);
              System.out.println("Files:");
              for(String file : wordsFiles.get(word) ){
                  System.out.println(file);
              }
         
      }*/
      
      for (String word : wordsFiles.keySet()){
          if(word.equals("laid")){
              for(String file : wordsFiles.get(word) ){
                      System.out.println("file with  \"laid\" word is :" + file);
              }
          }
          if(word.equals("tree")){
              for(String file : wordsFiles.get(word) ){
                     System.out.println("files with \"tree\" word in are:");
                     System.out.println(file);
              }
          }
      }
      
      
      
      
      
     }
    
    
    
    
}
