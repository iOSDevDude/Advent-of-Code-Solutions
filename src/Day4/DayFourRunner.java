package Day4;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DayFourRunner {

    public static void main(String[] args) {
        //Read input and transform to integers
        Scanner input = new Scanner(new File("src/Day3/tree_map.txt")).useDelimiter("\n");
        ArrayList<String> treeMap = new ArrayList<>();
        while(input.hasNext()) {
            treeMap.add(input.next());
        }
    }

    static void partOne() {

    }
}
