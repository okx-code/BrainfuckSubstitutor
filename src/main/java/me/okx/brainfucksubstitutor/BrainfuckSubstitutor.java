package me.okx.brainfucksubstitutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class BrainfuckSubstitutor {
    private static List<Character> bfChars = Arrays.asList('+', '-', '<', '>', '[', ']', '.', ',');

    public static void main(String[] args) {

        if(args.length < 1) {
            System.out.println("Usage: java -jar BrainfuckSubstitutor-1.0-SNAPSHOT.jar [args] <filename>\n" +
                    "  Arguments:\n" +
                    "    -v : Interpret in verbose mode\n" +
                    "    -h : Output in hexadecimal\n" +
                    "    -H : Output in hexadecimal (upppercase)");
            return;
        }

        Map<String, String> values = new HashMap<>();
        List<String> brainfuckCodeLines = new ArrayList<>();

        String code = null;

        boolean verbose = false, hexadecimalOutput = false, upperHex = false;
        if(args.length > 1) {
            for(int i = 0; i < args.length-1; i++) {
                if(args[i].startsWith("-")) {
                    if(args[i].contains("v")) {
                        verbose = true;
                    }
                    if(args[i].contains("h")) {
                        hexadecimalOutput = true;
                    }
                    if(args[i].contains("H")) {
                        hexadecimalOutput = true;
                        upperHex = true;
                    }
                }
            }
        }

        String fileName = args[args.length-1];

        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(new File(fileName).toPath());
        } catch(IOException ex) {
            System.out.println("Invalid filename!");
        }

        if(verbose) {
            // verbose
            for (String line : lines) {
                String[] split = line.split("=");
                if (split.length != 2) {
                    brainfuckCodeLines.add(line);
                    continue;
                }

                String replacement = split[1];

                for (Map.Entry<String, String> entry : values.entrySet()) {
                    replacement = replacement.replace(entry.getKey(), entry.getValue());
                }

                values.put(split[0], replacement);
            }
        } else {
            if(lines.size() < 2) {
                System.out.println("Invalid file! There must be at least two lines");
            }

            String vars = lines.remove(0);

            brainfuckCodeLines = lines;

            String currVar = "",
                    currCode = "";

            char[] chars = (vars + "\0").toCharArray();
            for(int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if(!bfChars.contains(c) && !values.containsKey(String.valueOf(c))) {
                    if(currCode.isEmpty()) {
                        currVar += c;
                    } else {
                        for (Map.Entry<String, String> entry : values.entrySet()) {
                            currCode = currCode.replace(entry.getKey(), entry.getValue());
                        }

                        values.put(currVar, currCode);

                        currCode = "";
                        currVar = "";

                        i--;
                    }
                } else {
                    currCode += c;
                }
            }
        }

        code = String.join("\n", brainfuckCodeLines);

        for (Map.Entry<String, String> entry : values.entrySet()) {
            code = code.replace(entry.getKey(), entry.getValue());
        }

        new Brainfuck().interpret(code, hexadecimalOutput, upperHex);
    }
}
