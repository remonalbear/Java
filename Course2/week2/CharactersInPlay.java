
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {

    private ArrayList<String> characters;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay(){
            characters=new ArrayList<String>();
            counts=new ArrayList<Integer>();
    }
    
    private void update(String person){
        int index=characters.indexOf(person);
        if(index != -1){
            int value=counts.get(index);
            counts.set(index,value+1);
        }else{
            characters.add(person);
            counts.add(1);
        }
    }
    
    private void findAllcharacters(){
        characters.clear();
        counts.clear();
        FileResource fr = new FileResource ();
        for (String line : fr.lines()){
            int periodIndex=line.indexOf('.');
            if (periodIndex != -1){
                String personName=line.substring(0,periodIndex);
                update(personName);
            }
        }
    }
    
    private void charactersWithNumParts(int num1,int num2){
        int index=0;
        int most=0;
        int num=0; 
        for(int i=0;i<characters.size();i++){
            int count=counts.get(i);
            if (count >=num1 && count <= num2){
                if (count>most){
                    index=i;
                    most=count;
                }
                System.out.println(characters.get(i)+" : "+counts.get(i));
                num++;
            }
        }
        
        System.out.println("the Most speaking character of them:" +characters.get(index)+" : "+counts.get(index));
        System.out.println("the number of them are : " +num);
    }
    
    private int mostSpeakingPerson(){
        int maxIndex=0;
        for (int i=0;i<characters.size();i++){
            if(counts.get(i)>counts.get(maxIndex)){
                maxIndex=i;
            }
        }
        return maxIndex;
    }
    
    public void tester(){
        characters.clear();
        counts.clear();
        findAllcharacters();
        //System.out.println("the most speaking person:"+characters.get(mostSpeakingPerson())+" : "+counts.get(mostSpeakingPerson()));
        charactersWithNumParts(10,15);
    }
}
