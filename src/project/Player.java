package project;
//============================================================================
// Name        : Player.java
// Author      : Alexander Diop
// Created     : 12/6/21
// Version     : 0.0.1
// Project     : Bright Health Bowling
// Description : Class object for a given bowling a player
//
//
// Compile:  javac ScoreboardController.java
// Run:      java ScoreboardController
//============================================================================

public class Player {
	
	private String name;
	private int score;
	private int [][] frames;
	
	public Player(String name, int numFrames) {
		this.name = name;
		score = 0;
		score = 0;
		this.frames = new int[2][numFrames + 1];
	}
	
	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int[][] getFrames() {
		return frames;
	}
	
	/*-------------------------------------------------------------------
	 * Function:    setFrame
	 * Purpose:     Method to assign a value to the frame and turn of the player after they've bowled a turn
	 * In args:     frame (int): the respective frame
	 * 				lastThrow (Throw): the latest throw
	 * Out args: 	
	 */
	public void setFrame(int frame, Throw lastThrow) {
		int value;
		
		if(lastThrow.getType() == 'X') {
			value = 10;
//			System.out.println(name + " got a STRIKE ");
		}else if(lastThrow.getType() == '/') {
			value = 10;
		}else {
			value = Character.getNumericValue(lastThrow.getType());
		}
		
		frames[lastThrow.getTurn()][frame] = value;
	}

}
