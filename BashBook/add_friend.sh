#!/bin/bash

if [ "$#" -ne 2 ]; then
echo "nok: program requires 2 args, but got $#" >&2
exit 1
fi

user="$1"
friend="$2"
echo "user and friend values recieved in add_friend $user $friend"

if [ ! -d "$user" ]; then 
echo "nok:user doesn't exist"
exit 2
fi

if [ ! -d "$friend" ]; then
echo "nok:Friend doesn't exist"
exit 3
fi

if grep "^$friend" "$user/friends.txt" > /dev/null; then
echo "nok:Friend is already in user's friend list"
exit 4
fi

echo "$friend" >> "$user/friends.txt" 
echo "ok:friend added"
exit 0
