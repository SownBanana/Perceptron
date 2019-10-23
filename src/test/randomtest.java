package test;

public class randomtest {
    public static void main(String[] args) {
        double[] a = new double[10];
        double[] b = new double[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = ( - Math.random())*100;
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + "");
        }
    }
}
