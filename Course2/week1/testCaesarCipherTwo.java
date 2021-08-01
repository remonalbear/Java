
/**
 * Write a description of testCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class testCaesarCipherTwo {

    private int[] countLetters(String message){
        int [] counts=new int[26];
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        for (int i=0;i<message.length();i++){
            char ch=message.charAt(i);
            int index=alphabet.indexOf(Character.toUpperCase(ch));
            if(index != -1) {
                counts[index] += 1;
            }
        }
        
        return counts;
    }

    private int maxIndex(int [] counts){
        int index=0;
        for (int i=0;i<counts.length;i++){
            if (counts[i] > counts[index]){
                index=i;
            }
        }
        return index;
    }
    
    private String halfOfString(String message,int start){
        String halfString="";
        for (int i=start;i<message.length();i+=2){
            halfString =halfString + message.charAt(i);
        }
        return halfString;
    }
    
    private int getKey(String message){
        int [] counts=countLetters(message);
        int keyIndex=maxIndex(counts);
        System.out.println("keyIndex:"+keyIndex);
        int key=keyIndex-4;
        if (keyIndex < 4)
        {
            key=26-(4-keyIndex); 
        }
        System.out.println("key:"+key);
        return key; 
    }

    private String breakCaesarCipherTwo(String message){
        String firstHalf=halfOfString(message,0);
        String secondHalf=halfOfString(message,1);
        int key1=getKey(firstHalf);
        int key2=getKey(secondHalf);
        CaesarCipherTwo cc=new CaesarCipherTwo(key1,key2);
        String decrypted=cc.decrypt(message);
        return decrypted;
    }
    
    public void simpleTests(){
     //System.out.println("auto decrypted: "+breakCaesarCipherTwo("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
     //CaesarCipherTwo cc=new CaesarCipherTwo(14,24);
     //System.out.println("encrypted: "+cc.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
     FileResource fr=new FileResource();
     String message =fr.asString();
     //System.out.println("message: "+message);
     //System.out.println("encrypted: "+cc.encrypt(message));
     //System.out.println("decrypted: "+cc.decrypt(message));
     System.out.println("auto decrypted: "+breakCaesarCipherTwo(message));
    }
}
