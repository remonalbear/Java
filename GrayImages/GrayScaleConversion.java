
/**
 * Write a description of GrayImages here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
public class GrayScaleConversion {
    
    private ImageResource convertToGray(ImageResource inputImage){
    
        ImageResource newImage=new ImageResource(inputImage.getWidth(),inputImage.getWidth());
        for(Pixel pixel: newImage.pixels() ){
            Pixel inputPixel=inputImage.getPixel(pixel.getX(),pixel.getY());
            int avgPixel=(inputPixel.getRed()+inputPixel.getGreen()+inputPixel.getBlue() )/3;
            pixel.setRed(avgPixel);
            pixel.setGreen(avgPixel);
            pixel.setBlue(avgPixel);
       }
     
       return newImage;
        
    }
    
    private void covertManyImage(){
        DirectoryResource dr=new DirectoryResource();
        for (File f:dr.selectedFiles()){
            ImageResource image=new ImageResource(f);
            ImageResource grayImage=convertToGray(image);
            grayImage.draw();
            String name=image.getFileName();
            grayImage.setFileName("gray_"+name);
            grayImage.save();
            
        }
     }
    
    public void test(){
        covertManyImage();
    }
    
    
    
    
    
}
