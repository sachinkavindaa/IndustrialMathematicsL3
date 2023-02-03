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


public final class IFN {
 

    // Global variable for values obatain from w,x,y and z. Global variable can be accesed anywhere within the class. 
private double _w;


    public IFN(double w){
        
        _w = w;
   

    }



    public static double dw(double w) {

        double US = 0;
        double mus = 4.8;


        return  US - mus*w;
    }


    public double getw(){
        return _w;
    }

    
    


    public static void main(String[] args) throws FileNotFoundException {

        //initial points 
        double w = 10;
      
        double dt = 0.1; 
        double time = 30;
        double iteration = time/dt;


        // ode ODE = new ode();

        
        File CSVfile = new File("readingsIFN.csv");

        ArrayList<IFN> values= new  ArrayList<>();
        //values is the arraylist created to store multiple values that are stored using the value object in the line 138
        //value object has 4 values namely w,x,y and z
        PrintWriter pw = new PrintWriter(CSVfile);
        // Use Euler's method to numerically solve ODE
        for (int i = 0; i < iteration; i++) {

            double k1w = dw(w) * dt;
    
    
            double k2w = dw(w + k1w/2) * dt;
         
    
            double k3w = dw(w + k2w/2) * dt;
    
    
            double k4w = dw(w + k3w) * dt;
     

            
    
        // update variables
        w =w+  (k1w + 2*k2w + 2*k3w + k4w) / 6;

    
        // store values in cancer object
        IFN value= new IFN(w);
        values.add(value);
    

            
        }
            String col1 = "w";
    
            
            pw.printf("%s",col1); 
            pw.print("\n");
            
            for(IFN reading: values){
            //every single element of the arraylist is taken into account and looped so that 
            //it is printed in the CSV file created above
            // System.out.println(reading.getw()+" "+reading.getx()+" "+reading.gety()+" "+reading.getz());
            pw.printf("%f",reading.getw());
            //the 4 values obtained for w,x,y and z are printed in the CSV file.
            pw.print("\n");
            //the printwriter has to be closed in oder for the code to finish execution of the CSV file creation.
        }
        pw.close();

    }
}

    

