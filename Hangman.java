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
        File file_name = new File("puzzles.txt");
        Scanner fr = new Scanner(file_name);
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        ArrayList<String> puzzleList = new ArrayList<String>();
        String playerPhrase;
        String playAgain;
        char guess;
        int guessesLeft;
        int wins = 0, losses = 0;
        
        // Read in list of puzzles
        while(fr.hasNextLine())
        {
            puzzleList.add(fr.nextLine());
        }

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
            guess = sc.nextLine().toUpperCase().charAt(0);

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

            // Display puzzle with updated blanks as player guesses
            System.out.println("\n" + puzzle);
            guessesLeft--;
        }

        // Spacing
        System.out.println("\n");

        // Get final solution to puzzle phrase
        System.out.print("Enter the correct phrase: ");
        playerPhrase = sc.nextLine().toUpperCase();

        // Check for accuracy
        if(puzzle.isSolution(playerPhrase))
        {
            wins++;
            System.out.println(
                "That is correct! You have " 
                + wins + " wins and " 
                + losses + " losses."
            );
        }
        else
        {
            losses++;
            System.out.println(
                "Sorry, the correct answer was " + puzzle.getPhrase()
                + "\nYou have " + wins + " wins and "
                + losses + " losses."
            );
        }



    }
}
