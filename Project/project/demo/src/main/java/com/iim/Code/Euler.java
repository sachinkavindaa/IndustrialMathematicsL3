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


public final class Euler {
 

    // Global variable for values obatain from w,x,y and z. Global variable can be accesed anywhere within the class. 
private double _w;
private double _x;
private double _y;
private double _z;

    public Euler(double w, double x, double y, double z){

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
    
            File CSVfile = new File("Euler.csv");
    
            ArrayList<Euler> values= new  ArrayList<>();
            //values is the arraylist created to store multiple values that are stored using the value object in the line 138
            //value object has 4 values namely w,x,y and z
            PrintWriter pw = new PrintWriter(CSVfile);
            // Use Euler's method to numerically solve ODE
            for (int i = 0; i < iteration; i++) {
    
                double dw = dw(w, x, y, z);
                double dx = dx(w, x, y, z);
                double dy = dy(w, x, y, z);
                double dz = dz(w, x, y, z);
                w = w + dw*dt;
                x = x + dx*dt;
                y = y + dy*dt;
                z = z + dz*dt;
    
                Euler value= new Euler(w, x, y, z);
                values.add(value);
    
                if(i%1000 ==0){
                    System.out.println("w: "+w+" x: "+x+" y: "+y+" z: "+z);
                }

            }

            String col1 = "w";
            String col2 = "x";
            String col3 = "y";
            String col4 = "z";
            
            pw.printf("%s,%s,%s,%s",col1,col2,col3,col4 ); 
            pw.print("\n");
            
            for(Euler reading: values){
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
