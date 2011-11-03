#!/bin/bash

# set success flag
TESTS_SUCCEDED=1

# compile
javac ${PROGRAM_NAME}.java

if [ $? -eq 0 ]; then
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
fi