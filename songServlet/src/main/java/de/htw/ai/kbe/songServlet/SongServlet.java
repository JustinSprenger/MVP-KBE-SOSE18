package de.htw.ai.kbe.songServlet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.spi.SyncResolver;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SongServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private List<Song> songs = new ArrayList<>();
	private Integer id = 0;
	private String mySignature = null;

	public void init(ServletConfig servletConfig) throws ServletException {
		this.mySignature = servletConfig.getInitParameter("signature");
		try {
			songs = ServletMethods.readJSONToSongs("D:/Uni/eclipse-workspace/MVP-KBE-SOSE18/MVP-KBE-SOSE18/songServlet/songs.json");
			songs.forEach((x -> {if(x.getId()>=id) {id=x.getId();}}));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Enumeration<String> paramNames = request.getParameterNames();
		response.setCharacterEncoding("UTF-8");
		ObjectMapper objectMapper = new ObjectMapper();
		
		String responseStr = "";
		String param = "";
		
		if(request.getContentType()!= null && (request.getContentType().endsWith("json") || request.getContentType().endsWith("xml"))) {
		while (paramNames.hasMoreElements()) {
			param = paramNames.nextElement();
			switch (param) {
			case "all": {
				if(request.getContentType().endsWith("json")) {
					response.setContentType("application/json");
					objectMapper.writerWithDefaultPrettyPrinter().writeValue(response.getOutputStream(), songs);
				
				}
				if(request.getContentType().endsWith("xml")) {
					response.setContentType("application/xml");
					Songs songz = new Songs();
					songs.forEach(sg -> songz.getSong().add(sg));
					try {
						ServletMethods.writeSongsToXML(songz, response.getOutputStream());
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
				break;
			case "songId": {
				int songID = (request.getParameterValues(param)[0].toString()).matches("\\d+") ? Integer.valueOf(request.getParameterValues(param)[0].toString()) : -1;
				
				
				if(songID!=-1 && (songs.stream().filter(x -> x.getId()==songID).findAny().orElse(null))!=null) {
					if(request.getContentType().endsWith("json")) {
						response.setContentType("application/json");
						objectMapper.writerWithDefaultPrettyPrinter().writeValue(response.getOutputStream(), songs.stream().filter(x -> x.getId()==songID).findAny().orElse(null));
					}
					if(request.getContentType().endsWith("xml")) {
						response.setContentType("application/xml");
						
						Songs songz = new Songs();
						songz.getSong().add(songs.stream().filter(x -> x.getId()==songID).findAny().orElse(null));
						
						try {
							ServletMethods.writeSongsToXML(songz , response.getOutputStream());
						} catch (JAXBException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				else {
					response.setContentType("text/plain");
					try (PrintWriter out = response.getWriter()) {
						responseStr += "\n\n" + mySignature;
						out.println(responseStr);
					}
					
				}
			}
				break;
			default: {
				responseStr = "Try again using ?all or ?id=<number>.";
			}
			}
		}
		
		}
		else {
			response.setContentType("text/plain");
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(response.getOutputStream(), "Such request Content-Type doesn\'t exist!" + getSignature());
		}
	}

	@Override
	public synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		if(request.getContentType().endsWith("json")) {
			
			ObjectMapper objectMapper = new ObjectMapper();
			try (InputStream is = request.getInputStream()) {
			Song song = (Song) objectMapper.readValue(is, new TypeReference<Song>(){});
			song.setId(++id);
			songs.add(song);
			try (PrintWriter out = response.getWriter()) {
				out.printf("{ \"id\" : %d }",id);
			}
			}
			}
		
			
			
		if(request.getContentType().endsWith("xml")) {
			try {
				Songs songz;// = ServletMethods.readXMLToSongs(request.getInputStream());
				Song song = (Song) songz.getSong().get(0);
				song.setId(++id);
				songs.add(song);
				
				try (PrintWriter out = response.getWriter()) {
					out.printf("{ \"id\" : %d }", id);
				}
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	@Override
	public void destroy()  {
		try {
			ServletMethods.writeSongsToJSON(songs,"D:/Uni/eclipse-workspace/MVP-KBE-SOSE18/MVP-KBE-SOSE18/songServlet/songz.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getSignature () {
		return this.mySignature;
	}
	
}