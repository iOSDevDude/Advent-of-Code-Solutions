package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayOneRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day1/expense_report.txt")).useDelimiter("\n");
        ArrayList<Integer> numbers = new ArrayList();
        while(input.hasNext()) {
            numbers.add(Integer.valueOf(input.next()));
        }

        partOne(numbers);
        partTwo(numbers);


    }

    static void partOne(ArrayList<Integer> numbers) {
        numbers.forEach(firstNum -> {
            numbers.forEach(secondNum -> {
                if(firstNum+secondNum == 2020) {
                    System.out.println(firstNum*secondNum);
                }
            });
        });
    }

    static void partTwo(ArrayList<Integer> numbers) {
        numbers.forEach(firstNum -> {
            numbers.forEach(secondNum -> {
                numbers.forEach(thirdNum -> {
                    if(firstNum+secondNum+thirdNum == 2020) {
                        System.out.println(firstNum*secondNum*thirdNum);
                    }
                });
            });
        });
    }
}
