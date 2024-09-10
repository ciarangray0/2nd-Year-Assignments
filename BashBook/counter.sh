#!/bin/bash

if [ "$#" -ne 1 ];
then
	echo "ERROR: program requires 1 arg, but got $#" >&2	
	exit 1
fi

# get name of the file with its address
file="$1"

# if the file exists
if [ ! -f "$file" ];
then
	echo "file does not exist"

	directory=$(dirname $file)
	# does the directory exist?
	if [ ! -d "$directory" ];
	then
		echo "ERROR: directory does not exist" >&2
		exit 1
	fi

	# create a new file which would containt 0
	touch "$file"
	echo 0 > "$file"	
fi

while [ true ];
do
	#wait 1 second
	sleep 1
	
	# read variable from the file
	typeset -i variable=$(cat $file)
	
	# increase variable value
	variable=$(expr "$variable" + 1)
	
	#write into the file again
	echo $variable > "$file"
	echo "wrote $variable into the file"
done

exit 0
