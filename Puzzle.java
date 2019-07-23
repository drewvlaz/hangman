/*
Drew Vlasnik

This class will represent a hangman puzzle
*/

public class Puzzle
{
    // Declare instances
    private String unformatedPuzzle;        // String read in from file
    private String category;                // Puzzle category
    private String phrase;                  // Puzzle user tries to solve
    private String phraseBlanks;            // String of puzzle skeleton
    private String guessedLetters;          // Keeps track of letters guessed
    private boolean puzzleSolved;           // State of puzzle being solved

    // Constructor
    public Puzzle(String rawFormat)
    {
        unformatedPuzzle = rawFormat;
        guessedLetters = "";
        puzzleSolved = false;
        formatCategory();
        formatPhrase();
        generatePhraseBlanks();
    }

    // Set the value of unformattedPuzzle
    public void setUnformatedPuzzle(String rawFormat)
    {
        unformatedPuzzle = rawFormat;
        guessedLetters = "";
        puzzleSolved = false;
        formatCategory();
        formatPhrase();
        generatePhraseBlanks();
    }

    // Get the unformated puzzle
    public String getUnformatedPuzzle()
    {
        return unformatedPuzzle;
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
        return phraseBlanks;
    }

    // Get the guessed letters
    public String getGuessedLetters()
    {
        return guessedLetters;
    }

    // Get whether puzzle was solved
    public boolean getPuzzleSolved()
    {
        return puzzleSolved;
    }

    // Format the puzzle category
    public void formatCategory()
    {
        category = unformatedPuzzle.substring(0, unformatedPuzzle.indexOf(":"));
    }

    // Format the puzzle phrase
    public void formatPhrase()
    {
        phrase = unformatedPuzzle.substring(unformatedPuzzle.indexOf(":") + 1);
    }

    // Generate phrase with blanks to solve
    public void generatePhraseBlanks()
    {
        phraseBlanks = "";

        for(int i=0; i<phrase.length(); i++)
        {
            if(phrase.charAt(i) != ' ')
            {
                phraseBlanks += "_";
            }
            else
            {
                phraseBlanks += " / ";
            }
        }
    }

    // Checks whether letter has been guessed already
    public boolean letterAlreadyGuessed(char guess)
    {
        if(guessedLetters.indexOf(guess) != -1)
        {
            return true;
        }
        else
        {
            guessedLetters += guess;
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
        int spaceCount = 0;

        for(int i=0; i<phrase.length(); i++)
        {
            // Keep track of spaces because they consist of " / " 
            // in phraseBlanks instead of just a single space
            if(phrase.charAt(i) == ' ')
            {
                spaceCount++;
            }
            else if(guess == phrase.charAt(i))
            {
               phraseBlanks = (
                   phraseBlanks.substring(0, i + spaceCount * 2)
                   + guess
                   + phraseBlanks.substring(i + spaceCount * 2 + 1)
               );
            }
        }
    }

    // Checks whether a phrase is a solution to the puzzle
    public boolean isSolution(String playerPhrase)
    {
        return playerPhrase.equals(phrase);
    }

    // Checks whether the puzzle is solved yet
    public boolean isSolved()
    {
        // If all blanks have been guess
        if(phraseBlanks.indexOf("_") == -1)
        {
            puzzleSolved = true;
            return true; 
        }
        else
        {
            return false;
        }
    }

    // Print puzzle phrase with guessed letters filled in
    public String toString()
    {
        return phraseBlanks;
    }
    
}