
/**
 * Write a description of ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class ImageInversion {
    private ImageResource invertImage(ImageResource inputImage){
    
        ImageResource newImage=new ImageResource(inputImage.getWidth(),inputImage.getWidth());
        
        for(Pixel pixel: newImage.pixels() ){
            Pixel inputPixel=inputImage.getPixel(pixel.getX(),pixel.getY());
            pixel.setRed(255-inputPixel.getRed());
            pixel.setGreen(255-inputPixel.getGreen());
            pixel.setBlue(255-inputPixel.getBlue());
        }
     
       return newImage;
        
    } 
    
    private void covertManyImage(){
            DirectoryResource dr=new DirectoryResource();
            for (File f:dr.selectedFiles()){
                ImageResource image=new ImageResource(f);
                ImageResource inverseImage=invertImage(image);
                inverseImage.draw();
                String name=image.getFileName();
                inverseImage.setFileName("invert_"+name);
                inverseImage.save();
                
            }
         }
    
    public void test(){
        covertManyImage();
    }
    
}
