package neuralNetwork;

import java.util.ArrayList;
import java.util.Arrays;

public class neuralNetwork {
	private ArrayList<ArrayList<neuron>> network = new ArrayList<ArrayList<neuron>>();
	public static double learningRate;
	
	public neuralNetwork(int starting, int ending, double learning, int... hidden) {
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
			backPropogate(instance[0]);
			System.out.println(Arrays.toString(instance) + " : " + network.get(network.size()-1).get(0).getValue() + " " + network.get(network.size()-1).get(1).getValue());
			System.out.println();
		}
	}
	
	public int[] testNetwork(ArrayList<double[]> dataSet) {
		int[] answers = new int[dataSet.size()];
		
		int count = 0;
		for(double[] data : dataSet) {
			newInput(data);
			answers[count] = getOutput();
			count++;
		}
		
		return answers;
	}
	
	private void newInput(double... inputValues) {
		int count = 0;
		for(int i = 1; i < inputValues.length; i++) {
			network.get(0).get(i-1).setValue(inputValues[i]);
		}
		for(neuron n : network.get(0)) {
			n.setValue(inputValues[count]);
			count++;
		}
	}
	
	private int getOutput(){
		double[] outputs = new double[network.get(network.size()-1).size()];
		int count = 0;
		for(neuron output : network.get(network.size()-1)) {
			output.calculateValue();
		}
		
		return getHighest(outputs);
	}
	
	private int getHighest(double[] values) {
		int highest = 0;
		
		for(int i = 1; i < values.length; i++) {
			if(values[i] > values[highest]) {
				highest = i;
			}
		}
		
		return highest;
	}
	
	private void backPropogate(double expected) {
		int count = 0;
		int right = 0;
		ArrayList<neuron> next;
		
		for(neuron n : network.get(network.size()-1)) {
			if(count == expected) {
				right = 0;
			} else {
				right = 1;
			}
			System.out.print(right + " : ");
			double x = n.getValue()-right;
			n.addError(.5*x*x);
			count++;
			right = 0;
		}
		
		for(int i = network.size()-1; i >= 0; i--) {
			next = network.get(i);
			for(neuron n: next) {
				//System.out.println("Error of node in Layer " + i + ": " + n.getError());
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
