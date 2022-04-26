package app;

public class App {
	 public static void main(String[] args) {
	        
	        if (args.length != 1) {
	            throw new RuntimeException("Invalid argument : must be int");
	        }
	        int acI = Integer.parseInt(args[0]);
	        if (acI != -1 &&
	            (acI < 0 ||
	             acI >= paragraphNum.NUMBER_OF_PARAGRAPHS)) {
	            throw new RuntimeException("Invalid argument : must be int");
	        }
	        new TextEditorFrame(acI);
	    }
}
