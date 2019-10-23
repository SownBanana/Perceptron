package demo;

import java.util.Scanner;

public class Control {
    public static double[] inputDouble(int NUM_INSTANCE){
        double[] x = new double[NUM_INSTANCE];
        for (int i = 0; i < NUM_INSTANCE; i++) {
            x[i] = new Scanner(System.in).nextDouble();
        }
        return x;
    }
    public static int[] inputInt(int NUM_INSTANCE){
        int[] x = new int[NUM_INSTANCE];
        for (int i = 0; i < NUM_INSTANCE; i++) {
            x[i] = new Scanner(System.in).nextInt();
        }
        return x;
    }
}
