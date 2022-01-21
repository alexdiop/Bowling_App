package project;
//============================================================================
// Name        : Throw.java
// Author      : Alexander Diop
// Created     : 12/6/21
// Version     : 0.0.1
// Project     : Bright Health Bowling
// Description : Class object for a given bowling attempt by a player
//
//
// Compile:  javac ScoreboardController.java
// Run:      java ScoreboardController
//============================================================================

public class Throw {
	
	private boolean [] pinState;
	private boolean secondTurn;
	int initNumPins;
	
	public Throw(boolean [] pins, int initNumPins, boolean secondTurn) {
		this.pinState = pins;
		this.initNumPins = initNumPins;
		this.secondTurn = secondTurn;
	}
	

	/*-------------------------------------------------------------------
	 * Function:    getType
	 * Purpose:     Method to return the 'type' of throw (Strike/Spare/numPins)
	 * In args:     
	 * Out args: 	result (char)
	 */
	public char getType() {
		char result = '0';
		int pinsRemaining = 0;
		
		for(int i = 0; i < pinState.length; i++) {
			if(pinState[i] == true){
				pinsRemaining++;
			}
		}
		
		if(pinsRemaining == 0) {
			if(secondTurn) {//spare
				result = '/';
			}else {//strike
				result = 'X';
			}
		}else {//knocked down 9 or fewer pins, but not all 
			int knockedDown = initNumPins - pinsRemaining;
			
			result = Character.forDigit(knockedDown,10);
		}
		
		return result;
	}

	/*-------------------------------------------------------------------
	 * Function:    getKnockedPins
	 * Purpose:     Method to return the number of pins knocked down in the throw
	 * In args:     
	 * Out args: 	knockedDown (int)
	 */
	public int getKnockedPins() {
		int pinsRemaining = 0;
		for(int i = 0; i < pinState.length; i++) {
			if(pinState[i]){
				pinsRemaining++;
			}
		}

		return initNumPins - pinsRemaining;
	}
	
	
	/*-------------------------------------------------------------------
	 * Function:    getTurn
	 * Purpose:     Method to return the turn (first or second) of the the throw
	 * In args:     
	 * Out args: 	turn (int) 0 for first turn, 1 for second turn
	 */
	public int getTurn() {
		if(secondTurn) {
			return 1;
		}else {
			return 0;
		}
	}


}
