
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {

    private boolean isVowel(char ch){
      String vowels="aeiou";
      int index=vowels.indexOf(Character.toLowerCase(ch));
      if(index != -1){
       return true; 
      }
      return false;
    }
    
    private String replaceVowels(String phrase,char ch){
        StringBuilder str=new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
            if(isVowel( str.charAt(i))){
                str.setCharAt(i,ch);
            }
        }
        return str.toString();
    }
    private String emphasize(String phrase,char ch){
        StringBuilder str=new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
            if(phrase.charAt(i) == ch){
                if((i+1)%2 == 0){
                    str.setCharAt(i,'+');
                }
                else
                {
                    str.setCharAt(i,'*');
                }
            }
        }
        return str.toString();
    }
    public void test(){
    System.out.println(emphasize("Mary Bella Abracadabra",'a'));
    }
}

