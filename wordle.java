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
            }
        }catch (IOException e){
            System.out.println("No existing file found. Canceling.");
            System.exit(0);
        }
    }

    // required variables for 1. 2. and 3.
    public static String word;
    public static String guess;
    public static ArrayList<String> wordList = new ArrayList<>();
    public static ArrayList<String> guesses = new ArrayList<>();

    static void printGuesses(){
        for(int i=0; i<guesses.size(); i++){
            if(guesses.get(i)!=null){
                System.out.println();
                System.out.print(guesses.get(i)+"     "+getCorrectness(guesses.get(i), i));
            }else
                break;
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

        // store guesses in board
        for(int i=0;i<5;i++){
            board[guessNum][i] = guessChars[i];
            results[guessNum][i] = 'X';
        }// storage

        // green check
        for(int i=0;i<5;i++)
            if(guessChars[i]==wordChars[i]){
                results[guessNum][i] = 'O';
                usedInWord[i] = true;
                usedInGuess[i] = true;
            }
        // end green check

        // yellow check
        for(int i=0; i<5; i++){
            if(usedInGuess[i])
                continue;
            for(int j=0; j<5; j++){
                if(!usedInWord[j]&&(guessChars[i]==wordChars[j])){
                    results[guessNum][i] = '#';
                    usedInWord[j] = true;
                    break;
                }
            }
        }// yellow check

        correctness = String.valueOf(results[guessNum]);
        return correctness;
    }// getCorrectness

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        guesses = new ArrayList<>();
        // 2.
        loadWords();
        word = wordList.get((int)(Math.random()*wordList.size()));

        System.out.println("Welcome to Word Game!");
        System.out.println("Guess a five-letter word. When a letter of the guess is:");
        System.out.println("1. not in the actual word, it will be marked with an X");
        System.out.println("2. In the word, but not that spot, it will be marked with a #");
        System.out.println("3. In the correct spot, it will be marked with an O");

        // 4.
        for(int i=0; i<6; i++){
            printGuesses();
            System.out.println();
            System.out.println();
            System.out.print("Guess "+(i+1)+": ");
            guess = br.readLine().trim();
            if(guess.length()!=5){
                System.out.println("Invalid length. Make sure it's five letters!");
                i--;
                continue;
            }else if(!wordList.contains(guess)){
                System.out.println("Not a valid word. Try again.");
                i--;
                continue;
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
                }// correct guess
                if(i==5){
                    System.out.println("Better luck next time! The word was "+word);
                    printGuesses();
                    System.out.println();
                }
            }// valid guess
        }// guessing loop
    }// main
}// class