# BrainfuckSubstitutor [![Build Status](https://travis-ci.org/okx-code/BrainfuckSubstitutor.svg?branch=master)](https://travis-ci.org/okx-code/BrainfuckSubstitutor)

BrainfuckSubstitutor (also known as *BF Substitutor* or *BFS*)is an extension of
Brainfuck that allows you to substitute parts of the code for variables.

There are two ways to write a program: In succinct mode, or in verbose mode.

By default, programs are read in succinct mode. To run in verbose mode,
use the command line flag `-v`.

First, I will provide an explanation of how programs are read in verbose mode.

## Verbose Mode

Programs are written by assigning code to variables, like such:

    a=+++++
Once BFS has read this line, all read code after that will have the character `a` substituted for `+++++`.

You can also assign variables using existing variables, like so:

    a=+++++
    b=aaaa
    bb.
This will set `a` to `+++++`, `b` to `aaaa`, or equivalently `++++++++++++++++++++`.

This will output the character `(`, with character code 40.

Due to the way this works, you can redefine characters in BF.

    +=+++++
    ++++++++.

The above program will set the character `+` to be replaced with `+++++`.
This will output `(`, with character code 40, as the are eight `+` symbols on
the second line.

## Succinct Mode

In succinct mode, variables can only be assigned on the first line.

To define a variable, you remove the `=` sign:

    a++++

Will set `a` to `++++`

Remember the previous program I showed you in verbose mode, that looks like this:

    a=+++++
    b=aaaa
    bb.
In succinct mode, you would rewrite it as this:

    a+++++baaaa
    bb.

Note that in this mode you may use `=` as a variable name unlike in verbose mode,
but in this mode you cannot use any of `+-<>[],.` as variable names.

Also note that programs in succinct mode are required to have at least two lines of code.

## Hexadecimal Output

This is a command line argument that allows you to output in hexadecimal,
for debugging purposes.

To output in lowercase hexadecimal, use `-h`. For uppercase hexadecimal, use `-H`.
If, for some odd reason, you specify both `-h` and `-H` flags, `-H` will override.

## Running BFS

    Usage: java -jar BrainfuckSubstitutor-1.0-SNAPSHOT.jar [args] <filename>
      Arguments:
        -v : Interpret in verbose mode
        -h : Output in hexadecimal
        -H : Output in hexadecimal (upppercase)

Arguments must always come before the filename. Arguments can be combined, like so:

    java -jar BrainfuckSubstitutor-1.0-SNAPSHOT.jar -vh myfile
This will run the same as:

    java -jar BrainfuckSubstitutor-1.0-SNAPSHOT.jar -v -h myfile
