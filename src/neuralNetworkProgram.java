import java.util.ArrayList;
import java.util.Arrays;

import neuralNetwork.neuralNetwork;

public class neuralNetworkProgram {
	static neuralNetwork test;
	
	public static void main(String[] args) {
		test = new neuralNetwork(2, 2, .05, 4);
		ArrayList<double[]> training = new ArrayList<double[]>();
		training.add(new double[] {1,1,0});
		
		training = new ArrayList<double[]>();
		training.add(new double[] {0,1,1});
		training.add(new double[] {0,0,0});
		training.add(new double[] {1,1,0});
		training.add(new double[] {1,0,1});
		
		for(int i = 0; i < 1000; i++) {
			test.trainNetwork(training);
		}
		
		
		System.out.println("Answer after: " + Arrays.toString(test.testNetwork(training)));
	}
}
