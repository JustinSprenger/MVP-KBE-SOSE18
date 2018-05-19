package de.htw.ai.kbe.songServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SongServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private List<Song> songs = new ArrayList<>();
	private Integer id = 0;
	private String mySignature = null;

	public void init(ServletConfig servletConfig) throws ServletException {
		this.mySignature = servletConfig.getInitParameter("signature");
		try {
			songs = ServletMethods.readJSONToSongs("songs.json");
			
			System.out.println("\n\nLaenge: "  +"\n\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Enumeration<String> paramNames = request.getParameterNames();
		
		System.out.println(request.getHeaderNames());
		response.setContentType("application/json");
		
		String responseStr = "";
		String param = "";
		
		while (paramNames.hasMoreElements()) {
			param = paramNames.nextElement();
			switch (param) {
			case "all": {
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.writerWithDefaultPrettyPrinter().writeValue(response.getOutputStream(), songs);
			}
				break;
			case "id": {

			}
				break;
			default: {

			}
			}
			
			
			responseStr = responseStr + param + "=" + request.getParameter(param) + "\n";
		}
		response.setContentType("text/plain");
		try (PrintWriter out = response.getWriter()) {
			responseStr += "\n" + mySignature;
			out.println(responseStr);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		ServletInputStream inputStream = request.getInputStream();
		byte[] inBytes = IOUtils.toByteArray(inputStream);
		try (PrintWriter out = response.getWriter()) {
			out.println(new String(inBytes));
		}
	}
	@Override
	public void destroy()  {
		try {
			ServletMethods.writeSongsToJSON(songs,"songs.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getSignature () {
		return this.mySignature;
	}
	
}