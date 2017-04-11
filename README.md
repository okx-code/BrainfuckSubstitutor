# BrainfuckSubstitutor

BrainfuckSubstitutor (also known as *BF Substitutor* or *BFS*)
is an extension of Brainfuck that allows you to substitute parts of the code for
variables.

There are two ways to write a program: In succinct mode, or in verbose mode.

Programs are written first by assigning code to variables, like such:

    a=+++++
Once BFS has read this line, all read code after that will have the character `a` substituted for `+++++`.

You can also assign variables using existing variables, as so:

    a=+++++
    b=aaaa
    bb.
This will set `a` to `+++++`, `b` to `aa`, or equivalently `++++++++++++++++++++`.

This will output the character `(`, with character code 40.
