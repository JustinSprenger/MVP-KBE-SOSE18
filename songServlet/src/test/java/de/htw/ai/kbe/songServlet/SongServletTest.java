//package de.htw.ai.kbe.songServlet;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.mock.web.MockServletConfig;
//
//import de.htw.ai.kbe.songServlet.SongServlet;
//
//public class SongServletTest {
//	 	private SongServlet servlet;
//	    private MockServletConfig config;
//	    private MockHttpServletRequest request;
//	    private MockHttpServletResponse response;
//
//	    private final static String SIGNATURE_STRING = "My test signature";
//	    
//	    @Before
//	    public void setUp() throws ServletException {
//	        servlet = new SongServlet();
//	        request = new MockHttpServletRequest();
//	        response = new MockHttpServletResponse();
//	        config = new MockServletConfig();
//	        config.addInitParameter("signature", SIGNATURE_STRING);
//	        servlet.init(config); //throws ServletException
//	    }
//	    
//	    @Test
//	    public void initShouldSetMySignature() {
//	    		assertEquals(SIGNATURE_STRING, servlet.getSignature());
//	    }
//
//	    @Test
//	    public void doGetShouldEchoParametersId() throws ServletException, IOException {
//	        request.addParameter("id", "2");
//
//	        servlet.doGet(request, response);
//	        System.out.println(response.getContentAsString());
//	        
//	        assertEquals("application/json", response.getContentType());
//	        assertTrue(response.getContentAsString().contains('"'+"id"+'"'+ " = 2"));
//	        assertTrue(response.getContentAsString().contains(SIGNATURE_STRING));        
//	    }
//	    
//	    @Test
//	    public void doGetFalseContentType() throws ServletException, IOException {
//	        request.addParameter("id", "2");
//
//	        servlet.doGet(request, response);
//	        System.out.println(response.getContentAsString());
//	        
//	        assertNotEquals("text/plain", response.getContentType());
//	        assertTrue(response.getContentAsString().contains('"'+"id"+'"'+ " = 2"));
//	        assertTrue(response.getContentAsString().contains(SIGNATURE_STRING));        
//	    }
//	    
//	    @Test
//	    public void doNotGetShouldEchoParametersId() throws ServletException, IOException {
//	        request.addParameter("id", "201");
//
//	        servlet.doGet(request, response);
//	        System.out.println(response.getContentAsString());
//	        
//	        assertEquals("application/json", response.getContentType());
//	        assertFalse(response.getContentAsString().contains('"'+"id"+'"'+ " = 201"));
//	        assertTrue(response.getContentAsString().contains(SIGNATURE_STRING));        
//	    }
//	    
//	    @Test
//	    public void doGetFalseId() throws ServletException, IOException {
//	        request.addParameter("id", "js");
//
//	        servlet.doGet(request, response);
//	        System.out.println(response.getContentAsString());
//	        
//	        assertEquals("application/json", response.getContentType());
//	        assertFalse(response.getContentAsString().contains('"'+"id"+'"'+ " = js"));
//	        assertTrue(response.getContentAsString().contains(SIGNATURE_STRING));        
//	    }
//	    
//	    @Test
//	    public void doNotGetNoId() throws ServletException, IOException {
//	        request.addParameter("id", "");
//
//	        servlet.doGet(request, response);
//	        System.out.println(response.getContentAsString());
//	        
//	        assertEquals("application/json", response.getContentType());
//	        assertFalse(response.getContentAsString().contains('"'+"id"+'"'+ " = "));
//	        assertTrue(response.getContentAsString().contains(SIGNATURE_STRING));        
//	    }
//	    
//	    @Test
//	    public void doGetShouldEchoParametersAll() throws ServletException, IOException {
//	        request.addParameter("all");
//	        servlet.doGet(request, response);
//	        String json = "[ {\r\n" + 
//	        		"  \"id\" : 10,\r\n" + 
//	        		"  \"title\" : \"7 Years\",\r\n" + 
//	        		"  \"artist\" : \"Lukas Graham\",\r\n" + 
//	        		"  \"album\" : \"Lukas Graham (Blue Album)\",\r\n" + 
//	        		"  \"released\" : 2015\r\n" + 
//	        		"}, {\r\n" + 
//	        		"  \"id\" : 9,\r\n" + 
//	        		"  \"title\" : \"Private Show\",\r\n" + 
//	        		"  \"artist\" : \"Britney Spears\",\r\n" + 
//	        		"  \"album\" : \"Glory\",\r\n" + 
//	        		"  \"released\" : 2016\r\n" + 
//	        		"}, {\r\n" + 
//	        		"  \"id\" : 8,\r\n" + 
//	        		"  \"title\" : \"No\",\r\n" + 
//	        		"  \"artist\" : \"Meghan Trainor\",\r\n" + 
//	        		"  \"album\" : \"Thank You\",\r\n" + 
//	        		"  \"released\" : 2016\r\n" + 
//	        		"}, {\r\n" + 
//	        		"  \"id\" : 7,\r\n" + 
//	        		"  \"title\" : \"i hate u, i love u\",\r\n" + 
//	        		"  \"artist\" : \"Gnash\",\r\n" + 
//	        		"  \"album\" : \"Top Hits 2017\",\r\n" + 
//	        		"  \"released\" : 2017\r\n" + 
//	        		"}, {\r\n" + 
//	        		"  \"id\" : 6,\r\n" + 
//	        		"  \"title\" : \"I Took a Pill in Ibiza\",\r\n" + 
//	        		"  \"artist\" : \"Mike Posner\",\r\n" + 
//	        		"  \"album\" : \"At Night, Alone.\",\r\n" + 
//	        		"  \"released\" : 2016\r\n" + 
//	        		"}, {\r\n" + 
//	        		"  \"id\" : 5,\r\n" + 
//	        		"  \"title\" : \"Bad Things\",\r\n" + 
//	        		"  \"artist\" : \"Camila Cabello, Machine Gun Kelly\",\r\n" + 
//	        		"  \"album\" : \"Bloom\",\r\n" + 
//	        		"  \"released\" : 2017\r\n" + 
//	        		"}, {\r\n" + 
//	        		"  \"id\" : 4,\r\n" + 
//	        		"  \"title\" : \"Ghostbusters (I'm not a fraid)\",\r\n" + 
//	        		"  \"artist\" : \"Fall Out Boy, Missy Elliott\",\r\n" + 
//	        		"  \"album\" : \"Ghostbusters\",\r\n" + 
//	        		"  \"released\" : 2016\r\n" + 
//	        		"}, {\r\n" + 
//	        		"  \"id\" : 3,\r\n" + 
//	        		"  \"title\" : \"Team\",\r\n" + 
//	        		"  \"artist\" : \"Iggy Azalea\",\r\n" + 
//	        		"  \"album\" : null,\r\n" + 
//	        		"  \"released\" : 2016\r\n" + 
//	        		"}, {\r\n" + 
//	        		"  \"id\" : 2,\r\n" + 
//	        		"  \"title\" : \"Mom\",\r\n" + 
//	        		"  \"artist\" : \"Meghan Trainor, Kelli Trainor\",\r\n" + 
//	        		"  \"album\" : \"Thank You\",\r\n" + 
//	        		"  \"released\" : 2016\r\n" + 
//	        		"}, {\r\n" + 
//	        		"  \"id\" : 1,\r\n" + 
//	        		"  \"title\" : \"Can’t Stop the Feeling\",\r\n" + 
//	        		"  \"artist\" : \"Justin Timberlake\",\r\n" + 
//	        		"  \"album\" : \"Trolls\",\r\n" + 
//	        		"  \"released\" : 2016\r\n" + 
//	        		"} ]\r\n" + 
//	        		"My test signature\r\n" + 
//	        		"";
//	        
//	        //System.out.println(songs.toString() + SIGNATURE_STRING);
//	        //System.out.println(response.getContentAsString());
//	        System.out.println(response.getContentType());
//	        
//	        assertEquals("UTF-8", response.getCharacterEncoding());
//	        assertEquals("application/json", response.getContentType());
//	        assertEquals(response.getContentAsString(),json);
//	        assertTrue(response.getContentAsString().contains(SIGNATURE_STRING));   
//	    }
//	    
//	    @Test
//	    public void doNotGetShouldEchoParametersAll() throws ServletException, IOException {
//	        request.addParameter("all");
//	        servlet.doGet(request, response);
//	        
//	        //System.out.println(songs.toString() + SIGNATURE_STRING);
//	        //System.out.println(response.getContentAsString());
//	        System.out.println(response.getContentType());
//	    
//	        assertEquals("UTF-8", response.getCharacterEncoding());
//	        assertEquals("application/json", response.getContentType());
//	        assertNotEquals(response.getContentAsString(),"");
//	        assertTrue(response.getContentAsString().contains(SIGNATURE_STRING));   
//	    }
//	    
//	    @Test
//	    public void doPostShouldEchoBody() throws ServletException, IOException {
//	        request.setContent("blablablabla".getBytes());
//	        servlet.doPost(request, response);
//	        assertEquals("text/plain", response.getContentType());
//	        assertTrue(response.getContentAsString().contains("blablablabla"));        
//	    }
//}
