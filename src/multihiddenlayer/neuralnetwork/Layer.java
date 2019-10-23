package multihiddenlayer.neuralnetwork;

import java.util.Arrays;

public class Layer {
    static enum layerType {I, H, O};
    private layerType layerType;
    private Neuron[] neurons;

    public Layer(NeuralNetwork neuralNetwork, layerType layerType) {
        this.layerType = layerType;
        switch (layerType){
            case I:{
                neurons = new Neuron[NeuralNetwork.INPUT_UNIT];
                for(int i = 0; i < NeuralNetwork.INPUT_UNIT; i++){
                    neurons[i] = new Neuron(layerType.I, 0);
                }
                break;
            }
            case H:{
                neurons = new Neuron[neuralNetwork.getNumOfHiddenUnits()];
                for (int i = 0; i < neuralNetwork.getNumOfHiddenUnits(); i++) {
                    neurons[i] = new Neuron(layerType.H, NeuralNetwork.INPUT_UNIT);
                }
                break;
            }
            case O:{
                neurons = new Neuron[NeuralNetwork.OUTPUT_UNIT];
                for (int i = 0; i < NeuralNetwork.OUTPUT_UNIT; i++) {
                    neurons[i] = new Neuron(layerType.O, neuralNetwork.getNumOfHiddenUnits());
                }
            }
        }
    }

    public Layer.layerType getLayerType() {
        return layerType;
    }

    public void setLayerType(Layer.layerType layerType) {
        this.layerType = layerType;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public void setNeurons(Neuron[] neurons) {
        this.neurons = neurons;
    }

    @Override
    public String toString() {
        return "Layer{" +
                 layerType +
                ", " + Arrays.toString(neurons) +
                '}';
    }
}
