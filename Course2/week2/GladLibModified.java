import edu.duke.*;
import java.util.*;

public class GladLibModified {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;
    private Random myRandom;
    private int replacedWords;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibModified(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibModified(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        myMap=new HashMap<String,ArrayList<String> >();
        String[] categories={"adjective","noun","color","country","name","animal","timeframe","verb","fruit"};
        for(int i=0;i<categories.length;i++){
            ArrayList<String> category=readIt(source+"/"+categories[i]+".txt"); 
            myMap.put(categories[i],category);
        }
        usedWords=new ArrayList<String>();
        usedCategories=new ArrayList<String>();
        replacedWords=0;
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        else if(myMap.containsKey(label)){
            if(!usedCategories.contains(label)){
                usedCategories.add(label);
            }
            return randomFrom(myMap.get(label));
        }else{
            return "**UNKNOWN**";
        }
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(usedWords.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
            replacedWords++;
        }
        System.out.println(sub);
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
   
    
    private int totalWordsInMap(){
        int count=0;
        for (String category : myMap.keySet() ){
            count+=myMap.get(category).size();
        }
        return count;
    }
    
    private int totalWordsConsiderd(){
        int count=0;
        for (String category : myMap.keySet() ){
            if (usedCategories.contains(category)){
                count+=myMap.get(category).size();
            }
            
        }
        return count;
    }
    
    public void makeStory(){
        usedWords.clear();
        replacedWords=0;
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("number of words replaced :"+replacedWords);
        System.out.println("the total number of words that were possible to pick from.:"+totalWordsInMap());
                System.out.println("the total number of words considerd :"+totalWordsConsiderd());
    }
    


}
