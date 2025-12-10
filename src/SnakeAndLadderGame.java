import java.util.*;
class Player {
    String name;
    int position;
    Player(String playerName) {
        name = playerName;
        position = 0;
    }
}
class SnakeAndLadder {
    static int WINNING_POSITION = 100;
    static int START_POSITION = 0;
    // UC 2: Roll die to get number between 1 to 6
    int rollDie() {
        return (int)(Math.random() * 6) + 1;
    }
    // UC 3: Check for option - No Play, Ladder, or Snake
    int checkOption() {
        return (int)(Math.random() * 3);
    }
    // UC 4, UC 5, UC 6: Play game for single player
    void playGame(Player player) {
        int diceCount = 0;
        System.out.println("\n" + player.name + " starts playing...");
        // UC 4: Repeat till player reaches 100
        while(player.position < WINNING_POSITION) {
            int dieNumber = rollDie();
            diceCount++;
            int option = checkOption();
            int previousPosition = player.position;
            switch(option) {
                case 0:
                    // No Play - stay at same position
                    System.out.println("Dice " + diceCount + ": Rolled " + dieNumber + " - No Play");
                    break;
                case 1:
                    // Ladder - move ahead
                    player.position += dieNumber;
                    // UC 5: Ensure exact winning position
                    if(player.position > WINNING_POSITION) {
                        player.position = previousPosition;
                        System.out.println("Dice " + diceCount + ": Rolled " + dieNumber + " - Ladder (exceeds 100, stay at " + player.position + ")");
                    } else {
                        System.out.println("Dice " + diceCount + ": Rolled " + dieNumber + " - Ladder (move from " + previousPosition + " to " + player.position + ")");
                    }
                    break;
                case 2:
                    // Snake - move behind
                    player.position -= dieNumber;
                    // UC 4: If position goes below 0, restart from 0
                    if(player.position < START_POSITION) {
                        player.position = START_POSITION;
                    }
                    System.out.println("Dice " + diceCount + ": Rolled " + dieNumber + " - Snake (move from " + previousPosition + " to " + player.position + ")");
                    break;
            }
            // UC 6: Report position after every die roll
            System.out.println("Current Position: " + player.position);
        }
        // UC 6: Report number of times dice was played
        System.out.println("\n" + player.name + " won the game!");
        System.out.println("Total dice rolls: " + diceCount);
    }
    // UC 7: Play game with 2 players
    void playGameWithTwoPlayers(Player player1, Player player2) {
        int diceCount1 = 0;
        int diceCount2 = 0;
        boolean player1Turn = true;
        System.out.println("\nGame starts with 2 players!");
        while(player1.position < WINNING_POSITION && player2.position < WINNING_POSITION) {
            Player currentPlayer = player1Turn ? player1 : player2;
            int dieNumber = rollDie();
            if(player1Turn) {
                diceCount1++;
            } else {
                diceCount2++;
            }
            int option = checkOption();
            int previousPosition = currentPlayer.position;
            boolean playAgain = false;
            System.out.println("\n" + currentPlayer.name + "'s turn:");
            switch(option) {
                case 0:
                    // No Play
                    System.out.println("Rolled " + dieNumber + " - No Play");
                    break;
                case 1:
                    // Ladder - move ahead and play again
                    currentPlayer.position += dieNumber;
                    if(currentPlayer.position > WINNING_POSITION) {
                        currentPlayer.position = previousPosition;
                        System.out.println("Rolled " + dieNumber + " - Ladder (exceeds 100, stay at " + currentPlayer.position + ")");
                    } else {
                        System.out.println("Rolled " + dieNumber + " - Ladder (move from " + previousPosition + " to " + currentPlayer.position + ")");
                        // UC 7: Player gets ladder, plays again
                        if(currentPlayer.position < WINNING_POSITION) {
                            playAgain = true;
                            System.out.println(currentPlayer.name + " gets another turn!");
                        }
                    }
                    break;
                case 2:
                    // Snake - move behind
                    currentPlayer.position -= dieNumber;

                    if(currentPlayer.position < START_POSITION) {
                        currentPlayer.position = START_POSITION;
                    }
                    System.out.println("Rolled " + dieNumber + " - Snake (move from " + previousPosition + " to " + currentPlayer.position + ")");
                    break;
            }
            System.out.println(currentPlayer.name + " Position: " + currentPlayer.position);
            // Switch turn only if not playing again
            if(!playAgain) {
                player1Turn = !player1Turn;
            }
        }
        // UC 7: Report which player won
        if(player1.position == WINNING_POSITION) {
            System.out.println("\n" + player1.name + " won the game!");
            System.out.println("Total dice rolls by " + player1.name + ": " + diceCount1);
        } else {
            System.out.println("\n" + player2.name + " won the game!");
            System.out.println("Total dice rolls by " + player2.name + ": " + diceCount2);
        }
    }
}
public class SnakeAndLadderGame {
    public static void main(String[] args) {
        System.out.println("Welcome to Snake and Ladder Game");
        Scanner sc = new Scanner(System.in);
        SnakeAndLadder game = new SnakeAndLadder();
        System.out.println("\nSelect Game Mode:");
        System.out.println("1. Single Player");
        System.out.println("2. Two Players");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        if(choice == 1) {
            // Single player game
            System.out.print("Enter player name: ");
            String name = sc.nextLine();
            Player player = new Player(name);
            game.playGame(player);
        } else if(choice == 2) {
            // Two player game
            System.out.print("Enter Player 1 name: ");
            String name1 = sc.nextLine();
            System.out.print("Enter Player 2 name: ");
            String name2 = sc.nextLine();
            Player player1 = new Player(name1);
            Player player2 = new Player(name2);
            game.playGameWithTwoPlayers(player1, player2);
        }
        sc.close();
    }
}