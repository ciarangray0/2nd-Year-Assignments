#!/bin/bash

if [ "$#" -ne 3 ]; then 
echo "nok: program requires 3 args, but got $#" >&2
exit 1
fi

sender="$1"
reciever="$2"
message="$3"

if [ ! -d "$sender" ]; then
echo "nok:sender doesn't exist"
exit 2
fi

if [ ! -d "$reciever" ]; then
echo "nok:Reciever doesn't exist"
exit 3
fi

if ! grep -q "^$sender" "$reciever/friends.txt" > /dev/null; then
echo "nok:Friend is not in user's friend list"
exit 4
fi

echo "$sender: $message" >> "$reciever/wall.txt"

echo "ok: message posted"
exit 0
