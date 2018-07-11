#! /bin/sh
#
# Usage: ./deleteSong.sh 4c87dubofnfrheesom6t0833qa 2
#
# 4c87dubofnfrheesom6t0833qa = token
# 2 = songId
#

if [ "$#" -ne 2 ]; then
    echo "Illegal number of parameters"
    echo "Usage: ./deleteSong.sh token songIdToBeDeleted"
    echo "Example: ./deleteSong.sh 4c87dubofnfrheesom6t0833qa 17"
    exit 1
fi

echo "--- DELETING SONG $2 WITH TOKEN--------"
curl -X DELETE \
     -H "Authorization: $1" \
     -H "Accept: text/plain" \
     -v "http://localhost:8080/songsRX/rest/songs/$2"
echo " "
echo "-------------------------------------------------------------------------------------------------"

