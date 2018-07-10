package de.htwBerlin.ai.kbe.ifaces;

import de.htwBerlin.ai.kbe.data.User;

public interface UserDAO {	
	public User findUserByUserId(String UserId);
	boolean isUserRegistred(String userId);
}
