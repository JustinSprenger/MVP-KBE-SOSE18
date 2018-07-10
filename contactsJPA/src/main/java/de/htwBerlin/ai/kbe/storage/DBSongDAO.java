package de.htwBerlin.ai.kbe.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.GenericEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.htwBerlin.ai.kbe.data.Song;
import de.htwBerlin.ai.kbe.ifaces.SongDAO;

@Singleton
public class DBSongDAO implements SongDAO{
	
	private EntityManagerFactory emf;
	private static int nextSongsID;

	@Inject
	public DBSongDAO(EntityManagerFactory emf) {
		this.emf = emf;
		nextSongsID = 0;
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Song> query = em.createQuery("select s from Song s", Song.class);
			if (query.getResultList() != null) {
				for (int i = 0; i < query.getResultList().size(); i++) {
					if (nextSongsID < query.getResultList().get(i).getId()) {
						nextSongsID = query.getResultList().get(i).getId();
					}
				}
			}
		} finally {
			em.close();
		}
	}

	@Override
	public GenericEntity<Collection<Song>> getAll() {
		GenericEntity<Collection<Song>> ret = null;
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Song> query = em.createQuery("select s from Song s", Song.class);
			if (query.getResultList() != null && query.getResultList().size() > 0) {
				ret = new GenericEntity<Collection<Song>>(query.getResultList()) {};
			}
		} finally {
			em.close();
		}
		return ret;
	}

	@Override
	public GenericEntity<Song> getOne(int id) {
		GenericEntity<Song> ret = null;
		EntityManager em = emf.createEntityManager();
		try {
			if (em.find(Song.class, id) != null) {
				ret = new GenericEntity<Song>(em.find(Song.class, id)) {};
			}
		} finally {
			em.close();
		}
		return ret;
	}

	@Override
	public int post(Song song) throws PersistenceException {
		int ret = -1;
		EntityManager em = emf.createEntityManager();
		if (song != null) {
			song.setId(++nextSongsID);
			try {
				em.getTransaction().begin();
				em.persist(song);
				em.getTransaction().commit();
				ret = song.getId();
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
				throw new PersistenceException("Could not persist entity: " + e.toString());
			} finally {
				em.close();
			}
		}
		return ret;
	}

	@Override
	public boolean put(Song song) throws PersistenceException {
		boolean ret = false;
		EntityManager em = emf.createEntityManager();
		if (song != null) {
			try {
				Song dbSong = null;
				if ((dbSong = em.find(Song.class, song.getId())) != null) {
					em.getTransaction().begin();
					dbSong.setTitle(song.getTitle());
					dbSong.setArtist(song.getArtist());
					dbSong.setAlbum(song.getAlbum());
					dbSong.setReleased(song.getReleased());
					em.getTransaction().commit();
					ret = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				em.getTransaction().rollback();
				throw new PersistenceException("Could not persist entity: " + e.toString());
			} finally {
				em.close();
			}
		}
		return ret;
	}

	@Override
	public boolean delete(int id) throws PersistenceException {
		boolean ret = false;
		EntityManager em = emf.createEntityManager();
		try {
			if (em.find(Song.class, id) != null) {
				em.getTransaction().begin();
				em.remove(em.find(Song.class, id));
				em.getTransaction().commit();
				ret = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			throw new PersistenceException("Could not remove entity: " + e.toString());
		} finally {
			em.close();
		}
		return ret;
	}
	
	
	
	
//	@Override
//	public boolean isSongInASonglist(int songID) {
//		if (this.getOne(songID) != null) {
//			GenericEntity<Song> songo = this.getOne(songID);
//			if (songo != null) {
//				Set<SongList> listo = songo.getEntity().isSongInASonglist();
//				if (listo != null && listo.size() != 0) {
//					for (SongList list : listo) {
//						Set<Song> l = list.getSongSet();
//						for (Song s : l) {
//							if (s.getId() == songID)
//								return true;
//						}
//					}
//				} 
//			}
//		}
//		return false;
//	}
	
	@SuppressWarnings("unchecked")
	static List<Song> readJSONToSongs(InputStream in) throws FileNotFoundException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return (List<Song>) objectMapper.readValue(in, new TypeReference<List<Song>>(){});
	}

}
