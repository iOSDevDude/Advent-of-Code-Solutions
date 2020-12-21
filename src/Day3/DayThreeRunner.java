package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DayThreeRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day3/tree_map.txt")).useDelimiter("\n");
        ArrayList<String> treeMap = new ArrayList<>();
        while(input.hasNext()) {
            treeMap.add(input.next());
        }

        partOne(3, 1,  treeMap);
        partTwo(Arrays.asList(1, 3, 5, 7, 1), Arrays.asList(1, 1, 1, 1, 2), treeMap);
    }

    static int partOne(int right, int down, ArrayList<String> map) {
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
            if(tempRight == 31) {
                tempRight = 0;
            }

            if(downIndex < map.size() && map.get(downIndex).charAt(tempRight) == '#') {
                treeCount += 1;
            }
        }

        System.out.println("Part One: " + treeCount);
        return treeCount;
    }

    static long partTwo(List<Integer> rights, List<Integer> downs, ArrayList<String> map) {
        long treeProduct = 1;
        for(int i=0; i<rights.size(); i++) {
            treeProduct*=partOne(rights.get(i), downs.get(i), map);
        }
        System.out.println("Part Two: " + treeProduct);
        return treeProduct;
    }
}
