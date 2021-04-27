package ass1;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int num=0;
        for (Point pt : s.getPoints()){
        num=num+1;
        }
        return num;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double avg=getPerimeter(s)/getNumPoints(s);
        return avg;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide=0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()){
         // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update the largest side
            if(currDist > largestSide){
                largestSide=currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        
        return largestSide;
    }

    public double getLargestX(Shape s) {
         // Put code here
        double largestXPoint=0.0;
        // For each point in the shape,
        for (Point pt : s.getPoints()){
            // Update the largest side
            double xValue=pt.getX();
            if(xValue > largestXPoint){
                largestXPoint=xValue;
            }
            
        }
        
        return largestXPoint;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter=0.0; 
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s=new Shape(fr);
            if(getPerimeter(s) > largestPerimeter){
                largestPerimeter=getPerimeter(s);
            }
            
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;   
        double largestPerimeter=0.0; 
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s=new Shape(fr);
            if(getPerimeter(s) > largestPerimeter){
                largestPerimeter=getPerimeter(s);
                temp=f;
            }
            
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        double avgLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        int numPoints= getNumPoints(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPoints);
        System.out.println("average length = " + avgLength);
        System.out.println("largest side = " + largestSide);
        System.out.println("largest X = " + largestX);
        
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest Perimeter = " + largestPerimeter);
       
        
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestPerimeterFile = getFileWithLargestPerimeter();
        System.out.println("file with the largest perimeter = " + largestPerimeterFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testFileWithLargestPerimeter();
    }
}
