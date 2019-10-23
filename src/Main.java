import demo.Control;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input: ");
        double x1 = new Scanner(System.in).nextDouble();
        double x2 = new Scanner(System.in).nextDouble();
        System.out.println(x1 + " xor " + x2 + " = " + XOR(x1,x2));
        return;
    }
    public static double XOR(double x1, double x2){
        double[] inAnd = new double[PerceptronLogic.NUM_INPUT];
        double[] inOr = new double[PerceptronLogic.NUM_INPUT];
        double[] inNand = new double[PerceptronLogic.NUM_INPUT];
        inAnd[0] = 0; inAnd[1] = 0; inAnd[2] = 0; inAnd[3] = 1;
        inOr[0] = 0; inOr[1] = 1; inOr[2] = 1; inOr[3] = 1;
        inNand[0] = 1; inNand[1] = 1; inNand[2] = 1; inNand[3] = 0;

        PerceptronLogic and = new PerceptronLogic(0);
        PerceptronLogic or = new PerceptronLogic(0);
        PerceptronLogic nand = new PerceptronLogic(0);

//        double positiveInfinity = Double.POSITIVE_INFINITY;
//        double negativeInfinity = Double.NEGATIVE_INFINITY;
//        for (int i = 4; i < PerceptronLogic.NUM_INSTANCE/4 ; i++){
//            and.x1[i] = Controller.randomDouble(-1000, 0);
//            and.x2[i] = Controller.randomDouble(-1000, 0);
//
//            inAnd[i] = 0.0;
//
//            or.x1[i] = Controller.randomDouble(-1000, 0);
//            or.x2[i] = Controller.randomDouble(-1000, 0);
//            inOr[i] = 0.0;
//
//            nand.x1[i] = Controller.randomDouble(-1000, 0);
//            nand.x2[i] = Controller.randomDouble(-1000, 0);
//            inNand[i] = 1.0;
//        }
//        for (int i = PerceptronLogic.NUM_INSTANCE/4; i < PerceptronLogic.NUM_INSTANCE/2 ; i++){
//            and.x1[i] = Controller.randomDouble(-1000, 0);
//            and.x2[i] = Controller.randomDouble(0, 1000);
//            inAnd[i] = 0;
//
//            or.x1[i] = Controller.randomDouble(-1000, 0);
//            or.x2[i] = Controller.randomDouble(0, 1000);
//            inOr[i] = 1.0;
//
//            nand.x1[i] = Controller.randomDouble(-1000, 0);
//            nand.x2[i] = Controller.randomDouble(0, 1000);
//            inNand[i] = 1.0;
//        }
//        for (int i = PerceptronLogic.NUM_INSTANCE/2; i < 3*PerceptronLogic.NUM_INSTANCE/4 ; i++){
//            and.x1[i] = Controller.randomDouble(0, 1000);
//            and.x2[i] = Controller.randomDouble(-1000, 0);
//            inAnd[i] = 0;
//
//            or.x1[i] = Controller.randomDouble(0, 1000);
//            or.x2[i] = Controller.randomDouble(-1000, 0);
//            inOr[i] = 1.0;
//
//            nand.x1[i] = Controller.randomDouble(0, 1000);
//            nand.x2[i] = Controller.randomDouble(-1000, 0);
//            inNand[i] = 1.0;
//        }
//        for (int i = 3*PerceptronLogic.NUM_INSTANCE/4; i < PerceptronLogic.NUM_INSTANCE ; i++){
//            and.x1[i] = Controller.randomDouble(0, 1000);
//            and.x2[i] = Controller.randomDouble(0, 1000);
//            inAnd[i] = 1.0;
//
//            or.x1[i] = Controller.randomDouble(0, 1000);
//            or.x2[i] = Controller.randomDouble(0, 1000);
//            inOr[i] = 1.0;
//
//            nand.x1[i] = Controller.randomDouble(0, 1000);
//            nand.x2[i] = Controller.randomDouble(0, 1000);
//            inNand[i] = 0.0;
//        }
        and.rClass = inAnd;
        and.train();
        or.rClass = inOr;
        or.train();
        nand.rClass = inNand;
        nand.train();

        double gateNand = nand.calculateOutput(x1, x2);
        double gateOr = or.calculateOutput(x1, x2);
        System.out.println(x1 + " or " + x2 + " = "+ gateOr);
        System.out.println(x1 + " nand " + x2 + " = "+ gateNand);
        System.out.println(x1 + " and " + x2 + " = "+ and.calculateOutput(x1, x2));
        System.out.println(and.calculateOutput(and.calculateOutput(1, 0)));
        System.out.println(and.calculateOutput(1,1));
        System.out.println(and.calculateOutput(0,0));
        return and.calculateOutput(gateNand, gateOr);
    }
}
