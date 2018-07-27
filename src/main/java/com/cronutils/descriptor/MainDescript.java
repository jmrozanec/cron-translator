package com.cronutils.descriptor;

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.BackpropType;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.GravesLSTM;
import org.deeplearning4j.nn.conf.layers.LSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.IOException;

//https://medium.com/machine-learning-world/keras-lstm-to-java-a3124402d69
//
public class MainDescript {
    public static void main(String[] args) throws InvalidKerasConfigurationException, IOException, UnsupportedKerasConfigurationException {
        int source_chars_count = 10;
        int target_chars_count = 15;
        LSTM encoder = new LSTM.Builder().nIn(source_chars_count).nOut(256).build();
        LSTM decoder = new LSTM.Builder().nIn(256).nOut(target_chars_count).build();
        DenseLayer dense = new DenseLayer.Builder().nIn(target_chars_count).nOut(target_chars_count).activation(Activation.SOFTMAX).build();
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(12345)
                .iterations(30)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(Updater.ADAM)
                .list()
                .layer(0, encoder)
                .layer(1, decoder)
                .layer(2, dense)
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        model.setListeners(new ScoreIterationListener(1));

        DataSetIterator iter = new MnistDataSetIterator(64,100,true);
        while(iter.hasNext()) {
            DataSet next = iter.next();
            model.fit(new DataSet(next.getFeatureMatrix(),next.getFeatureMatrix()));//TODO replace source for target
        }

        /*
            # Encoder model
            encoder_input = Input(shape=(None,len(eng_chars)))
            encoder_LSTM = LSTM(256,return_state = True)
            encoder_outputs, encoder_h, encoder_c = encoder_LSTM (encoder_input)
            encoder_states = [encoder_h, encoder_c]

            # Decoder model
            decoder_input = Input(shape=(None,len(fra_chars)))
            decoder_LSTM = LSTM(256,return_sequences=True, return_state = True)
            decoder_out, _ , _ = decoder_LSTM(decoder_input, initial_state=encoder_states)
            decoder_dense = Dense(len(fra_chars),activation='softmax')
            decoder_out = decoder_dense (decoder_out)

            model = Model(inputs=[encoder_input, decoder_input],outputs=[decoder_out])

            # Run training
            model.compile(optimizer='adam', loss='categorical_crossentropy')
         */



        /*
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT).iterations(1)
                .learningRate(0.1)
                .seed(12345)
                .regularization(true)
                .l2(0.001)
                .weightInit(WeightInit.XAVIER)
                .updater(Updater.RMSPROP)
                .list()
                .layer(0, new GravesLSTM.Builder().nIn(iter.inputColumns()).nOut(lstmLayerSize)
                        .activation(Activation.TANH).build())
                .layer(1, new GravesLSTM.Builder().nIn(lstmLayerSize).nOut(lstmLayerSize)
                        .activation(Activation.TANH).build())
                .layer(2, new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT).activation(Activation.SOFTMAX)        //MCXENT + softmax for classification
                        .nIn(lstmLayerSize).nOut(nOut).build())
                .backpropType(BackpropType.TruncatedBPTT).tBPTTForwardLength(tbpttLength).tBPTTBackwardLength(tbpttLength)
                .pretrain(false).backprop(true)
                .build();
        */
    }

    private static void loadModel() throws UnsupportedKerasConfigurationException, IOException, InvalidKerasConfigurationException {
        ComputationGraph network = KerasModelImport.importKerasModelAndWeights("/Users/jorozanec/other/repo/private/cron-translator/translate-best.h5");
        //network.evaluate()
        //network.doEvaluation()
    }
}
