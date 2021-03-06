package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DaySevenRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day7/bag_rules.txt")).useDelimiter("\n");
        List<String> rules = new ArrayList<>();
        while(input.hasNext()) {
            rules.add(input.next());
        }

        List<Rule> parsedRules = parseRules(rules);

        System.out.println("Num Rules: " + parsedRules.size());

        System.out.println("Part One: " + partOne(parsedRules));
        System.out.println("Part Two: " + partTwo(parsedRules));
    }

    public static int partOne(List<Rule> rules) {

        Set<String> containers = new HashSet<>();

        for(Rule rule : rules) {
            if(rule.subBags.containsKey("shiny gold")) {
                containers.add(rule.bagColor);
            }
        }

        int lastListSize = 0;
        while(containers.size() != lastListSize) {
            lastListSize = containers.size();
            Set<String> tempRules = new HashSet<>();
            for(String container : containers) {
                for(Rule rule : rules) {
                    if(rule.subBags.containsKey(container)) {
                        tempRules.add(rule.bagColor);
                    }
                }
            }

            containers.addAll(tempRules);
        }


        containers.remove("shiny gold");
        return containers.size();
    }

    public static int partTwo(List<Rule> rules) {
        List<Map<String, Integer>> children = new ArrayList<>();

        Rule shinyGoldRule = rules.stream().filter(rule -> rule.bagColor.equals("shiny gold")).findFirst().get();
        children.add(shinyGoldRule.subBags);

        while(true) {
            HashMap subBagCounts = new HashMap();
            children.get(children.size()-1).forEach((color, multiplier) -> {
                for (Rule rule : rules) {
                    if (rule.bagColor.equals(color)) {
                        rule.subBags.forEach((subColor, subCount) -> {
                            if(!subBagCounts.containsKey(subColor)) {
                                subBagCounts.put(subColor, multiplier * subCount);
                            } else {
                                subBagCounts.put(subColor, (int)subBagCounts.get(subColor) + (multiplier*subCount));
                            }
                        });
                    }
                }
            });

            if(subBagCounts.isEmpty()) {
                break;
            } else {
                children.add(subBagCounts);
            }
        }

        int count = 0;
        for(Map<String, Integer> map : children) {
            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                count+=entry.getValue();
            }
        }

        return count;
    }

    static ArrayList<Rule> parseRules(List<String> ruleStringList) {
        ArrayList<Rule> rules = new ArrayList<>();

        ruleStringList.forEach(rule -> rules.add(parseRule(rule)));

        return rules;
    }

    static Rule parseRule(String ruleString) {
        Rule rule = new Rule();

        rule.bagColor = ruleString.substring(0, ruleString.indexOf("bags")-1).toLowerCase();
        ruleString = ruleString.substring(ruleString.indexOf("contain")+"contain".length()+1);

        HashMap<String, Integer> subBags = new HashMap<>();

        if(ruleString.contains("no other bags")) {
            return rule;
        }

        while(ruleString.contains("bag")) {
            int numBags = Integer.parseInt(ruleString.substring(0, ruleString.indexOf(" ")));
            String subBagColor = ruleString.substring(ruleString.indexOf(" ")+1, ruleString.indexOf("bag")-1);
            ruleString = ruleString.substring(ruleString.indexOf("bag")+3);
            if(ruleString.charAt(0) == ',') {
                ruleString = ruleString.substring(2);
            } else if (ruleString.charAt(0) == 's' && ruleString.length() > 2) {
                ruleString = ruleString.substring(3);
            }
            subBags.put(subBagColor.toLowerCase(), numBags);
        }

        rule.subBags = subBags;
        return rule;
    }

    private static class Rule {
        String bagColor;

        Map<String, Integer> subBags = new HashMap<>();

        public String toString() {
            return "Bag Color: " + bagColor + " Sub Bags " + subBags.toString();
        }
    }
}
