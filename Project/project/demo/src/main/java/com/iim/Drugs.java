package main.java.com.iim;
import java.io.IOException;
import java.io.PrintWriter;
import com.opencsv.CSVWriter;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import java.text.Format;
import com.iim.ode;


public final class Drugs {
 

    // Global variable for values obatain from w,x,y and z. Global variable can be accesed anywhere within the class. 
private double _w;
private double _x;
private double _y;
// private double _z;

    public Drugs(double w, double x, double y){

//  
        
        _w = w;
        _x = x;
        _y = y;
        
    }



    public static double dw(double w, double x, double y) {

        double US = 0.1;
        double mus = 4.8;


        return  US - mus*w;
    }

    public static double dx(double w, double x, double y) {

   
        double Js = 1.3;
        double gammaD= 1;
        double muj = 1.3;

        return Js - gammaD*x*y - muj*x;
    }

    public static double dy(double w, double x, double y) {
        double UD = 0.2;
        double muD = 10;

        return UD - muD*y;
    }





    public double getw(){
        return _w;
    }

    public double getx(){
        return _x;
    }
    
    public double gety(){
        return _y;
    }
    


    public static void main(String[] args) throws FileNotFoundException {


            //initial points 
       
            double w = 0;
            double x = 0;
            double y = 0;
            double dt = 0.1; 
            double time = 10;
            double iteration = time/dt;
    
            File CSVfile = new File("Drugs1.csv");
    
            ArrayList<Drugs> values= new  ArrayList<>();
            //values is the arraylist created to store multiple values that are stored using the value object in the line 138
            //value object has 4 values namely w,x,y and z
            PrintWriter pw = new PrintWriter(CSVfile);
            // Use Drugs's method to numerically solve ODE

            for (int i = 0; i < iteration; i++) {
    
                double dw = dw(w, x, y);
                double dx = dx(w, x, y);
                double dy = dy(w, x, y);
            
                w = w + dw*dt;
                x = x + dx*dt;
                y = y + dy*dt;
              
            
    
                Drugs value= new Drugs(w, x, y);
                values.add(value);
    
                if(i%1000 ==0){
                    System.out.println("w: "+w+" x: "+x+" y: "+y);
                }

            }

            String col1 = "w";
            String col2 = "x";
            String col3 = "y";
           
            
            pw.printf("%s,%s,%s",col1,col2,col3); 
            pw.print("\n");
            
            for(Drugs reading: values){
            //every single element of the arraylist is taken into account and looped so that 
            //it is printed in the CSV file created above
            // System.out.println(reading.getw()+" "+reading.getx()+" "+reading.gety()+" "+reading.getz());
            pw.printf("%f,%f,%f",reading.getw(),reading.getx(),reading.gety());
            //the 4 values obtained for w,x,y and z are printed in the CSV file.
            pw.print("\n");
            //the printwriter has to be closed in oder for the code to finish execution of the CSV file creation.
        }
        pw.close();
    
        }
    }
