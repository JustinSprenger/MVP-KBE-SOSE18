package de.htwBerlin.ai.kbe.storage;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import de.htwBerlin.ai.kbe.data.User;
import de.htwBerlin.ai.kbe.ifaces.UserDAO;


@Singleton
public class DBUserDAO implements UserDAO {
	private EntityManagerFactory emf;

	@Inject
	public DBUserDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public boolean isUserRegistred(String userId) {
		User usr = findUserByUserId(userId);
		return usr!=null ? true : false;
	}


	@Override
	public User findUserByUserId(String aUserId) { 
	    EntityManager em = emf.createEntityManager(); try { 
	    TypedQuery<User> query = 
	    em.createQuery("SELECT u FROM User u where u.userId = :userId",
	    User.class);
	    query.setParameter("userId", aUserId); 
	    User user = query.getSingleResult(); 
	    return user;
	} catch (NoResultException nre) { return null; } 
	  finally { em.close(); } 
	} 
}
