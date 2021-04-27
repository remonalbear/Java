
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part4 {

    private int findStopCodon(String dna,int startIndex,String stopCodon)
    {
        int stopIndex=dna.indexOf(stopCodon,startIndex+3);
        while(stopIndex != -1){
        
        int diff=stopIndex-startIndex;
        
        if(diff%3 == 0)
        {
            return stopIndex; 
        }
        else
        {
            stopIndex=dna.indexOf(stopCodon,stopIndex+1);   
        }
        
    }
    return -1;
    }
    
   private String findGene(String dna,int where){
    int startIndex=dna.indexOf("ATG",where);
    if(startIndex == -1)
    {
        return "";
    }
    int taaIndex=findStopCodon(dna,startIndex,"TAA"); 
    int tagIndex=findStopCodon(dna,startIndex,"TAG"); 
    int tgaIndex=findStopCodon(dna,startIndex,"TGA");
    int stopIndex=0;
    if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex<taaIndex)){
        stopIndex=tgaIndex;
    }
    else{
    stopIndex=taaIndex;
    }
    
    if(stopIndex == -1 || (tagIndex != -1 && tagIndex<stopIndex)){
    stopIndex=tagIndex; 
    }
    
    if(stopIndex ==-1){
    return "";
    }
    
      
    return dna.substring(startIndex,stopIndex+3);   
    }
    
    private void printAllGenes(String dna){
        int startIndex=0;
        while(true){
           String gene=findGene(dna,startIndex);
           if(gene.isEmpty()){
           break; 
           }
           System.out.println(gene);
           startIndex=dna.indexOf(gene,startIndex)+gene.length();
    
    }
}

    private StorageResource getAllGenes(String dna){
         int startIndex=0;
        StorageResource resource=new StorageResource();
        while(true){
           String gene=findGene(dna,startIndex);
           if(gene.isEmpty()){
               break; 
           }
           resource.add(gene);
           startIndex=dna.indexOf(gene,startIndex)+gene.length();    
    }
    return resource;
}

private double cgRatio(String dna){
int count=0;
double ratio=0.0;
char[] chars=dna.toCharArray();
for(char c :chars){
    if(c=='C' || c=='G'){
    count++;
   }
        
}
ratio=(double) count/dna.length();
return ratio;
}

private int countCTG(String dna){
int count=0;
while(true){
    int index =dna.indexOf("CTG");
    if (index == -1 ){
    break;
    }else{
    count++;
    dna=dna.substring(index+3,dna.length());
    }
}
return count;
}

private void processGenes(StorageResource sr){
int geneCount=0;
int longStringsCount=0;
int highRatioStringsCount=0;
int longestGeneLength=0;
for(String s: sr.data()){
    geneCount++;
    if(s.length()>60){
    System.out.println("String > 60 characters: "+s);
    longStringsCount++;
    }
    if(cgRatio(s)>0.35){
    System.out.println("String with C G ratio>.35: "+s);
    highRatioStringsCount++;
    }
    if(s.length()>longestGeneLength){
    longestGeneLength=s.length();
    }

}
System.out.println("count of genes:"+geneCount);
System.out.println("count of string with more than 60 characters:"+longStringsCount);
System.out.println("count of string with with C G ratio > .35:"+highRatioStringsCount);
System.out.println("longest gene length :"+longestGeneLength);

}

public void testPrintAllGenes(){
   System.out.println("---------------");
   String dna="AGGTAAAAAATGGGAAAGTAGATGAAGTTATATATGAAATAATATATGAAATAAAAAATGGGGTGA";
   System.out.println(dna);
   System.out.println("genes found:");
   StorageResource resource= getAllGenes(dna);
   for(String gene : resource.data()){
    System.out.println(gene);
    }
   }
 public void testcgRatio(){
    System.out.println("--------------");
    String dna="ATGCCATAG";
    System.out.println(cgRatio(dna));
   }
 public void testCountCTG(){
    System.out.println("--------------");
    String dna="ATGCTGATACTGBCTG";
    System.out.println(countCTG(dna));
   }
  public void testProcessGenes(){
    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    FileResource fr = new FileResource("GRch38dnapart.fa");
    String sr = fr.asString();
    sr=sr.toUpperCase();
    System.out.println("count of CTG:"+countCTG(sr));
    StorageResource resource= getAllGenes(sr);
    processGenes(resource);
   }
}
