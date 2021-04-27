 


/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part4 {

    public void youtubeUrl(){
    URLResource source=new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
    System.out.println("new run"); 
    for (String word : source.words() ){
    word=word.toLowerCase();
    int youtubeindex=word.indexOf("youtube.com");
    if( youtubeindex != -1){
    int startIndex=word.lastIndexOf("\"",youtubeindex);
    int endIndex=word.indexOf("\"",youtubeindex);
    String url =word.substring(startIndex,endIndex+1);
    System.out.println(url);
    }
    }
    }
    
}
