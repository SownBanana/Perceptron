import java.text.DecimalFormat;

public class Controller {
    public static double randomDouble(double min, double max){
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        double d = min + Math.random() *(max - min);
        String s = decimalFormat.format(d);
        double rs = Double.parseDouble(s);
        return rs;
    }
}
