package project;
//============================================================================
// Name        : Scoreboard.java
// Author      : Alexander Diop
// Created     : 12/6/21
// Version     : 0.0.1
// Project     : Bright Health Bowling
// Description : Class to print game attributes
//
//
//
// Compile:  javac ScoreboardController.java
// Run:      java ScoreboardController
//============================================================================
import java.util.Vector;

public class Scoreboard {
	
	/*-------------------------------------------------------------------
	 * Function:    displayScoreboard
	 * Purpose:     Print the scores of each frame as well as the final scores
	 * In args:     players: vector array of players in the game
	 * In/Out args: void
	 */
	public void displayScoreboard(Vector<Player> players) {
		
		System.out.println("WELCOME TO THE BOWLING ALLEY\n"
				+ "=====================================================");
		
		for(int i = 0; i < players.size(); i++) {
			
			System.out.print(players.get(i).getName() + ": ");
			int [][] playerFrames = players.get(i).getFrames();
			
			for(int j = 0; j < playerFrames[0].length - 1; j++) {
				
				if(playerFrames[0][j] == 10) {//if the frame was a strike
					
					System.out.print("(X");
					
				}else {
					
					System.out.print("(" + playerFrames[0][j]);
					
					if(playerFrames[1][j] == 10) {//if the frame was a spare
						System.out.print("|/");
					}else {
						System.out.print("|" + playerFrames[1][j]);
					}
				}
				
				//PRINT THIRD THROW IN 10TH FRAME 
				if(j == playerFrames[0].length - 2 && (playerFrames[0][j] == 10 || playerFrames[1][j] == 10)) {

					System.out.print("|" + playerFrames[0][j+1]);
				}
				
				System.out.print(") ");
			}
			
			System.out.println("--->[" + players.get(i).getScore() + "]");
		}
	}
	
	
	/*-------------------------------------------------------------------
	 * Function:    displayAnimation
	 * Purpose:     Print the winner of the game as well as their final score
	 * In args:     players: vector array of players in the game
	 * In/Out args: void
	 */
	public void displayAnimation(Vector<Player> players) {
		
		int winnerIndex = 0;
		int highestScore = -1;
		
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).getScore() > highestScore) {
				winnerIndex = i;
				highestScore = players.get(i).getScore();
			}
		}
		
		System.out.println("\nWinner: " + players.get(winnerIndex).getName() + " with a score of " + highestScore);
	}
}
