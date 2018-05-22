package de.htw.ai.kbe.songServlet;

public class Song {

	private Integer id;
	private String title;
	private String artist;
	private String album;
	private Integer released;

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
