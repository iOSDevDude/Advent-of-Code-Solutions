package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DayFourRunner {

    public static void main(String[] args) throws FileNotFoundException {
        //Read input and transform to integers
        Scanner input = new Scanner(new File("src/Day4/passport_records.txt")).useDelimiter(" |\\n");
        ArrayList<String> passportDataList = new ArrayList<>();
        while(input.hasNext()) {
            passportDataList.add(input.next());
        }

        ArrayList<Map<String, String>> passportList = passportListTransformer(passportDataList);

        partOne(passportList);

    }

    static void partOne(ArrayList<Map<String, String>> passports) {
        long validPassportCount = passports.stream()
                .filter(p -> p.get("byr") != null && p.get("iyr") != null && p.get("eyr") != null && p.get("hgt") != null && p.get("hcl") != null && p.get("ecl") != null && p.get("pid") != null)
                .count();

        System.out.println("Part One: " + validPassportCount);
    }

    static ArrayList<Map<String, String>> passportListTransformer(ArrayList<String> passportDataList) {
        ArrayList<Map<String, String>> passportList = new ArrayList<>();

        HashMap<String, String> passport = new HashMap<>();
        int index = 0;
        for(String element : passportDataList) {
            if(element.length() == 0) {
                passportList.add(passport);
                passport = new HashMap<>();
            } else {
                passport.put(element.substring(0, element.indexOf(":")), element.substring(element.indexOf(":")+1));
            }

            index++;
            if(index == passportDataList.size()) {
                passportList.add(passport);
                break;
            }
        }

        return passportList;
    }
}
