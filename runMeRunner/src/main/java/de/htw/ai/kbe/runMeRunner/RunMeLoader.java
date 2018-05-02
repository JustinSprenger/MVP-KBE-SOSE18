package de.htw.ai.kbe.runMeRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import de.htw.ai.kbe.PropsFileUtil.PropsFileReadException;
import de.htw.ai.kbe.PropsFileUtil.PropsFileUtil;

public class RunMeLoader {
	Class<?> clas;
	String cls;
	public RunMeLoader() {
		clas = null;
	}
	
	private void load() {

    	try {
    		clas = Class.forName(cls);
    	
    	}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    
	}

	public boolean validInput(String input) {
		boolean validInput = false; 
		
		try {
			if(!PropsFileUtil.readProperties(input).getProperty("classToLoad").isEmpty()) {
				validInput = true; 
				cls=PropsFileUtil.readProperties(input).getProperty("classToLoad");
			}else {
				validInput = false; 
				System.out.println("In der Props ist kein Klassenname enthalten");
			}
		} catch (PropsFileReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
					
		return validInput;
	}

	public void runMethods(String outputFilename)  {
		this.load();
		int methodCount = 0;
		List<String> methodNamesWithRunMe = new ArrayList<String>();
		List<String> methodNamesNotInvokable = new ArrayList<String>();
		
		
		methodCount = (clas.getDeclaredMethods().length!= -1) ? clas.getDeclaredMethods().length : 0;
		
		for(final Method method : clas.getDeclaredMethods()) {
		
			if(method.isAnnotationPresent(RunMe.class)) {
				
				methodNamesWithRunMe.add(method.getName());
				
				if(Modifier.isPrivate(method.getModifiers()))
					methodNamesNotInvokable.add(method.getName());
	
			}
		}

		if(!outputFilename.isEmpty()) {
			PrintStream output = null;
			try {
				output = new PrintStream(new FileOutputStream(outputFilename));
				
				output.printf("Anzahl der Methoden : %d%n", methodCount);
								
				for(int j =0; j < methodNamesWithRunMe.size();j++) {
					output.printf("%n%nMethoden mit Anno%nMethod %d : %s%n", j, methodNamesWithRunMe.get(j));
				}
				
				for(int j =0; j < methodNamesNotInvokable.size();j++) {
					output.printf("%n%nMethoden mit Anno nicht invokabel%nMethod %d : %s%n", j, methodNamesNotInvokable.get(j));
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.setOut(output);
		}
		else {
			
			
			System.out.printf("Anzahl der Methoden - %d%n", methodCount);
							
			for(int j =0; j < methodNamesWithRunMe.size();j++) {
				System.out.printf("%n%nMethoden mit Anno%nMethod  : %s%n", methodNamesWithRunMe.get(j));
			}
			
			for(int j =0; j < methodNamesNotInvokable.size();j++) {
				System.out.printf("%n%nMethoden mit Anno nicht invokabel%nMethod %d : %s%n", j, methodNamesNotInvokable.get(j));
			}
		}
		
	}

	@Override
	public String toString() {
		return "";
	}
	
	
}
