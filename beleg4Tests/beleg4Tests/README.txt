Vorbereitung: 

Ihre songsRX-Software aus dem git runterladen und installieren

cp -r  beleg4Tests/ songsRX/

Ihre SongLists-Payloads ins Verzeichnis "beleg4Tests/" kopieren und dort meine SongLists-Payloads überschreiben:

cd songsRX/beleg4Tests

cp YOUR_SongList.json aSongList.json

cp YOUR_SongList.xml aSongList.xml

cp YOUR_BadSongList.json aSongListBad.json


Fuer die Praesentation... same procedure as usual:

cd songsRX

git log | less

git status

mvn clean package

deploy songsRX.war in Tomcat

cd beleg4Tests

Alle Skripte in diesem Verzeichnis muessen mit Argumenten aufgerufen werden.
Ein Skript-Aufruf ohne Argument zeigt eine "Usage"-Message an. Zum Beispiel:

beleg4Tests-> ./getToken.sh 
    Illegal number of parameters
    Usage: ./getToken.sh userId
    Example: ./getToken.sh eschuler

TOKEN holen fuer einen Ihrer User:
./getToken.sh eschuler

TESTING "/songLists" endpoint:

GET ALL song lists:
./GETALLsongListTester.sh token userIdForToken
./GETALLsongListTester.sh token otherUserId

GET A song list
./GETsongListTester.sh token userIdForToken songListId
./GETsongListTester.sh token userIdForToken non-existing-songId
./GETsongListTester.sh token otherUserId publicSongListId 
./GETsongListTester.sh token otherUserId privateSongListId

POST a song list: Sie sollten die folgenden Payloads in diesem Verzeichnis:
aSongList.xml, aSongList.json, aSongListBad.json 
mit Ihren Payloads ueberschreiben. 
POSTsongListTester.sh schickt diese Payloads an Ihren Service.

./POSTsongListTester.sh token userIdForToken
./POSTsongListTester.sh token otherUserId

DELETE a song list: 
./DELETEsongListTester.sh token userIdForToken songListId
./DELETEsongListTester.sh token userIdForToken non-existing-songListId
./DELETEsongListTester.sh token otherUserId songListId

DELETE a song which is in a song list:
./deleteSong.sh token songIdInAList

cd ..
git add beleg4Tests/*
git commit -m "Test results" beleg4Tests/*
git push