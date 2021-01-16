/** 
 * This Class is the main class for the assignment.
 * It sets up the playing grid by asking user input and randomly generates positions for the computer
 * It handles the gameplay loop and the end of the game
 * It also has methods for altering the grid, displaying the grid, converting a user input into workable integers and checking input validity
 */

//----------------------------------
//Assignment 04 Main Class
//Written by: Louis-Antoine LeBel 40175005
//For COMP248 Section Rj-x
//----------------------------------

package comp248;

import java.util.Scanner;
public class Battleship {
	
	/** Initialize static variables to be accessed by all methods in this class */

	static int x; //coordinate in the horizontal axis (letters, here)
	static int y; //coordinate in the vertical axis (numbers)
	
	static int playerHitCount = 0; //goes up everytime one of the player's ship is hit, max value of 6
	static int cpuHitCount = 0; //goes up everytime one of the computer's ship is hit, max value of 6
	
	static Cell[][] cells = new Cell[8][8]; //instance of the gameboard
	
	static boolean playerTurn = true; //true if it is the player's turn to play
	static boolean playerSkipTurn = false; //gets set to true when player hits a grenade
	static boolean cpuSkipTurn = false; //gets set to true when cpu hits a grenade
	
	public static void main(String[] args){
		
		/** Initialize variables */
		Scanner input = new Scanner(System.in);
		
		/** Fill grid with default cells */
		for(int i =0; i<cells.length; i++){
			for(int e =0; e< cells[i].length; e++){
				cells[i][e] = new Cell();
			}
		}
		
		/** Greeting message */
		System.out.println("Hi, let's play Battleship!");
		
		/** Set player ships */
		int type =1; //keep track of the type of cell the player is inputting (1 for ship, 2 for grenade)
		String playerAnswer;
		
		//loop runs twice, once for placing ships and the other for grenades
		for(int i =6; i>3; i-=2){
			for(int e =0; e<i; e++){ //runs up to value of i. 6 times for ships, 4 for grenades
				while(true){ //while loop to ensure we get a good user input
					
					System.out.print("Enter the coordinates of your " + ((type == 1)? "ship": "grenade") +" #" + (e+1) +" ");
					
					playerAnswer = input.next();
					
					if(isInputValid(playerAnswer)){
						
						x = getX(playerAnswer);
						y = getY(playerAnswer);
					
						if(cells[x][y].getType() == 0){ //check if selected coordinate is empty
							cells[x][y].setType(type); //set the cell to its proper type
							cells[x][y].setPlayer(true); //set the cell to player-owned
							break; //while loops ends if input is correct
						}
						else
							System.out.print("Sorry, coordinates already used. Try again\n");
					}
					else
						System.out.print("Sorry, coordinates outside the grid. Try again\n");
				}
			}
			type++; //change type to grenade for the second loop
		}
		
		/** Set computer ships */
		type = 1;
		
		for(int i =6; i>3; i-=2){
			for(int e =0; e<i; e++){
				while(true){ //while loop to ensure we get a random coordinate that does not intersect with player inputs

					/** Randomly generate coordinate */
					x = (int)(Math.random() * 8);
					y = (int)(Math.random() * 8);
					
					if(cells[x][y].getType() == 0){ //check if random coordinate is an empty cell, run back the while loop otherwise
						cells[x][y].setType(type);
						cells[x][y].setPlayer(false);
						//System.out.println("Cpu " + ((type == 1)? "ship": "grenade") +" #" + (e+1) + " placed at " + ((char)(y+65)) + (x+1)); //debug
						break; //end while loop
					}
				}	
			}
			type++; //change type to grenade
		}
		
		//finalGrid(cells); //debug
		
		System.out.println("OK, the computer placed its ships and grenades at random. Let's Play.\n\n");
		
		/** Gameplay loop */
		
		drawGrid(cells); //draw whole game board once before running the program
		
		/** Main while loop where the game happens */
		while(playerHitCount <6 && cpuHitCount < 6){ //loop stops when either player or cpu have all their ships blown up
			
			/** Player turn */
			if(playerTurn){ //this runs if it is the player's turn, goes to else-statement if it is the cpu's turn
				
				/** Grenade skip-turn check */
				if(playerSkipTurn){ //check if player has hit a grenade their last turn
					playerSkipTurn = false; //remove the skip turn
					playerTurn = !playerTurn; //change the turn back to a cpu turn
					continue; //go to the next loop iteration
				}
				
				while(true){
					/** Ask user for their coordinate */
					System.out.print("position of your rocket: ");
					playerAnswer = input.next();
					
					if(isInputValid(playerAnswer)){ //check if input is valid and set x, y coordinates if it is
						x = getX(playerAnswer);
						y = getY(playerAnswer);
						
						if(!cells[x][y].getCalled())
							launchRocket(); //launch a rocket only if the cell has not been called before
						
						else
							System.out.println("Position already called");
						
						break; //break while loop if input is valid
					}
					else
						System.out.print("Sorry, coordinates outside the grid. Try again\n");
				}
			}
			
			/** Cpu turn */
			else {
				
				if(cpuSkipTurn){ //works the same as the player skip turn
					cpuSkipTurn = false;
					playerTurn = !playerTurn;
					continue;
				}
				
				/** While loop to make sure the randomly-generated input doesn't hit the cpu's own ships/grenades or a tile that was already called */
				while(true){
					x = (int)(Math.random() * 8);
					y = (int)(Math.random() * 8);
					
					//System.out.println("trying: " + x + " ," + y); //debug
					
					if(!cells[x][y].getCalled() && (((cells[x][y].getType() == 1 || cells[x][y].getType() == 2) && cells[x][y].getPlayer() || cells[x][y].getType() == 0)))
						break; //input is valid if cell has not been called, is a player or grenade ship or if the cell is empty
				}
				System.out.println("position of the computer's rocket: " + ((char)(y +65)) + (x+1));
				
				launchRocket();
				
				}
			
			/** This runs at the end of every turn*/
			playerTurn = !playerTurn; //switch who's playing
			drawGrid(cells); //print the current grid
			
			} //end of gameplay loop
		
		/** Declare winner */
		if(cpuHitCount==6)
			System.out.println("You Win!\nFinal Grid:");
		
		else
			System.out.println("\nYou Lose :(\nFinal Grid:");
		
		finalGrid(cells); //print final grid
		input.close(); //close scanner
		}
	
		
	
