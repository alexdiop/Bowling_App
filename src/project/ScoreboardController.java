package project;
//============================================================================
// Name        : ScoreboardController.java
// Author      : Alexander Diop
// Created     : 12/6/21
// Version     : 0.0.1
// Project     : Bright Health Bowling
// Description : Main class to initialize and control bowling game
//
//
// Compile:  javac ScoreboardController.java
// Run:      java ScoreboardController
//============================================================================

import java.util.Vector;

public class ScoreboardController {
	
	private static Scoreboard scoreboard;
	private static LaneHardwareController lane;
	private final static int NUMFRAMES = 10;
	private static Vector<Player> players;
	private static int playerTurn;
	
	
	public static void main(String[] args) {

		//A real application would ask for users names by physical input here
		String[] names = { "Alexander", "Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "Sophia", "Isabella",
				"Charlotte", "Benjamin", "Amelia", "Lucas", "Mia", "Mason", "Harper"};
		
		scoreboard = new Scoreboard();
		lane = new LaneHardwareController();
		players = new Vector<Player>();
		for(int i = 0; i < names.length; i++) {
			players.add(new Player(names[i], NUMFRAMES));
		}
		
		beginGame();
		scoreboard.displayScoreboard(players);
		scoreboard.displayAnimation(players);

	}

	
	/*-------------------------------------------------------------------
	 * Function:    beginGame
	 * Purpose:     Method to begin bowling game 
	 * In args:     
	 * Out args: 	
	 */
	public static void beginGame() {

		//Loop through all ten frames
		for(int i = 0; i < NUMFRAMES; i++){

			playerTurn = 0;
			while(playerTurn < players.size()) {

				//in a real application players would attempt a bowl here
				Throw firstThrow = lane.countPins(false);
				
				tallyPoints(players.get(playerTurn), firstThrow, i);
				//UPDATE SCORE HERE
				players.get(playerTurn).setFrame(i, firstThrow);
				
				char lastResult = firstThrow.getType();
				//if the attempt is not a strike OR the last attempt was a strike and it's the 10th frame
				if(lastResult != 'X') {
					//in a real application players would attempt a bowl here
					Throw secondThrow = lane.countPins(true);
					tallyPoints(players.get(playerTurn), secondThrow, i);
					//UPDATE SCORE HERE
					players.get(playerTurn).setFrame(i, secondThrow);
					lastResult = secondThrow.getType();
					
				}
				
				//if the player bowled a strike or spare in the 10th frame
				if((lastResult == '/' || lastResult == 'X') && i == NUMFRAMES - 1) {

					lane.resetPins();
					//in a real application players would attempt a bowl here
					Throw thirdThrow = lane.countPins(false);
					tallyPoints(players.get(playerTurn), thirdThrow, i+ 1);
					//UPDATE SCORE HERE
					players.get(playerTurn).setFrame(i+1, thirdThrow);
					lastResult = thirdThrow.getType();
				}
				
				playerTurn++;
				lane.resetPins();
			}
			
		}
		
	}
	
	
	/*-------------------------------------------------------------------
	 * Function:    tallyPoints
	 * Purpose:     Helper Method to calculate the points of a throw
	 * In args:     player (Player): respective player
	 * 				latestThrow (Throw)
	 * 				latestFrame(int): latest (current) frame
	 * Out args: 	
	 */
	private static void tallyPoints(Player player, Throw latestThrow, int latestFrame) {
		
		if(latestThrow.getType() == 'X' || latestThrow.getType() == '/') {
			player.setScore(player.getScore() + 10);
		}else if (latestThrow.getTurn() == 1){
			player.setScore(player.getScore() + Character.getNumericValue(latestThrow.getType()));
		}

        calculatePrevFrame(player, latestThrow, latestFrame);
	}
	

	/*-------------------------------------------------------------------
	 * Function:    calculatePrevFrame
	 * Purpose:     Helper Method to calculate the points from previous frames
	 * In args:     player (Player): respective player
	 * 				latestThrow (Throw)
	 * 				frameNum(int): latest (current) frame
	 * Out args: 	
	 */
	private static void calculatePrevFrame(Player player, Throw latestThrow, int frameNum) {
        if (frameNum >= 1) { // there has been at least one previous frame
            
        	//if the last frame was a spare and this is the first turn of the current frame
            if(player.getFrames()[1][frameNum - 1] == 10 && latestThrow.getTurn() == 0) {
                player.setScore(player.getScore() + latestThrow.getKnockedPins());
            }else if(player.getFrames()[0][frameNum - 1] == 10 && (latestThrow.getTurn() == 1 || latestThrow.getKnockedPins() == 10))
            {//if the last frame was a strike and this is the last turn of the frame or the player got another strike
                player.setScore(player.getScore() + latestThrow.getKnockedPins());
            }
        }
    }

}
