
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies()
    {
        myWords=new ArrayList<String>();
        myFreqs=new ArrayList<Integer>();
    }
    
    private void findUnique (){
     myWords.clear(); 
     myFreqs.clear();
     FileResource fr=new FileResource (); 
     for(String word :fr.words()){
         int index = myWords.indexOf(word.toLowerCase());
         if (index != -1 ){
             int count = myFreqs.get(index);
             myFreqs.set(index,count+1);
         }
         else{
             myWords.add(word.toLowerCase());
             myFreqs.add(1);
         }
     }
    }
    
    private int findIndexOfMax(){
        int maxIndex=0;
        for (int i=0;i<myWords.size();i++){
            if(myFreqs.get(i)>myFreqs.get(maxIndex)){
                maxIndex=i;
            }
        }
        return maxIndex;
    }
    
    public void tester(){
    findUnique();
    System.out.println("Number of Unique Words:"+myWords.size());
    /*for (int i=0;i<myWords.size();i++){
        System.out.println(myFreqs.get(i)+" "+myWords.get(i));
    }*/
    System.out.println("The word that occurs most often and its count are:"+myWords.get(findIndexOfMax())+myFreqs.get(findIndexOfMax()));
    }
}
