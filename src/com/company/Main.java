package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Create a board
        final int len = 3;
        char[][] enter = new char[len][len];

        for (char[] chars : enter) {
            Arrays.fill(chars, ' ');
        }
        //print a board
        printBoard(enter);
        movePlayer(enter);
    }

    // move the player around the board
    private static void movePlayer (char[][] enter) {

        Scanner scanner = new Scanner(System.in);

        boolean flag = true;

        int x = 0;
        int y = 0;

        while (true) {
            System.out.print("Enter the coordinates: ");


            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
                y = scanner.nextInt();
            } else if (!scanner.hasNextInt()){
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }
            if (x < 1 || x > 3 || y < 1 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (enter[x - 1][y - 1] == 'X' || enter[x - 1][y - 1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            if(flag) {
                enter[x - 1][y - 1] = 'X';
                flag = false;
                printBoard(enter);
            } else {
                enter[x - 1][y - 1] = 'O';
                flag = true;
                printBoard(enter);
            }

            if (getState(enter).equals("X wins") || getState(enter).equals("O wins")) {
                System.out.println(getState(enter));
                break;
            } else if (isGameFinished(enter)) {
                System.out.println(getState(enter));
                break;
            }
        }
    }

    // print filled board
    private static void printBoard(char[][] enter) {
        System.out.println("---------");

        for (char[] chars : enter) {
            System.out.print("| ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println("|");
        }

        System.out.println("---------");
    }

    // get game state
    private static String getState(char[][] enter) {
        boolean xWins = isTheWinner(enter, 'X');
        boolean oWins = isTheWinner(enter, 'O');

        if (xWins && oWins || isImpossible(enter)) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (!isGameFinished(enter)) {
            return "Game not finished";
        } else {
            return "Draw";
        }

    }
    // Game finished or not
    private static boolean isGameFinished(char[][] enter) {
        for (char[] chars : enter) {
            for (char aChar : chars) {
                if(aChar == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    //Determine the winner
    private static boolean isTheWinner(char[][] enter, char symbol) {
        return (enter[0][0] == symbol && enter[0][1] == symbol && enter[0][2] == symbol) ||
                (enter[1][0] == symbol && enter[1][1] == symbol && enter[1][2] == symbol) ||
                (enter[2][0] == symbol && enter[2][1] == symbol && enter[2][2] == symbol) ||
                (enter[0][0] == symbol && enter[1][0] == symbol && enter[2][0] == symbol) ||
                (enter[0][1] == symbol && enter[1][1] == symbol && enter[2][1] == symbol) ||
                (enter[0][2] == symbol && enter[1][2] == symbol && enter[2][2] == symbol) ||
                (enter[0][0] == symbol && enter[1][1] == symbol && enter[2][2] == symbol) ||
                (enter[0][2] == symbol && enter[1][1] == symbol && enter[2][0] == symbol);
    }
    //Testing the impossible
    private static boolean isImpossible(char[][] enter) {
        int xCount = 0;
        int oCount = 0;

        for (char[] chars : enter) {
            for (char aChar : chars) {
                if (aChar == 'X') {
                    xCount++;
                }
                if (aChar == 'O') {
                    oCount++;
                }
            }
        }

        return Math.abs(xCount - oCount) > 1;
    }
}
