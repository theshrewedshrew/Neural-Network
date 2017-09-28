package network.main;

import java.util.Random;

public class Neuron {
	private float neuron;
	
	private NeuralStatus status;
	
	private int numberOfConnectedNeurons;
	
	private float[] weight;
	private float[] weightDelta;
	
	private Random r;
	
	public Neuron(float neuron, NeuralStatus status){
		this.neuron = neuron;
		this.status = status;
		
		r = new Random();
	}
	
	public Neuron(float neuron){
		this.neuron = neuron;
		
		r = new Random();
	}
	
	public Neuron(Neuron neuron){
		this.neuron = neuron.neuron;
		this.status = neuron.status;
		this.numberOfConnectedNeurons = neuron.numberOfConnectedNeurons;
		this.weight = neuron.weight;
		this.weightDelta = neuron.weightDelta;
		
		r = new Random();
	}
	
	public void initializeWeights(int numberOfConnectedNeurons){
		this.numberOfConnectedNeurons = numberOfConnectedNeurons;
		weight = new float[numberOfConnectedNeurons];
		weightDelta = new float[numberOfConnectedNeurons];
		
		if(status == NeuralStatus.Input || status == NeuralStatus.Hidden){
			for(int i = 0; i < weight.length; i++){
				weight[i] = r.nextFloat() - .5f;
			}
		}
		else{
			weight = null;
			weightDelta = null;
		}
	}
	
	public void updateStatus(NeuralStatus status){
		this.status = status;
	}
	
	public void addFloatToNeuron(float update){
		neuron += update;
	}
	
	public void tanHNeuron(){
		neuron = (float) Math.tanh(neuron);
	}
	
	public float getNeuron(){
		return neuron;
	}
	
	public float FeedForward(int indexOfOutputNeuron){
		return neuron * weight[indexOfOutputNeuron];
	}

	public void mutate(float chance){
		float rn = r.nextFloat() * 1000;
		float num = (chance / 4) * 1000;
		for(int i = 0; i < weight.length; i++){
			if(rn <= num){
				weight[i] *= -1;
			}
			else if(rn <= num * 2){
				weight[i] = r.nextFloat() - .5f;
			}
			else if(rn <= num * 3){
				float factor = r.nextFloat() + 1f;
				weight[i] *= factor;
			}
			else if(rn <= num * 4){
				float factor = r.nextFloat();
				weight[i] *= factor;
			}
		}
	}
	
	public String toString(){
		String toString = "Neuron: " + neuron;
		return toString;
	}
}