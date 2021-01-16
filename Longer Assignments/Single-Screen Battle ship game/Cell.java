/** 
 * This Class is the Cell Class.
 * A cell is a singular node/position on the game grid
 * The class contains variables, a default constructor and sets/gets methods
 */

//----------------------------------
//Assignment 04 Cell class
//Written by: Louis-Antoine LeBel 40175005
//For COMP248 Section Rj-x
//----------------------------------
package comp248;

public class Cell {

	private int type; //type of the cell: 0 for nothing, 1 for ship, 2 for grenade
	private boolean isPlayer; //if the cell has a ship or grenade placed by the player
	private boolean wasCalled; //if the cell was previously called
	
	/** Default constructor */
	public Cell(){
		type =0;
		isPlayer=false;
		wasCalled=false;
	}
	
	/** sets and gets */
	
	public void setType(int t){
		type = t;
	}
	
	public void setPlayer(boolean p){
		isPlayer = p;
	}
	
	public void setCalled(boolean c){
		wasCalled = c;
	}
	
	public int getType(){
		return type;
	}
	
	public boolean getPlayer(){
		return isPlayer;
	}
	
	public boolean getCalled(){
		return wasCalled;
	}
}
