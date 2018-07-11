#! /bin/sh
#
# Usage: ./songsTester.sh 4c87dubofnfrheesom6t0833qa 2
#
# 4c87dubofnfrheesom6t0833qa = token
# 2 = songId
#

if [ "$#" -ne 2 ]; then
    echo "Illegal number of parameters"
    echo "Usage: ./songsTester.sh token songIdToBeRequestedAndDeleted"
    echo "Example: ./songsTester.sh 4c87dubofnfrheesom6t0833qa 17"
    exit 1
fi

echo "--- REQUESTING SONG WITHOUT TOKEN SHOULD RETURN 401--------"
curl -X GET \
     -H "Accept: application/json" \
     -v "http://localhost:8080/songsRX/rest/songs/$2"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING ALL SONGS (XML) WITH TOKEN: ------------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING SONG with id $2 IN XML WITH TOKEN: ------------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/songsRX/rest/songs/$2"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING SONG with id $2 IN JSON WITH TOKEN: ------------"
curl -X GET \
     -H "Authorization: $1" \
     -H "Accept: application/json" \
     -v "http://localhost:8080/songsRX/rest/songs/$2"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- POSTING WITHOUT TOKEN SHOULD RETURN 401------------------"
curl -X POST \
     -H "Content-Type: application/json" \
     -H "Accept: text/plain" \
     -d "@aSong.json" \
     -v "http://localhost:8080/songsRX/rest/songs"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- POSTING A JSON SONG WITH TOKEN ------------------"
curl -X POST \
     -H "Authorization: $1" \
     -H "Content-Type: application/json" \
     -H "Accept: text/plain" \
     -d "@aSong.json" \
     -v "http://localhost:8080/songsRX/rest/songs"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- POSTING A XML SONG WITH TOKEN  ------------------"
curl -X POST \
     -H "Authorization: $1" \
     -H "Content-Type: application/xml" \
     -H "Accept: text/plain" \
     -d "@aSong.xml" \
     -v "http://localhost:8080/songsRX/rest/songs"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- UPDATING XML-SONG WITHOUT TOKEN SHOULD RETURN 401------------------"
curl -X PUT \
     -H "Content-Type: application/xml" \
     -H "Accept: text/plain" \
     -d "@anUpdatedSong.xml" \
     -v "http://localhost:8080/songsRX/rest/songs/$2"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- UPDATING JSON-SONG WITH TOKEN ------------------"
curl -X PUT \
     -H "Authorization: $1" \
     -H "Content-Type: application/json" \
     -H "Accept: text/plain" \
     -d "@anUpdatedSong.json" \
     -v "http://localhost:8080/songsRX/rest/songs/$2"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- DELETING SONG $2 WITH TOKEN--------"
curl -X DELETE \
     -H "Authorization: $1" \
     -H "Accept: text/plain" \
     -v "http://localhost:8080/songsRX/rest/songs/$2"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- DELETING WITHOUT TOKEN SHOULD RETURN 401 --------"
curl -X DELETE \
     -H "Accept: text/plain" \
     -v "http://localhost:8080/songsRX/rest/songs/28"
echo " "
echo "-------------------------------------------------------------------------------------------------"

