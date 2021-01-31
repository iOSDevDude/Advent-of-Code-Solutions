package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayEightRunner {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Part One: " + partOne(parseInstructions()));
        System.out.println("Part Two: " + partTwo(parseInstructions()));
    }

    public static int partOne(List<Instruction> instructions) {
        return runInstructions(instructions).accumulator;
    }


    //Inefficient but functional
    public static int partTwo(List<Instruction> instructions) throws FileNotFoundException{

        List<Instruction> instructionsCopy = new ArrayList<>(instructions);

        for(int i=0; i<instructions.size(); i++) {
            Instruction instruction = instructionsCopy.get(i);
            if(InstructionType.valueOf(instruction.operation) == InstructionType.acc) {
                continue;
            }

            switch (InstructionType.valueOf(instruction.operation)) {
                case jmp:
                    instruction.operation = InstructionType.nop.name();
                    break;
                case nop:
                    instruction.operation = InstructionType.jmp.name();
            }

            RunResult result = runInstructions(instructionsCopy);
            if(result.terminatedSuccessfully) {
                return result.accumulator;
            } else {
                instructionsCopy = parseInstructions();
            }
        }

        return 0;
    }

    public static RunResult runInstructions(List<Instruction> instructions) {
        int accumulator = 0;

        for(int i=0; i<instructions.size(); i++) {
            Instruction instruction = instructions.get(i);

            if(instruction.executed) {
                return new RunResult(false, accumulator);
            }

            switch (InstructionType.valueOf(instruction.operation)) {
                case acc:
                    accumulator+=instruction.argument;
                    break;
                case jmp:
                    i+=instruction.argument-1;
                    break;
                case nop:
                    break;
            }
            instructions.get(i).executed = true;
        }

        return new RunResult(true, accumulator);
    }

    public static List<Instruction> parseInstructions() throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Day8/instructions.txt")).useDelimiter("\n");
        List<String> rawInstructions = new ArrayList<>();
        while(input.hasNext()) {
            rawInstructions.add(input.next());
        }

        List<Instruction> instructions = new ArrayList<>();

        for(String rawInstruction : rawInstructions) {
            String operation = rawInstruction.substring(0, rawInstruction.indexOf(" "));
            String argument = rawInstruction.substring(rawInstruction.indexOf(" ")+1);

            instructions.add(new Instruction(operation, Integer.parseInt(argument)));
        }

        return instructions;
    }

    private enum InstructionType {
        acc, jmp, nop
    }

    private static class Instruction {
        String operation;
        Integer argument;
        Boolean executed;

        Instruction(String operation, Integer argument) {
            this.operation = operation;
            this.argument = argument;
            this.executed = false;
        }
    }

    private static class RunResult {
        Boolean terminatedSuccessfully;
        Integer accumulator;

        public RunResult(Boolean terminatedSuccessfully, Integer accumulator) {
            this.terminatedSuccessfully = terminatedSuccessfully;
            this.accumulator = accumulator;
        }
    }
}
