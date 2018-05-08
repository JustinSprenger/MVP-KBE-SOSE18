package de.htw.ai.kbe.runMeRunner;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import de.htw.ai.kbe.PropsFileUtil.PropsFileReadException;
import de.htw.ai.kbe.PropsFileUtil.PropsFileUtil;

/**
 * Unit test for simple App.
 */
public class AppTest
{
	PropsFileUtil props;
	Properties p;
	RunMeLoader rml;
	
	@Before
	public void initApp()
	{
		props = new PropsFileUtil();
		rml = new RunMeLoader();
	}
	
	@Test
    public void testValidInputValidInput()
    {
		assertTrue(rml.validInput("props.properties"));
    }
	
	@Test
    public void testValidInputInvalidInput()
    {
		assertEquals(false, rml.validInput("props.propert"));
	}
	
	@Test
    public void testValidInputNoInput()
    {
		assertEquals(false, rml.validInput(""));
	}
	
	@Test
    public void testValidInputNullInput()
    {
		assertEquals(false, rml.validInput(null));
	}
		
	@Test
    public void testRunMethodsValidOutput()
    {	
		try {
		rml.validInput("props.properties");
		rml.runMethods("runMeReport.txt");
		assertTrue(true);
		} catch (Exception e) { 
			assertTrue(false);
		}
    }
		
	@Test
    public void testRunMethodsNoOutput()
    {	
		try {
		rml.validInput("props.properties");
		rml.runMethods("");
		assertTrue(true);
		} catch (Exception e) { 
			assertTrue(false);
		}
    }
	
	@Test
    public void testRunMethodsNullOutput()
    {	
		try {
		rml.validInput("props.properties");
		rml.runMethods(null);
		assertTrue(false);
		} catch (Exception e) { 
			assertTrue(true);
		}
    }
}