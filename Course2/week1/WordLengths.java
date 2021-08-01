
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {

    private int lengthOf(String word){
        int count=word.length();
        if (! Character.isLetter(word.charAt(0))){
            count -= 1;
        }
        if(! Character.isLetter(word.charAt(word.length() - 1))){
            count -= 1;
        }
        return count;
        
    }
    private void countWordLengths(FileResource resource,int [] counts){
        for(String word:resource.words()){
            int len=lengthOf(word);
            if (len > counts.length){
                len=counts.length;
            }
            if (len > 0){
                System.out.println(word +":"+len);
                counts[len] +=1;
            }
            
        }
    }
    
    public void testCountWordLength(){
    FileResource fr=new FileResource();
    int [] counts=new int [31];
    countWordLengths(fr,counts);
    for (int i =0 ;i<counts.length;i++){
        System.out.println(counts[i]);
    }
    }
}
