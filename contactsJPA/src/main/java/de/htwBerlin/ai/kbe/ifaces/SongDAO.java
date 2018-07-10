package de.htwBerlin.ai.kbe.ifaces;

import javax.ws.rs.core.GenericEntity;
import de.htwBerlin.ai.kbe.data.Song;
import java.util.Collection;

public interface SongDAO {
	public GenericEntity<Collection<Song>> getAll();

	public GenericEntity<Song> getOne(int id);

	public int post(Song song);

	public boolean put(Song song);

	public boolean delete(int id);

}