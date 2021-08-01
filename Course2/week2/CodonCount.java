
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import edu.duke.*;
public class CodonCount {

    private HashMap<String,Integer> codons;
    
    public CodonCount (){
        codons= new HashMap<String,Integer> ();
    }
    
    private void buildCodonMap(String dna, int start ){
        codons.clear();
        dna.trim();
        for (int i=start;i<dna.length()-3;i+=3){
            String codon=dna.substring(i,i+3);
            if (codons.containsKey(codon)){
                codons.put(codon,codons.get(codon) + 1 );
            }
            else{
                codons.put(codon,1);
            }
        }
    }
    
    private String getMostCommonCodon(){
        String mostCodon="";
        for(String codon:codons.keySet()){
            if(!codons.containsKey(mostCodon)){ // for the first intilization
                mostCodon=codon;
            }
            if (codons.get(codon) > codons.get(mostCodon)){
                mostCodon=codon;
            }
        }
        return mostCodon;
    }
    
    private void printCodonCounts(int start,int end){
        for (String codon:codons.keySet()){
            int count=codons.get(codon);
            if(count >= start && count <= end ){
                System.out.println(codon+" "+count);
            }
        }
    }
    
    public void tester(){
        FileResource fr=new FileResource();
        String DNA = fr.asString();
        DNA.toUpperCase();
        if( !Character.isLetter( DNA.charAt(DNA.length() - 1) ) ){
            DNA=DNA.substring(0,DNA.length() - 1);
        }
        for(int i=0;i<3;i++){
            buildCodonMap(DNA,i);
            System.out.println("Reading frame starting with "+i+" results in "+codons.size()+
            " unique codons and most common codon is "+getMostCommonCodon()+" with count "+
            codons.get(getMostCommonCodon()));;
            printCodonCounts(7,7);
        }
    }
    
    
    
    
}
