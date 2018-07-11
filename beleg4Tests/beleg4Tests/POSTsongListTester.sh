#! /bin/sh
#
# Usage: ./POSTsongListTester.sh 4c87dubofnfrheesom6t0833qa eschuler
#
# 4c87dubofnfrheesom6t0833qa - token
# eschuler = userID

if [ "$#" -ne 2 ]; then
    echo "Illegal number of parameters"
    echo "Usage: ./POSTsongListTester.sh token userId"
    echo "Example: ./POSTsongListTester.sh 4c87dubofnfrheesom6t0833qa eschuler"
    exit 1
fi

echo "--- POSTING A XML SONGLIST -----------------------"
curl -X POST \
     -H "Authorization: $1" \
     -H "Content-Type: application/xml" \
     -H "Accept: text/plain" \
     -d "@aSongList.xml" \
     -v "http://localhost:8080/songsRX/rest/userId/$2/songLists"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- POSTING A JSON SONGLIST   ------------------"
curl -X POST \
     -H "Authorization: $1" \
     -H "Content-Type: application/json" \
     -H "Accept: text/plain" \
     -d "@aSongList.json" \
     -v "http://localhost:8080/songsRX/rest/userId/$2/songLists"
echo " "

echo "--- POSTING A XML SONGLIST WITH NON-EXISTING SONG  ------------------"
curl -X POST \
     -H "Authorization: $1" \
     -H "Content-Type: application/json" \
     -H "Accept: text/plain" \
     -d "@aSongListBad.json" \
     -v "http://localhost:8080/songsRX/rest/userId/$2/songLists"
echo " "
echo "-------------------------------------------------------------------------------------------------"


