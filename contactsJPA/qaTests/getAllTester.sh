#! /bin/sh

echo "--- REQUESTING ALL JSON CONTACT ------------"
curl -X GET \
     -H "Accept: application/json" \
     -v "http://localhost:8080/contactsJPA/rest/contacts"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING ALL XML CONTACT--------"
curl -X GET \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/contactsJPA/rest/contacts"
echo " "
echo "-------------------------------------------------------------------------------------------------"
