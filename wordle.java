// wordle.java
// Richard Morsching

/*
    Logic:
    1. check to see if word file exists. If not, cancel program and print error.
       a. if it does, bring overrandom words into an array
    2. choose a random word from file and store it in a variable 'word'.
    3. create an empty string var 'guess' for user in.
    4. create a loop where:
        a. user has six guesses
        b. user inputs a guess 'guess'
        c. if should be grey, make the letter "X"
        d. if should be yellow, make the letter "#"
        e. if should be green, make the letter "O"
        f. print all guessed words and correctess, EX. 
            WORDS     XX#XO
        g. if guess is correct, end loop and print something
    5. if all guesses are used and user did not guess right, print the right word before ending.
*/

import java.io.*;
import java.util.*;



public class wordle{

    // 1.
    static void loadWords(){
        try(BufferedReader br = new BufferedReader(new FileReader("words.txt"))){
            String line;
            while((line=br.readLine())!=null){
                String thisWord = line.trim();
                // 1a.
                wordList.add(thisWord);
                // adds each line from words.txt to a separate allowed word bank
            }
        }catch (IOException e){
            System.out.println("No existing file found. Canceling.");
            System.exit(0);
            // in case of removal of words.txt
        }
    }

    // required variables for 1. 2. and 3.
    public static String word;
    public static String guess;
    public static ArrayList<String> wordList = new ArrayList<>();
    public static ArrayList<String> guesses = new ArrayList<>();
    // self-explanatory, adds variables to handle the answer and current guess as well as arrayLists to handle possible words and all made guesses
    
    static void printGuesses(){
        for(int i=0; i<guesses.size(); i++){
            if(guesses.get(i)!=null){
                System.out.println();
                System.out.print(guesses.get(i)+"     "+getCorrectness(guesses.get(i), i));
                // if there is a guess in the list of guesses at point i, it prints the word and its "correctness",
            }else
                break;
            // and if there are no more guesses, it breaks
        }
    }// printGuesses

    static String getCorrectness(String guess, int guessNum){
        String correctness = "";
        char[][] board = new char[6][5];
        char[][] results = new char[6][5];
        char[] guessChars = guess.toCharArray();
        char[] wordChars = word.toCharArray();
        boolean[] usedInWord = new boolean[5];
        boolean[] usedInGuess = new boolean[5];
        // adds some fun arrays. String correctness is only here for the output,
        // board is a 2D array which shows each guessed word per-row
        // results is where the "correctness" character (X, #, O) is stored on the same row as board
        // guessChars is an array which contains in each value one character of the current guess
        // wordChars is an array which stores one character of the answer per value
        // usedInWord stores a bool value for each character of the guess to see if it has been used in the answer
        // usedInGuess stores a bool value for each character of the guess to see if it has been used yet

        // store guesses in board
        for(int i=0;i<5;i++){
            board[guessNum][i] = guessChars[i];
            results[guessNum][i] = 'X';
            // populates the board with the proper characters for the given row
            // populates the results array with default 'X' character, as if not # || O, must be X
        }// storage

        // green check
        for(int i=0;i<5;i++)
            if(guessChars[i]==wordChars[i]){
                results[guessNum][i] = 'O';
                usedInWord[i] = true;
                usedInGuess[i] = true;
                // pretty simple, if the char in pos i is used in the answer, results and both usedIn funcs updated accordingly
            }
        // end green check

        // yellow check
        for(int i=0; i<5; i++){
            if(usedInGuess[i])
                continue;
            // if the letter is already green, skip it
            for(int j=0; j<5; j++){
                if(!usedInWord[j]&&(guessChars[i]==wordChars[j])){
                    results[guessNum][i] = '#';
                    usedInWord[j] = true;
                    break;
                    // less simple, if the letter is both not used already AND guessChar of i is somewhere located in the answer,
                    // results are updated and usedInWord ONLY is updated to avoid dupe '#' :
                    // I.E., if word="drive" & guess="added", correctness="X#X#X"
                }
            }
        }// yellow check

        correctness = String.valueOf(results[guessNum]);
        // used .valueOf to avoid a really annoying addition problem here
        return correctness;
    }// getCorrectness

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        guesses = new ArrayList<>();
        // creates a buffered reader and re-initializes "guesses". Was unnecessary but doesn't hurt
        // 2.
        loadWords();
        word = wordList.get((int)(Math.random()*wordList.size()));
        // grabs word at random value of wordList

        System.out.println("Welcome to Word Game!");
        System.out.println("Guess a five-letter word. When a letter of the guess is:");
        System.out.println("1. not in the actual word, it will be marked with an X");
        System.out.println("2. In the word, but not that spot, it will be marked with a #");
        System.out.println("3. In the correct spot, it will be marked with an O");
        // would be too long for "\n"s

        // 4.
        for(int i=0; i<6; i++){
            printGuesses();
            System.out.println();
            System.out.println();
            System.out.print("Guess "+(i+1)+": ");
            guess = br.readLine().toLowerCase().trim();
            // forgot to make chars lowercase in init. commit but remembered to trim extra spaces...
            if(guess.length()!=5){
                System.out.println("Invalid length. Make sure it's five letters!");
                i--;
                continue;
                // ensures that guesses are within regulation size. Now that I think about it this is unneccesary since
                // nothing in the word bank is longer than five letters, but it's a more specific error so it's fine
            }else if(!wordList.contains(guess)){
                System.out.println("Not a valid word. Try again.");
                i--;
                continue;
                // aforementioned word bank check. A bit more restrictive but I don't know how the real Wordle generates words
                // which people commonly know everyday and this lets me avoid having to figure out their whole algorithm lol
            }else{
                guesses.add(guess);
                if(guess.equals(word)){
                    if(i==0)
                        System.out.println("Genius!");
                    else if(i==1)
                        System.out.println("Magnificent!");
                    else if(i==2)
                        System.out.println("Impressive!");
                    else if(i==3)
                        System.out.println("Splendid!");
                    else if(i==4)
                        System.out.println("Great!");
                    else
                        System.out.println("Phew!");
                    printGuesses();
                    System.out.println();
                    // if guess is correct, spit out a proper message. Used the actual Wordle responses for this
                }// correct guess
                if(i==5){
                    System.out.println("Better luck next time! The word was "+word);
                    printGuesses();
                    System.out.println();
                    // if they didn't end up getting the right answer, tell them what it was
                }
            }// valid guess
        }// guessing loop
    }// main
}// class
