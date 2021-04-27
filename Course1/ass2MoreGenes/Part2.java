
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

    
    
    private int howMany(String stringa,String stringb){
    int counter=0;
    while(true){
    int startIndex=stringb.indexOf(stringa);
    if (startIndex == -1){
    break;
    }
    else{
    counter++;
    stringb=stringb.substring(startIndex+stringa.length(),stringb.length());
    }
    
   } 
    return counter;
    }
    
    public void testHowMany(){
    System.out.println("---------------------");
    System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
    System.out.println(howMany("AA","ATAAAA"));
    }
}
