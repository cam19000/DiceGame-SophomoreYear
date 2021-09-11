/**
 * Cameron Cross
 */
package dicegame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author 19194
 */
public class DiceGame {

    private Player[] players;
    private int numPlayers;
    private int pot;
    private boolean gameOver;
    private int mode;

    /**
     * Constructor that calls the setUpGame method.
     */
    public DiceGame() {
        setUpGame();
    }

    /**
     * Method that returns the number of players when called
     *
     * @return int numPlayers
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Method that returns an array of player objects when called
     *
     * @return players[]
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Method that returns whether the game is over
     *
     * @return Boolean gameOver
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Method that sets up the game and allows for the player to choose which
     * mode they wish to play on. Returns nothing Asks how many people are going
     * to play and sets = numPlayers Asks each player for their name and uses
     * the input to create a new player with that name Calls the displayRules
     * method at the end
     */
    public void setUpGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which mode do you want to play? \nEasy: 1\nHard: 2");
        mode = sc.nextInt();
        while (mode != 1 && mode != 2) {
            System.out.println("Error: Please input 1 or 2. (easy or hard mode)");
            mode = sc.nextInt();
        }
        System.out.println("How many people are going to play?");
        numPlayers = sc.nextInt();
        sc.nextLine();

        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ":");
            String n = sc.nextLine();
            players[i] = new Player(n);
        }
        displayRules();
    }

    /**
     * Method that displays the rules for the game based on which mode the
     * player chose during the setUpGame method.
     */
    public void displayRules() {
        if (mode == 1) {
            System.out.println("Easy mode chosen!");
            System.out.println("RULES!\nEach player player places a bet and chooses a number "
                    + "between 2 and 12.\nThe total of all the bets forms a \"pot.\""
                    + "\nThen two dices are rolled.\nIf one of the players bet on the result correctly, she or he wins the entire pot.\n"
                    + "If more than one player bet on that number, the one who bet the most wins the entire pot.\n"
                    + "If there is a tie, they split the pot.\nIf nobody bet on that number, the money remains in the pot for the next round.\n"
                    + "The game is over if one of the players run out of money.");
        } else {
            System.out.println("Hard mode chosen!");
            System.out.println("RULES!\nEach player player places a bet and chooses two numbers "
                    + "between 1 and 6.\nThe total of all the bets forms a \"pot.\""
                    + "\nThen two dices are rolled.\nIf one of the players bet on the result correctly, she or he wins the entire pot.\n"
                    + "If more than one player bet on those numbers, the one who bet the most wins the entire pot.\n"
                    + "If there is a tie, they split the pot.\nIf nobody bet on those numbers, the money remains in the pot for the next round.\n"
                    + "The game is over if one of the players run out of money.");
        }

    }

    /**
     * Method that loops through each of the players in the player object array
     * and calls the playTurn method based on which player is up. Updates the
     * pot based on the bet amount for each player Displays the balance in the
     * pot Also creates 2 random numbers between 1 and 6 (dice1, dice2) and
     * stores these methods in the outcomes (outcome,o1,o2) Calls the
     * checkWinner method based on which game mode is active mode 1 calls
     * checkWinner(outcome), mode 2 calls checkWinner(o1, o2) Sets Boolean
     * gameOver to true if the balance of any player is 0.
     */
    public void playGame() {
        for (int i = 0; i < numPlayers; i++) {
            playTurn(players[i]);
            pot = pot + players[i].getBetAmount();
        }
        System.out.println("The balance in the pot is: $" + pot);
        Random w = new Random();
        Random z = new Random();
        int dice1 = w.nextInt(6) + 1;
        int dice2 = z.nextInt(6) + 1;
        int outcome = dice1 + dice2;
        int o1 = dice1;
        int o2 = dice2;
        if (mode == 1) {
            checkWinner(outcome);
        }
        if (mode == 2) {
            checkWinner(o1, o2);
        }

        for (int i = 0; i < numPlayers; i++) {
            if (players[i].getBalance() == 0) {
                gameOver = true;
                break;
            }
        }
    }

    /**
     * Method that plays the turn based on which player object is in the
     * parameter of the method when called. Displays the balance of each player
     * and asks what they would like to bet Displays an error if the betAmount
     * exceeds the balance of the player Sets the players input in
     * setBetAmount(betAmount) Updates the players balance accordingly based on
     * the bet setBalance(player.getBalance() - betAmount) Asks the player for
     * his or her guess/guesses and stores it in the variable guess Displays an
     * error if guess is not in range 2-12 for mode 1, and 1-6 for mode 2 Calls
     * the setGuess method based on the players guess or guesses
     *
     * @param player object
     */
    public void playTurn(Player player) {
        System.out.println("Player " + player.getName() + ", your balance is: $" + player.getBalance() + " How much would you like to bet?");
        Scanner sc = new Scanner(System.in);
        int betAmount = sc.nextInt();
        while (betAmount > player.getBalance()) {
            System.out.println("Error: your bet amount exceeds your balance. Please re-enter your bet amount.");
            betAmount = sc.nextInt();
        }

        player.setBetAmount(betAmount);
        player.setBalance(player.getBalance() - betAmount);

        if (mode == 1) {
            System.out.println("Enter your guess:");
            int guess = sc.nextInt();
            while (guess > 12 || guess < 2) {
                System.out.println("Error: your guess must be in the range of 2 - 12. Please re-enter your guess.");
                guess = sc.nextInt();
            }
            player.setGuess(guess);
        }
        if (mode == 2) {
            System.out.println("Enter your first guess:");
            int guess = sc.nextInt();
            while (guess > 6 || guess < 1) {
                System.out.println("Error: your guess must be in the range of 1 - 6. Please re-enter your guess.");
                guess = sc.nextInt();
            }
            System.out.println("Enter your second guess:");
            int guess1 = sc.nextInt();
            while (guess1 > 6 || guess1 < 1) {
                System.out.println("Error: your guess must be in the range of 1 - 6. Please re-enter your guess.");
                guess1 = sc.nextInt();

            }
            player.setGuess(guess);
            player.setGuess1(guess1);
        }

    }

    /**
     * Method the checks the winner of the round and update the balance of each
     * player, and the pot accordingly. Creates a new ArrayList in order to
     * store which players had correct guesses Int correctGuesses is used to
     * store how many players had correct guesses Runs through loop checking if
     * any players had matches, if so, correctGuesses gets augmented by 1 If
     * correctGuesses = 0, displays that no players won and how much each player
     * lost. The pot remains If correctGuesses = 1, the player with the correct
     * guess wins everything in the pot. Displays how much each player won and
     * lost using a for loop. Pot gets updated to 0 If correctGuesses greater
     * than 1, runs a check to see which player had the highest bet using a for
     * loop Creates highestBet in order to store what the highest bet was in the
     * round Creates a new int numHighest to store how many players had the
     * highest bet If numHighest = 1, then the player with the correct guess and
     * highest bet wins the pot. Displays how much each player won and lost,
     * updates pot to 0. If numHighest greater than 1, then the players with the
     * correct guess, and the highest bets splits the pot evenly. (potSplit used
     * to store how much each player gets) Displays how much each player wins,
     * and sets the pot = 0.
     *
     * @param outcome (int)
     */
    public void checkWinner(int outcome) {
        // Creates ArrayList to store the winning players using a for loop
        ArrayList<Integer> whichPlayer = new ArrayList<Integer>();
        int correctGuesses = 0;
        for (int i = 0; i < numPlayers; i++) {
            if (players[i].getGuess() == outcome) {
                correctGuesses = correctGuesses + 1;
                whichPlayer.add(i);
            }
        }
        // If correctGuesses = 0, loop through each player and display how much they lost using the getBetAmount method. Leave the pot
        if (correctGuesses == 0) {
            System.out.println("The outcome is " + outcome + " For this round\nNo one wins");
            for (int i = 0; i < numPlayers; i++) {
                System.out.println(players[i].getName() + " lost $" + players[i].getBetAmount() + " this round");
            }
            return;
            // If correctGuesses = 1, the player with the corret guess wins the pot. Display how much each player won or lost, and set pot=0.
        } else if (correctGuesses == 1) {
            int b = players[whichPlayer.get(0)].getBalance() + pot;
            players[whichPlayer.get(0)].setBalance(b);
            System.out.println("The outcome is " + outcome + " For this round");
            System.out.println(players[whichPlayer.get(0)].getName() + " won $" + pot + " this round");
            for (int i = 0; i < numPlayers; i++) {
                int exclude = whichPlayer.get(0);
                if (i != exclude) {
                    System.out.println(players[i].getName() + " lost $" + players[i].getBetAmount() + " this round");
                }
            }
            pot = 0;
            //Loops through each of the players checking to see what the highest bet was
        } else {
            // Create two integers to store the highest bet, and the number of highest bets
            int highestBet = 0;
            int numHighest = 0;
            for (int i = 0; i < whichPlayer.size(); i++) {
                if (players[whichPlayer.get(i)].getBetAmount() > highestBet && players[whichPlayer.get(i)].getGuess() == outcome) {
                    highestBet = players[whichPlayer.get(i)].getBetAmount();
                }
            }
            for (int i = 0; i < whichPlayer.size(); i++) {
                if (players[whichPlayer.get(i)].getBetAmount() == highestBet && players[whichPlayer.get(i)].getGuess() == outcome) {
                    numHighest = numHighest + 1;

                }

            }
            // If only one high bet, the player with the correct guess and highest bet wins the pot. Displays how much each player won or lost, sets pot=0.
            if (numHighest == 1) {
                for (int i = 0; i < numPlayers; i++) {
                    if (players[i].getBetAmount() == highestBet && players[i].getGuess() == outcome) {
                        players[i].setBalance(players[i].getBalance() + pot);
                        System.out.println("The outcome is " + outcome + " For this round");
                        System.out.println(players[i].getName() + " won $" + pot);
                    } else {
                        System.out.println(players[i].getName() + " lost $" + players[i].getBetAmount() + " this round");
                    }
                }
                pot = 0;
            }
            // If multiple players have high bets, they evenly split the pot. Displays how much each player won or lost, updates pot to 0.
            if (numHighest > 1) {
                System.out.println("The outcome is " + outcome + " For this round");
                int potSplit = pot / numHighest;
                for (int i = 0; i < numPlayers; i++) {
                    if (players[i].getBetAmount() == highestBet && players[i].getGuess() == outcome) {
                        players[i].setBalance(players[i].getBalance() + potSplit);
                        System.out.println(players[i].getName() + " won " + potSplit + " this round");

                    } else {
                        System.out.println(players[i].getName() + " lost " + players[i].getBetAmount() + " this round");
                    }

                }
                pot = 0;
            }
        }
    }

    /**
     * Method the checks the winner of the round and update the balance of each
     * player, and the pot accordingly. Creates a new ArrayList in order to
     * store which players had correct guesses Int correctGuesses is used to
     * store how many players had correct guesses Runs through loop checking if
     * any players had matches, if so, correctGuesses gets augmented by 1 If
     * correctGuesses = 0, displays that no players won and how much each player
     * lost. The pot remains If correctGuesses = 1, the player with the correct
     * guess wins everything in the pot. Displays how much each player won and
     * lost using a for loop. Pot gets updated to 0 If correctGuesses greater
     * than 1, runs a check to see which player had the highest bet using a for
     * loop Creates highestBet in order to store what the highest bet was in the
     * round Creates a new int numHighest to store how many players had the
     * highest bet If numHighest = 1, then the player with the correct guess and
     * highest bet wins the pot. Displays how much each player won and lost,
     * updates pot to 0. If numHighest greater than 1, then the players with the
     * correct guess, and the highest bets splits the pot evenly. (potSplit used
     * to store how much each player gets) Displays how much each player wins,
     * and sets the pot = 0.
     *
     * @param o1 (int)
     * @param o2 (int)
     */
    public void checkWinner(int o1, int o2) {
        // Checks to see which players had correct guesses and how many players had correct guesses
        ArrayList<Integer> whichPlayer = new ArrayList<Integer>();
        int correctGuesses = 0;
        for (int i = 0; i < numPlayers; i++) {
            if (players[i].getGuess() + players[i].getGuess1() == o1 + o2) {
                if ((players[i].getGuess() == o1 || players[i].getGuess() == o2) && (players[i].getGuess1() == o1 || players[i].getGuess1() == o2)) {
                    correctGuesses = correctGuesses + 1;
                    whichPlayer.add(i);
                }
            }
        }
        // If correctGuesses = 0, loop through each player and display how much they lost using the getBetAmount method. Leave the pot
        if (correctGuesses == 0) {
            System.out.println("The outcome is " + o1 + ", " + o2 + " For this round\nNo one wins");
            for (int i = 0; i < numPlayers; i++) {
                System.out.println(players[i].getName() + " lost $" + players[i].getBetAmount() + " this round");
            }
            return;
            // If correctGuesses = 1, the player with the correct guess wins the pot. Displays how much each player won and lost, sets pot = 0.
        } else if (correctGuesses == 1) {
            int b = players[whichPlayer.get(0)].getBalance() + pot;
            players[whichPlayer.get(0)].setBalance(b);
            System.out.println("The outcome is " + o1 + ", " + o2 + " For this round");
            System.out.println(players[whichPlayer.get(0)].getName() + " won $" + pot + " this round");
            for (int i = 0; i < numPlayers; i++) {
                int exclude = whichPlayer.get(0);
                if (i != exclude) {
                    System.out.println(players[i].getName() + " lost $" + players[i].getBetAmount() + " this round");
                }
            }
            pot = 0;
            // Loops through the players checking to see what the highest bet is
        } else {
            int highestBet = 0;
            int numHighest = 0;
            for (int i = 0; i < whichPlayer.size(); i++) {
                if (players[whichPlayer.get(i)].getBetAmount() > highestBet) {
                    highestBet = players[whichPlayer.get(i)].getBetAmount();

                }
            }
            for (int i = 0; i < whichPlayer.size(); i++) {
                if (players[whichPlayer.get(i)].getBetAmount() == highestBet) {
                    numHighest = numHighest + 1;

                }

            }
            // If only one high bet, the player with the correct guess and highest bet wins the pot. Displays how much each player won or lost, sets pot=0.
            if (numHighest == 1) {
                for (int i = 0; i < numPlayers; i++) {
                    if (players[i].getBetAmount() == highestBet) {
                        if (players[i].getGuess() + players[i].getGuess1() == o1 + o2) {
                            if ((players[i].getGuess() == o1 || players[i].getGuess() == o2) && (players[i].getGuess1() == o1 || players[i].getGuess1() == o2)) {
                                players[i].setBalance(players[i].getBalance() + pot);
                                System.out.println("The outcome is " + o1 + ", " + o2 + " For this round");
                                System.out.println(players[i].getName() + " won $" + pot);
                            }
                        }

                    } else {
                        System.out.println(players[i].getName() + " lost $" + players[i].getBetAmount() + " this round");
                    }
                }
                pot = 0;
            }
            // If multiple players have high bets, they evenly split the pot. Displays how much each player won or lost, updates pot to 0.
            if (numHighest > 1) {
                System.out.println("The outcome is " + o1 + ", " + o2 + " For this round");
                int potSplit = pot / numHighest;
                for (int i = 0; i < numPlayers; i++) {
                    if (players[i].getBetAmount() == highestBet) {
                        if (players[i].getGuess() + players[i].getGuess1() == o1 + o2) {
                            if ((players[i].getGuess() == o1 || players[i].getGuess() == o2) && (players[i].getGuess1() == o1 || players[i].getGuess1() == o2)) {
                                System.out.println(players[i].getName() + " won " + potSplit + " this round");
                                players[i].setBalance(players[i].getBalance() + potSplit);
                            }
                        } else {
                            System.out.println(players[i].getName() + " lost " + players[i].getBetAmount() + " this round");
                        }
                    }
                }

            }
            pot = 0;
        }
    }

}
