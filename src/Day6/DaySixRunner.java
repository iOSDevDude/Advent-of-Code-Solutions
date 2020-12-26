package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DaySixRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day6/customs_declarations.txt")).useDelimiter("\n");
        List<String> declarations = new ArrayList();
        while(input.hasNext()) {
            declarations.add(input.next());
        }

        System.out.println(declarations);
        partOne(declarations);
        partTwo(declarations);
    }

    static void partOne(List<String> declarations) {

        int overalTotal = 0;

        List<Character> currentDeclarations = new ArrayList<>();
        for(int j=0; j<declarations.size(); j++) {

            String declaration = declarations.get(j);

            if(declaration.length() == 0) {
                overalTotal+=currentDeclarations.size();
                currentDeclarations.clear();
            }
            for(int i=0; i<declaration.length(); i++) {
                if(!currentDeclarations.contains(declaration.charAt(i))) {
                    currentDeclarations.add(declaration.charAt(i));
                }
            }
            if(j == declarations.size()-1) {
                overalTotal+=currentDeclarations.size();
            }
        }

        System.out.println("Part One: " + overalTotal);
    }

    static void partTwo(List<String> declarations) {
        int overalTotal = 0;

        Map<Character, Integer> groupDeclarations = new HashMap<>();
        int groupResponseCount = 0;
        for(int i=0; i<=declarations.size(); i++) {

            if(i == declarations.size() || declarations.get(i).length() == 0) {
                for(Character character : groupDeclarations.keySet()) {
                    if(groupDeclarations.get(character) == groupResponseCount) {
                        overalTotal++;
                    }
                }

                groupResponseCount = 0;
                groupDeclarations.clear();
                continue;
            }

            String declaration = declarations.get(i);

            groupResponseCount++;
            for(int j=0; j<declaration.length(); j++) {
                groupDeclarations.put(declaration.charAt(j), groupDeclarations.get(declaration.charAt(j)) != null ? groupDeclarations.get(declaration.charAt(j))+1 : 1);
            }
        }

        System.out.println("Part Two: " + overalTotal);

    }
}
