#!/bin/bash

if [ "$#" -ne 1 ]; then
echo "nok:program needs 1 argument, but got $#" >&2
exit 1
fi

user="$1"

if [ -d "$user" ]; then
echo "nok:user already exists"
exit 2
fi

mkdir "$user"
touch "$user/wall.txt"
touch "$user/friends.txt"
echo "ok: user created"
exit 0
