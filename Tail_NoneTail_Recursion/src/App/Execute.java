package App;

import Common.NoneTailRecursion;
import Common.TailRecursion;

public class Execute {
    public void runProgram() {
        TailRecursion tailRecursion = new TailRecursion();
        NoneTailRecursion noneTailRecursion = new NoneTailRecursion();

        System.out.println("TAIL RECURSION: ");
        tailRecursion.demoTailRecursion(5);
        System.out.println("\nFactorial: " + tailRecursion.Factorial(6, 1));
        
        System.out.println("\nNONE-TAIL RECURSION:");
        noneTailRecursion.demoNoneTailRecursion(5);
        System.out.println("Factorial: " + noneTailRecursion.Factorial(4));
    }
    public static void main(String[] args) {
        Execute exe = new Execute();
        exe.runProgram();
    }
}
