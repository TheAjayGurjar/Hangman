import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class HangMan {
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner scanner = new Scanner(new File("words.txt"));
        Scanner keyboard = new Scanner(System.in);


        // checking file is read by java properly
        // while(scanner.hasNext()){
        //     System.out.println(scanner.next());
        // }
        
        // making a list of words to access each word
        List<String> words = new ArrayList<>();
        while(scanner.hasNext()){
            words.add(scanner.next());
        }

        // picking up a random word from words list
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));
        // checking word is a random word from words list
         //System.out.println(word);

         // Checking which mode user wants to play
         System.out.println("Welcome! to the Hang Man Game :");
         System.out.println("Press 1 for single player mode OR Press 2 for Double player mode :");
        int gameMode = keyboard.nextInt();
        System.out.println();
        if(gameMode==1){
            gamePlay(keyboard, word);
        } else if(gameMode==2){
            System.out.println("Player 1:- Enter the word to challange player 2 ( in small letters only ) :");
            word=keyboard.next();
            System.out.println();
            System.out.println("Player 2:- Play the game:");
            System.out.println();
            gamePlay(keyboard, word);
        } else {
            System.out.println("Entered Wrong Value. Try Again!");
        }

        System.out.println();
        System.out.println("Made with by The Ajay Gurjar");
        System.out.println();

    }

    // Game Play method
    public static void gamePlay(Scanner keyboard, String word) throws FileNotFoundException{
        
        // game stats
        int wrongCount = 0;
        int gameLevel = 1;

        // Getting charachter guessed by player
        List<Character> playerGuesses = new ArrayList<>();

        // asking user to guess continously till they guess the right word
        while(true){
            //printing game stats
            System.out.println("Game Level: "+gameLevel);
            System.out.println("Guesses Left: "+(4-wrongCount));
            //printing the hangman
            hangManDraw(wrongCount);
            hangStand(wrongCount);

            // Game Over
            if(wrongCount>=5){
                System.out.println("Oops! Player 2 loose.");
                System.out.println("GAME OVER");
                break;
            }

            // printing current state of the word guessed
            
            printWordState(word, playerGuesses);

            // getting next guess
            if(!getPlayerGuess(keyboard, word, playerGuesses)){
                wrongCount++;
            }else{
                gameLevel++;
            }
            
            //checking if the word is guessed right and won the game
            if(printWordState(word, playerGuesses)){
                System.out.println("Hurray! Player 2 Won!");
                System.out.println("GAME OVER");
                break;
            }
            // giving the user chance to guess whole word
            System.out.println("Please enter the word you are guessing :");
            if(keyboard.next().equals(word)){
                System.out.println("Hurray! Player 2 Won!");
                break;
            }
            else{
                System.out.println("Nope! Try again.");
                System.out.println();
            }

        }
    }

    // method for printig the charachter while guessing process till the player has 
    // guesswd the word correctly
    public static boolean printWordState(String word, List<Character> playerGuesses){
        int correctCount = 0;
        System.out.print("The letters you guessed correct :  ");
        for(int i = 0; i < word.length(); i++){
            if(playerGuesses.contains(word.charAt(i))){
                correctCount++;
                System.out.print(word.charAt(i));
            }
            else{
                System.out.print("-");
            }
        }
        System.out.println();
        return (correctCount==word.length());
    }

    //method for getting player guess
    public static boolean getPlayerGuess( Scanner keyboard, String word, List<Character> playerGuesses){
        System.out.println("Please enter the charachter you are guessing :");
        String letterGuess = keyboard.next();
        playerGuesses.add(letterGuess.charAt(0));

        // printing current state of the word guessed till now
        // printWordState(word, playerGuesses);
        return word.contains(letterGuess);
    }

    //printing the hangman stand
    public static void hangStand(int wrongCount) throws FileNotFoundException{

       Scanner scanner = new Scanner(new File("hangstand.txt"));
       List<String> hangstand = new ArrayList<>();
       while(scanner.hasNext()){
        hangstand.add(scanner.nextLine());
       }
       for(int i = wrongCount+1; i<hangstand.size(); i++){
        System.out.println(hangstand.get(i));
       }
       System.out.println();
    }

    //printing the hangman
    public static void hangManDraw(int wrongCount) throws FileNotFoundException{

        Scanner scanner = new Scanner(new File("hangman.txt"));
        List<String> hangman = new ArrayList<>();
        while(scanner.hasNext()){
         hangman.add(scanner.nextLine());
        }
        for(int i = 0; i<wrongCount+1; i++){
         System.out.println(hangman.get(i));
        }
        
     }
}