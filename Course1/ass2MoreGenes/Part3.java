
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    private int howMany(String stringa,String stringb){
        int counter=0;
        while(true){
        int startIndex=stringb.indexOf(stringa);
        if (startIndex == -1){
        break;
        }
        else{
        counter++;
        stringb=stringb.substring(startIndex+stringa.length(),stringb.length());
        }
        
       } 
       return counter;
    }
    
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
    
    public int countGenes(String dna){
    int count=0;
        while(true){
            String gene=findGene(dna);
           if(gene.isEmpty()){
           break; 
           }
           count++;
           int startIndex=dna.indexOf(gene);
           dna=dna.substring(startIndex+gene.length(),dna.length());
    
    }
    return count;
}

   public void testCountGenes(){
       System.out.println("---------------");
       String dna="AGGTAAAAAATGGGAAAGTAGATGAAGTTATATATGAAATAATATATGAAATAAAAAATGGGGTGA";
       System.out.println(dna);
       System.out.println("number of genes found:");
       System.out.println(countGenes(dna));
   }

}
