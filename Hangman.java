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
        Scanner sc = new Scanner(file_name);
        Random rd = new Random();
        ArrayList<String> puzzle_list = new ArrayList<String>();
        String guess = "";
        int guesses_left = 7;
        

        // Read in list of puzzles
        while(sc.hasNextLine())
        {
            puzzle_list.add(sc.nextLine());
        }

        // Select a random puzzle
        int puzzle_selection = 0; //rd.nextInt(puzzle_list.size());
        Puzzle puzzle = new Puzzle(puzzle_list.get(puzzle_selection));
        // Remove selected puzzle in order to not repeat later
        puzzle_list.remove(puzzle_selection);

        System.out.println(puzzle.getCategory());
        System.out.println(puzzle.getPhrase());
        System.out.println(puzzle);
        puzzle.fillBlanks('S');
        puzzle.fillBlanks('T');
        puzzle.fillBlanks('H');
        puzzle.fillBlanks('N');
        puzzle.fillBlanks('R');
        puzzle.fillBlanks('K');
        System.out.println(puzzle);


    }
}
