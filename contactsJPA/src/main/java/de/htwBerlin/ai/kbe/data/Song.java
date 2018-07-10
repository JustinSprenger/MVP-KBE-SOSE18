package de.htwBerlin.ai.kbe.data;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "song")
@Entity
@Table
public class Song {
	@Id
	private int id;
	@Column
	private String title;
	@Column
	private String artist;
	@Column
	private String album;
	@Column
	private Integer released;
//	@Transient
//	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "songsSet")
//	@JoinTable(name = "SongList_Song", 
//		joinColumns = {
//			@JoinColumn(name = "songList_id", referencedColumnName = "id") },
//		inverseJoinColumns = {
//			@JoinColumn(name = "song_id", referencedColumnName = "id") 
//		})
//	private Set<SongList> songListsSet;
	

		// needed for JAXB
		public Song() {
		}

		// Example of a builder:
		public static class Builder {
			// required parameter
			private Integer id;
			private String title;
			private String artist;
			private String album;
			private Integer released;
			

			public Builder(@NotNull Integer id, @NotNull String title, @NotNull String artist, @NotNull String album, @NotNull Integer released) {
				this.id = id;
				this.title = title;
				this.artist = artist;
				this.album = album;
				this.released = released;				
			}
	

			public Song build() {
				return new Song(this);
			}
		}

		private Song(Builder builder) {
			this.id = builder.id;
			this.title = builder.title;
			this.artist = builder.artist;
			this.album = builder.album;
			this.released = builder.released;
		}
		
	// Getters & Setters
		
//	public Set<SongList> isSongInASonglist() {
//		return this.songListsSet;
//	}
//
//	public void setSongListsSet(Set<SongList> songListsSet) {
//		this.songListsSet = songListsSet;
//	}

	public Integer getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public Integer getReleased() {
		return released;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public void setReleased(Integer released) {
		this.released = released;
	}

  @Override
  public String toString() {
      return "{\n   \"id\" = " + id + ",\n   \"title\" = \"" + title + "\",\n   \"artist\" = \"" + artist + "\",\n   \"album\" = \"" + album + "\",\n   \"released\" = "
              + released + "\n}";
  }
	
}

