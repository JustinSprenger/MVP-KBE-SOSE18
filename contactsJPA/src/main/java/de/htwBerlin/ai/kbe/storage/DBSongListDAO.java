package de.htwBerlin.ai.kbe.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.GenericEntity;
import de.htwBerlin.ai.kbe.data.Song;
import de.htwBerlin.ai.kbe.data.SongList;
import de.htwBerlin.ai.kbe.ifaces.SongListDAO;

@Singleton
public class DBSongListDAO implements SongListDAO {

		private EntityManagerFactory emf;
		private static int nextSongListID;


		@Inject
		public DBSongListDAO(EntityManagerFactory emf) {
			this.emf = emf;
			EntityManager em = emf.createEntityManager();
			try {
				TypedQuery<SongList> query = em.createQuery("select s from SongList s", SongList.class);
				if (query.getResultList() != null) {
					for (int i = 0; i < query.getResultList().size(); i++) {
						if (nextSongListID < query.getResultList().get(i).getId()) {
							nextSongListID = query.getResultList().get(i).getId();
						}
					}
				}
			} finally {
				em.close();
			}
		}

		@Override
		public GenericEntity<Collection<SongList>> getAll(String userId, boolean isSameUserRequesting) {
			GenericEntity<Collection<SongList>> ret = null;
			EntityManager em = emf.createEntityManager();
			List<SongList> data = null;
			try {
				TypedQuery<SongList> query = em.createQuery("select s from SongList s", SongList.class);
				if (query.getResultList() != null && query.getResultList().size() > 0) {
					data = query.getResultList();
				}
			} finally {
				em.close();
			}
			if (data != null && data.size() > 0) {
				List<SongList> selectedData = new ArrayList<SongList>();
				for (int i = 0; i < data.size(); i++) {
					if (data.get(i).getUser().getUserId().equals(userId)) {
						if (isSameUserRequesting) {
							selectedData.add(data.get(i));
						} else if (!data.get(i).isPrivate()) {
							selectedData.add(data.get(i));
						}
					}
				}
				if (selectedData.size() > 0) {
					ret = new GenericEntity<Collection<SongList>>(selectedData) {};
				}
			}
			return ret;
		}
		
		@Override
		public List<SongList> getAll(String userId) {
			EntityManager em = emf.createEntityManager();
			try {
				TypedQuery<SongList> query = em.createQuery("select s from SongList s", SongList.class);
				if (query.getResultList() != null && query.getResultList().size() > 0) {
					return query.getResultList();
				}
			} finally {
				em.close();
			}
			return null;
		}
		
		
		@Override
		public GenericEntity<SongList> getOne(int id, String userIDOwner, String userIDCaller) {
			GenericEntity<SongList> ret = null;
			EntityManager em = emf.createEntityManager();
			try {
				if (em.find(SongList.class, id) != null && em.find(SongList.class, id).getUser().getUserId().equals(userIDOwner)) {
					if (userIDOwner.equals(userIDCaller)) {
						ret = new GenericEntity<SongList>(em.find(SongList.class, id)) {};
					} else {
						ret = new GenericEntity<SongList>(em.find(SongList.class, id)) {};
					}
				}
			} finally {
				em.close();
			}
			return ret;
		}

		@SuppressWarnings("unused")
		@Override
		public int post(SongList songList) throws PersistenceException {
			int ret = -1;
			EntityManager em = emf.createEntityManager();
			if (songList != null) {
				if(songList.getSongSet()==null) return ret;
				List<Song> allNewSongs = new ArrayList<>(songList.getSongSet());
				List<Song> allExistingSongs = em.createQuery("select s from Song s", Song.class).getResultList();
				if (allExistingSongs != null && allExistingSongs.size() > 0) {
					boolean found = false;
					boolean allSongsFound = true;
					for (int i = 0; i < allNewSongs.size(); i++) {
						found = false;
						for (int j = 0; j < allExistingSongs.size(); j++) {
							if (allNewSongs.get(i).getId() == allExistingSongs.get(j).getId()) {
								found = true;
								break;
							}
						}
						if (!found) {
							allSongsFound = false;
							break;
						}
					}
					if (allSongsFound) {
						try {
							em.getTransaction().begin();
							songList.setId(++nextSongListID);
							em.persist(songList);
							em.getTransaction().commit();
							ret = songList.getId();
						} catch (PersistenceException e) {
							e.printStackTrace();
							em.getTransaction().rollback();
							throw new PersistenceException("Could not persist entity: " + e.toString());
						} finally {
							em.close();
						}
					}
				}
			}
			return ret;
		}

		@Override
		public boolean delete(int id, String userIDOwner) throws PersistenceException {
			boolean ret = false;
			EntityManager em = emf.createEntityManager();
			try {
				if (em.find(SongList.class, id) != null && em.find(SongList.class, id).getUser().getUserId().equals(userIDOwner)) {
					em.getTransaction().begin();
					em.createQuery("delete SongList where id = " + id).executeUpdate();
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

		@Override
		public boolean isSongInList(int songID) {
			EntityManager em = emf.createEntityManager();
			try {
				TypedQuery<SongList> query = 
					    em.createQuery("SELECT s FROM SongList_Song s where s.song_id = :song_id",
					    SongList.class);
					    query.setParameter("song_id", songID); 
					    SongList sl = query.getSingleResult();
					    return sl!=null ? true : false;
				
			} catch(NullPointerException ex) {
				ex.printStackTrace();
				return false;
			}
			finally {
				em.close();
			}
		}
}
