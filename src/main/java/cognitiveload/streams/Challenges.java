package cognitiveload.streams;

import java.util.List;

public class Challenges {

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5, 6);

        var result = sumOfEvenNumbers(numbers);
        System.out.println(result);

        var resultWithStreams = sumOfEvenNumbersUsingStreams(numbers);
        System.out.println(resultWithStreams);
    }

    /**
     * Return the sum of all even numbers in a list non streams
     */
    public static int sumOfEvenNumbers(List<Integer> numbers) {
        var sum = 0;
        for(Integer number: numbers) {
            if (number != null && number % 2 == 0) {
                sum = sum + number;
            }
        }
        return sum;
    }

    /** return sum of all even numbers with streams
     *
     */
    public static int sumOfEvenNumbersUsingStreams(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number != null)
                .filter(number -> number % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
