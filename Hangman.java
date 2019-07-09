/* 
Drew Vlasnik

This program will simulate a game of hangman. A puzzle category
will be provided and a series of blanks are given to the player
for them to guess letters to solve the puzzle. The player will
be given 7 guesses in order to get the puzzle correct. 
*/

import java.io.*;
import java.util.*;

public class Hangman
{
    public static void main(String[] args) throws IOException
    {
        // Declare variables
        File file_name = new File("puzzles.txt");                   // File that contains puzzle categories and phrases
        Scanner fr = new Scanner(file_name);                        // Reads in puzzles from file
        Scanner sc = new Scanner(System.in);                        // Reads in user input
        Random rd = new Random();                                   // Used to select a random puzzle
        ArrayList<String> puzzleList = new ArrayList<String>();     // Stores list of unformatted puzzles
        String playAgain = "";                                      // Yes or no to play another game
        String playerPhrase;                                        // Final guess for puzzle phrase when player runs out of guesses
        char guess;                                                 // Letter guess from player
        int guessesLeft;                                            // Number of guesses left - default is 7
        int wins = 0, losses = 0;                                   // For a win/loss ratio
        
        // Read in list of puzzles
        while(fr.hasNextLine())
        {
            puzzleList.add(fr.nextLine());
        }

        do
        {
            // Select a random puzzle
            int puzzleSelection = rd.nextInt(puzzleList.size());
            Puzzle puzzle = new Puzzle(puzzleList.get(puzzleSelection));
            // Remove selected puzzle in order to not repeat later
            puzzleList.remove(puzzleSelection);

            // Initialize guesses for this puzzle
            guessesLeft = 7;

            // Display category and puzzle phrase blanks
            System.out.println("Category: " + puzzle.getCategory());
            System.out.println("\n" + puzzle);

            // Player guesses until no guesses left
            while(guessesLeft > 0)
            {
                // Display guesses left
                System.out.println("\nYou have " + guessesLeft + " guesses to solve the puzzle.");
        
                // Get user selection
                System.out.print("Guess a letter: ");
                guess = validate(sc.nextLine().toUpperCase().charAt(0));

                // Check puzzle for guess
                if(puzzle.letterAlreadyGuessed(guess))
                {
                    System.out.println("You already picked that letter.");
                    System.out.println("\n" + puzzle);
                    continue;
                }
                else if(puzzle.hasLetter(guess))
                {
                    puzzle.fillBlanks(guess);
                }
                else
                {
                    System.out.println("Sorry there are no \"" + guess + "\'s\".");
                }

                // Display updated puzzle with guesses
                System.out.println("\n" + puzzle);
                guessesLeft--;
            }

            // Get final solution to puzzle phrase
            System.out.print("\nEnter the correct phrase: ");
            playerPhrase = sc.nextLine().toUpperCase();

            // Check for accuracy
            if(puzzle.isSolution(playerPhrase))
            {
                wins++;
                System.out.println("\nThat is correct!");
            }
            else
            {
                losses++;
                System.out.println("\nSorry, the correct answer was " + puzzle.getPhrase());
            }

            // Confirm there are more puzzles
            if(puzzleList.size() > 0)
            {
                // Ask if player would like to play again
                System.out.print("Would you like to play again? ");
                playAgain = validate(sc.nextLine().toLowerCase());
                System.out.println("\n");
            }
        }
        while(playAgain.equals("yes") && puzzleList.size() > 0);

        // Display win/loss
        System.out.println("You have " + wins + " wins and " + losses + " losses.\n");
    }

    /* Accepts a string
    Validates to make sure it is yes or no */
    public static String validate(String response)
    {
        Scanner sc = new Scanner(System.in);

        while(!response.equals("yes") && !response.equals("no"))
        {
            System.out.print("\n\tPlease respond \"yes\" or \"no\": ");
            response = sc.nextLine().toLowerCase();
        }

        return response;
    }

    /* Accepts a char
    Validates to make sure it is a letter */
    public static char validate(char guess)
    {
        Scanner sc = new Scanner(System.in);

        while(!Character.isAlphabetic(guess))
        {
            System.out.print("\n\tPlease enter a letter: ");
            guess = sc.nextLine().toUpperCase().charAt(0);
        }

        return guess;
    }
}