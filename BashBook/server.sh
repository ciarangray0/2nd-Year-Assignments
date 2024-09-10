#!/bin/bash

while true; do
read -r request < server.pipe
echo "the request from the server pipe: $request"
read -a request_array <<< "$request"

command="${request_array[0]}"
user="${request_array[1]}"

echo "The command and user variable stored in server $command $user"

case "$command" in
"create")
./create.sh "$user"
check=$?
if [ $check -eq 1 ]; then
echo "nok: program requires 2 args" > "$user".pipe
elif [ $check -eq 2 ]; then
echo "nok: user doesn't exist" > "$user".pipe
else
echo "ok: user created" > "$user".pipe
fi
;;

"add")
friend="${request_array[2]}"   
./add_friend.sh "$user" "$friend"
check=$?
if [ $check -eq 1 ]; then
echo "nok: program requires 3 args" > "$user".pipe
elif [ $check -eq 2 ]; then
echo "nok: user doesn't exist" > "$user".pipe
elif [ $check -eq 3 ]; then
echo "nok: user $friendID doesn't exist" > "$user".pipe
elif [ $check -eq 4 ]; then
echo "nok: friend already in friend list" > "$user".pipe
else		
echo "ok: friend $friend added" > "$user".pipe
fi
;;

"post")
reciever="${request_array[2]}"
message="${request_array[@]:3}"
./post_message.sh "$user" "$reciever" "${message[@]}"
check=$?
if [ $check -eq 1 ]; then
echo "nok: program requires 4 args" > "$user".pipe
elif [ $check -eq 2 ]; then
echo "nok: sender doesn't exist" > "$user".pipe
elif [ $check -eq 3 ]; then
echo "nok: reciever doesn't exist" > "$user".pipe
elif [ $check -eq 4 ]; then
echo "nok: sender not in reciever friend list" > "$user".pipe
else
echo "ok: message posted" > "$user".pipe
fi
;;

"display")    
./display_wall.sh "$user"
check=$?
if [ $check -eq 1 ]; then
echo "nok: program requires 2 args" > "$user".pipe

fi
;;

*)
;;

esac

echo "Accepted Commands: {create|add|post|display}" > "$user".pipe

done
