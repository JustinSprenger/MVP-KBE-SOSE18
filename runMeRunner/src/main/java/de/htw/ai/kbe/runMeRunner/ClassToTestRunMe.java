package de.htw.ai.kbe.runMeRunner;

public class ClassToTestRunMe {
	
	@RunMe
	public void method_will_run(){}
	
	@RunMe
	public void method_will_run_2(){}
	
	@RunMe
	private void method_will_not_run(){}
	
	private void method_will_not_run_2(){}
	
	protected void method_will_run_3(){}
}
