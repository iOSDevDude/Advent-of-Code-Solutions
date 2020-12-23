package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaySixRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day6/customs_declarations.txt")).useDelimiter("\n");
        List<String> declarations = new ArrayList();
        while(input.hasNext()) {
            declarations.add(input.next());
        }

        System.out.println(declarations);
        partOne(declarations);
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

        System.out.print("Part One: " + overalTotal);
    }
}
