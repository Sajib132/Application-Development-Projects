package excelProjectTestbed;

import javafx.application.Application;
	
	import javafx.scene.Scene;
	import javafx.scene.layout.Pane;
	import javafx.stage.Stage;

	
	/*******
	 * <p> Title: ExcelAnalysisTool class. </p>
	 * 
	 * <p> Description: A JavaFX demonstration application  for a sequence of projects </p>
	 * 
	 
	 * @author Sajib Biswas
	 * 
	 * @version 1.00	2019-04-30 The JavaFX-based GUI for the implementation of a Excel Spreadsheet Data Analysis Project
	 * 
	 */

	public class ExcelAnalysisTool extends Application {
		public final static double WINDOW_WIDTH = 900;
		public final static double WINDOW_HEIGHT = 500;
		
		
		public UserInterface theGUI;
		
		@Override
		public void start(Stage theStage) throws Exception {
			
			theStage.setTitle("Sajib Biswas");			// Label the stage (a window)
			
			Pane theRoot = new Pane();							// Create a pane within the window
			
			theGUI = new UserInterface(theRoot);					// Create the Graphical User Interface
			
			Scene theScene = new Scene(theRoot, WINDOW_WIDTH, WINDOW_HEIGHT);	// Create the scene
		
			theStage.setScene(theScene);							// Set the scene on the stage
			
			theStage.show();
		}
			public static void main(String[] args) {					// This method may not be required
				launch(args);											// for all JavaFX applications using
			}
		
		
	}
