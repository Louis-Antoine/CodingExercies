/**
 *  This program displays a grid a draws a line that moves in random direction until it can no longer move (trapped by itself or reached the edge)
 *  */

package exercisesEnClasseLeRetoure;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
public class Ex_15_35 extends Application{
	//variable that need to be accessed in keyframe event handler
	Timeline timeline;
	int posX =8;
	int posY =8;
	public void start(Stage primaryStage) {
	    // Create a pane
	    Pane pane = new Pane();
	    BorderPane bPane = new BorderPane();
	
	    Point[][] pointArr = new Point[17][17];
	    Line[][][] lineArr = new Line[16][16][2];
	    
	    //initiate points
	    
	    for(int i = 0; i<pointArr.length; i++){
	    	pointArr[i][0] = new Point((int)(i*32), 0, false);
	    	for(int e = 1;e<pointArr[i].length; e++){
	    		pointArr[i][e] = new Point((int)(i*32),(int)(e*32), false);
	    	}
	    	
	    }
	    
	    //start button
	    Button start = new Button("Start");
	    HBox box = new HBox(1);
	    box.getChildren().add(start);
	    box.setAlignment(Pos.CENTER);
	    
	    
	    
	    //draw lines
	    for(int i = 0; i<lineArr.length; i++){
	    	for(int e = 0;e<lineArr[i].length; e++){
	    		lineArr[i][e][0] = new Line(pointArr[i][e].x,pointArr[i][e].y, pointArr[i][e].x,pointArr[i][e].y +32); //vertical line
	    		lineArr[i][e][1] = new Line(pointArr[i][e].x,pointArr[i][e].y, pointArr[i][e].x +32,pointArr[i][e].y); //horizontal line
	    		lineArr[i][e][0].setOpacity(0.1);
	    		lineArr[i][e][1].setOpacity(0.1);
	    		pane.getChildren().addAll(lineArr[i][e][0],lineArr[i][e][1]);
	    	}
	    }
	    //draw bottom line for aesthetics
	    Line bottomLine = new Line(0,512,512,512);
	    bottomLine.setOpacity(0.1);
	    pane.getChildren().add(bottomLine);

	    //timeline
	    timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> {
	    	linePath(pointArr, lineArr);
	    	
	    }));
	    timeline.setCycleCount(Timeline.INDEFINITE);

	    //button event
	    start.setOnAction(e -> {
	        timeline.play();
	      });
	    //layout config
	    bPane.setCenter(pane);
	    bPane.setBottom(box);
	    
	 // Create a scene and place it in the stage
	    Scene scene = new Scene(bPane, 512, 550);
	    primaryStage.setTitle("Line Path"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	    	
	}
	
	public static void main(String[] args) {
	    launch(args);
	  }
	
	
	public void linePath(Point[][] pointArr, Line[][][] lineArr){
		boolean moved = false; //true if line moved
	    
		//check if line is in boundary before running the loop
		if(posX>0&&posY>0&&posX<16&&posY<16){
		
		//loop to make sure at least one movement happens per keyframe
		while(!moved){	
			double rdmDir = Math.random();
	    	pointArr[posX][posY].isPassed = true;
	    	//move down
	    	if(rdmDir<0.250&&!pointArr[posX][posY+1].isPassed){
	    		System.out.println("down");
	    		lineArr[posX][posY][0].setOpacity(1);
	    		posY+=1;
	    		moved = true;
	    	}
	    	
	    	//move up
	    	else if(rdmDir>=0.750&&!pointArr[posX][posY-1].isPassed){
	    		System.out.println("up");
	    		lineArr[posX][posY-1][0].setOpacity(1);
	    		posY-=1;
	    		moved = true;
	    	}
	    	
	    	//move right
	    	else if(rdmDir<0.750&&rdmDir>=0.500&&!pointArr[posX+1][posY].isPassed){
	    		System.out.println("right");
	    		lineArr[posX][posY][1].setOpacity(1);
	    		posX+=1;
	    		moved = true;
	    	}
	    	
	    	//move left
	    	else if(rdmDir<0.50&&rdmDir>=0.250&&!pointArr[posX-1][posY].isPassed){
	    		System.out.println("left");
	    		lineArr[posX-1][posY][1].setOpacity(1);
	    		posX-=1;
	    		moved = true;
	    	}
	    	
	    	//check if point is trapped
	    	else if(pointArr[posX][posY-1].isPassed&&pointArr[posX][posY+1].isPassed&&pointArr[posX-1][posY].isPassed&&pointArr[posX+1][posY].isPassed){
	    		moved = true;
	    		timeline.stop();
	    	}
	    }
		}
		else
			timeline.stop();
	}
}