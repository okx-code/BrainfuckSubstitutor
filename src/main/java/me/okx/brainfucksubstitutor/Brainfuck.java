package me.okx.brainfucksubstitutor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Brainfuck {
    private Reader reader = new InputStreamReader(System.in);
    private final int LENGTH = 65535;
    private byte[] mem = new byte[LENGTH];
    private int dataPointer;

    public void interpret(String code, boolean hexadecimalOutput, boolean upperHex) {
        int l = 0;
        for(int i = 0; i < code.length(); i++) {
            if(code.charAt(i) == '>') {
                dataPointer = (dataPointer == LENGTH-1) ? 0 : dataPointer + 1;
            } else if(code.charAt(i) == '<') {
                dataPointer = (dataPointer == 0) ? LENGTH-1 : dataPointer - 1;
            } else if(code.charAt(i) == '+') {
                mem[dataPointer]++;
            } else if(code.charAt(i) == '-') {
                mem[dataPointer]--;
            } else if(code.charAt(i) == '.') {
                if(hexadecimalOutput) {
                    String hex = Integer.toHexString(mem[dataPointer]);

                    hex = upperHex ? hex.toUpperCase() : hex;

                    String formatted = String.format("%2s", hex).replace(' ', '0');
                    System.out.print(formatted + " ");
                } else {
                    System.out.print((char) mem[dataPointer]);
                }
            } else if(code.charAt(i) == ',') {
                try {
                    byte read = (byte) reader.read();
                    if(read == -1) {
                        read = 0;
                    }
                    mem[dataPointer] = read;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(code.charAt(i) == '[') {
                if(mem[dataPointer] == 0) {
                    i++;
                    while(l > 0 || code.charAt(i) != ']') {
                        if(code.charAt(i) == '[') l++;
                        if(code.charAt(i) == ']') l--;
                        i++;
                    }
                }
            } else if(code.charAt(i) == ']') {
                if(mem[dataPointer] != 0) {
                    i--;
                    while(l > 0 || code.charAt(i) != '[') {
                        if(code.charAt(i) == ']') l++;
                        if(code.charAt(i) == '[') l--;
                        i--;
                    }
                    i--;
                }
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}