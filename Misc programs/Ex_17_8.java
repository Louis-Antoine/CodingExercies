/** 
 * This program stores in a .dat file how many times it ran and prints it out
 * */

package exercisesEnClasseLeRetoure;

import java.io.*;

public class Ex_17_8 {

	public static void main(String[] args){
		
		try ( //create a random access file
				RandomAccessFile inout = new RandomAccessFile("Exercise_17_08.dat", "rw");
			) {
			//if file length is zero start the count
			if(inout.length()==0)
				inout.writeInt(1);
			
			//run hello world
			System.out.println("Hello World");
			
			//get the count & print it  and add 1
			inout.seek(0);
			int currentCount = inout.readInt();

			System.out.println("This program has ran " + (currentCount) +" times.");
			
			//add 1 to the count
			inout.seek(0);
			inout.writeInt(currentCount+=1);
				
			
			
		}
		catch(Exception e){}
	}
}
