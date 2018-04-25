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
        ClassLoader classLoader = App.class.getClassLoader();
    	String cls;
    	try {
			cls = PropsFileUtil.readProperties(args[0]).getProperty("classToLoad");
		} catch (PropsFileReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	cls = "de.htw.ai.kbe.MyClassWithRunMes";
    	Class aClass;
		try {
			aClass = classLoader.loadClass("com.jenkov.MyClass");
			System.out.println("aClass.getName() = " + aClass.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	
    	System.out.println( "Hello World!" );
    }
    
}
