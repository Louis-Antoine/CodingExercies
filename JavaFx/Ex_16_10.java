/**
 * This program asks the user for a txt file path and displays it in a text field
 *  */

package exercisesEnClasseLeRetoure;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class Ex_16_10 extends Application{

	@Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
	    
		//textArea
		TextArea text = new TextArea();

	    //button
	    
		Button view = new Button("View");
		
	    //textField
	    
		TextField field = new TextField();
		field.setPrefWidth(230);
	    
		HBox hbox = new HBox();
	    hbox.getChildren().addAll(new Label("Filename"), field, view);
	    
	    hbox.setAlignment(Pos.CENTER);
	    
	    //button events
	    view.setOnAction(e->{
	    	File file = new File(field.getText());
	    	try{ 
	    		Scanner inputFile = new Scanner(file);
	    		String fullText = "";
	    		
	    		while(inputFile.hasNext()){
	    			fullText = fullText  + inputFile.nextLine() + "\n";
	    		}
	    		
	    		text.setText(fullText);
	    		
	    		inputFile.close();
	    	}
	    	catch(FileNotFoundException ex){
	    		field.setText("File not found");
	    	}
	    	
	    });
	    

	    //borderpane
	    BorderPane bPane = new BorderPane();
	    
	    //set up layout
	    
	    bPane.setCenter(text);
	    bPane.setBottom(hbox);
	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(bPane, 350, 300);

	    primaryStage.setTitle("Exercise16_10"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }


	  /**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }
}