	/** Method to print the board in its current state, called after each turn */
	public static void drawGrid(Cell[][] cells){
		
		System.out.println("   A B C D E F G H");
		
		for(int i=0; i<cells.length; i++){
			System.out.print(" "+ (i+1) + " ");
			for(int e=0; e<cells.length; e++){
				
				if(!cells[i][e].getCalled()) //check if cell is untouched
					System.out.print("_ ");
				
				else if(cells[i][e].getCalled() && cells[i][e].getType() == 1) //check if cell has a hit ship
					System.out.print(cells[i][e].getPlayer()? "s ": "S "); //print lowercase s if ship is the player's ship, capital otherwise
				
				else if(cells[i][e].getCalled() && cells[i][e].getType() == 2)//check if cell has a hit grenade
					System.out.print(cells[i][e].getPlayer()? "g ": "G "); //print lowercase g if grenade is the player's grenade, capital otherwise
				
				else
					System.out.print("* "); //print '*' if there was nothing on a hit cell
			}
			System.out.println();
		}
	}
	
	/** Method to display the final board with every grenade and ship */
	public static void finalGrid(Cell[][] cells){
		
		System.out.println("   A B C D E F G H");
		
		for(int i=0; i<cells.length; i++){
			System.out.print(" "+ (i+1) + " ");
			for(int e=0; e<cells[i].length; e++){
				
				if(cells[i][e].getType() == 1) //check if cell has a ship
					System.out.print(cells[i][e].getPlayer()? "s ": "S "); //print lowercase s if ship is the player's ship, capital otherwise
				
				else if(cells[i][e].getType() == 2)//check if cell has a grenade
					System.out.print(cells[i][e].getPlayer()? "g ": "G "); //print lowercase g if grenade is the player's grenade, capital otherwise
				
				else
					System.out.print("_ ");
			}
			System.out.println();
		}
	}
	
	/** This method takes coordinate for a rocket and changes the game board accordingly */
	public static void launchRocket(){
		
		/** Switch statement to check what type of cell the rocket was launched on */
		switch(cells[x][y].getType()){
		
			case 1: //Ship cell
				System.out.println("ship hit");
				
				if(cells[x][y].getPlayer())
					playerHitCount++; //add a sunk player ship if there was a player ship on the cell
				
				else
					cpuHitCount++; //add a sunk cpu ship if there was a cpu ship on the cell
				
				cells[x][y].setCalled(true); //set the cell to called
				
				break;
		
			case 2: //grenade cell
				System.out.println("boom! grenade.");
				
				cells[x][y].setCalled(true); //set the cell to called
				
				playerSkipTurn = false; cpuSkipTurn = false; //reset all skipped turns to avoid strange skipped turns overlaps
				
				if(playerTurn)
					playerSkipTurn = true; //skip a player turn if it was the player's turn
				else
					cpuSkipTurn = true; //skip a cpu turn if it was the cpu's turn
				
				break;
			
			default: //empty cell
				System.out.println("nothing");
				cells[x][y].setCalled(true); //set the cell to called
				break;
		}
	}
	
	/** Method to return a number from 0-7 from a letter */
	public static int getX(String s){
		return Integer.parseInt(s.substring(1)) -1;
	}
	
	/** Method to return a number from 0-7 from the second character in a coordinate input string */
	public static int getY(String s){
		return ((int)(Character.toUpperCase(s.charAt(0)))) - 65;
	}
	
	/** Method that checks if user input is within the array bounds */
	public static boolean isInputValid(String s){
		int letterIndex = ((int)(Character.toUpperCase(s.charAt(0)))) - 65; //subtract 65 because unicode of 'A' is 65
		int numberIndex = Integer.parseInt(s.substring(1)) -1;
		
		if(letterIndex <0 || letterIndex > 7 || numberIndex <0 || numberIndex > 7) //check if input is out of bounds
			return false;
		else
			return true;
	}
}