package multihiddenlayer.neuralnetwork;

import java.util.Arrays;

public class NeuralNetwork {            //1 hidden layer
    final static int INPUT_UNIT = Calculation.inputData[0].length;
    final static int OUTPUT_UNIT = 1;
    final static double LEARNING_RATE = 0.1;

    private int numOfHiddenUnits;
    private Layer[] layers = new Layer[Layer.layerType.values().length];

    public NeuralNetwork(int numOfHiddenUnits) {
        this.numOfHiddenUnits = numOfHiddenUnits;
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(this, Layer.layerType.values()[i]);
        }
    }

    public int getNumOfHiddenUnits() {
        return numOfHiddenUnits;
    }

    public void setNumOfHiddenUnits(int numOfHiddenUnits) {
        this.numOfHiddenUnits = numOfHiddenUnits;
    }

    public Layer[] getLayers() {
        return layers;
    }

    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    @Override
    public String toString() {
        return "{" +
                Arrays.toString(layers) +
                '}';
    }

    public NeuralNetwork feedForward(double input[]){

        for (int i = 0; i < layers.length; i++) {
            switch (layers[i].getLayerType()){
                case I:{
                    for (int j = 0; j < layers[i].getNeurons().length; j++) {
                        layers[i].getNeurons()[j].setOutput(input[j]);
                    }
                    break;
                }
                case H: {
                    for (int j = 0; j < layers[i].getNeurons().length; j++) {
                        double weightSum = 0;
                        for (int k = 0; k < layers[i].getNeurons()[j].getWeights().length; k++) {
                            weightSum += layers[i].getNeurons()[j].getWeights()[k] * layers[i-1].getNeurons()[k].getOutput();
                        }
                        layers[i].getNeurons()[j].calOutput(weightSum);                //calculate activation
                    }
                    break;
                }
                case O: {
                    double weightSum = 0;
                    for (int j = 0; j < layers[i].getNeurons()[0].getWeights().length; j++) {
                        weightSum += layers[i].getNeurons()[0].getWeights()[j]* layers[i-1].getNeurons()[j].getOutput();
                    }
                    layers[i].getNeurons()[0].calOutput(weightSum);                //calculate activation
                    break;
                }
            }
        }
        return this;
    }


    public NeuralNetwork backPropagation(double targetRs){
        Neuron[] inputLayer = layers[0].getNeurons();
        Neuron[] hiddenLayer = layers[1].getNeurons();
        Neuron outputLayer = layers[2].getNeurons()[0];
        //update weight for output layer    dJ/dw = dJ/dout * dout/dnet * dnet/dw
        //dJ/d
        outputLayer.setError((targetRs - outputLayer.getOutput())*outputLayer.derivativeOutput()); //dJ/dout * dout/dnet
        for (int i = 0; i < outputLayer.getWeights().length; i++) {
            outputLayer.getWeights()[i] =
                    outputLayer.getWeights()[i] + LEARNING_RATE*outputLayer.getError()*hiddenLayer[i].getOutput();
        }
        //update weight for hidden layer
        for (int i = 0; i < hiddenLayer.length; i++) {
            hiddenLayer[i].setError(outputLayer.getWeights()[i]*outputLayer.getError()*hiddenLayer[i].derivativeOutput());
            for (int j = 0; j < hiddenLayer[i].getWeights().length; j++) {
                hiddenLayer[i].getWeights()[j] =
                        hiddenLayer[i].getWeights()[j] + LEARNING_RATE*hiddenLayer[i].getError()*inputLayer[j].getOutput();
            }
        }

        return this;
    }
}
