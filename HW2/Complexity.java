
public class Complexity {
	//Burak Yesil
	// "I pledge my honor that I have abided by the Stevens Honor System."
	
	
	
	//Problem 1
	public static void method1(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println( i + "   " + j);
			}
		}
	}
	
	//Problem 2
	public static void method2(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
				System.out.println(n);
				}
			}
		}
	}
	
	//Problem 3
	public static void method3(int n) {
		for (int i = 1; i < n; i *= 2) {
			System.out.println(i);
		}
	}
	
	//Problem 4	
/** 
 * 
 *   Iteration      |     Start   |  End  |
 * 		1					0		  31
 * 		2				  	16	  	  31
 * 	    3				  	24        31
 * 		4                   28        31
 * 		5					30        31
 * 		6                   31        31
 * 
 * 
 *   Iteration      |     Start   |  End  |
 * 		1					0		  63
 * 		2					32		  63
 * 		3					48		  63
 * 		4    				56		  63
 * 		5					60		  63
 * 		6					62		  63
 * 		7 					63		  63
 * **/
	
	//Problem 5
	// ( 2 ^ (number_of_iterations - 1 ) ) = size
	// or 
	// (log base 2 (size)) =  (iterations - 1)
	
	
	//Problem 6
	// O(log(n))
	
	
	//Problem 7
	public static void method4(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j *= 2) {
				System.out.println(i + "    " + j);
			}
		}
	}
	
	
	//Problem 8
	public static void method5(int n) {
		for (int i = 1; i < n; i *= 2) {
			for (int j = 1; j < n; j *= 2) {
				System.out.println(i + "    " + j);
			}
		}
	}
	
	

	
}
