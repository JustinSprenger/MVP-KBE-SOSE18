package de.htwBerlin.ai.kbe.api;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import de.htwBerlin.ai.kbe.auth.Auth;
import de.htwBerlin.ai.kbe.data.Song;
import de.htwBerlin.ai.kbe.data.SongList;
import de.htwBerlin.ai.kbe.ifaces.SongListDAO;
import java.util.Collection;

@Path("/userId")
public class SongListWebService {
	private SongListDAO songListsDAO;

	@Inject
	public SongListWebService(SongListDAO songListsDAO) {
		this.songListsDAO = songListsDAO;
	}

	@GET
	@Path("/{userId}/songLists")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getAll(@HeaderParam("Authorization") String token, @PathParam("userId") String userID) {
		Response ret = Response.status(401).entity("Error. Invalid authorization.\n").build();
		if (Auth.isTokenValid(token)) {
			return (songListsDAO.getAll(userID, Auth.findUserByToken(token).equals(userID)) != null)
					? Response.status(200)
							.entity((GenericEntity<Collection<SongList>>) songListsDAO.getAll(userID,
									Auth.findUserByToken(token).equals(userID)))
							.build()
					: Response.status(404).entity("ERROR. No data found.").build();
		}
		return ret;
	}
	
	@GET
	@Path("/{userId}/songLists/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getOne(@HeaderParam("Authorization") String token, @PathParam("userId") String userID, @PathParam("id") int id) {
		Response ret = Response.status(401).entity("Error. Invalid authorization.\n").build();
		if(Auth.isTokenValid(token)) {
			if (songListsDAO.getOne(id, userID, Auth.findUserByToken(token)) != null) {
				if(!songListsDAO.getOne(id, userID, Auth.findUserByToken(token)).getEntity().isPrivate())
					ret = Response.status(200)
							.entity((GenericEntity<SongList>) songListsDAO.getOne(id, userID, Auth.findUserByToken(token)))
							.build();
			} else { 
				ret = Response.status(404).entity("ERROR. No data found.").build(); }
		}
		return ret;
	}
	
	
	@POST
	@Path("/{userId}/songLists")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	public Response post(@HeaderParam("Authorization") String token, @PathParam("userId") String userID, @Context UriInfo uriInfo, SongList songList) {
		Response ret = Response.status(401).entity("Error. Invalid authorization.\n").build();
		if(Auth.isTokenValid(token)) {	
//			if (Auth.findUserByToken(token).equals(userID)) {
//				if (songList != null) {
//					int newID = songListsDAO.post(songList);
//					if (newID != -1) {
//						ret = Response.status(201).entity(uriInfo.getAbsolutePathBuilder().path(Integer.toString(newID)).build().toString()).build();
//					} else {
//						ret = Response.status(400).entity("ERROR. Invalid input.\n").build();
//					}
//				} else {
//					ret = Response.status(400).entity("ERROR. Invalid input.\n").build();
//				}
//			} else {
//				ret = Response.status(401).entity("ERROR. Not allowed to post Songlists for other users.\n").build();
//			}
		ret = Response.status(400).entity("WARNING. POST Method for SongList not implemented!\n").build();
		}
		return ret;
	}
	
	@DELETE
	@Path("/{userId}/songLists/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@HeaderParam("Authorization") String token, @PathParam("userId") String userID, @PathParam("id") int id) {
		Response ret = Response.status(401).entity("Error. Invalid authorization.\n").build();
		if(Auth.isTokenValid(token)) {	
			if (Auth.findUserByToken(token).equals(userID)) {
				if (songListsDAO.delete(id, userID)) {
					ret = Response.status(204).build();
				} else {
					ret = Response.status(404).entity("ERROR. No data found.").build();					
				}
			} else {
				ret = Response.status(401).entity("ERROR. Not allowed to delete Songlists for other users.\n").build();
			}
		}
		return ret;
	}
}
