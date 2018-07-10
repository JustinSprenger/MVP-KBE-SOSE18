#! /bin/sh

echo "--- REQUESTING CONTACT 47 IN XML--------"
curl -X GET \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/contactsJPA/rest/contacts/47"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING CONTACT 48 IN JSON--------"
curl -X GET \
     -H "Accept: application/json" \
     -v "http://localhost:8080/contactsJPA/rest/contacts/48"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING CONTACT 22 --------"
curl -X GET \
     -H "Accept: application/xml" \
     -v "http://localhost:8080/contactsJPA/rest/contacts/22"
echo " "
echo "-------------------------------------------------------------------------------------------------"

echo "--- REQUESTING CONTACT with id NEW --------"
curl -X GET \
     -H "Accept: application/json" \
     -v "http://localhost:8080/contactsJPA/rest/contacts/NEW"
echo " "
echo "-------------------------------------------------------------------------------------------------"