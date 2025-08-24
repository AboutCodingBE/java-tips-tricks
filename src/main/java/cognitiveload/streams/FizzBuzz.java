package cognitiveload.streams;

import java.util.List;

public class FizzBuzz {

    public static void main(String[] args) {
        var numbers = List.of(2, 3, 5, 15, 9, 7, 8, 113, 999, 555);
        fizzBuzz(numbers);
    }

    public static void fizzBuzz(List<Integer> numbers) {
        for (Integer number: numbers) {
            if (number == null) {
                continue;
            }
            if (number % 3 == 0 && number % 5 == 0) {
                System.out.println("FizzBuzz ");
            }
            else if (number % 3 == 0) {
                System.out.println("Fizz ");
            }
            else if (number % 5 == 0) {
                System.out.println("Buzz ");
            }
            else {
                System.out.println(number + " ");
            }
        }
    }
}
