#! /bin/sh
#
# Usage:
# ./getToken.sh userId
# Example:
# ./getToken.sh eschuler
# 

if [ "$#" -ne 1 ]; then
    echo "Illegal number of parameters"
    echo "Usage: ./getToken.sh userId"
    echo "Example: ./getToken.sh eschuler"
    exit 1
fi

echo "--- REQUESTING A TOKEN ------------"
curl -X GET \
     -v "http://localhost:8080/songsRX/rest/auth?userId=$1"
echo " "
echo "-------------------------------------------------------------------------------------------------"

