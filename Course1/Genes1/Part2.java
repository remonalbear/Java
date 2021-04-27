package Genes1;


/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {

   private String findSimpleGene(String dna,String start,String end){
    
    Boolean isLowerCase=false;
    String startCodon=start;
    String endCodon=end;
    String gene="";
    if(dna.equals(dna.toLowerCase())){
    isLowerCase=true;
    startCodon=startCodon.toUpperCase();
    endCodon=endCodon.toUpperCase();
    dna=dna.toUpperCase();
   }
   
    int startIndex=dna.indexOf(startCodon);
    if (startIndex>=0){
    int endIndex=dna.indexOf(endCodon,startIndex+3);
        if(endIndex>=0){
        String sequence=dna.substring(startIndex,endIndex+3);
            if(sequence.length()%3 == 0){
            gene=sequence;
            }
        }
    }
    
    if(isLowerCase){
    gene=gene.toLowerCase();
    }
    return gene;
    }
    


    public void testSimpleGene(){
        String start="ATG";
        String end="TAA";
        String g1="atgaagtaa";
        String g2="ATGAGTAA";
        String g3="AAGTATAA";
        String g4="ATGATAGAA";
        String g5="AGATA";
        String g6="ATTGAGAATGGGATAGTAAGATGTA";
        System.out.println("new run:");
        System.out.println(g1);
        System.out.println(findSimpleGene(g1,start,end));
        System.out.println(g2);
        System.out.println(findSimpleGene(g2,start,end));
        System.out.println(g3);
        System.out.println(findSimpleGene(g3,start,end));
        System.out.println(g4);
        System.out.println(findSimpleGene(g4,start,end));
        System.out.println(g5);
        System.out.println(findSimpleGene(g5,start,end));
         System.out.println(g6);
        System.out.println(findSimpleGene(g6,start,end));
    }
}

