/**
 * This program generates two circles which can be dragged around the screen.
 * They are linked by a line and the distances between the two circles is displyed in pixels.
 * */

package exercisesEnClasseLeRetoure;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Ex_15_16 extends Application{
	//initialize circle centers
	double x1 = 40;
    double y1 = 40;
    double x2 = 120;
    double y2 = 150;
	@Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
	    Pane pane = new Pane();

	    double paneWidth = 250;
	    double paneHeight = 250;
 

	    //****Create Circles
	    Circle circle1 = new Circle(x1, y1, 10);
	    Circle circle2 = new Circle(x2, y2, 10);
	    circle1.setFill(Color.WHITE);
	    circle1.setStroke(Color.BLACK);
	    circle2.setFill(Color.WHITE);
	    circle2.setStroke(Color.BLACK);
	    
	    //create line
	    Line line = new Line(x1, y1, x2, y2);

	    //create text
	    Text text = new Text((x1 + x2) / 2, (y1 + y2) / 2,
	      (int)getDistance(x1, y1, x2, y2) + "");

	    //drag event for circle1
	    circle1.setOnMouseDragged(e -> {
	        //set circle center based on mouse pos
	    	x1 = e.getX();
	        y1 = e.getY();
	    	
	    	circle1.setCenterX(x1);
	        circle1.setCenterY(y1);
	        line.setStartX(x1);
	        line.setStartY(y1);
	        
	        text.setText((int)getDistance(x1, y1, x2, y2) + "");
	        text.setX((x2+x1)/2);
	        text.setY((y2+y1)/2);
	      });
	  
	    //drag event for circle2
	    circle2.setOnMouseDragged(e -> {
	        x2 = e.getX();
	        y2 = e.getY();
	    	
	    	circle2.setCenterX(x2);
	        circle2.setCenterY(y2);
	        line.setEndX(x2);
	        line.setEndY(y2);
	        
	        text.setText((int)getDistance(x1, y1, x2, y2) + "");
	        text.setX((x2+x1)/2);
	        text.setY((y2+y1)/2);
	      });
	    
	    
	    pane.getChildren().addAll(line, text, circle2, circle1);
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(pane, paneWidth, paneHeight );

	    primaryStage.setTitle("Exercise15_16"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	  }

	  public static double getDistance(double x1, double y1, double x2, double y2) {
	    return Math.sqrt((x1 - x2) * (x1 -x2) + (y1 - y2) * (y1 - y2));
	  }

	  /**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }

	  
	}