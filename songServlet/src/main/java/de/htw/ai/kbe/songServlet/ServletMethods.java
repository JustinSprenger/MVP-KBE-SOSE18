package de.htw.ai.kbe.songServlet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServletMethods {
	// Reads a list of songs from an XML-file into Songs.java
	static Song readXMLToSongs(String filename) throws JAXBException, FileNotFoundException, IOException {
		JAXBContext context = JAXBContext.newInstance(Song.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
			return (Song) unmarshaller.unmarshal(is);
		}
	}

	// Reads a list of songs from a JSON-file into List<Song>
	@SuppressWarnings("unchecked")
	static List<Song> readJSONToSongs(String filename) throws FileNotFoundException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try (InputStream is = new BufferedInputStream(new FileInputStream(filename))) {
			//System.out.println("\n\nList<Song>: "+(List<Song>) objectMapper.readValue(is, new TypeReference<List<Song>>(){})+"\n\n");
			return (List<Song>) objectMapper.readValue(is, new TypeReference<List<Song>>(){});
		}
	}

	// Write a List<Song> to a JSON-file
	static void writeSongsToJSON(List<Song> songs, String filename) throws FileNotFoundException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		try (OutputStream os = new BufferedOutputStream(new FileOutputStream(filename))) {
			objectMapper.writeValue(os, songs);
		}
	}
}

