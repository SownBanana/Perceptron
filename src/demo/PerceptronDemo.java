package demo;

import javax.swing.*;
import java.awt.*;

import java.util.*;
import java.io.*;
import java.math.*;
import java.text.*;

public class PerceptronDemo{
    final static int MAX_LOOP = 100;
    final static double LEARNING_RATE = 0.1;
    final static int NUM_INSTANCE = 10;
    final static int theta = 0;

    public static void main(String[] args) {
//        2 input variable
        double[] x1 = new double[NUM_INSTANCE];
        double[] x2 = new double[NUM_INSTANCE];
//        double[] x3 = new double[NUM_INSTANCE];
        int[] rClass = new int[NUM_INSTANCE];
//                                                      random point of class red
        for (int i = 0; i < NUM_INSTANCE/2; i++) {
            x1[i] = randomDouble(5, 10) ;
            x2[i] = randomDouble(4, 8);
//            x3[i] = randomDouble(2, 9);
            rClass[i] = 1;
            System.out.println(x1[i] + "\t" + x2[i] + "\t" /*+ x3[i] + "\t"*/ + rClass[i]);
        }
//                                                       random point of class blue
        for (int i = NUM_INSTANCE/2; i < NUM_INSTANCE; i++) {
            x1[i] = randomDouble(-1, 3);
            x2[i] = randomDouble(-4, 2);
//            x3[i] = randomDouble(-3, -5);
            rClass[i] = 0;
            System.out.println(x1[i] + "\t" + x2[i] + "\t" /*+ x3[i] + "\t"*/ + rClass[i]);
        }
//        weight
        double[] w = new double[4]; //2 for input & 1 for bias

        w[0] = 1; //for bias
        w[1] = randomDouble(0,1);
        w[2] = randomDouble(0,1);
//        w[3] = randomDouble(0,1);
        System.out.println("\n======> First Boundary :" + w[1]+ "x1 + " + w[2] + "x2 + "  /*+ w[3] + "x3 + "*/+ w[0] + " " +
                "= 0");
        double localError; // y
        double globalError;
        int i, index, p, pClass;

        index = 0;
        globalError = 2;

        while (globalError != 0 && index < MAX_LOOP){
            index++;
            globalError = 0;
            //loop p through all instance
            for (p = 0; p < NUM_INSTANCE; p++) {
//                                                                    predict class
                pClass = calculateOutput(theta, w, x1[p], x2[p]/*, x3[p]*/);
//                                                                    difference between predict and reality
                localError = rClass[p] - pClass;
//                                                                     update weight
                w[0] += LEARNING_RATE * localError;
                w[1] += LEARNING_RATE * localError * x1[p];
                w[2] += LEARNING_RATE * localError * x2[p];
//                w[3] += LEARNING_RATE * localError * x3[p];

                globalError += (localError*localError);

            }
            System.out.println("Loop " + index + ": RMSE = "+ Math.sqrt(globalError/NUM_INSTANCE));
        }
        System.out.println("\n======> Boundary :" + w[1]+ "x1 + " + w[2] + "x2 + "  /*+ w[3] + "x3 + "*/+ w[0] + " = 0");

//       ============ check answer
        double randomTest = Math.random();
        int k;
        for (k = 0; k < 10; k++) {
            double a = randomDouble(-10, 10);
            double b = randomDouble(-10, 10);
//            double c = randomDouble(-10, 10);

            pClass = calculateOutput(theta, w, a, b/*,c*/);
            System.out.println("Random point: x1: " + a + ", x2: "+ b /*+ ", x3: "+c*/);
            System.out.println("Class: "+ pClass);
        }
    }

//    public static int calculateOutput(int theta, double w[], double x1, double x2){
//        double sum = x1*w[1] + x2*w[2] + w[0];
//        return (sum >= theta)?1:0;
////        return sum;
//    }
//    public static int calculateOutput(int theta, double w[], double x1, double x2, double x3){
//        double sum = x1*w[1] + x2*w[2] + x3*w[3] + w[0];
//        return (sum >= theta)?1:0;
////        return sum;
//    }
        public static int calculateOutput(int theta, double w[], double...m){
            int i = 1;
            double sum = 0;
            for (double x:m) {
                sum += x*w[i++];
            }
            sum +=  w[0];
            return (sum >= theta)?1:0;
//        return sum;
        }
    public static double randomDouble(int min, int max){
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        double d = min + Math.random() *(max - min);
        String s = decimalFormat.format(d);
        double rs = Double.parseDouble(s);
        return rs;
    }
}




















