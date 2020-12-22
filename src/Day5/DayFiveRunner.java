package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayFiveRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day5/seat_assignments.txt")).useDelimiter("\n");
        List<String> assignments = new ArrayList();
        while(input.hasNext()) {
            assignments.add(input.next());
        }

        partOne(128, 8, assignments);

    }

    static void partOne(int depth, int width, List<String> assignments) {

        int highestAssignmentId = 0;
        for(String assignment : assignments) {
            int upperBound = depth;
            int lowerBound = 0;

            int leftBound = 0;
            int rightBound = width;

            for(int i=0; i<assignment.length(); i++) {
                switch (assignment.charAt(i)) {
                    case 'F': upperBound = (upperBound+lowerBound)/2; break;
                    case 'B': lowerBound = (upperBound+lowerBound)/2; break;
                    case 'L': rightBound = (leftBound+rightBound)/2; break;
                    case 'R': leftBound = (leftBound+rightBound)/2; break;
                }
            }

            int seatId = lowerBound*8+leftBound;
            if(highestAssignmentId < seatId) {
                highestAssignmentId = seatId;
            }
        }

        System.out.println("Part One: " + highestAssignmentId);
    }
}
