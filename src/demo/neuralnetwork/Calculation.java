package demo.neuralnetwork;

import java.util.Scanner;

public class Calculation {
    final static int NUMB_OF_EPOCHS = 20000;
    static double inputData[][] = {{0,0}, {0,1}, {1,0}, {1,1}};
    static double targetResult[] = {0, 1, 1, 0};


    static void showResult(double[] result){
        System.out.println(" ________________________________________________________________");
        System.out.println("|     A     |     B     |  Target Result  |  Result  |   Match   |");
        System.out.println(" ________________________________________________________________");
        for (int i = 0; i < inputData.length; i++) {
            System.out.println("|    "+inputData[i][0]+"    |"+"    "+inputData[i][1]+"    |"+
                    "       "+targetResult[i]+"       |"+" "+ String.format("%.5f",result[i]) +"  |"+
                    "   "+String.format("%.2f", (targetResult[i] == 1)?result[i]*100:(1-result[i])*100) + "%  |");
        }
        System.out.println(" ________________________________________________________________");
    }
    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork();
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check){
            System.out.println("1. run  |   " +
                    "2. train   |   " +
                    "3. exit    |");
            int selection = scanner.nextInt();
            switch (selection){
                case 1:{
                    double[] result = new double[4];
                    for (int i = 0; i < inputData.length; i++) {
                        result[i] =
                                neuralNetwork.feedForward(inputData[i])
                                        .getNeurons()[NeuralNetwork.INPUT_UNIT + NeuralNetwork.HIDDEN_UNIT].getOutput();
                    }
                    showResult(result);
                    break;
                }
                case 2:{
                    for (int i = 0; i < NUMB_OF_EPOCHS; i++) {
                        for (int j = 0; j < inputData.length; j++) {
                            neuralNetwork.feedForward(inputData[j]).backPropagation(targetResult[j]);
                            System.out.println(neuralNetwork.toString());
                            System.out.println("==");
                        }
                    }
                    System.out.println("Done!");
                    break;
                }
                case 3:{
                    check = false;
                    break;
                }
            }
        }
    }
}
