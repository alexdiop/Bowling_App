package project;
//============================================================================
// Name        : LaneHardwareController.java
// Author      : Alexander Diop
// Created     : 12/6/21
// Version     : 0.0.1
// Project     : Bright Health Bowling
// Description : Class object for the physical device in the bowling alley that counts pins
//
//
// Compile:  javac ScoreboardController.java
// Run:      java ScoreboardController
//============================================================================

import java.util.Arrays;
import java.util.Random;

public class LaneHardwareController {
	
	private boolean [] pins;
	private int numPins;
	
	public LaneHardwareController() {
		pins = new boolean[10];
		Arrays.fill(pins, Boolean.TRUE);
		numPins = 10;
	}
	
	/*-------------------------------------------------------------------
	 * Function:    countPins
	 * Purpose:     Method to 'count' the number pins after a throw
	 * In args:     secondTurn (boolean) indicates whether or not 
	 * 					this throw is the second of a given frame
	 * Out args: 	lastThrow (Throw) the latest throw object
	 */
	public Throw countPins(boolean secondTurn){
		
		//a real application would assess how many pins have been knocked down here (WITH HARDWARE)
		Random random = new Random(); //java.util.Random
		for (int i = 0; i < pins.length; i++) {
			if(pins[i]) {
			    pins[i] = random.nextBoolean();
			    if(pins[i] && random.nextBoolean()) {
				    pins[i] = random.nextBoolean();
			    }
			}
		}
		
		return new Throw(pins, numPins, secondTurn);	
	}
	
	/*-------------------------------------------------------------------
	 * Function:    resetPins
	 * Purpose:     Method to reset the pins in the alley
	 * In args:     
	 * Out args: 	
	 */
	public void resetPins() {

		Arrays.fill(pins, Boolean.TRUE);
		numPins = 10;
	}

}
