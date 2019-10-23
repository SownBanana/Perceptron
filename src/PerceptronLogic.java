import java.util.Scanner;

public class PerceptronLogic {
    final static int MAX_LOOP = 100;
    final static double LEARNING_RATE = 0.3;
    final static int NUM_INPUT = 40;
//    final static int theta = 0;

    double[] x1 = new double[NUM_INPUT];
    double[] x2 = new double[NUM_INPUT];
    double[] rClass = new double[NUM_INPUT];
    double[] w = new double[3];

    public PerceptronLogic() {
    }
    public PerceptronLogic(int w0){

        x1[0] = 0;  x2[0] = 0;
        x1[1] = 0;  x2[1] = 1;
        x1[2] = 1;  x2[2] = 0;
        x1[3] = 1;  x2[3] = 1;

     }
    public double[] train(){
        double error; // y
        double cost;
        int i, index, p;
        double pClass;
        w[0] = 1; //for bias
        w[1] = Math.random();
        w[2] = Math.random();
        index = 0;
        cost = 1;

        while (cost != 0 && index < MAX_LOOP){
            index++;
            cost = 0;
            //loop p through all instance
            for (i = 0; i < NUM_INPUT; i++) {
//                                                                    predict class
                pClass = calculateOutput(x1[i], x2[i]);
//                                                                    difference between predict and reality
                error = rClass[i] - pClass;
//                                                                     update weight
                w[0] += LEARNING_RATE * error;
                w[1] += LEARNING_RATE * error * x1[i];
                w[2] += LEARNING_RATE * error * x2[i];

                cost += (error*error);

            }
//            System.out.println("Loop " + index + ": MSE = "+ (cost/NUM_INPUT));
        }
//        System.out.println("\n======>" + w[1]+ "x1 + " + w[2] + "x2 + " + w[0] + " = 0");
        return w;
    }

    public double calculateOutput(double...m){
        int i = 1;
        double sum = w[0];
        for (double x:m) {
            sum += x*w[i++];
        }
//        return (sum >= 0.5)?1:0;                            // hàm dấu
        return 1.0 / (1 + Math.exp(-1.0 * sum));            // hàm sigmoid
    }

    void randomInput(){
        for (int i = 0; i < PerceptronLogic.NUM_INPUT/4 ; i++){
            x1[i] = Math.random()/2;
            x2[i] = Math.random()/2;
            rClass[i] = 0;
        }
        for (int i = PerceptronLogic.NUM_INPUT/4; i < PerceptronLogic.NUM_INPUT/2 ; i++){
            x1[i] = Math.random()/2;
            x2[i] = Math.random()/2 + 0.5;
            rClass[i] = 1;
        }
        for (int i = PerceptronLogic.NUM_INPUT/2; i < 3*PerceptronLogic.NUM_INPUT/4 ; i++){
            x1[i] = Math.random()/2 + 0.5;
            x2[i] = Math.random()/2;
            rClass[i] = 1;
        }
        for (int i = 3*PerceptronLogic.NUM_INPUT/4; i < PerceptronLogic.NUM_INPUT ; i++){
            x1[i] = Math.random()/2 + 0.5;
            x2[i] = Math.random()/2 + 0.5;
            rClass[i] = 1;
        }
//        for (int i = 0; i < NUM_INPUT; i++) {
//            System.out.println(x1[i] + " " + x2[i]+ " "+ rClass[i]);
//        }
    }
    public static void main(String[] args) {
        PerceptronLogic or = new PerceptronLogic();
        or.randomInput();
        or.train();
        String exit = "";
        while(exit != "exit"){
            System.out.println("Input: ");
            double x1 = new Scanner(System.in).nextDouble();
            double x2 = new Scanner(System.in).nextDouble();
            System.out.println(x1 + " or " + x2 + " = " + or.calculateOutput(x1, x2));
//            System.out.println("Tiếp?");
//            exit = new Scanner(System.in).nextLine();
        }
    }
}
