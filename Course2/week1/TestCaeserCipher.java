
/**
 * Write a description of TestCaeserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaeserCipher {
    
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
    
    private String breakCaesarCipher(String message){
        int [] counts=countLetters(message);
        int keyIndex = maxIndex(counts);
        int key=keyIndex-4;
        if (keyIndex < 4)
        {
            key=26-(4-keyIndex); 
        }
        
        CaesarCipher cc =new CaesarCipher(26-key);
        String decrypted = cc.decrypt(message);
        return decrypted;
        
    }
    
    public void simpleTests(){
     CaesarCipher cc=new CaesarCipher(15);
     System.out.println("encrypted: "+cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
     /*FileResource fr=new FileResource();
     String message =fr.asString();
     System.out.println("message: "+message);
     System.out.println("encrypted: "+cc.encrypt(message));
     System.out.println("decrypted: "+cc.decrypt(message));
     System.out.println("auto decrypted: "+breakCaesarCipher(cc.encrypt(message)));*/
    }
}
