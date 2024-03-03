package src;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class experiments {
	
	public static void main(String[] args) {
		// experiment1();
		// experiment2();
		experiment3();
	}
	
	public static void experiment1() {
		for(int i = 1; i < 7; i++) {
			float numOfLinks = 0;
			float amountOfTrees = 0;
			float avgDuration = 0;
			for (int k =0 ; k < 10; k++) {
				BinomialHeap heap = new BinomialHeap();
				int n = (int) Math.pow(3, i+5);
				Instant startTime = Instant.now();
				for (int j = 1; j < n; j++) {
					heap.insert(j, "j");
				}
				
				Instant endTime = Instant.now();
				Duration duration = Duration.between(startTime, endTime);
				numOfLinks += heap.linkcounter;
				amountOfTrees += heap.numTrees();
				avgDuration += duration.toMillis();
			}
			String result = String.format("experimet: %s", i);
			String result1 = String.format("amount of links: %s", (numOfLinks/10));
			String result2 = String.format("amount of trees: %s", (amountOfTrees/10));
			System.out.println(result);
			System.out.println(result1);
			System.out.println("Elapsed time: " + (avgDuration/10) + " milliseconds");
			System.out.println(result2);
		}
	}
	
	public static void experiment2() {
		for(int i = 1; i < 7; i++) {
			float numOfLinks = 0;
			float amountOfTrees = 0;
			float avgDuration = 0;
			float avgDelatedRanks = 0;
			for (int k =0 ; k < 10; k++) {
				BinomialHeap heap = new BinomialHeap();
				int n = (int) Math.pow(3, i+5);
				// Step 1: Generate an array of integers from 1 to n in random order
	            List<Integer> numbers = new ArrayList<>();
	            for (int j = 1; j < n; j++) {
	                numbers.add(j);
	            }
	            Collections.shuffle(numbers);

	            Instant startTime = Instant.now();
	            
	            // Step 2: Insert the shuffled numbers into the heap
	            for (int j = 0; j < n - 1; j++) {
	                heap.insert(numbers.get(j), "j");
	            }
	            
	            for (int j = 1; j <= n/2; j++) {
					heap.deleteMin();
				}
				Instant endTime = Instant.now();
				Duration duration = Duration.between(startTime, endTime);
				numOfLinks += heap.linkcounter;
				amountOfTrees += heap.numTrees();
				avgDuration += duration.toMillis();
				avgDelatedRanks += heap.deletedRanks;
			}
			String result = String.format("experimet: %s", i);
			String result1 = String.format("amount of links: %s", (numOfLinks/10));
			String result2 = String.format("amount of trees: %s", (amountOfTrees/10));
			String result3 = String.format("sum deleted ranks: %s", (avgDelatedRanks/10));
			System.out.println(result);
			System.out.println(result1);
			System.out.println("Elapsed time: " + (avgDuration/10) + " milliseconds");
			System.out.println(result2);
			System.out.println(result3);
		}
	}
	
	public static void experiment3() {
		for(int i = 1; i < 7; i++) {
			float numOfLinks = 0;
			float amountOfTrees = 0;
			float avgDuration = 0;
			float avgDelatedRanks = 0;
			int lowerBound = (int) Math.pow(2, 5) - 1;
			for (int k =0 ; k < 10; k++) { 
				BinomialHeap heap = new BinomialHeap();
				int n = (int) Math.pow(3, i+5);
				Instant startTime = Instant.now();
				for (int j = n-1; j > 0; j--) {
					heap.insert(j, "j");
				}
				while (heap.size > lowerBound) {
					heap.deleteMin();
				}
				
				Instant endTime = Instant.now();
				Duration duration = Duration.between(startTime, endTime);
				numOfLinks += heap.linkcounter;
				amountOfTrees += heap.numTrees();
				avgDuration += duration.toMillis();
				avgDelatedRanks += heap.deletedRanks;
			}
			String result = String.format("experimet: %s", i);
			String result1 = String.format("amount of links: %s", (numOfLinks/10));
			String result2 = String.format("amount of trees: %s", (amountOfTrees/10));
			String result3 = String.format("sum deleted ranks: %s", (avgDelatedRanks/10));
			System.out.println(result);
			System.out.println(result1);
			System.out.println("Elapsed time: " + (avgDuration/10) + " milliseconds");
			System.out.println(result2);
			System.out.println(result3);
		}
	}
}
