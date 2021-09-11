/*
Cameron Cross
 */
package dicegame;

/**
 *
 * @author 19194
 */
public class Player {

    private String name;
    private int balance;
    private int betAmount;
    private int guess;
    private int guess1;
    public int INITIAL_BALANCE = 500;

    /**
     * Constructor that takes String name as parameter and sets = name.
     *
     * @param name
     */
    public Player(String name) {
        this.name = name;
        balance = INITIAL_BALANCE;
    }

    /**
     * Method that returns the name of a given player.
     *
     * @return name(String)
     */
    public String getName() {
        return name;
    }

    /**
     * Method that returns the balance of a given player.
     *
     * @return balance (int)
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Method that returns the bet amount of a given player.
     *
     * @return betAmount (int)
     */
    public int getBetAmount() {
        return betAmount;
    }

    /**
     * Method that returns the guess of a given player
     *
     * @return guess (int)
     */
    public int getGuess() {
        return guess;
    }

    /**
     * Method that returns the second guess of a given player.
     *
     * @return guess1 (int)
     */
    public int getGuess1() {
        return guess1;
    }

    /**
     * Method that sets the balance of a given player.
     *
     * @param balance (int)
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * Method that sets the betAmount of a given player.
     *
     * @param betAmount (int)
     */
    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    /**
     * Method that sets the guess of a given player.
     *
     * @param guess (int)
     */
    public void setGuess(int guess) {
        this.guess = guess;
    }

    /**
     * Method that sets the second guess of a given player.
     *
     * @param guess1 (int)
     */
    public void setGuess1(int guess1) {
        this.guess1 = guess1;
    }

}
