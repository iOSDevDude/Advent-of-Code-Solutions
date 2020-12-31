package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DaySevenRunner {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day7/bag_rules.txt")).useDelimiter("\n");
        List<String> rules = new ArrayList();
        while(input.hasNext()) {
            rules.add(input.next());
        }

        System.out.println(rules);
        createBagTree(rules);
    }

    static void partOne(List<String> rules) {
        BagTree bagTree = new BagTree(new Bag(null, null, null, null, null));
        bagTree.findBagByColor("test");
    }

    static Bag createBagTree(List<String> bagRules) {
//        Bag bag = new Bag(null, null, null, null, null);
        BagTree bagTree = null;

        for(String rule : bagRules) {
            String color = rule.substring(0, rule.indexOf("bags")-1);
            rule = rule.substring(rule.indexOf("bags")+13);

            if(bagTree == null) {
                bagTree = new BagTree(new Bag(color));
            }

            Bag existingParent = bagTree.findBagByColor(color);
            if(existingParent == null) {
                existingParent = new Bag(color);
            }


            while(rule.contains(",")) {

            }


            System.out.println(rule);
        }


//       return bag;
    }

    private static class BagTree {
        private Bag root;

        public BagTree(Bag bag) {
            root = bag;
        }

        public Bag findBagByColor(String color) {
            if(root == null) {
                return null;
            }

            return findBagByColor(root, color);
        }

        private Bag findBagByColor(Bag bag, String color) {
            if(bag.color.equals(color)) {
                return bag;
            }
            if(bag.hasChildBag()) {
                Bag foundBag = findBagByColor(bag.nextChildBag, color);
                if(foundBag != null) {
                    return foundBag;
                }
            }
            if(bag.hasSiblingBag()) {
                Bag foundbag = findBagByColor(bag.nextSiblingBag, color);
                if(foundbag != null) {
                    return foundbag;
                }
            }

            return null;
        }
    }

    private static class Bag {
        public String color;
        public Bag nextChildBag;
        public Integer nextChildBagCount;
        public Bag nextSiblingBag;
        public Integer nextSiblingBagCount;

        public Bag(String color) {
            this.color = color;
        }

        public Bag(String color, Bag nextChildBag, Integer nextChildBagCount, Bag nextSiblingBag, Integer nextSiblingBagCount) {
            this.color = color;
            this.nextChildBag = nextChildBag;
            this.nextChildBagCount = nextChildBagCount;
            this.nextSiblingBag = nextSiblingBag;
            this.nextSiblingBagCount = nextSiblingBagCount;
        }

        public void addChild(Bag bag) {
            
            Bag tempBag = this;
            if(tempBag.hasChildBag()) {
                tempBag = this.nextChildBag;
                while (bag.hasSiblingBag()) {
                    tempBag = tempBag.nextSiblingBag;
                }

                tempBag.nextSiblingBag = bag;
            } else {
                tempBag.nextChildBag = bag;
            }
        }
        
        public void addSibling(Bag bag) {
            Bag tempBag = this;
            while(tempBag.hasSiblingBag()) {
                tempBag = tempBag.nextSiblingBag;
            }

            tempBag.nextSiblingBag = bag;
        }

        public boolean hasChildBag() {
            return nextChildBag != null;
        }

        public boolean hasSiblingBag() {
            return nextSiblingBag != null;
        }
    }
}
