package Genes1;


/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {

    private Boolean twoOccurrences(String stringa,String stringb){
    int firstOccurnce=stringa.indexOf(stringb);
    if(firstOccurnce != -1){
        int secondOccurnce=stringa.indexOf(stringb,firstOccurnce+stringb.length());
        if(secondOccurnce != -1){
        return true;
        }
    }
    return false;
    }
    
    private String lastPart(String stringa,String stringb){
    int firstOccurnce=stringa.indexOf(stringb);
    String match=stringa;
    if(firstOccurnce != -1){
        match=stringa.substring(firstOccurnce+stringb.length());
    }
    return match;
    }
    
    public void testing(){
    System.out.println("new run");
    System.out.println(twoOccurrences("A story by Abby Long","by"));
    System.out.println(twoOccurrences("banana","an"));
    System.out.println(twoOccurrences("ctgtatgta","atg"));
    System.out.println(lastPart("banana","an"));
    System.out.println(lastPart("forest","zoo"));
        
        
    }
}
