package excelProjectTestbed;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.CheckListView;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the Effort Analysis Tool. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 *  
 *  
 * @author Sajib Biswas 
 * 
 * @version 1.00	2019-04-30 The JavaFX-based GUI for the implementation of a Excel Spreadsheet Data Analysis Project
 */

public class UserInterface {

	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/

	private final double BUTTON_WIDTH = 60;
	
	// These are the application values required by the user interface
	private Label label_excelanalysis = new Label("Excel Spreadsheet Data Analysis (Effort Log Analysis) ");
	private Label label_filelocation = new Label("File location");
	private TextField text_filelocation = new TextField();
	private Button button_Browse = new Button("Browse");
	private Button button_Pivottable = new Button("Genarate Pivot Table");
	private Button button_Exit = new Button("Quit");
	private CheckBox checkBox = new CheckBox("Select All");
	private  DirectoryChooser fileChooser = new  DirectoryChooser();
	public CheckListView<String> listView = new CheckListView<String>();
	private Window theStage;

	
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */

	public UserInterface(Pane theRoot) {
		
		
		
		// Label theScene with the name of the ExcelAnalysis, centered at the top of the pane
		setupLabelUI(label_excelanalysis, "Arial", 24, ExcelAnalysisTool.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the file location label just above it, left aligned
		setupLabelUI(label_filelocation, "Arial", 18, 700, Pos.BASELINE_LEFT, 10, 40);
		
		// Establish the text field for file location. these will work according to the browse button and 
		//show the path of the selected file
				setupTextUI(text_filelocation, "Arial", 18,ExcelAnalysisTool.WINDOW_WIDTH/2 + 30, Pos.BASELINE_LEFT, 10, 70, true);
				text_filelocation.textProperty().addListener((observable, oldValue, newValue) -> { });
				
	// Establish a A CheckBox with title "Select All" is available on the user interface 
	//with its working functionality to select all faculty effort log sheets in single attempt.	
	setupcheckBox(checkBox, "Arial", 15, ExcelAnalysisTool.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 20, 125, true);
	checkBox.setOnAction(ev -> MarkAll());
				
				
	//Establish a listView with appropriate size			
		listView.setMaxSize(480,250);
		listView.setMinSize(480,250);
		listView.setLayoutX(20);  
		listView.setLayoutY(150);
		
		
	// Establish the browser button, position it, and link it to methods to accomplish its work
	setupButtonUI(button_Browse, "Symbol", 18, BUTTON_WIDTH, Pos.BASELINE_LEFT, 550, 68);
	button_Browse.setStyle("-fx-text-fill:black;");
	button_Browse.setOnAction(evt-> dialogbox());
				
	// Establish the browser Generate Pivot table, position it, and link it to methods to accomplish its work
	setupButtonUI(button_Pivottable, "Symbol", 18, BUTTON_WIDTH, Pos.BASELINE_LEFT, 20, 420);	
	button_Pivottable.setOnAction((event) -> { GeneratePivotTable(); });
				
	// Establish the Quit button, position it, and link it to methods to accomplish its work
	setupButtonUI(button_Exit, "Symbol", 18, BUTTON_WIDTH, Pos.BASELINE_LEFT, 450, 420);
	button_Exit.setStyle("-fx-text-fill:black;");
    button_Exit.setOnAction(e-> System.exit(0));
		       
	
	// Place all of the just-initialized GUI elements into the pane
	theRoot.getChildren().addAll(label_excelanalysis,label_filelocation,text_filelocation,button_Browse,
		button_Exit,listView,checkBox,button_Pivottable);
	}
		

				/**********
				 * Private local method to initialize the standard fields for a label
				 */
				private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
					l.setFont(Font.font(ff, f));
					l.setMinWidth(w);
					l.setAlignment(p);
					l.setLayoutX(x);
					l.setLayoutY(y);		
				}
				
