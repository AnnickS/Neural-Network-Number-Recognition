package neuralNetwork;

import java.util.ArrayList;

public class neuralNetwork {
	private ArrayList<ArrayList<neuron>> network = new ArrayList<ArrayList<neuron>>();
	public static double learningRate;
	
	public neuralNetwork(int starting, int ending, int learning, int... hidden) {
		learningRate = learning;
		
		ArrayList<neuron> layer = new ArrayList<neuron>();
		
		for(int i = 0; i < starting; i++) {
			layer.add(new neuron());
		}
		
		network.add(layer);
		
		for(int i: hidden) {
			layer = new ArrayList<neuron>();
			for(int j = 0; j < i; j++) {
				layer.add(new neuron());
			}
			
			network.add(layer);
		}
		
		layer = new ArrayList<neuron>();
		
		for(int i = 0; i < ending; i++) {
			layer.add(new neuron());
		}
		
		network.add(layer);
		
		buildNetwork();
	}
	
	private void buildNetwork() {
		ArrayList<neuron> current;
		ArrayList<neuron> previous;
		
		for(int i = network.size()-1; i > 0; i--) {
			current = network.get(i-1);
			previous = network.get(i);
			
			for(neuron out: previous) {
				for(neuron in: current) {
					out.addInput(in);
				}
			}
		}
	}
	
	public void trainNetwork(ArrayList<double[]> dataSet) {
		for(double[] instance: dataSet) {
			newInput(instance);
			getOutput();
		}
	}
	
	public void newInput(double... inputValues) {
		int count = 0;
		for(neuron n : network.get(0)) {
			n.setValue(inputValues[count]);
			count++;
		}
	}
	
	public double[] getOutput(){
		double[] outputs = new double[network.get(network.size()-1).size()];
		int count = 0;
		for(neuron output : network.get(network.size()-1)) {
			outputs[count] = output.calculateValue();
		}
		
		return outputs;
	}
	
	private void backPropogate(double... expected) {
		int count = 0;
		for(neuron n : network.get(network.size()-1)) {
			double x = n.getValue()-expected[count];
			double half = 1/2;
			n.addError(half*x*x);
			count++;
		}
		
		for(ArrayList<neuron> list : network) {
			for(neuron n: list) {
				n.setError();
			}
		}
		for(ArrayList<neuron> list : network) {
			for(neuron n: list) {
				n.changeWeights();
			}
		}
	}
}
