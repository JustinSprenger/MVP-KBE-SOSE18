package de.htwBerlin.ai.kbe.auth;

import java.util.Map;
import java.util.HashMap;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public class Auth {
	private static Map<String, String> allUsersTokens = new HashMap<>();
	
	
	public static String generateToken(String userID) {
		String returnToken = "";
		boolean tokenGenerated = false;
		String genToken = "";
		while (!tokenGenerated) {
			RandomStringGenerator randomStringGenerator =
				new RandomStringGenerator.Builder()
				    .withinRange('0', 'z')
				    .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
				    .build();
			genToken = randomStringGenerator.generate(138);
			
			if (!allUsersTokens.containsKey(genToken)) {
				allUsersTokens.put(genToken, userID);
				returnToken = genToken;
				tokenGenerated = true;
			}
		}

		return returnToken;
	}

	public static boolean isTokenValid(String authToken) {
		return (allUsersTokens.containsKey(authToken));
	}

	public static String findUserByToken(String authToken) {
		return (allUsersTokens.get(authToken));
	}
}
