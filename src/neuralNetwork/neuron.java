package neuralNetwork;

import java.util.ArrayList;

public class neuron {
	private ArrayList<neuronAxon> inputs;
	private ArrayList<neuronAxon> outputs;
	private double value;
	private double error = 0.0;
	
	public neuron() {
		inputs = new ArrayList<neuronAxon>();
	}
	
	public void setValue(double startingValue) {
		value = startingValue;
	}
	
	public void addInput(neuron input) {
		inputs.add(new neuronAxon(input, this));
	}
	
	public double getValue() {
		return value;
	}
	
	public double getError() {
		return error;
	}
	
	public void setError() {
		for(neuronAxon Axon : inputs) {
			Axon.addError(error, value);
		}
	}
	
	public void addError(double errorValue) {
		error += errorValue;
	}
	
	public double calculateValue() {
		if(inputs.isEmpty()) {
			return value;
		} else {
			double output = 0;
		
			for(neuronAxon axon: inputs) {
				output += axon.calculateValue();
			}
		
			//sigmoid value here
			value = sigmoid(output);
		
			return value;
		}
	}
	
	public void changeWeights() {
		for(neuronAxon axon: inputs) {
			axon.changeWeight((value*(1-value))*error);
		}
		error = 0;
	}
	
	private double sigmoid(double x) {
		return (1/(1+Math.exp(-x)));
	}
}