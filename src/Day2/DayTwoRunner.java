package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DayTwoRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day2/passwords.txt")).useDelimiter("\n");
        ArrayList<String> passwordEntries = new ArrayList<>();
        while(input.hasNext()) {
            passwordEntries.add(input.next());
        }

        partOne(passwordEntries);
        partTwo(passwordEntries);
    }

    static void partOne(ArrayList<String> passwordEntries) {
        Integer count = 0;

        for(String passwordEntry : passwordEntries) {
            int minNum = Integer.valueOf(passwordEntry.substring(0, passwordEntry.indexOf('-')));
            int maxNum = Integer.valueOf(passwordEntry.substring(passwordEntry.indexOf('-')+1, passwordEntry.indexOf(":")-2));
            final char character = passwordEntry.substring(passwordEntry.indexOf(":")-1, passwordEntry.indexOf(":")).charAt(0);
            String password = passwordEntry.substring(passwordEntry.indexOf(":")+2);
            long numCharacterAppearances = password.chars().filter(c -> c == character).count();

            if(numCharacterAppearances >= minNum && numCharacterAppearances <= maxNum) {
                count+=1;
            }
        }

        System.out.println("Part One Count: " + count);
    }

    static void partTwo(ArrayList<String> passwordEntries) {
        Integer count = 0;

        for(String passwordEntry : passwordEntries) {
            int firstPos = Integer.valueOf(passwordEntry.substring(0, passwordEntry.indexOf('-')))-1;
            int seconPos = Integer.valueOf(passwordEntry.substring(passwordEntry.indexOf('-')+1, passwordEntry.indexOf(":")-2))-1;
            final char character = passwordEntry.substring(passwordEntry.indexOf(":")-1, passwordEntry.indexOf(":")).charAt(0);
            String password = passwordEntry.substring(passwordEntry.indexOf(":")+2);

            if(password.charAt(firstPos) == character && password.charAt(seconPos) != character) {
                count+=1;
            } else if(password.charAt(firstPos) != character && password.charAt(seconPos) == character) {
                count+=1;
            }
        }

        System.out.println("Part Two Count: " + count);
    }
}
