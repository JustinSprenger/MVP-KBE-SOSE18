#! /bin/sh

echo "--- POSTING A JSON CONTACT ------------------"
curl -X POST \
     -H "Content-Type: application/json" \
     -H "Accept: text/plain" \
     -d "@oneContact.json" \
     -v "http://localhost:8080/contactsJPA/rest/contacts"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- POSTING A XML CONTACT ------------------"
curl -X POST \
     -H "Content-Type: application/xml" \
     -H "Accept: text/plain" \
     -d "@oneContact.xml" \
     -v "http://localhost:8080/contactsJPA/rest/contacts"
echo " "
echo "-------------------------------------------------------------------------------------------------"
