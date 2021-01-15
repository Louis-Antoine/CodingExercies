/**
 * This program creates a small 2D solar system.
 * The orbit direction can be changed with the left and right arrows, paused with the down arrow and restarted with the up arrow
 * The instructor had also asked for the planets to get dimmer as they went around the star
 *  */


package exercisesEnClasseLeRetoure;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.animation.Interpolator;
import javafx.scene.Group;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javafx.animation.FadeTransition;
import javafx.scene.shape.Ellipse;
public class lab_7_1temp extends Application {
	  @Override // Override the start method in the Application class
	  public void start(Stage primaryStage) {
	    // Create a pane
	    Pane pane = new Pane();

	    // Create a rectangle
	    Rectangle rectangle = new Rectangle (0, 0, 600, 300);
	    rectangle.setFill(Color.BLUE);
	    rectangle.setStroke(Color.BLUE);
	    
	    //mercury
	    Ellipse mercuryOrbit = new Ellipse(300, 150, 60, 30);
	    mercuryOrbit.setFill(Color.BLUE);
	    mercuryOrbit.setStroke(Color.YELLOW);
	    
	    Circle mercury = new Circle(360, 150, 7);
	    mercury.setFill(Color.ORANGE);
	    mercury.setStroke(Color.ORANGE);
	    //put orbit and planet into group
	    Group mercuryG = new Group();
	    mercuryG.getChildren().addAll(mercuryOrbit, mercury);
	    mercuryG.setRotate(15);
	    
	  //venus
	    Ellipse venusOrbit = new Ellipse(300, 150, 90, 45);
	    venusOrbit.setFill(Color.BLUE);
	    venusOrbit.setStroke(Color.YELLOW);
	    
	    Circle venus = new Circle(390, 150, 7);
	    venus.setFill(Color.PINK);
	    venus.setStroke(Color.PINK);
	    //put orbit and planet into group
	    Group venusG = new Group();
	    venusG.getChildren().addAll(venusOrbit, venus);
	    venusG.setRotate(10);
	  //transitions
	    PathTransition ptVenus = new PathTransition();
	    ptVenus.setDuration(Duration.millis(5000));
	    
	    ptVenus.setPath(venusOrbit);
	    ptVenus.setNode(venus);

	    ptVenus.setOrientation(
	      PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
	    ptVenus.setCycleCount(Timeline.INDEFINITE);
	    ptVenus.setAutoReverse(false);
	    ptVenus.setInterpolator(Interpolator.LINEAR);
	    
	    FadeTransition ftVenus =
	  	      new FadeTransition(Duration.millis(2500), venus);
	    ftVenus.setFromValue(1.0);
	    ftVenus.setToValue(0.5);
	    ftVenus.setCycleCount(Timeline.INDEFINITE);
	    ftVenus.setAutoReverse(true);
	    
	  //earth
	    Ellipse earthOrbit = new Ellipse(300, 150, 150, 75);
	    earthOrbit.setFill(Color.BLUE);
	    earthOrbit.setStroke(Color.YELLOW);
	    
	    Circle earth = new Circle(450, 150, 7);
	    earth.setFill(Color.GREEN);
	    earth.setStroke(Color.GREEN);
	    //put orbit and planet into group
	    Group earthG = new Group();
	    earthG.getChildren().addAll(earthOrbit, earth);
	    earthG.setRotate(-17);
	  //transitions
	    PathTransition ptEarth = new PathTransition();
	    ptEarth.setDuration(Duration.millis(5000));
	    
	    ptEarth.setPath(earthOrbit);
	    ptEarth.setNode(earth);

	    ptEarth.setOrientation(
	      PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
	    ptEarth.setCycleCount(Timeline.INDEFINITE);
	    ptEarth.setAutoReverse(false);
	    ptEarth.setInterpolator(Interpolator.LINEAR);
	    
	    FadeTransition ftEarth =
	  	      new FadeTransition(Duration.millis(2500), earth);
	    ftEarth.setFromValue(1.0);
	    ftEarth.setToValue(0.5);
	    ftEarth.setCycleCount(Timeline.INDEFINITE);
	    ftEarth.setAutoReverse(true);
	    
	    //mars
	    Ellipse marsOrbit = new Ellipse(300, 150, 215, 140);
	    marsOrbit.setFill(Color.BLUE);
	    marsOrbit.setStroke(Color.YELLOW);
	    
	    Circle mars = new Circle(515, 150, 7);
	    mars.setFill(Color.RED);
	    mars.setStroke(Color.RED);
	    //put orbit and planet into group
	    Group marsG = new Group();
	    marsG.getChildren().addAll(marsOrbit, mars);
	    marsG.setRotate(15);
	    
	    //transitions
	    PathTransition ptMars = new PathTransition();
	    ptMars.setDuration(Duration.millis(5000));
	    
	    ptMars.setPath(marsOrbit);
	    ptMars.setNode(mars);

	    ptMars.setOrientation(
	      PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
	    ptMars.setCycleCount(Timeline.INDEFINITE);
	    ptMars.setAutoReverse(false);
	    ptMars.setInterpolator(Interpolator.LINEAR);
	    
	    FadeTransition ftMars =
	  	      new FadeTransition(Duration.millis(2500), mars);
	    ftMars.setFromValue(1.0);
	    ftMars.setToValue(0.5);
	    ftMars.setCycleCount(Timeline.INDEFINITE);
	    ftMars.setAutoReverse(true);

	    // Create a circle
	    Circle circle = new Circle(300, 150, 10);
	    circle.setFill(Color.YELLOW);
	    circle.setStroke(Color.BLACK);
	    
	    //put all planets into a group
	    Group planets = new Group();
	    planets.getChildren().addAll(marsG, earthG, venusG, mercuryG);

	    // Add circle and rectangle to the pane

	    pane.getChildren().add(rectangle);
	    pane.getChildren().add(planets);
	    //pane.getChildren().addAll(marsG, earthG, venusG, mercuryG);
	    pane.getChildren().add(circle);

	 // Create a scene and place it in the stage
	    Scene scene = new Scene(pane, 600, 300);
	    primaryStage.setTitle("Path and Fade Transition"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	    
	    // Create a path transition
	    PathTransition pt = new PathTransition();
	    pt.setDuration(Duration.millis(5000));
	    
	    pt.setPath(mercuryOrbit);
	    pt.setNode(mercury);

	    pt.setOrientation(
	      PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
	    pt.setCycleCount(Timeline.INDEFINITE);
	    pt.setAutoReverse(false);
	    pt.setInterpolator(Interpolator.LINEAR);
	    
	 // Apply a fade transition to ellipse
	    FadeTransition ft =
	      new FadeTransition(Duration.millis(2500), mercury);
	    ft.setFromValue(1.0);
	    ft.setToValue(0.5);
	    ft.setCycleCount(Timeline.INDEFINITE);
	    ft.setAutoReverse(true);

	      scene.setOnKeyPressed(e -> {
	          if (e.getCode() == KeyCode.UP) {

	        	  pt.play(); // Start animation
	        	  ptVenus.play();
	        	  ftVenus.play();
	        	  ft.play();
	        	  ptEarth.play();
	        	  ftEarth.play();
	        	  ptMars.play();
	        	  ftMars.play();
	          }
	          else if (e.getCode() == KeyCode.DOWN) {
	        	//stop animation
	        	  pt.stop(); 
	        	  ptVenus.stop();
	        	  ftVenus.stop();
	        	  ft.stop();
	        	  ptEarth.stop();
	        	  ftEarth.stop();
	        	  ptMars.stop();
	        	  ftMars.stop();
	        	  }
	          else if (e.getCode() == KeyCode.LEFT) {
	        	  //start counter-clockwise
	        	  pt.stop(); //stop animations
	        	  ptVenus.stop();
	        	  ftVenus.stop();
	        	  ft.stop();
	        	  ptEarth.stop();
	        	  ftEarth.stop();
	        	  ptMars.stop();
	        	  ftMars.stop();
	        	  pt.setRate(-1); //reverse animations
	        	  ptVenus.setRate(-1);
	        	  ptMars.setRate(-1);
	        	  ptEarth.setRate(-1);
	        	  pt.play(); // reStart animations
	        	  ptVenus.play();
	        	  ftVenus.play();
	        	  ft.play();
	        	  ptEarth.play();
	        	  ftEarth.play();
	        	  ptMars.play();
	        	  ftMars.play();
	        	  
	          }
	          else if (e.getCode() == KeyCode.RIGHT) {
	        	  // Start animation clockwise
	        	  pt.stop(); //stop animations
	        	  ptVenus.stop();
	        	  ftVenus.stop();
	        	  ft.stop();
	        	  ptEarth.stop();
	        	  ftEarth.stop();
	        	  ptMars.stop();
	        	  ftMars.stop();
	        	  pt.setRate(1); //reverse animations
	        	  ptVenus.setRate(1);
	        	  ptMars.setRate(1);
	        	  ptEarth.setRate(1);
	        	  ft.setRate(1);
	        	  ftVenus.setRate(1);
	        	  ftEarth.setRate(1);
	        	  ftMars.setRate(1);
	        	  pt.play(); // reStart animations
	        	  ptVenus.play();
	        	  ftVenus.play();
	        	  ft.play();
	        	  ptEarth.play();
	        	  ftEarth.play();
	        	  ptMars.play();
	        	  ftMars.play();
	          }
	        });

	    
	  }

	  /**
	   * The main method is only needed for the IDE with limited
	   * JavaFX support. Not needed for running from the command line.
	   */
	  public static void main(String[] args) {
	    launch(args);
	  }
	}