package de.htwBerlin.ai.kbe.config;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import de.htwBerlin.ai.kbe.ifaces.SongDAO;
import de.htwBerlin.ai.kbe.ifaces.SongListDAO;
import de.htwBerlin.ai.kbe.ifaces.UserDAO;
import de.htwBerlin.ai.kbe.storage.DBSongDAO;
import de.htwBerlin.ai.kbe.storage.DBSongListDAO;
import de.htwBerlin.ai.kbe.storage.DBUserDAO;

public class DependencyBinder extends AbstractBinder {
    @Override
    protected void configure() {
    	
        bind (Persistence
                .createEntityManagerFactory("songsDB-PU"))
                .to(EntityManagerFactory.class);
   
        bind(DBSongDAO.class)
        .to(SongDAO.class)
        .in(Singleton.class);
        
        bind(DBUserDAO.class)
        .to(UserDAO.class)
        .in(Singleton.class);
        
        bind(DBSongListDAO.class)
        .to(SongListDAO.class)
        .in(Singleton.class);
    }
}
