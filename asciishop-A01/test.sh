#!/bin/bash

PROGRAM_NAME="AsciiShop"
INPUT_NAME_TEMPLATE="asciishop-A01-pp.i"
OUTPUT_NAME_TEMPLATE="asciishop-A01-pp.o"
GENERATED_OUTPUT_NAME="asciishop-A01-pp.out"
NUMBER_OF_INPUT_FILES="3"

javac ${PROGRAM_NAME}.java

for i in `seq 1 ${NUMBER_OF_INPUT_FILES}`
do
	java $PROGRAM_NAME < $INPUT_NAME_TEMPLATE$i > $GENERATED_OUTPUT_NAME$i
	diff $OUTPUT_NAME_TEMPLATE$i $GENERATED_OUTPUT_NAME$i --strip-trailing-cr

done
