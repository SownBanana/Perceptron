package demo.neuralnetwork;

public class Neuron {
    private LayerType.layerType layerType;
    private double[] weights;        //W
    private double bias;            //b
    private double output;          //activation - a
    private double error;           //lỗi - y hat

    public Neuron(LayerType.layerType layerType) {
        this.layerType = layerType;
        weights = new double[]{0.5 - Math.random(), 0.5 - Math.random()};
        bias = 0.5 - Math.random();
        output = 0;
        error = 0;
    }

    public Neuron(LayerType.layerType layerType, double[] weights) {
        this.layerType = layerType;
        this.weights = weights;
        this.bias = 0.5 - Math.random();
        output = 0;
        error = 0;
    }

    public LayerType.layerType getLayerType() {
        return layerType;
    }

    public void setLayerType(LayerType.layerType layerType) {
        this.layerType = layerType;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    @Override
    public String toString() {
        if (layerType == LayerType.layerType.I) return "("+layerType+": "+output+")";
        else return "(" +
                 layerType +
                ": " + String.format("%.3f", weights[0]) +
                " " +  String.format("%.3f", weights[1]) +
                " " +  String.format("%.3f",bias) +
                " " +  String.format("%.5f",output) +
                ')';
    }

    public double sigmoid( double value){
        return 1 / (1 + Math.exp(-1 * value));
    }
    public void calOutput(double weightSum){              //apply the activation (sigmoid)
        this.output = sigmoid(weightSum);
    }
    public double derivativeOutput(){
        return this.output*(1 - this.output);              //sigmoid' = sigmoid*(1-sigmoid)
    }

}
