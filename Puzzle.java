/*
Drew Vlasnik

This class will represent a hangman puzzle
*/

public class Puzzle
{
    // Declare instances
    private String unformated_puzzle;
    private String category;
    private String phrase;
    private String phrase_blanks;

    // Constructor
    public Puzzle(String raw_format)
    {
        unformated_puzzle = raw_format;
        category = formatCategory();
        phrase = formatPhrase();
        generatePhraseBlanks();
    }

    // Format the puzzle category and return it
    public String formatCategory()
    {
        return unformated_puzzle.substring(0, unformated_puzzle.indexOf(":"));
    }

    // Format the puzzle phrase and return it
    public String formatPhrase()
    {
        return unformated_puzzle.substring(unformated_puzzle.indexOf(":") + 1);
    }

    // Generate phrase with blanks to solve
    public void generatePhraseBlanks()
    {
        phrase_blanks = "";

        for(int i=0; i<phrase.length(); i++)
        {
            if(phrase.charAt(i) != ' ')
            {
                phrase_blanks += "_";
            }
            else
            {
                phrase_blanks += " / ";
            }
        }
    }

    // Get the puzzle category
    public String getCategory()
    {
        return category;
    }

    // Get the puzzle phrase
    public String getPhrase()
    {
        return phrase;
    }

    // Returns boolean of whether a letter is in puzzle phrase
    public boolean hasLetter(char guess)
    {
        return true;
    }


    // Print solve status of puzzle phrase 
    public String toString()
    {
        return phrase_blanks;
    }
    
}