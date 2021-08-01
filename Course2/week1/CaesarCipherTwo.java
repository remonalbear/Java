
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {

    private String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1,int key2){
        mainKey1=key1;
        mainKey2=key2;
        shiftedAlphabet1=alphabet.substring(mainKey1)+alphabet.substring(0,mainKey1);
        shiftedAlphabet2=alphabet.substring(mainKey2)+alphabet.substring(0,mainKey2);
    }
    

    
    public String encrypt(String message){
        StringBuilder str=new StringBuilder(message);
        for (int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(ch));
            if (index != -1){
                if (Character.isUpperCase(ch)){
                    if (i%2 == 0 ){
                        str.setCharAt(i,shiftedAlphabet1.charAt(index));
                    }
                    else {
                        str.setCharAt(i,shiftedAlphabet2.charAt(index));
                    }
                }
                else
                {
                    if(i%2==0){
                        str.setCharAt(i,Character.toLowerCase(shiftedAlphabet1.charAt(index)));
                    }
                    else{
                         str.setCharAt(i,Character.toLowerCase(shiftedAlphabet2.charAt(index)));
                    }
                }
            }
        }
        return str.toString();    
    }
    
    public String decrypt(String message){
        CaesarCipherTwo cc=new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        String decrypted=cc.encrypt(message);
        return decrypted;
    }
}
