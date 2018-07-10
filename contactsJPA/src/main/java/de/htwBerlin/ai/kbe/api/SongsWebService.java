package de.htwBerlin.ai.kbe.api;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import de.htwBerlin.ai.kbe.auth.Auth;
import de.htwBerlin.ai.kbe.data.Song;
import de.htwBerlin.ai.kbe.data.SongList;
import de.htwBerlin.ai.kbe.ifaces.SongDAO;
import de.htwBerlin.ai.kbe.ifaces.SongListDAO;
import de.htwBerlin.ai.kbe.storage.DBSongDAO;
import de.htwBerlin.ai.kbe.storage.DBSongListDAO;

@Path("/songs")
public class SongsWebService {
	
	@Inject
	private SongDAO songsDAO;

	@Inject
	private SongListDAO songListDAO;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getAll(@HeaderParam("Authorization") String token) {
		Response ret = Response.status(401).entity("Error. Invalid authorization.\n").build();
		if (Auth.isTokenValid(token)) {
			GenericEntity<Collection<Song>> returnData = songsDAO.getAll();
			if (returnData != null) {
				ret = Response.status(200).entity(returnData).build();
			}
			else {
				ret = Response.status(404).entity("ERROR. There are no songs yet.").build();
			}
		}
		return ret;
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getOne(@HeaderParam("Authorization") String token, @PathParam("id") int id) {
		Response ret = Response.status(401).entity("Error. Invalid authorization.\n").build();
		if (Auth.isTokenValid(token)) {
			GenericEntity<Song> returnData = songsDAO.getOne(id);
			if (returnData != null) {
				ret = Response.status(201).entity(returnData).build();
			} else {
				ret = Response.status(404).entity("Error. No data with ID " + id + " found.\n").build();
			}
		}
		return ret;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response post(@HeaderParam("Authorization") String token, Song song, @Context UriInfo uriInfo) {
		Response ret = Response.status(401).entity("Error. Invalid authorization.\n").build();
		if (Auth.isTokenValid(token)) {
			if (song != null) {
				int newID = songsDAO.post(song);
				if (newID != -1) {
					ret = Response.status(201)
							.entity(uriInfo.getAbsolutePathBuilder().path(Integer.toString(newID)).build().toString())
							.build();
				} else {
					ret = Response.status(400)
							.entity("ERROR. Bad song input.")
							.build();
				}
			}
		}
		return ret;
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response put(@HeaderParam("Authorization") String token, @PathParam("id") int id, Song song) {
		Response ret = Response.status(401).entity("Error. Invalid authorization.\n").build();
		if (Auth.isTokenValid(token)) {
			if (song != null) {
				ret = Response.status(400).entity("Error. Song ID should be the same in both Payload and the URL.\n").build();
				if (song.getId() == id) {
					ret = Response.status(404).entity("Error. No data with ID " + id + " found.\n").build();
					if (songsDAO.put(song)) {
						ret = Response.status(204).build();
					}
				}
			} else {
				ret = Response.status(400).entity("Error. Invalid data input.\n").build();
			}
		}
		return ret;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@HeaderParam("Authorization") String token, @PathParam("id") int id) {
		Response ret = Response.status(401).entity("Error. Invalid authorization.\n").build();
		if (Auth.isTokenValid(token)) {
			boolean songIsInList = false;
			List<SongList> sl = songListDAO.getAll(Auth.findUserByToken(token));
			if (sl != null) {
				for (SongList slist : sl) {
					for (Song s : slist.getSongSet()) {
						if (s.getId() == id)
							songIsInList = true;
					}
				}
			}

			if (songIsInList)
				ret = Response.status(403)
						.entity("Error. Song with ID " + id + " is in a Songlist. Deletion not allowed.\n").build();
			else {
				if (songsDAO.delete(id))
					ret = Response.status(204).build();
				else
					ret = Response.status(404).entity("Error. No data with ID " + id + " found.\n").build();
			}
		}
		return ret;
	}
}