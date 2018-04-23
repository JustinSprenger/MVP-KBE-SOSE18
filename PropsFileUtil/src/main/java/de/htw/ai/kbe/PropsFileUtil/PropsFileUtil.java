package de.htw.ai.kbe.PropsFileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropsFileUtil {
	
	public static Properties readProperties(String name) throws PropsFileReadException, IOException{
		Properties props = new Properties();
		String fname = "";
		if(name != null){
			fname = name.replaceAll("\\s","");
		}
		if(name == null||fname == ""){
			throw new PropsFileReadException("Filename ist Null");
		}
		if(name.endsWith(".properties")){
			
		}else{
			throw new PropsFileReadException("Es ist keine Properties datei");
		}
		File file = new File(name);
		if(file.exists() == false){
			throw new PropsFileReadException("Datei existiert nicht");
		}
		FileInputStream fileinput = new FileInputStream(file);
		props.load(fileinput);
		return props;
	}
}