package de.htw.ai.kbe.runMeRunner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	if(args.length == 0) {
    		RunMeLoader rml = new RunMeLoader();
        	
    		//"de.htw.ai.kbe.MyClassWithRunMes"
    		if(rml.validInput("props.properties")) {
    			rml.runMethods("");
    		}
    			
    	}
    	else {
    		System.out.println("It should be run: \"java jar runMeRunner-1.0-jar-with-dependencies.jar –p propsFile –o runMeReport.txt\"");
    	}
    }
 	String cls;
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
//	try {
//		if(!PropsFileUtil.readProperties(args[0]).getProperty("classToLoad").isEmpty()) {
//			cls=PropsFileUtil.readProperties(args[0]).getProperty("classToLoad");
//		}else {
//			System.out.println("");
//		}
//	} catch (PropsFileReadException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//	cls = "de.htw.ai.kbe.MyClassWithRunMes";
//	if(cls!=null) {
//		rml.load(cls);
//	}else {
//		System.out.println("In der Props ist kein Klassenname enthalten");
//	}
 	
 	
}
