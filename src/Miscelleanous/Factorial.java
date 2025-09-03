package Miscelleanous;

class WithoutRecursion {

	int getFactorial(int num) {
		int result = 1;
		for (int i = 1; i <= num; i++) {
			result *= i;
		}
		return result;
	}
}
//
//class WithRecursion {
//	int getFactorial(int num) {
//
//	}
//}

public class Factorial {
	public static void main(String[] args) {
		int num = 10;
		int num2 = 3;

		WithoutRecursion fact = new WithoutRecursion();
		int result = fact.getFactorial(num);
		int result2 = fact.getFactorial(num2);
		System.out.printf("Factorial of %d: %d, %d", num, result, result2);
	}
}
