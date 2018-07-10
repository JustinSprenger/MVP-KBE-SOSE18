#! /bin/sh

echo "--- POSTING A NON-JSON FILE ----"
curl -X POST \
     -H "Content-Type: application/json" \
     -H "Accept: text/plain" \
     -d "@notAJSONFile.txt" \
     -v "http://localhost:8080/contactsJPA/rest/contacts"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- POSTING AN EMPTY FILE -----------"
curl -X POST \
     -H "Content-Type: application/xml" \
     -H "Accept: text/plain" \
     -d "@emptyFile.txt" \
     -v "http://localhost:8080/contactsJPA/rest/contacts"
echo " "
echo "-------------------------------------------------------------------------------------------------"
