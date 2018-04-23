package de.htw.ai.kbe.PropsFileUtil;

import java.io.IOException;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		PropsFileUtil props = new PropsFileUtil();
		Properties p;
		try {
			p = props.readProperties("app.properties");
			System.out.println(p.getProperty("name"));
			System.out.println(p);
		} catch (PropsFileReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
