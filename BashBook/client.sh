#!/bin/bash
if [ -z "$1" ]; then
echo "Error: program requires a request"
exit 1
fi

user="$1"

if [ ! -p server.pipe ]; then
mkfifo server.pipe
sleep 1
fi

if [ ! -p "$user".pipe ]; then
mkfifo "$user".pipe
fi

while true; do
echo "" > server.pipe
read -p "Enter your request, then the UserID, then the message, in that order" request friend message

echo "$request" "$user" "$friend" "$message" > server.pipe

read -r sResponse < "$user".pipe

echo $sResponse

done
