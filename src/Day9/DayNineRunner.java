package Day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayNineRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day9/cipher.txt")).useDelimiter("\n");
        List<Integer> cipher = new ArrayList<>();
        while(input.hasNext()) {
            cipher.add(Integer.parseInt(input.next()));
        }

        System.out.println("Part One: " + partOne(cipher, 25));
    }

    public static int partOne(List<Integer> cipher, Integer preambleLength) {

        return 0;
    }
}
