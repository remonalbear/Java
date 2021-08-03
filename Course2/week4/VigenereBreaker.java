import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dict=new HashSet<String>();
        for (String line : fr.lines()){
            dict.add(line.toLowerCase());
        }
        return dict;
    }
    
    public char mostCommonCharIn(HashSet<String> dict){
        HashMap<Character,Integer> charMap=new HashMap<Character,Integer>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0;i<alphabet.length();i++)
        {
            charMap.put(alphabet.charAt(i),0);
        }
        char mostCommon='e';
        for (String word : dict){
            for (int i =0 ;i<word.length();i++){
                char c=word.charAt(i);
                if (charMap.containsKey(c)){
                    charMap.put(c,charMap.get(c) + 1);
                }else{
                    charMap.put(c,1);
                }
            }
        }
        
        for (char c : charMap.keySet()){
            if(charMap.get(c) > charMap.get(mostCommon)){
                mostCommon=c;
            }
        }
        return mostCommon;
    }
    
    public int countWords(String message,HashSet<String> dict){
        int count=0;
        for(String word : message.split("\\W")){
            if(dict.contains(word.toLowerCase())){
                count++;
            }
        }
        return count;
    }
    
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder data=new StringBuilder();
        for (int i=whichSlice;i<message.length();i+=totalSlices ){
            data.append(message.charAt(i));
        }
        return data.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker=new CaesarCracker(mostCommon);
        for (int i=0; i<klength ;i++){
            String sub=sliceString(encrypted,i,klength);
            int k=cracker.getKey(sub);
            key[i]=k;
        }  
        return key;
    }


    
    public String breakForLanguage(String message,HashSet<String> dict){
        String decryptedFinal="";
        int maxCount=0;
        int keyLength=0;
        char mostCommon=mostCommonCharIn(dict);
        System.out.println("Most common chat : "+mostCommon);
        for (int i=1;i<=100;i++){   
            int []keys=tryKeyLength(message,i,mostCommon);
            VigenereCipher cipher=new VigenereCipher(keys);
            String decrypted =cipher.decrypt(message);
            int wordsCount=countWords(decrypted,dict);
            System.out.println("message "+decrypted.substring(0,50)+" count : "+wordsCount);
            if (wordsCount>maxCount){
                maxCount=wordsCount;
                decryptedFinal=decrypted;
                keyLength=i;
            }
        }
        System.out.println("key Length : "+keyLength);
        System.out.println("max valid words : "+maxCount);
        return decryptedFinal;
    }
    
    public String  breakForAllLangs(String message,HashMap<String,HashSet<String>> langsMap){
        String bestLang="";
        int maxCount=0;
        for(String lang: langsMap.keySet()){
            System.out.println("break for "+ lang );
            String res=breakForLanguage(message,langsMap.get(lang));
            int count=countWords(res ,langsMap.get(lang));
            if (count > maxCount)
            {
                maxCount=count;
                bestLang=lang;
            }
        }
        String decrypted=breakForLanguage(message,langsMap.get(bestLang));
        System.out.println("best language is : "+ bestLang +" with max count "+ maxCount);
        System.out.println("the decrypted message : "+ decrypted.substring(0,100));
        return decrypted;
    
    }
    
    public void breakVigenere () {
       FileResource f=new FileResource();
       String message=f.asString().toLowerCase();
       String [] languages={"Danish", "Dutch", "English", "French",
                                "German", "Italian", "Portuguese", "Spanish"};
       HashMap<String,HashSet<String>> langsMap=new HashMap<String,HashSet<String>>();
       for(int i=0;i<languages.length;i++){
           System.out.println("Reading "+languages[i]+" Dictionary");
           FileResource file=new FileResource("dictionaries/"+languages[i]);
           HashSet<String> dict= readDictionary(file);
           langsMap.put(languages[i],dict);
        }
       String decrypted=breakForAllLangs(message,langsMap);
       System.out.println(decrypted.substring(0,250));
    }
    
    
}
