package neuralNetwork;

public class neuronAxon {
	private neuron from;
	private neuron to;
	private double weight;
	
	public neuronAxon(neuron fromNeuron, neuron toNeuron) {
		from = fromNeuron;
		to = toNeuron;
		weight = Math.random();
	}
	
	public void changeWeight(double change) {
		weight += from.getValue()*change*neuralNetwork.learningRate;
	}
	
	public double calculateValue() {
		double value = from.calculateValue() * weight;
		return value;
	}
	
	public void addError() {
		from.addError(weight*to.getError()*neuralNetwork.learningRate*(to.getValue()*(1-to.getValue())));
	}
}
