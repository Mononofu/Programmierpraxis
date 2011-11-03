#!/bin/bash

PROGRAM_NAME="AsciiShop"
INPUT_NAME_TEMPLATE="asciishop-A02-PP.i"
OUTPUT_NAME_TEMPLATE="asciishop-A02-PP.o"
GENERATED_OUTPUT_NAME="asciishop-A02-PP.out"
NUMBER_OF_INPUT_FILES="4"

# set success flag
TESTS_SUCCEDED=1

# compile
javac ${PROGRAM_NAME}.java

for i in `seq 1 ${NUMBER_OF_INPUT_FILES}`
do
	java $PROGRAM_NAME < $INPUT_NAME_TEMPLATE$i > $GENERATED_OUTPUT_NAME$i
	OUT=`diff $OUTPUT_NAME_TEMPLATE$i $GENERATED_OUTPUT_NAME$i --strip-trailing-cr`  
	if [ -n "$OUT" ]; then
		# diff is not empty, test failed
		echo "Test $i failed:"
		echo "$OUT"
		TESTS_SUCCEDED=0
	fi
done

if [ $TESTS_SUCCEDED -eq 1 ]; then
	echo "all tests succeded"
fi
