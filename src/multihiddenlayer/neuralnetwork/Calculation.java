package multihiddenlayer.neuralnetwork;

import java.util.Scanner;

public class Calculation {
    final static int NUMB_OF_EPOCHS = 20000;
    final static int NUMB_OF_TRAIN_CASE = 10;
    final static int DIMENSION_OF_INPUT = 3;
    final static int INPUT_RANGE = 2;
    static double[][] inputData;// = {{1,0,0}, {1,0,1}, {1,1,0}, {1,1,1}};
    static double[] targetResult;// = {0, 1, 1, 0};

    static String arrToString(double[] arr){
        String rs = "";
        for (int i = 0; i < arr.length; i++) {
            rs = rs + " O["+ i +"]: " + String.format("%.2f", arr[i]);
        }
        return rs;
    }
    static void showResult(double[] result, double[][] hiddenOutput){
        System.out.println(" " +
                "_______________________________________________________________________________________________________________");
        System.out.println("|      A      |      B      |    Kết quả thực   |             Hidden Output            " +
                " |  Kết " +
                "quả  |   Khớp    |");
        System.out.println(" " +
                "_______________________________________________________________________________________________________________");
        for (int i = 0; i < inputData.length; i++) {
            System.out.println("|    "+String.format("%.3f",inputData[i][1])+"    |"+"    "+String.format("%.3f",
                    inputData[i][2])+"    |"+
                    "       "+String.format("%.3f",targetResult[i])+"       |"+arrToString(hiddenOutput[i]) +
                    "      |  "+ String.format("%.5f",
                    result[i]) +
                    "  |"+
                    "   "+String.format("%.2f", (targetResult[i] == 1)?result[i]*100:(1-result[i])*100) + "%  |");
        }
        System.out.println(" ________________________________________________________________");
    }
    static void ramdomDataSet(){
        inputData = new double[NUMB_OF_TRAIN_CASE][DIMENSION_OF_INPUT];
        targetResult = new double[NUMB_OF_TRAIN_CASE];

        for (int i = 0; i < NUMB_OF_TRAIN_CASE/4; i++) {
            inputData[i][0] = 1;
            inputData[i][1] = (-Math.random())*INPUT_RANGE;
            inputData[i][2] = (-Math.random())*INPUT_RANGE;
            targetResult[i] = 0;
        }
        for (int i = NUMB_OF_TRAIN_CASE/4; i < NUMB_OF_TRAIN_CASE/2; i++) {
            inputData[i][0] = 1;
            inputData[i][1] = (-Math.random())*INPUT_RANGE;
            inputData[i][2] = (Math.random())*INPUT_RANGE;
            targetResult[i] = 1;
        }
        for (int i = NUMB_OF_TRAIN_CASE/2; i < 3*NUMB_OF_TRAIN_CASE/4; i++) {
            inputData[i][0] = 1;
            inputData[i][1] = (Math.random())*INPUT_RANGE;
            inputData[i][2] = (-Math.random())*INPUT_RANGE;
            targetResult[i] = 1;
        }
        for (int i = 3*NUMB_OF_TRAIN_CASE/4; i < NUMB_OF_TRAIN_CASE; i++) {
            inputData[i][0] = 1;
            inputData[i][1] = (Math.random())*INPUT_RANGE;
            inputData[i][2] = (Math.random())*INPUT_RANGE;
            targetResult[i] = 0;
        }
        for (int i = 0; i < NUMB_OF_TRAIN_CASE; i++) {
            System.out.println(inputData[i][0] + ", " +inputData[i][1] + ", " + inputData[i][2] + " : " + targetResult[i]);
        }
    }
    public static void XORDataSet(){
        inputData = new double[][]{{1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
        targetResult = new double[]{0, 1, 1, 0};
        for (int i = 0; i < inputData.length; i++) {
            System.out.println(inputData[i][0] + ", " +inputData[i][1] + ", " + inputData[i][2] + " : " + targetResult[i]);
        }
    }
    public static void main(String[] args) {
//        ramdomDataSet();
        XORDataSet();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số neuron của hidden layer: ");
        int numofhidden = scanner.nextInt();
        NeuralNetwork neuralNetwork = new NeuralNetwork(numofhidden);
        boolean check = true;
        while (check){
            System.out.println("1. tính  |   " +
                    "2. train   |   " +
                    "3. nhập    |" +
                    "4. thoát    |");
            int selection = scanner.nextInt();
            switch (selection){
                case 1:{
                    double[] result = new double[inputData.length];
                    double[][] hiddenOutput = new double[inputData.length][numofhidden];
                    for (int i = 0; i < inputData.length; i++) {
                        NeuralNetwork show = neuralNetwork.feedForward(inputData[i]);
                        result[i] = show.getLayers()[2].getNeurons()[0].getOutput();
                        for (int j = 0; j < numofhidden; j++) {
                            hiddenOutput[i][j] = show.getLayers()[1].getNeurons()[j].getOutput();
                        }
                    }
                    showResult(result, hiddenOutput);
                    break;
                }
                case 2:{
                    System.out.println("training...");
                    for (int i = 0; i < NUMB_OF_EPOCHS; i++) {
//                        System.out.println("Vòng lặp "+i);
                        for (int j = 0; j < inputData.length; j++) {
                            neuralNetwork.feedForward(inputData[j]).backPropagation(targetResult[j]);
//                            System.out.println(neuralNetwork.toString());
                        }
                    }
                    System.out.println("Done!");
                    break;
                }
                case 3:{
                    double input[] = new double[3];
                    input[0] = 1;
                    input[1] = scanner.nextInt();
                    input[2] = scanner.nextInt();
                    System.out.println(neuralNetwork.feedForward(input).getLayers()[2].getNeurons()[0].getOutput());
                    break;
                }
                case 4:{
                    check = false;
                    break;
                }
            }
        }
    }
}
