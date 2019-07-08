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
    private String guessed_letters;

    // Constructor
    public Puzzle(String raw_format)
    {
        unformated_puzzle = raw_format;
        guessed_letters = "";
        formatCategory();
        formatPhrase();
        generatePhraseBlanks();
    }

    // Set the value of unformatted_puzzle
    public void setUnformatedPuzzle(String raw_format)
    {
        unformated_puzzle = raw_format;
    }

    // Get the unformated puzzle
    public String getUnformatedPuzzle()
    {
        return unformated_puzzle;
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

    // Get the phrase blanks
    public String getPhraseBlanks()
    {
        return phrase_blanks;
    }

    // Get the guessed letters
    public String getGuessedLetters()
    {
        return guessed_letters;
    }

    // Format the puzzle category and return it
    public void formatCategory()
    {
        category = unformated_puzzle.substring(0, unformated_puzzle.indexOf(":"));
    }

    // Format the puzzle phrase and return it
    public void formatPhrase()
    {
        phrase = unformated_puzzle.substring(unformated_puzzle.indexOf(":") + 1);
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

    // Checks whether letter has been guessed already
    public boolean letterAlreadyGuessed(char guess)
    {
        if(guessed_letters.indexOf(guess) != -1)
        {
            return true;
        }
        else
        {
            guessed_letters += guess;
            return false;
        }
    }

    // Checks whether letter is in puzzle phrase
    public boolean hasLetter(char guess)
    {
        return phrase.indexOf(guess) != -1;
    }

    // Fills the blanks of guessed letters in the puzzle phrase
    public void fillBlanks(char guess)
    {
        int space_count = 0;

        for(int i=0; i<phrase.length(); i++)
        {
            // Keep track of spaces because they consist of " / " 
            // in phrase_blanks instead of just a single space
            if(phrase.charAt(i) == ' ')
            {
                space_count++;
            }
            else if(guess == phrase.charAt(i))
            {
               phrase_blanks = (
                   phrase_blanks.substring(0, i + space_count * 2)
                   + guess
                   + phrase_blanks.substring(i + space_count * 2 + 1)
               );
            }
        }
    }

    // Checks whether a phrase is a solution to the puzzle
    public boolean isSolution(String player_phrase)
    {
        return player_phrase.equals(phrase);
    }

    // Print puzzle phrase with guessed letters filled in
    public String toString()
    {
        return phrase_blanks;
    }
    
}