package Miscelleanous;

public class Fibonacci {
	static int getFibonacciSum(int num) {
		int[] myArray = getFibonacciSeries(num);
		int sum = 0;
		for (int i = 0; i < myArray.length; i++) {
			int value = myArray[i];
			sum = sum + value;
		}
		return sum;
	}

	static int[] getFibonacciSeries(int num) {
		int[] fibSeries = new int[num];
		fibSeries[0] = 0;
		fibSeries[1] = 1;
		for (int i = 2; i < num; i++) {
			fibSeries[i] = fibSeries[i - 1] + fibSeries[i - 2];
		}
		return fibSeries;
	}

	public static void main(String[] args) {

	}
}
