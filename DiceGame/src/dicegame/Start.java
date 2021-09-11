/*
Cameron Cross
 */
package dicegame;

/**
 *
 * @author 19194
 */
public class Start {

    public static void main(String[] args) {

        System.out.println("Welcome to the Dice Game!");
        DiceGame dg = new DiceGame();
        int round = 1;
        while (!dg.isGameOver()) {
            System.out.println("***********************");
            System.out.println("      ROUND " + round);
            System.out.println("***********************");
            System.out.println();
            dg.playGame();
            round++;
        }
        System.out.println("Game is over. Everyone's balance is as below: ");
        for (int i = 0; i < dg.getNumPlayers(); i++) {
            System.out.println(dg.getPlayers()[i].getName() + ": $" + dg.getPlayers()[i].getBalance());

        }

    }
}
