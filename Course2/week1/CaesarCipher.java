
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class CaesarCipher {
    private String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        mainKey=key;
        shiftedAlphabet=alphabet.substring(mainKey)+alphabet.substring(0,mainKey);
    }
    
    public String encrypt(String input){
    StringBuilder str=new StringBuilder(input);
    
    for (int i=0;i<str.length();i++){
        char ch=str.charAt(i);
        int index = alphabet.indexOf(Character.toUpperCase(ch));
        if (index != -1){
            if (Character.isUpperCase(ch)){
                str.setCharAt(i,shiftedAlphabet.charAt(index));
            }
            else
            {
                str.setCharAt(i,Character.toLowerCase(shiftedAlphabet.charAt(index)));
            }
        }
    }
    return str.toString();    
    }
    
    public String decrypt(String input){
        CaesarCipher cc=new CaesarCipher(26-mainKey);
        String decrypted=cc.encrypt(input);
        return decrypted;
    }
    
    
}
