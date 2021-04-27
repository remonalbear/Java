
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    
   private int findStopCodon(String dna,int startIndex,String stopCodon)
    {
    int stopIndex=dna.indexOf(stopCodon,startIndex+3);
    
    if(stopIndex == -1)
    {
        return dna.length();
    }
    
    int diff=startIndex-stopIndex;
    
    if(diff%3 == 0)
    {
    return stopIndex; 
    }
    else
    {
        return dna.length();
    }
        

    }
    
   private String findGene(String dna){
    
    int startIndex=dna.indexOf("ATG");
    if(startIndex == -1)
    {
        return "";
    }
    int taaIndex=findStopCodon(dna,startIndex,"TAA");
    int tagIndex=findStopCodon(dna,startIndex,"TAG");
    int tgaIndex=findStopCodon(dna,startIndex,"TGA");
    int temp=Math.min(taaIndex,tagIndex);
    int stopIndex=Math.min(temp,tgaIndex);
    if(stopIndex==dna.length()){
    return "";
    }
    
    String gene=dna.substring(startIndex,stopIndex+3);
    
    
    return gene;
    }
    
    private void printAllGenes(String dna){
    
        while(true){
           String gene=findGene(dna);
           if(gene.isEmpty()){
           break; 
           }
           System.out.println(gene);
           int startIndex=dna.indexOf(gene);
           dna=dna.substring(startIndex+gene.length(),dna.length());
    
    }
}
    
    public void testFindCodon(){
    String dna1="ATGAATAA";
    int startIndex1=dna1.indexOf("ATG");
    System.out.println("index= ");
    System.out.println(findStopCodon(dna1,startIndex1,"TAA"));
    
    String dna2="ATGAAATAA";
    int startIndex2=dna2.indexOf("ATG");
    System.out.println("index= ");
    System.out.println(findStopCodon(dna2,startIndex2,"TAA"));
    
    String dna3="ATGAATTA";
    int startIndex3=dna3.indexOf("ATG");
    System.out.println("index= ");
    System.out.println(findStopCodon(dna3,startIndex3,"TAA"));
    
    String dna4="TATATGAAATAA";
    int startIndex4=dna4.indexOf("ATG");
    System.out.println("index= ");
    System.out.println(findStopCodon(dna4,startIndex4,"TAA"));
   }
   
    public void testFindGene(){
    System.out.println("---------------------------");
    String dna1="AATGCTAACTAGCTGACTAAT";
    System.out.println(dna1);
    System.out.println(findGene(dna1));
    
    String dna2="ATGGGAAAGTAG";
    System.out.println(dna2);
    System.out.println(findGene(dna2));
    
    String dna3="ATGAAGTTA";
    System.out.println(dna3);
    System.out.println(findGene(dna3));
    
    String dna4="TATATGAAATAA";
    System.out.println(dna4);
    System.out.println(findGene(dna4));
    
    String dna5="TATATGAAATAAAAAATGGGGTGA";
    System.out.println(dna5);
    System.out.println(findGene(dna5));
   }
   
   public void tesyPrintAllGenes(){
   System.out.println("---------------");
   String dna="AGGTAAAAAATGGGAAAGTAGATGAAGTTATATATGAAATAATATATGAAATAAAAAATGGGGTGA";
   System.out.println(dna);
   System.out.println("genes found:");
   printAllGenes(dna);
   }
}
