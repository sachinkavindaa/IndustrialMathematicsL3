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


public final class runge {
 

    // Global variable for values obatain from w,x,y and z. Global variable can be accesed anywhere within the class. 
private double _w;
private double _x;
private double _y;
private double _z;

    public runge(double w, double x, double y, double z){

//  
        
        _w = w;
        _x = x;
        _y = y;
        _z = z;
    }



    public static double dw(double w, double x, double y, double z) {

        double lamdaS1 = 1;
        double S = 0.4;
        double k_1 = 4;
        double k_2 = 1;
        double alpha = 1.5;


        return  lamdaS1*S +( (k_1*Math.pow((k_2),2))/ (Math.pow((k_2),2) + alpha*Math.pow((x),2)) ) - w;
    }

    public static double dx(double w, double x, double y, double z) {

        double lamda_k = 1;
        double lamda_J = 4;
        double J = 0;
        double K = 5;
        double lamdaS2 = 1;
        double k_3 = 4;
        double k_4 = 1;
        double beta = 1;
        double S = 1;

        return ((lamda_k + lamda_J* J) / (K+ lamdaS2* S)) + (((k_3)*Math.pow((k_4),2)) / (Math.pow((k_4),2))+ beta*Math.pow((w),2))-x;
    }

    public static double dy(double w, double x, double y, double z) {
        double lamda_1 = 0.2;
        double k_5 = 1;
        double k_6 = 1;
        double gemma = 1;
        double lamda_3 = 1.2;
        double mu_B = 1.2;

        return lamda_1+ (k_5*Math.pow((k_6),2)) / (Math.pow((k_6),2)+ gemma *Math.pow((w),2)) +  lamda_3* (x) - (mu_B)*y;
    }

    public static double dz(double w, double x, double y, double z) {
       // double lamda_1 = 0.2;
        double lamda_2 = 0.2;
        double k_7 = 4;
        double k_8 = 1;
        double delta = 1;
        double mu_X = 5;
        

        return lamda_2 + (k_7*Math.pow((k_8),2)) / (Math.pow((k_8),2) + delta*Math.pow((y),2))- (mu_X)*z;
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
    
    public double getz(){
        return _z;
    }
    


    public static void main(String[] args) throws FileNotFoundException {

        //initial points 
        double w = 0;
        double x = 0;
        double y = 0;
        double z = 0;
        double dt = 0.0001; 
        double time = 10;
        double iteration = time/dt;


        // ode ODE = new ode();

        
        File CSVfile = new File("readingsrunge.csv");

        ArrayList<runge> values= new  ArrayList<>();
        //values is the arraylist created to store multiple values that are stored using the value object in the line 138
        //value object has 4 values namely w,x,y and z
        PrintWriter pw = new PrintWriter(CSVfile);
        // Use Euler's method to numerically solve ODE
        for (int i = 0; i < iteration; i++) {

            double k1w = dw(w, x, y, z) * dt;
            double k1x = dx(w, x, y, z) * dt;
            double k1y = dy(w, x, y, z) * dt;
            double k1z = dz(w, x, y, z) * dt;
    
            double k2w = dw(w + k1w/2, x + k1x/2, y + k1y/2, z + k1z/2) * dt;
            double k2x = dx(w + k1w/2, x + k1x/2, y + k1y/2, z + k1z/2) * dt;
            double k2y = dy(w + k1w/2, x + k1x/2, y + k1y/2, z + k1z/2) * dt;
            double k2z = dz(w + k1w/2, x + k1x/2, y + k1y/2, z + k1z/2) * dt;
    
            double k3w = dw(w + k2w/2, x + k2x/2, y + k2y/2, z + k2z/2) * dt;
            double k3x = dx(w + k2w/2, x + k2x/2, y + k2y/2, z + k2z/2) * dt;
            double k3y = dy(w + k2w/2, x + k2x/2, y + k2y/2, z + k2z/2) * dt;
            double k3z = dz(w + k2w/2, x + k2x/2, y + k2y/2, z + k2z/2) * dt;
    
            double k4w = dw(w + k3w, x + k3x, y + k3y, z + k3z) * dt;
            double k4x = dx(w + k3w, x + k3x, y + k3y, z + k3z) * dt;
            double k4y = dy(w + k3w, x + k3x, y + k3y, z + k3z) * dt;
            double k4z = dy(w + k3w, x + k3x, y + k3y, z + k3z) * dt;

            
    
        // update variables
        w =w+  (k1w + 2*k2w + 2*k3w + k4w) / 6;
        x =x+  (k1x + 2*k2x + 2*k3x + k4x) / 6;
        y =y+  (k1y + 2*k2y + 2*k3y + k4y) / 6;
        z =z+  (k1z + 2*k2z + 2*k3z + k4z) / 6;
    
        // store values in cancer object
        runge value= new runge(w, x, y, z);
        values.add(value);
    
    //     // write values to CSV file
    //     StringBuilder sb = new StringBuilder();
    //     sb.append(w);
    //     sb.append(",");
    //     sb.append(x);
    //     sb.append(",");
    //     sb.append(y);
    //     sb.append(",");
    //     sb.append(z);
    //     sb.append("\n");
    //     pw.write(sb.toString());
    
    // }
    // pw.close();
    

            
            // w = wnew;
            // x = xnew;
            // y = ynew;
            // z = znew;
            
           
            
            
            
            //printwriter is used to create the CSV file that is used to store the data
            
            //4 columns are defined in order to name the header row of the table
            
        }
            String col1 = "w";
            String col2 = "x";
            String col3 = "y";
            String col4 = "z";
            
            pw.printf("%s,%s,%s,%s",col1,col2,col3,col4 ); 
            pw.print("\n");
            
            for(runge reading: values){
            //every single element of the arraylist is taken into account and looped so that 
            //it is printed in the CSV file created above
            // System.out.println(reading.getw()+" "+reading.getx()+" "+reading.gety()+" "+reading.getz());
            pw.printf("%f,%f,%f,%f",reading.getw(),reading.getx(),reading.gety(),reading.getz());
            //the 4 values obtained for w,x,y and z are printed in the CSV file.
            pw.print("\n");
            //the printwriter has to be closed in oder for the code to finish execution of the CSV file creation.
        }
        pw.close();

    }
}
