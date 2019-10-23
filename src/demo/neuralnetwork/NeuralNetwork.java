package demo.neuralnetwork;

import java.util.Arrays;

public class NeuralNetwork {            //1 hidden layer
    final static int INPUT_UNIT = 2;
    final static int HIDDEN_UNIT = 2;
    final static double LEARNING_RATE = 0.1;
    private Neuron[] neurons;

    public NeuralNetwork() {
        this.neurons = neurons = new Neuron[INPUT_UNIT + HIDDEN_UNIT + 1];      //+1 output unit
        for(int i = 0; i < INPUT_UNIT; i++){
            neurons[i] = new Neuron(LayerType.layerType.I);
        }
        for (int i = INPUT_UNIT; i < INPUT_UNIT + HIDDEN_UNIT; i++) {
            neurons[i] = new Neuron(LayerType.layerType.H);
        }
        neurons[INPUT_UNIT + HIDDEN_UNIT] = new Neuron(LayerType.layerType.O);
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public void setNeurons(Neuron[] neurons) {
        this.neurons = neurons;
    }

    @Override
    public String toString() {
        return "NeuralNetwork{" +
                 Arrays.toString(neurons) +
                '}';
    }

    public NeuralNetwork feedForward(double input[]){
        double weightSum = 0;
        int index = 0;
        int indexO = 2;
        for (int i = 0; i < neurons.length; i++) {
            switch (neurons[i].getLayerType()){
                case I:{
                    neurons[i].setOutput(input[i]);
                    break;
                }
                case H: {
                    for (int j = 0; j < neurons[i].getWeights().length; j++) {
                        weightSum += neurons[i].getWeights()[j]*neurons[index++].getOutput();
                    }
                    weightSum += neurons[i].getBias();
                    neurons[i].calOutput(weightSum);                //calculate activation
                    break;
                }
                case O: {
                    for (int j = 0; j < neurons[i].getWeights().length; j++) {
                        weightSum += neurons[i].getWeights()[j]*neurons[indexO++].getOutput();
                    }
                    weightSum += neurons[i].getBias();
                    neurons[i].calOutput(weightSum);                //calculate activation
                    break;
                }
            }
        }
        return this;
    }

    /*
    * E2 = d(J/Z2) = 1/2(Y hat - Y)^2'/N = 1/2(Y hat - A)^2'/N = (Y hat - A)A'/N TH này bỏ N => hội tụ nhanh
    * d(J/W2) = A1*E2
    * d(J/b2) = E2
    * E1 = (W2*E2)*f'(Z1)
    * d(J/W1) = A0*E1
    * d(J/b1) = E1
    * */

    public NeuralNetwork backPropagation(double targetRs){
        neurons[4].setError((targetRs - neurons[4].getOutput())*neurons[4].derivativeOutput());
        neurons[4].getWeights()[0] =
                    neurons[4].getWeights()[0] + LEARNING_RATE*neurons[4].getError()*neurons[2].getOutput();
        neurons[4].getWeights()[1] =
                neurons[4].getWeights()[1] + LEARNING_RATE*neurons[4].getError()*neurons[3].getOutput();
        neurons[4].setBias(neurons[4].getBias() + LEARNING_RATE*neurons[4].getError());

        //
        neurons[3].setError(neurons[4].getWeights()[1]*neurons[4].getError()*neurons[3].derivativeOutput());
        neurons[3].getWeights()[0] =
                neurons[3].getWeights()[0] + LEARNING_RATE*neurons[3].getError()*neurons[0].getOutput();
        neurons[3].getWeights()[1] =
                neurons[3].getWeights()[1] + LEARNING_RATE*neurons[3].getError()*neurons[1].getOutput();
        neurons[3].setBias(neurons[3].getBias() + LEARNING_RATE*neurons[3].getError());

        //
        neurons[2].setError(neurons[4].getWeights()[0]*neurons[4].getError()*neurons[2].derivativeOutput());
        neurons[2].getWeights()[0] =
                neurons[3].getWeights()[0] + LEARNING_RATE*neurons[2].getError()*neurons[0].getOutput();
        neurons[2].getWeights()[1] =
                neurons[2].getWeights()[1] + LEARNING_RATE*neurons[2].getError()*neurons[1].getOutput();
        neurons[2].setBias(neurons[3].getBias() + LEARNING_RATE*neurons[2].getError());

        return this;
    }

    /*public NeuralNetwork backPropagationOutputLayer(double targetRs, int op, int N){
        int forward = op - 1;
        neurons[op].setError((neurons[op].getOutput() - targetRs)/N);
        for (int i = neurons[op].getWeights().length - 1; i <= 0 ; i++) {
            neurons[op].getWeights()[i] =
                    neurons[op].getWeights()[i] + LEARNING_RATE*neurons[op].getError()*neurons[forward--].getOutput();
        }
        neurons[op].setBias(neurons[op].getBias() + LEARNING_RATE*neurons[op].getError());

        return this;
    }
    public NeuralNetwork backPropagation(int i*//*, LayerType.layerType layerType*//*){    //e output = J'(z)  -- z = W*a(forward) + b
//        if(i == lengthOfInputLayer) return this;
        int backward = i+1;
        int forward = i-neurons[i].getWeights().length;     // forward = 1
        int index = 0;
        for (int neu = 2; neu <= i; neu--) {
            neurons[neu].setError((neurons[backward].getWeights()[index++])*neurons[backward].getError()*neurons[neu].derivativeOutput());
            neurons[neu].setBias(neurons[neu].getBias() + LEARNING_RATE*neurons[neu].getError());
            for (int j = neurons[neu].getWeights().length - 1; j <= 0 ; j++) {                //eL = W(L+1)*e(L-1)*f'
                // (zL)
                neurons[neu].getWeights()[j] =
                        neurons[neu].getWeights()[j] + LEARNING_RATE*neurons[neu].getError()*neurons[forward--].getOutput();
            }
            forward += neurons[neu].getWeights().length;
        }
//        backPropagation(i - lengthOfLayer);
        return this;
    }*/
}
