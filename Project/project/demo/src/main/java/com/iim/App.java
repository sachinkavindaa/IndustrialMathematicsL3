 package com.iim;
//  import com.zetcode;

import java.io.IOException;
import java.io.PrintWriter;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
 import java.util.ArrayList;


/**
 * Hello world!
 */
public final class App {

private double _x;
private double _y;
private double _z;



    public App(double x, double y, double z){
        _x = x;
        _y = y;
        _z = z;

        
    }

    public static double dx(double x, double y, double z) {

        double a = 0.1;
        return -10*(x - y)+a;
    }

    public static double dy(double x, double y, double z) {
        return -x*z + 28*x - y;
    }

    public static double dz(double x, double y, double z) {
        return x*y - 8*z/3;
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
        double x = 0.0, y = 20.0, z = 25.0;
        double dt = 0.001; 
        int time = 6000;

        //StdDraw.clear(StdDraw.LIGHT_GRAY);
        //StdDraw.setXscale(-25, 25);
        //StdDraw.setYscale(  0, 50);
        //StdDraw.enableDoubleBuffering();
        File CSVfile = new File("readingsApp.csv");
        ArrayList<App> values= new  ArrayList<>();
        // Use Euler's method to numerically solve ODE
        for (int i = 0; i < time; i++) {

            // original system
            double xnew = x + dx(x, y, z) * dt;
            double ynew = y + dy(x, y, z) * dt;
            double znew = z + dz(x, y, z) * dt;
            x = xnew;
            y = ynew;
            z = znew;
      

            App value = new App(x, y, z);
            values.add(value);
            
            PrintWriter pw = new PrintWriter(CSVfile);
            String col1 = "x";
            String col2 = "y";
            String col3 = "z";
            
            pw.printf("%s,%s,%s",col1,col2,col3 ); 
            pw.print("\n");
            
            for(App reading: values){
                pw.printf("%f,%f,%f",reading.getx(),reading.gety(),reading.getz());
                pw.print("\n");
                
            }

            
        
        }
    }
}
