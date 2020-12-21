package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThreeRunner {

    public static void main(String[] args) throws FileNotFoundException {
        //Read input and transform to integers
        Scanner input = new Scanner(new File("src/Day3/tree_map.txt")).useDelimiter("\n");
        ArrayList<String> treeMap = new ArrayList<>();
        while(input.hasNext()) {
            treeMap.add(input.next());
        }

        System.out.println(treeMap.toString());

        partOne(3, 1,  treeMap);
    }

    static void partOne(int right, int down, ArrayList<String> map) {
        int rowLength = map.get(0).length();
        int downIndex = 0;
        int rightIndex = 0;
        int treeCount = 0;

        while(downIndex < map.size()) {
            downIndex+=down;
            rightIndex+=right;

            int tempRight = rightIndex;
            if(rightIndex > rowLength) {
                tempRight = tempRight % rowLength;
            }

            if(downIndex < map.size() && map.get(downIndex).charAt(tempRight) == '#') {
                treeCount += 1;
            }
        }

        System.out.println(treeCount);
    }
}
