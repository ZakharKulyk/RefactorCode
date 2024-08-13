package game;

import java.util.Scanner;

public class Game {
    Scanner scan = new Scanner(System.in);
    byte input;
    byte rand;

    boolean boxAvailable = false;
    byte winner = 0;
    char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public void startGame() {
        System.out.println("Enter box number to select. Enjoy!\n");

        boolean boxEmpty = false;
        while (true) {
            printBoard();
            if (!boxEmpty) {
                cleanBoard();
                boxEmpty = true;
            }

            if (winner == 1) {
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            } else if (winner == 2) {
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            } else if (winner == 3) {
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            }

            askForMove();

            if (checkIfSomeBodyWon('X')) {
                winner = 1;
                continue;
            }

            boxAvailable = false;

            checkIfStillFreePlaces();

            if (!boxAvailable) {
                winner = 3;
                continue;
            }

            calculateNexBotMove();

            if (checkIfSomeBodyWon('O')) {
                winner = 2;
            }
        }
    }

    public void printBoard() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
    }

    public void cleanBoard() {
        for (byte i = 0; i < 9; i++) {
            box[i] = ' ';
        }
    }

    public void askForMove() {
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    System.out.println("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else
                System.out.println("Invalid input. Enter again.");
        }
    }


    public void checkIfStillFreePlaces() {
        for (byte i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                boxAvailable = true;
                break;
            }
        }
    }

    public void calculateNexBotMove() {
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    public boolean checkIfSomeBodyWon(char sign) {
        int[][] winningCondition = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {8, 4, 0}
        };
        for (int[] numbers : winningCondition) {
            if (box[numbers[0]] == sign && box[numbers[1]] == sign && box[numbers[2]] == sign) {
                return true;
            }
        }
        return false;
    }

}