				/**********
				 * Private local method to initialize the standard fields for a text field
				 */
				private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
					t.setFont(Font.font(ff, f));
					t.setMinWidth(w);
					t.setMaxWidth(w);
					t.setAlignment(p);
					t.setLayoutX(x);
					t.setLayoutY(y);		
					t.setEditable(e);
				}
				
				/**********
				 * Private local method to initialize the standard fields for a button
				 */
				private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
					b.setFont(Font.font(ff, f));
					b.setMinWidth(w);
					b.setAlignment(p);
					b.setLayoutX(x);
					b.setLayoutY(y);		
				}
				/**********
				 * Private local method to initialize the standard fields for a checkBox
				 */
				
				private void setupcheckBox(CheckBox t, String ff, double f, double w, Pos p, double x, double y, boolean e){
					t.setFont(Font.font(ff, f));
					t.setMinWidth(w);
					t.setMaxWidth(w);
					t.setLayoutX(x);
					t.setLayoutY(y);		
				}
				
				/**********************************************************************************************

				Implementation of file chooser concept on browse button and also perform 
				the operation of selection of specific .xlsx files from CheckListView control.
				
				*/
				
			private void dialogbox() {
		        File file = fileChooser.showDialog(theStage);
		        if (file != null) {
		            text_filelocation.setText(file.getAbsolutePath() + "\n");
		        }
		        
		        String directory =file.getAbsolutePath() ;
				
				List <String> fileNamesList = new ArrayList <String> ();
				try {
					Files.newDirectoryStream(Paths.get(directory),
							path -> path.toString().endsWith(".xlsx")).forEach(filePath -> fileNamesList.add(filePath.toString()));
				}catch (IOException e) {
					e.printStackTrace();
				}
				  ObservableList<String> data =  FXCollections.observableArrayList();    
				
				for (String name : fileNamesList) {
					data.add(name.substring(directory.length()+1));
				listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				
				}
				listView.setItems(data);
			
			}
			
			// This method works for checkBox with its working functionality to select all faculty effort log sheets in single attempt
			public void MarkAll() {
				if(checkBox.isSelected()) {
					listView.getCheckModel().checkAll();
					listView.refresh();
				}
				else {
					listView.getCheckModel().clearChecks();
					listView.refresh();
				}
			}
	//This method works for merging all the selected file in a single sheet named 'Final project File'		
	private void GeneratePivotTable() {
		
		int count=0;
		for(int i=0; i< listView.getCheckModel().getItemCount();i++) { 
			if(listView.getCheckModel().isChecked(i)) {
				count++;
		}
			}
		String[] excelFiles = new String[count];
			
		ObservableList<String> data1 =  FXCollections.observableArrayList(listView.getCheckModel().getCheckedItems());
		data1.toArray();
		for (int i=0; i<count; i++) {
			excelFiles[i] = data1.get(i);
			
		}
		
		
		XSSFSheet Sheets[] = new XSSFSheet[count];          //Create the no of selected excel files sheet
        
	    XSSFWorkbook workbook;

	    workbook = new XSSFWorkbook();
    
      	 
	    for(int ndx = 0; ndx <count ; ndx++ )          // Creating the sheets in the file
	    {
	           Sheets[ndx]= workbook.createSheet(excelFiles[ndx]);
	    }   
	    
	                  
	    FileOutputStream fos = null;
	    try {           
	    	
	             fos = new FileOutputStream(new File("Final Project File.xlsx"));     // Creating a file on the users folder
	             	
	             for(int i=0;i<count;i++)
				 {                                                 
	                    Workbook workbook1 = WorkbookFactory.create(new File(excelFiles[i]));
	                    List<List<String>> rows = new ArrayList<List<String>>(); 
	                                									
	                    Sheet sheet = workbook1.getSheetAt(0);         // Take  the first sheet in every file               
	                    DataFormatter dataFormatter = new DataFormatter();
	                    for (Row row: sheet) 
	                    {
	                      	List<String> headerRow = new ArrayList<String>();
	                        for(Cell cell: row) 
	                        {
	                            String cellValue = dataFormatter.formatCellValue(cell);
	                        	headerRow.add(cellValue);
	                        }   
	                        rows.add(headerRow);
	                      }
	                        		
	                      try {
	                     			int rownum=0;
	                         		for (int j = 0; j < rows.size(); j++) 
	                         		{
	                          				Row row1 = Sheets[i].createRow(rownum);
	                          				List<String> colList= rows.get(j);
	                          				for(int k=0; k<colList.size(); k++)
	                          				{
	                          					Cell cell = row1.createCell(k);
	                          					cell.setCellValue(colList.get(k));
	                          				}
	                          				rownum++;
	                          		}
	                          		
	                      } 
	                      
	                      catch (Exception e) 
	                      {
	                          e.printStackTrace();
	                          		
	                      } 
	                      finally 
	                      {}
	                     
	                 }
	                                
	                               workbook.write(fos);
	                                workbook.close();
	                                fos.flush();
	                                fos.close();
	                             
	                  } catch (Exception e) {
	                                e.printStackTrace();
	                  }
	    
	     }
	
		 
		
	}
	
    





