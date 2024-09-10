#!/bin/bash

if [ "$#" -ne 1 ]; then
echo "nok: program requires 1 args, but got $#" >&2
exit 1
fi

user="$1"

if [ ! -d "$user" ]; then
echo "nok:user doesn't exist"
exit 2
fi

echo "start of file:"
cat "$user/wall.txt"
echo "end of file"

exit 0
