import java.text.DecimalFormat;
import java.util.Scanner;

public class Perceptron {
    final static int MAX_LOOP = 100;
    final static double LEARNING_RATE = 0.1;
    final static int NUM_INSTANCE = 4;
    final static int theta = 0;

    double[] x1 = new double[NUM_INSTANCE];
    double[] x2 = new double[NUM_INSTANCE];
    int[] rClass = new int[NUM_INSTANCE];
    double[] w = new double[3];

    public Perceptron() {
    }
    public Perceptron(int w0){
        this.w[0] = 1;
    }
    public double[] train(){
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
                pClass = calculateOutput(theta, w, x1[p], x2[p]);
//                                                                    difference between predict and reality
                localError = rClass[p] - pClass;
//                                                                     update weight
                w[0] += LEARNING_RATE * localError;
                w[1] += LEARNING_RATE * localError * x1[p];
                w[2] += LEARNING_RATE * localError * x2[p];

                globalError += (localError*localError);

            }
            System.out.println("Loop " + index + ": RMSE = "+ Math.sqrt(globalError/NUM_INSTANCE));
        }
        System.out.println("\n======> Boundary :" + w[1]+ "x1 + " + w[2] + "x2 + " + w[0] + " = 0");
        return w;
    }
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
