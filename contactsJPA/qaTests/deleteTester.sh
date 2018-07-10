#! /bin/sh

echo "--- DELETING CONTACT ------------------"
curl -X DELETE \
     -v "http://localhost:8080/contactsJPA/rest/contacts/24"
echo " "
echo "-------------------------------------------------------------------------------------------------"
