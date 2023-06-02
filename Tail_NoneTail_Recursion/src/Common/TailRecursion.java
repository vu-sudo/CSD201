package Common;

public class TailRecursion {

    public void demoTailRecursion(Integer number) {
        if(number == 0) {
            return;
        } else {
            System.out.print(number + " ");
        }
        demoTailRecursion(number - 1);
    }

    public Integer Factorial(Integer currentNumber, Integer previousMultiplication) {
        if(currentNumber <= 0) {
            return previousMultiplication;
        } else {
            return Factorial(currentNumber - 1, previousMultiplication* currentNumber);
        }
    }
}
