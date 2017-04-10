# BrainfuckSubstitutor

BrainfuckSubstitutor (or BFS for short) is an extension of Brainfuck that allows you to substitute parts of the code for characters.

Programs are written first by assigning code to variables, like such:

    a=+++++
Once BFS has read this line, all read code after that will have the character `a` substituted for `+++++`.

You can also assign variables using existing variables, as so:

    a=+++++
    b=aaaa
    bb.
This will set `a` to `+++++`, `b` to `aa`, or equivalently `++++++++++++++++++++`.

This will output the character `(`, with character code 40.
