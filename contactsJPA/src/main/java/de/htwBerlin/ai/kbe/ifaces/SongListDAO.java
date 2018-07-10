package de.htwBerlin.ai.kbe.ifaces;

import javax.ws.rs.core.GenericEntity;
import de.htwBerlin.ai.kbe.data.SongList;
import java.util.Collection;
import java.util.List;

public interface SongListDAO {
	public GenericEntity<Collection<SongList>> getAll(String userId, boolean isOwner);

	public GenericEntity<SongList> getOne(int id, String owner, String userId);

	public int post(SongList songList);

	public boolean delete(int id, String owner);

	boolean isSongInList(int songID);

	public List<SongList> getAll(String string);
}
