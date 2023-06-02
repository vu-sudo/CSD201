package Common;

public class NoneTailRecursion {

    public void demoNoneTailRecursion(Integer number) {
        if(number == 0) {
            return;
        }
        demoNoneTailRecursion(number - 1);
        System.out.print(number + " ");
    }

    public Integer Factorial(Integer currentNumber) {
        if(currentNumber <= 0) {
            return 1;
        } else {
            return currentNumber*Factorial(currentNumber - 1 );
        }
    }
}
