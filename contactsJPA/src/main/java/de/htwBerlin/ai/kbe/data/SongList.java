package de.htwBerlin.ai.kbe.data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@XmlRootElement(name = "songlist")
@Entity
@Table
public class SongList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "isPrivate")
	private boolean isPrivate;
	
	@ManyToOne
	@JoinColumn(name = "ownerId")
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "SongList_Song", 
		joinColumns = {
			@JoinColumn(name = "song_id", referencedColumnName = "id") },
		inverseJoinColumns = {
			@JoinColumn(name = "songList_id", referencedColumnName = "id") 
		})
	private Set<Song> songsSet;
	
	public SongList() {
	}

	public SongList(User user, Set<Song> songSet, boolean isPrivate) {
		
		this.user = user;
		this.songsSet = songSet;
		if (this.songsSet == null) {
			this.songsSet = new HashSet<Song>();
		}
		this.isPrivate = isPrivate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public boolean isPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Song> getSongSet() {
		return songsSet;
	}

	public void setSongSet(Set<Song> songList) {
		this.songsSet = songList;
	}
}
