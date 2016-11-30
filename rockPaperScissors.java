
/*******************
 * Aaron Toran
 * 6/19/2016
 * 
 * Rock paper scissors command line game 
 * 
 * 
 *******************/
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class rockPaperScissors {
	public static void main(String[] args) {
		boolean done = false;
		String rematch = "";
		rockPaperScissors game = new rockPaperScissors();
		// game loop
		while (done == false) {
			// outputting the instructions and creating the IO
			System.out.println("Please enter your list of actions each action must be separated by a space");
			Scanner kbd = new Scanner(System.in);
			//making the user input lower case for easier data manipulation
			String actions = kbd.nextLine().toLowerCase();
			//checks for null value
			if (!actions.equals("")) {
				// splitting the action string using spaces as the delimiter then using the split list as input for making the arraylist
				ArrayList<String> actionList = new ArrayList<String>(Arrays.asList(actions.split("\\s+")));
				// getting checking for duplicates and checking to make sure their are more then two actions
				int length = actionList.size();
				if (length > 2  && !game.duplicateChecker(actionList, length)) {
					// Creating a copy of the actionList and rotating it so that i can use the rotated list to pick the winner 
					ArrayList<String> powers = new ArrayList<String>(actionList);
					Collections.rotate(actionList, 1);
					game.rules(actionList, powers, length);
					boolean nextMatch = true;
					while(nextMatch){
						if(rematch.equals("")){
						nextMatch = true;
						game.match(actionList, powers, kbd, length);
						System.out.println("Play again(P)? Exit game(X)? Choose new Action(A)");
						rematch = kbd.nextLine().toLowerCase();
						}
						if(rematch.equals("x")){
							kbd.close();
							done = true;
							nextMatch = false;
							
						}else if (rematch.equals("a")){
							rematch = "";
							nextMatch = false;
						}else if (rematch.equals("p")){
							rematch = "";
						}else{
							System.out.println("selection not found please try again");
							System.out.println("Play again(R)? Exit game(N)?");
							rematch = kbd.nextLine().toLowerCase();
						}
					}
				}
					else {
					System.out.println(
							"There were errors in your input please make sure that there are no duplicates and that the action list entered is greater than two please try again.");
				}
			} else {
				System.out.println("Nothing entered Please try again");
			}
		}
	}

	// match function handles the user input for the round and makes the
	// computer pick a random action
	public void match(ArrayList<String> actionList, ArrayList<String> powers, Scanner kbd, int length) {
		System.out.println("Type in your choice of action");
		boolean goodGame = false;
		while (goodGame == false) {
			String action = kbd.nextLine().toLowerCase();
			if (turnInputChecker(actionList, action, length)) {
				// sentinel value that checks if the match has been completed
				goodGame = true;
				Random randomGenerator = new Random();
				int rand = randomGenerator.nextInt(length);
				String computerAction = actionList.get(rand);
				System.out.println("The computer throws :  " + computerAction);
				if (powers.indexOf(computerAction) == actionList.indexOf(action)) {
					System.out.println("The computer Wins");
				} else if (powers.indexOf(action) == actionList.indexOf(computerAction)) {
					System.out.println("You Win");
				} else {
					System.out.println("Draw");
				}
			} else {
				System.out.println("selection not found please try again");
			}
		}

	}

	// prints out which action beats the other action
	public void rules(ArrayList<String> actionList, ArrayList<String> powers, int length) {
		System.out.println("Rules: ");
		for (int j = 0; j < length; j++) {
			System.out.println(powers.get(j) + " beats " + actionList.get(j));
		}
	}
	//checks for duplicate's
	public boolean duplicateChecker(ArrayList<String> actionList, int length) {
		for (int j = 0; j < length; j++) {
			for (int i = j + 1; i < length; i++) {
				if (actionList.get(j).equals(actionList.get(i))) {
					return true;
				}
			}
		}
		return false;
	}

	// checking to see if the action the user picked is valid
	public boolean turnInputChecker(ArrayList<String> actionList, String action, int length) {
		if (action.equals(""))
			return false;
		for (int i = 0; i < length; i++) {
			if (action.equals(actionList.get(i))) {
				return true;
			}
		}
		return false;
	}

}