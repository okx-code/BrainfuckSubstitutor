package me.okx.brainfucksubstitutor;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrainfuckSubstitutor {
    public static void main(String[] args) throws Exception {
        if(args.length != 1) {
            System.out.println("Usage: java -jar BrainfuckSubstitutor-1.0-SNAPSHOT.jar <filename>");
            return;
        }

        Map<String, String> values = new HashMap<>();

        List<String> lines = Files.readAllLines(new File(args[0]).toPath());

        List<String> brainfuckCodeLines = new ArrayList<>();

        for(String line : lines) {
            String[] split = line.split("=");
            if(split.length != 2) {
                brainfuckCodeLines.add(line);
                continue;
            }

            String replacement = split[1];

            for(Map.Entry<String, String> entry : values.entrySet()) {
                replacement = replacement.replace(entry.getKey(), entry.getValue());
            }

            values.put(split[0], replacement);
        }

        String code = String.join("\n", brainfuckCodeLines);

        for(Map.Entry<String, String> entry : values.entrySet()) {
            code = code.replace(entry.getKey(), entry.getValue());
        }

        new Brainfuck().interpret(code);
    }
}
