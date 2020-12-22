package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        partTwo(passportList);

    }

    static void partOne(ArrayList<Map<String, String>> passports) {
        long validPassportCount = passports.stream()
                .filter(p -> p.get("byr") != null && p.get("iyr") != null && p.get("eyr") != null && p.get("hgt") != null && p.get("hcl") != null && p.get("ecl") != null && p.get("pid") != null)
                .count();

        System.out.println("Part One: " + validPassportCount);
    }

    static void partTwo(ArrayList<Map<String, String>> passports) {
        long validPassportCount = passports.stream()
                .filter(p -> p.get("byr") != null && p.get("byr").matches("-?\\d+") && Integer.valueOf(p.get("byr")) >= 1920 && Integer.valueOf(p.get("byr")) <= 2002)
                .filter(p -> p.get("iyr") != null && p.get("iyr").matches("-?\\d+") && Integer.valueOf(p.get("iyr")) >= 2010 && Integer.valueOf(p.get("iyr")) <= 2020)
                .filter(p -> p.get("eyr") != null && p.get("eyr").matches("-?\\d+") && Integer.valueOf(p.get("eyr")) >= 2020 && Integer.valueOf(p.get("eyr")) <= 2030)
                .filter(p -> p.get("ecl") != null && (p.get("ecl").equals("amb") || p.get("ecl").equals("blu") || p.get("ecl").equals("brn") || p.get("ecl").equals("gry") || p.get("ecl").equals("grn") || p.get("ecl").equals("hzl") || p.get("ecl").equals("oth")))
                .filter(p -> p.get("pid") != null && p.get("pid").matches("-?\\d+") && p.get("pid").length() == 9)
                .filter(p -> {

                    String hgt = p.get("hgt");
                    if(hgt == null) {
                        return false;
                    }

                    String height = hgt.substring(0, hgt.length()-2);
                    String unit = hgt.substring(hgt.length()-2);

                    if(!height.matches("-?\\d+")) {
                        return false;
                    }

                    switch(unit) {
                        case "cm":
                            return Integer.valueOf(height) >= 150 && Integer.valueOf(height) <= 193;
                        case "in":
                            return Integer.valueOf(height) >= 59 && Integer.valueOf(height) <= 76;
                        default:
                            return false;
                    }
                })
                .filter(p -> {
                    String hcl = p.get("hcl");
                    if(hcl == null) {
                        return false;
                    }

                    String color = hcl.substring(1);

                    if(hcl.charAt(0) != '#' || color.length() != 6) {
                        return false;
                    }

                    for(int i=0; i<color.length(); i++) {
                        if(!Set.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f').contains(color.charAt(i))) {
                            return false;
                        }
                    }

                    return true;
                })
                .count();

        System.out.println("Part Two: " + validPassportCount);
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
