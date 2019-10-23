package multihiddenlayer.neuralnetwork;

import java.util.Arrays;

public class Neuron {
    private Layer.layerType layerType;
    private double[] weights;        //W
    private double output;          //activation - a
    private double error;           //lỗi - y hat



    public Neuron(Layer.layerType layerType, int numOfWeight) {
        this.layerType = layerType;
        if (layerType != Layer.layerType.I){
            this.weights = new double[numOfWeight];
            weights[0] = 1.0;                       //bias
            for (int i = 1; i < numOfWeight; i++) {
                weights[i] = 0.5 - Math.random();
            }
        }
        output = 0;
        error = 0;
    }

    public Layer.layerType getLayerType() {
        return layerType;
    }

    public void setLayerType(Layer.layerType layerType) {
        this.layerType = layerType;
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
        if (layerType == Layer.layerType.I) return "("+layerType+": "+output+")";
        else return "(" +
                 layerType +
                ": " + String.format("%.3f", weights[0]) +
                " " +  String.format("%.3f", weights[1]) +
//                " " +  String.format("%.3f", weights[2]) +
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
