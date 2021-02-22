
package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import calculator.Calculator;

import java.util.StringTokenizer;

import calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * <p> Copyright: Lynn Robert Carter Â© 2017 </p>
 * 
 * @author Lynn Robert Carter
 * @author Sajib Biswas 
 * @author Neaz Morshed
 * @version 4.00	2017-10-17 The JavaFX-based GUI for the implementation of a calculator
 * @version 4.1  	2019-01-21 Implementation of a integer calculator
 * @version 4.2     2019-02-19 Implementation of double calculator
 * @version 4.3     2019-03-19 Implementation of FSM to provide more accurate error message
 * @version 4.4     2019-04-06 Implementation of FSM on Error Term and perform all the operators with error term
 * 
 * <p>Team Definition: My task was to create three different label and text field for error term and '±' symbol between
 * measured value and error term. My next role was to implement FSM on error term. My role was also work on the error term
 * to take values (0.05) though the text field is empty.
 * Neaz is suppose to implement the logic for all operators to calculate value with error term. His role was to 
 * work on resultant value's error term has just one significant digit.
 * </p>
 * 
 */

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 4;

	// These are the application values required by the user interface
	private Label label_IntegerCalculator = new Label("Double Calculator with Error Term");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();
	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00d7");				
	private Button button_Div = new Button("\u00f7");				
	private Button button_log = new Button("Log\u2082");    
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errResult = new Label("");
	private Label label_Operand1ErrorTerm = new Label("ErrorTerm");
	private Label label_Operand2ErrorTerm = new Label("ErrorTerm");
	private Label label_ResultErrorTerm = new Label("ErrorTerm");
	private Label label_err = new Label("\u00B1");                        //This Unicode represent the symbol of  '±'
	private Label label_err1 = new Label("\u00B1");
	private Label label_err2 = new Label("\u00B1");
	
	private TextFlow errMeasuredValue;
	private TextFlow errMeasuredValue1;
	private Text errMVPart1 = new Text();
	private Text errMVPart2 = new Text();
	private Text errMVPart3 = new Text();
	private Text errMVPart4 = new Text();     
		    
	private TextFlow errErrorTerm;
	private TextFlow errErrorTerm1;
    private Text errETPart1 = new Text();
    private Text errETPart2 = new Text();	    
    private Text errETPart3 = new Text();
    private Text errETPart4 = new Text();	
    
    private Label label_errOperand1ErrorTerm = new Label("");   
    private TextField text_Operand1ErrorTerm = new TextField();
    private Label label_errOperand2ErrorTerm = new Label("");   
    private TextField text_Operand2ErrorTerm = new TextField();	  
    private Label label_errResultErrorTerm = new Label("");   
	private TextField text_ResultErrorTerm = new TextField();	
	    
	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.
	
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();

	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
				
		// There are six gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6.5;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_IntegerCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 40);
		// Label the first operand error term along with first operand
		setupLabelUI(label_Operand1ErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 550, 40);
		// Label the second operand error term along with second operand
		setupLabelUI(label_Operand2ErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 550, 135);
		// Label the Result error term along with result 
		setupLabelUI(label_ResultErrorTerm, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 550, 220);
		//Establish a symbol for '±' between the first operand and first operand error term
		setupLabelUI(label_err, "Arial", 24,30, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2, 70);
		//Establish a symbol for '±' between the second operand and second operand error term
		setupLabelUI(label_err1, "Arial", 24,30, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2, 160);
		//Establish a symbol for '±' between the result operand and result error term
		setupLabelUI(label_err2, "Arial", 24,30, Pos.BASELINE_LEFT, Calculator.WINDOW_WIDTH/2, 250);
		
		
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH/2 
				-50, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		// Move focus to the second operand when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		
		// Establish the Error Term textfield for the first operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1ErrorTerm, "Arial", 18, 400, Pos.BASELINE_LEFT, 
				Calculator.WINDOW_WIDTH/2 + 50, 70, true);
		text_Operand1ErrorTerm.textProperty().addListener((observable, oldValue, newValue) 
				-> {setOperand1(); });
		text_Operand1ErrorTerm.setEditable(true);
		
		// Establish an error message for the first operand Measured Value, left aligned
		label_errOperand1.setTextFill(Color.RED);
		label_errOperand1.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand1, "Arial", 14,  
				Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 20, 122);
		
		// Establish an error message for the first operand ErrorTerm, left aligned
		label_errOperand1ErrorTerm.setTextFill(Color.RED);
		label_errOperand1ErrorTerm.setAlignment(Pos.BASELINE_LEFT);
		setupLabelUI(label_errOperand1ErrorTerm, "Arial", 14,  
						Calculator.WINDOW_WIDTH+150, Pos.BASELINE_LEFT, 550, 122);
		
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 135);
				
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH/2 
				- 50, Pos.BASELINE_LEFT, 10, 160, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		// Establish an error message for the second operand Measured Value, left aligned
		label_errOperand2.setTextFill(Color.RED);
		label_errOperand2.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2, "Arial", 14,  
						Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 20, 210);
		
				
		// Establish the Error Term textfield for the second operand.  If anything changes, process
		// all fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2ErrorTerm, "Arial", 18, 400, Pos.BASELINE_LEFT, 
				Calculator.WINDOW_WIDTH/2 + 50, 160, true);
		text_Operand2ErrorTerm.textProperty().addListener((observable, oldValue, newValue) 
					-> {setOperand2(); });
		text_Operand2ErrorTerm.setEditable(true);
		
		// Establish an error message for the second operand ErrorTerm, left aligned
		label_errOperand2ErrorTerm.setTextFill(Color.RED);
		label_errOperand2ErrorTerm.setAlignment(Pos.BASELINE_RIGHT);
		setupLabelUI(label_errOperand2ErrorTerm, "Arial", 14,  
						Calculator.WINDOW_WIDTH+150, Pos.BASELINE_LEFT, 550, 210);
				
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 220);
		
		// Establish an error message for the second operand just above it with, left aligned
				setupLabelUI(label_errResult, "Arial", 14, Calculator.WINDOW_WIDTH-150-10, Pos.BASELINE_LEFT, 22, 220);
				label_errResult.setTextFill(Color.RED);
				
				// Establish the result output field.  It is not editable, so the text can be selected and copied, 
				// but it cannot be altered by the user.  The text is left aligned.
				setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH/2 
						- 50, Pos.BASELINE_LEFT, 10, 250, true);		
				
		// Establish the resultErrorTerm output field. It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_ResultErrorTerm, "Arial", 18, 400, Pos.BASELINE_LEFT, 
					Calculator.WINDOW_WIDTH/2 + 50, 250, true);					
		text_ResultErrorTerm.setEditable(true);
							
						

				// Error Message for the Measured Value for operand 1
				errMVPart1.setFill(Color.BLACK);
			    errMVPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errMVPart2.setFill(Color.RED);
			    errMVPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    errMeasuredValue = new TextFlow(errMVPart1, errMVPart2);
				errMeasuredValue.setMinWidth(Calculator.WINDOW_WIDTH-10);
				errMeasuredValue.setLayoutX(20);  
				errMeasuredValue.setLayoutY(100);
				
				
				// Error Message for the Measured Value for operand 2
				errMVPart3.setFill(Color.BLACK);
			    errMVPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errMVPart4.setFill(Color.RED);
			    errMVPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    errMeasuredValue1 = new TextFlow(errMVPart3, errMVPart4);
				errMeasuredValue1.setMinWidth(Calculator.WINDOW_WIDTH-20); 
				errMeasuredValue1.setLayoutX(20);  
				errMeasuredValue1.setLayoutY(190);
				
				

		
				// Error Message for operand1ErrorTerm
			    errETPart1.setFill(Color.BLACK);
			    errETPart1.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errETPart2.setFill(Color.RED);
			    errETPart2.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    errErrorTerm = new TextFlow(errETPart1, errETPart2);
				errErrorTerm.setMinWidth(Calculator.WINDOW_WIDTH-10); 
				errErrorTerm.setLayoutX(560);  
				errErrorTerm.setLayoutY(100);

				
				// Error Message for the operand2ErrorTerm
			    errETPart3.setFill(Color.BLACK);
			    errETPart3.setFont(Font.font("Arial", FontPosture.REGULAR, 18));
			    errETPart4.setFill(Color.RED);
			    errETPart4.setFont(Font.font("Arial", FontPosture.REGULAR, 24));
			    errErrorTerm1 = new TextFlow(errETPart3, errETPart4);
				errErrorTerm1.setMinWidth(Calculator.WINDOW_WIDTH-10); 
				errErrorTerm1.setLayoutX(560);  
				errErrorTerm1.setLayoutY(190);
				
			
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 330);
		button_Add.setOnAction((event) -> { addOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 330);
		button_Sub.setOnAction((event) -> { subOperands(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 330);
		button_Mpy.setOnAction((event) -> { mpyOperands(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 330);
		button_Div.setOnAction((event) -> { divOperands(); });
		// Establish the Logarithm "Log" button, position it, and link it to methods to accomplish its work
				setupButtonUI(button_log, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 330);
				button_log.setOnAction((event) -> { logOperands(); });
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1, label_errOperand1, 
				label_Operand2, text_Operand2, label_errOperand2, label_Result, text_Result, label_errResult, 
				button_Add, button_Sub, button_Mpy, button_Div,button_log,errMeasuredValue,errMeasuredValue1,errErrorTerm
				,label_errOperand1ErrorTerm,text_Operand1ErrorTerm,label_errOperand2ErrorTerm,text_Operand2ErrorTerm,errErrorTerm1
               ,label_errResultErrorTerm,text_ResultErrorTerm,label_Operand1ErrorTerm,label_Operand2ErrorTerm,
               label_ResultErrorTerm,label_err,label_err1,label_err2);
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

	
	
	
	private void performGo() {
		String errMessage = CalculatorValue.checkMeasureValue(text_Operand1.getText());
		if (errMessage != "") {
			
			label_errOperand1.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			errMVPart1.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			errMVPart2.setText("\u21EB");
		}
	
	
	else {
		
		errMessage = BusinessLogic.checkErrorTerm(text_Operand1ErrorTerm.getText());
		if (errMessage != "") {
			
			label_errOperand1ErrorTerm.setText(BusinessLogic.errorTermErrorMessage);
			String input = BusinessLogic.errorTermInput;
			if (BusinessLogic.errorTermIndexofError <= -1) return;
			errETPart1.setText(input.substring(0, BusinessLogic.errorTermIndexofError));
			errETPart2.setText("\u21EB");
		}
	}
		
	}
	
	private void performGo1() {
		String errMessage = CalculatorValue.checkMeasureValue(text_Operand2.getText());
		if (errMessage != "") {
			
			label_errOperand2.setText(CalculatorValue.measuredValueErrorMessage);
			if (CalculatorValue.measuredValueIndexofError <= -1) return;
			String input = CalculatorValue.measuredValueInput;
			errMVPart3.setText(input.substring(0, CalculatorValue.measuredValueIndexofError));
			errMVPart4.setText("\u21EB");
		}
		else {
			
			errMessage = BusinessLogic.checkErrorTerm(text_Operand2ErrorTerm.getText());
			if (errMessage != "") {
				
				label_errOperand2ErrorTerm.setText(BusinessLogic.errorTermErrorMessage);
				String input = BusinessLogic.errorTermInput;
				if (BusinessLogic.errorTermIndexofError <= -1) return;
				errETPart3.setText(input.substring(0, BusinessLogic.errorTermIndexofError));
				errETPart4.setText("\u21EB");
			}
		}	
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");								// Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		
		if (perform.setOperand1(text_Operand1.getText(),text_Operand1ErrorTerm.getText() )) {	
			label_errOperand1.setText("");
				
			                                                 // Set the operand and see if there was an error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");	
			
			label_errOperand1.setText("");
			errMVPart1.setText("");
			errMVPart2.setText("");
			label_errOperand1ErrorTerm.setText("");
			errETPart1.setText("");
			errETPart2.setText("");
			
			
			performGo();
			
		}
		else 		
			// If there's a problem with the operand, display error essage
			performGo();	
	}
	
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		label_Result.setText("Result");				
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText(),text_Operand2ErrorTerm.getText())) {
			label_errOperand2.setText("");
			
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
			label_errOperand2.setText("");
			errMVPart3.setText("");
			errMVPart4.setText("");
			label_errOperand2ErrorTerm.setText("");
			errETPart3.setText("");
			errETPart4.setText("");
			performGo1();
			
			
		}
		else
			
			performGo1();
	}
	
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				label_errOperand2.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}

	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, divide, logarithm) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition();						// Call the business logic add method
		
		StringTokenizer st = new StringTokenizer(theAnswer,":");    //This StringTokenizer break the values into tokens
		                                                            //When it finds ":", it breaks them into tokens
		
		//Display a fixed value (0.05) if the error term is empty for any or both operands 
		// This value is also being calculated
		if (text_Operand1ErrorTerm.getText().length()==0) text_Operand1ErrorTerm.setText("0.05");
		if (text_Operand2ErrorTerm.getText().length()==0) text_Operand2ErrorTerm.setText("0.05");
		
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {	                                // Check the returned String to see if it is okay
			label_errOperand1.setText("");                              // If okay, display it in the result field and
			label_errOperand2.setText("");                                
			text_Result.setText(st.nextToken());				//This text field shows the calculated Sum of both MeasuredValue		
			text_ResultErrorTerm.setText(st.nextToken());       //This text field shows the calculated Sum of both ErrorTerm
			label_Result.setText("Sum");								// change the title of the field to "Sum"
		}
		else {														// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands(){
		// Check to see if both operands are defined and valid
				if (binaryOperandIssues()) 									// If there are issues with the operands, return
					return;													// without doing the computation
				
				// If the operands are defined and valid, request the business logic method to do the subtraction and return the
				// result as a String. If there is a problem with the actual computation, an empty string is returned
				String theAnswer = perform.subtraction();	
				StringTokenizer st = new StringTokenizer(theAnswer,":");      //This StringTokenizer break the values into tokens
                                                                              //When it finds ":", it breaks them into tokens
				
				//Display a fixed value (0.05) if the error term is empty for any or both operands 
				// This value is also being calculated
				if (text_Operand1ErrorTerm.getText().length()==0) text_Operand1ErrorTerm.setText("0.05");
				if (text_Operand2ErrorTerm.getText().length()==0) text_Operand2ErrorTerm.setText("0.05");                                                      // Call the business logic subtraction method
				label_errResult.setText("");									// Reset any result error messages from before
				if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
					                                                       // If okay, display it in the result field and
					                                                        // If okay, display it in the result field and
					
					text_Result.setText(st.nextToken());					//This text field shows the calculated Difference of both MeasuredValue				
					text_ResultErrorTerm.setText(st.nextToken());			//This text field shows the calculated Difference of both ErrorTerm				
					label_Result.setText("Difference");								// This is the field for "Difference"
				}
				else {														// Some error occurred while doing the subtraction.
					text_Result.setText("");									// Do not display a result if there is an error.				
					label_Result.setText("Result");							// Reset the result label if there is an error.
					label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
				}	
		}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands(){
		
			// Check to see if both operands are defined and valid
			if (binaryOperandIssues()) 									// If there are issues with the operands, return
				return;													// without doing the computation
			
			// If the operands are defined and valid, request the business logic method to do the multiplication and return the
			// result as a String. If there is a problem with the actual computation, an empty string is returned
			String theAnswer = perform.multiplication();                    // Call the business logic multiplication method
			StringTokenizer st = new StringTokenizer(theAnswer,":");    //This StringTokenizer break the values into tokens
                                                                        //When it finds ":", it breaks them into tokens
			
			//Display a fixed value (0.05) if the error term is empty for any or both operands 
			// This value is also being calculated
			if (text_Operand1ErrorTerm.getText().length()==0) text_Operand1ErrorTerm.setText("0.05");
			if (text_Operand2ErrorTerm.getText().length()==0) text_Operand2ErrorTerm.setText("0.05");  
			
			label_errResult.setText("");									// Reset any result error messages from before
			if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
				                                                        // If okay, display it in the result field and
			                                                          	// If okay, display it in the result field and
				                          
				
				text_Result.setText(st.nextToken());					//This text field shows the calculated product of both MeasuredValue		
				text_ResultErrorTerm.setText(st.nextToken());			//This text field shows the calculated product of both ErrorTerm					
				label_Result.setText("Product");								// This is the field for "Product"
			}
			else {														// Some error occurred while doing the multiplication.
				text_Result.setText("");									// Do not display a result if there is an error.				
				label_Result.setText("Result");							// Reset the result label if there is an error.
				label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
			}	
												
	}

	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
		
			// Check to see if both operands are defined and valid
			if (binaryOperandIssues()) 									// If there are issues with the operands, return
				return;													// without doing the computation
			
			// If the operands are defined and valid, request the business logic method to do the division and return the
			// result as a String. If there is a problem with the actual computation, an empty string is returned
			String theAnswer = perform.division();	         // Call the business logic division methods
			//Display a fixed value (0.05) if the error term is empty for any or both operands 
			// This value is also being calculated
			if (text_Operand1ErrorTerm.getText().length()==0) text_Operand1ErrorTerm.setText("0.05");
			if (text_Operand2ErrorTerm.getText().length()==0) text_Operand2ErrorTerm.setText("0.05");      
			 
			label_errResult.setText("");
			if(theAnswer.length()>0) {
				StringTokenizer st = new StringTokenizer(theAnswer,":");    //This StringTokenizer break the values into tokens
                                                                            //When it finds ":", it breaks them into tokens
				
				text_Result.setText(st.nextToken());  						// If okay, display it in the result field and
				text_ResultErrorTerm.setText(st.nextToken());						// If okay, display it in the result field and
				label_Result.setText("Quotient");						// change the title of the field to "Quotient"
			 
			  if(BusinessLogic.errorzero) {
				  text_Result.setText("");
				  label_Result.setText("");
				  text_ResultErrorTerm.setText("");
				  label_errResult.setText("Can not divide by zero");
				  }
			  }
		
			else{														// Some error occurred while doing the division.
				text_Result.setText("");								// Do not display a result if there is an error.					
				label_Result.setText("Result");							// Reset the result label if there is an error.
				label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
			}
														
	}
	/**********
	 * This is the logarithm routine
	 * 
	 */
	private void logOperands(){
		
		// If the first operand is defined and valid, request the business logic method to do the logarithm and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		
		String theAnswer = perform.log();           // Call the business logic logarithm method
		StringTokenizer st = new StringTokenizer(theAnswer,":");         
		label_errResult.setText("");									
		if (theAnswer.length() > 0) {	
			text_Result.setText(st.nextToken());
			text_Operand2.setText("");
			text_Operand1ErrorTerm.setText("");
			text_Operand2ErrorTerm.setText(""); 
			text_ResultErrorTerm.setText("");       
			            							
			label_Result.setText("Logarithm");								// This is the field for "logarithm"
		}
		
}
}
