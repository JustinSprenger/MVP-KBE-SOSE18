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
		if(input!=null || input != "") {
		
		try {
			if(PropsFileUtil.readProperties(input).containsKey("classToLoad") && !PropsFileUtil.readProperties(input).getProperty("classToLoad").isEmpty()) {
				validInput = true; 
				cls=PropsFileUtil.readProperties(input).getProperty("classToLoad");
			}else 
			{
				validInput = false; 
				System.out.println("In der Props ist kein oder falscher Klassenname enthalten");
			}
			
			
		} catch (PropsFileReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
					
		return validInput;
		}
		else {
			System.out.println("Die properties Datei wurde nicht angegeben!");
			return false;
		}
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

		if(!outputFilename.isEmpty() && outputFilename!=null) {
			PrintStream output = null;
			try {
				output = new PrintStream(new FileOutputStream(outputFilename));
				
				output.printf("--------------------------------------------------------");
				output.printf("%nName der Klasse : %s", cls);
				output.printf("%n%nAnzahl der Methoden : %d", methodCount);
				
				output.printf("%n%nAnzahl der Methoden mit Anno : %d", methodNamesWithRunMe.size());
				for(int j =0; j < methodNamesWithRunMe.size();j++) {
					output.printf("%n   %d.Method : %s", j+1, methodNamesWithRunMe.get(j));
				}
				
				output.printf("%n%nAnzahl der Methoden mit Anno nicht invokabel : %d", methodNamesNotInvokable.size());
				for(int j =0; j < methodNamesNotInvokable.size();j++) {
					output.printf("%n   %d.Method : %s", j+1, methodNamesNotInvokable.get(j));
				}
				output.printf("%n--------------------------------------------------------%n");
				System.setOut(output);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					}
		else {
			System.out.printf("--------------------------------------------------------");
			System.out.printf("%nName der Klasse : %s", cls);
			System.out.printf("%n%nAnzahl der Methoden : %d", methodCount);
			System.out.printf("%n%nAnzahl der Methoden mit Anno : %d", methodNamesWithRunMe.size());
			for(int j =0; j < methodNamesWithRunMe.size();j++) {
				System.out.printf("%n   %d.Method : %s", j, methodNamesWithRunMe.get(j));
			}
			System.out.printf("%n%nAnzahl der Methoden mit Anno nicht invokabel : %d", methodNamesNotInvokable.size());
			for(int j =0; j < methodNamesNotInvokable.size();j++) {
				System.out.printf("%n   %d.Method : %s", j, methodNamesNotInvokable.get(j));
			}
			System.out.printf("%n--------------------------------------------------------%n");
		}
		
	}

	@Override
	public String toString() {
		return "";
	}
	
	
}
