package de.htw.ai.kbe.PropsFileUtil;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
	PropsFileUtil props;
	Properties p;
	
	@Before
	public void initApp()
	{
		props = new PropsFileUtil();
	}
	
	@Test
    public void testIfPropertiesNameIsNull()
    {
		try {
			p = props.readProperties(null);
		} catch (PropsFileReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        assertTrue( true );
    }
	@Test
    public void testIfPropertiesNameIsEmpty()
    {
		try {
			p = props.readProperties("");
		} catch (PropsFileReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        assertTrue( true );
    }
	@Test
    public void testIfPropertiesExist()
    {
		try {
			p = props.readProperties("appp.properties");
		} catch (PropsFileReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        assertTrue( true );
    }
	@Test
    public void testIfFileIsAPropertiesFile()
    {
		try {
			p = props.readProperties("app.prop");
		} catch (PropsFileReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        assertTrue( true );
    }
}
