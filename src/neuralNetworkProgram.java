import java.util.Arrays;

import neuralNetwork.neuralNetwork;

public class neuralNetworkProgram {
	static neuralNetwork test;
	
	public static void main(String[] args) {
		test = new neuralNetwork(3, 1, 2);
		test.newInput(1,1,1);
		double[] output = test.getOutput();
		
		System.out.println(Arrays.toString(output));
	}
}
