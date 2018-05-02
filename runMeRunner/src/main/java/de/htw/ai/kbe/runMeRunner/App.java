package de.htw.ai.kbe.runMeRunner;
import java.io.IOException;

import de.htw.ai.kbe.PropsFileUtil.*;;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String cls;
    	try {
			cls = PropsFileUtil.readProperties(args[0]).getProperty("classToLoad");
		} catch (PropsFileReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	cls = "de.htw.ai.kbe.MyClassWithRunMes";
    	
    	//method.invoke();
    	System.out.println( "Hello World!" );
    }
    
}
