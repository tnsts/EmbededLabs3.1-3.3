package com.example.genetic;

import java.util.ArrayList;
import java.util.Random;

public class Genetic {
	private int a, b, c, d;
	private int y;
	
	public void setData(int a, int b, int c, int d, int y) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.y = y;
	}

	public Population calculate(int sizeOfGenaration, double mutaionProbability) {
		Random rand = new Random();
		ArrayList<Population> generation = new ArrayList<>();
		
		for (int i = 0; i < sizeOfGenaration; i++) {
			int cell = y/2;
			generation.add(new Population(rand.nextInt(cell),
										  rand.nextInt(cell),
										  rand.nextInt(cell),
										  rand.nextInt(cell)));
		}
		
		while(true) {
			
			// ---- conformity assessment section ----		
			int[] deltas = new int[generation.size()];
			int k = 0;
			for(Population p : generation) {
				deltas[k] = calculateFitnessFunction(p);
				if (deltas[k] == 0) {
					return p;
				}
				k++;
			}
			
			// ---- get parents section ----	
			double[] parentProbability = getParentProbability(deltas);
			
			ArrayList<Population> parents = new ArrayList<>();
			
            int[] wheel = new int[generation.size() + 1];
            wheel[0] = 0;
            for (int i = 0; i < wheel.length - 1; i++) {
                wheel[i + 1] = wheel[i] + (int)(parentProbability[i] * 100);
            }
            wheel[wheel.length - 1] = 100;
            
            for(int i = 0; i < generation.size(); i++) {
            	int shot = rand.nextInt(100);
            	for (int sec = 0; sec < wheel.length - 1; sec++) {
                    if (shot < wheel[sec + 1] && shot >= wheel[sec]) {            	
                    	parents.add(generation.get(sec));
                    }
                }
            }
            
            generation.clear();
            
            // ---- crossover section -----
            if (parents.size() % 2 == 0) {
            	for (int i = 0; i < parents.size(); i += 2) {
                	Population parA = parents.get(i);
                	Population parB = parents.get(i + 1);
                	generation.add(new Population(parA.x1, parA.x2, parB.x3, parB.x4));
                	generation.add(new Population(parB.x1, parB.x2, parA.x3, parA.x4));
                }
            } else {
            	for (int i = 0; i < parents.size() - 1; i += 2) {
                	Population parA = parents.get(i);
                	Population parB = parents.get(i + 1);
                	generation.add(new Population(parA.x1, parA.x2, parB.x3, parB.x4));
                	generation.add(new Population(parB.x1, parB.x2, parA.x3, parA.x4));
                }
            	generation.add(parents.get(parents.size() - 1));
            }
            
            // ---- mutation section ----
            if (Math.random() < mutaionProbability) {
            	Population victim = generation.get(rand.nextInt(generation.size()));
            	generation.remove(victim);
            	int gen = rand.nextInt(4);
            	int oper = rand.nextInt(2);
            	if (oper == 0) {
            		switch(gen) {
            			case 0: victim.x1++; break;
            			case 1: victim.x2++; break;
            			case 2: victim.x3++; break;
            			case 3: victim.x4++; break;
            		}
            	} else {
            		switch(gen) {
        			case 0: victim.x1--; break;
        			case 1: victim.x2--; break;
        			case 2: victim.x3--; break;
        			case 3: victim.x4--; break;
        		}
            	}
            	generation.add(victim);
            }
		}
	}
	
	private int calculateFitnessFunction(Population p) {
		return Math.abs(y - (p.x1 * a + p.x2 * b + p.x3 * c + p.x4 * d));  
	}
	
	private double[] getParentProbability(int[] deltas) {
		double deltaSum = 0;
		double res[] = new double[deltas.length];
		for (int i = 0; i < deltas.length; i++) {
			deltaSum += 1.0/deltas[i];
		}
		for (int i = 0; i < deltas.length; i++) {
			res[i] = 1.0/deltas[i]/deltaSum;
		}
		return res;
	}
	
}
