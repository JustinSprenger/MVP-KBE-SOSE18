package de.htw.ai.kbe.runMeRunner;
import org.apache.commons.cli.*;

public class App 
{
    public static void main( String[] args )
    {
    	if(args.length != 0 && args[0]!="-p") {
    		Options options = new Options();

            Option input = new Option("p", "input", true, "Properties file name.");
            input.setRequired(false);
            options.addOption(input);

            Option output = new Option("o", "output", true, "Output file name.");
            output.setRequired(false);
            options.addOption(output);

            CommandLineParser parser = new DefaultParser();
            HelpFormatter formatter = new HelpFormatter();
            CommandLine cmd;

            try {
                cmd = parser.parse(options, args);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                formatter.printHelp("utility-name", options);

                System.exit(1);
                return;
            }

            String inputFilePath = cmd.getOptionValue("input");
            String outputFilePath = cmd.getOptionValue("output");

    		RunMeLoader rml = new RunMeLoader();
        	
    		if(rml.validInput(inputFilePath)) {
    			if(outputFilePath!=null)
    				rml.runMethods(outputFilePath);
    			else
    				rml.runMethods("");
    		}
    			
    	}
    	else {
    		System.out.println("It should be run: \"java -jar ./target/runMeRunner-1.0-jar-with-dependencies.jar -p [propsFile] -o [runMeReport.txt]\"");
    	}
    }
}
