package de.htw.ai.kbe.PropsFileUtil;

public class PropsFileReadException extends Exception{
	
	public PropsFileReadException(){
		super("test");
	}
	
	public PropsFileReadException(String err){
		super(err);
	}
}