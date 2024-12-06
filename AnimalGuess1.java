import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class AnimalGuess1 {
    /** create two sets containing possible yes and no response **/
    public static Set<String> yesSet = new HashSet<String>(Arrays.asList("yes", "true", "y"));
    public static HashSet<String> noSet = new HashSet<String>(Arrays.asList("no", "n", "false"));

    /**
     * Takes an input from the user and converts it to a response
     * @param input
     * @return response
     */
    public static String readLine(String input){
        Scanner scan = new Scanner(input);
        HashSet<String> set = new HashSet<String>(); //for multiple responses
        String response = null;
        while (scan.hasNext()){
            String store = scan.next();
            set.add(store);
            response = store;
        }
        //scan.nextLine();
        return response;   
    }

    /**
     * Takes in a response and determines if it's valid
     * @param response
     * @return true or false
     */
    public static Boolean validResponse(String response){
        response = response.toLowerCase();
        return (yesSet.contains(response) || noSet.contains(response));
    }

    public static String nullResponse(Boolean state){
        Scanner input = new Scanner(System.in);
        String response = "";
        while (state == null){
            System.out.println("You must enter a valid response. It is either yes or no!");
            response = input.nextLine();
            state = askForClues(response);
        }
        return response;
        
    }

    /** gives the user a prompt to answer
     * pass in the input from the user and return true or false */
    /**
     * Checks a user's input and returns true if it's yes-type response
     * returns false if it's a no-type response
     * returns null otherwise
     * @param input
     * @return true, false, or null
     */
    public static Boolean askForClues(String input){
        input = input.toLowerCase();
        if (validResponse(input)){
            if (yesSet.contains(input)){return true;}
            else if (noSet.contains(input)){return false;}
            else{return null;}
        }
        else{
            return null;
        }
    }

    public static void helpMeLearn(){
        
    }

    public static void main(String[] args) {
        /** condition determines whether or not to continue with the game */
        Boolean continueCondition = true;
        
        /** main outer loop-- runs the guessing game again */
        while (continueCondition){
            /** sample decision trees */
            DecisionTree root = new DecisionTree("Is it a mammal?");
            DecisionTree left = new DecisionTree("Does it have hooves?");
            DecisionTree leftLeft = new DecisionTree("Cow");
            DecisionTree leftRight = new DecisionTree("Rabbit");
            DecisionTree right = new DecisionTree("Is it a reptile?");
            DecisionTree rightRight = new DecisionTree("mosquito");
            DecisionTree rightLeft = new DecisionTree("Crocodile");
            left.setLeft(leftLeft);
            left.setRight(leftRight);
            right.setLeft(rightLeft);
            right.setRight(rightRight);
            root.setLeft(left);
            root.setRight(right);
            DecisionTree currentPosition = null; //keeps track of position in tree during traversal

            /** initialize scanner to read in user's input */
            Scanner input = new Scanner(System.in);

            /** requests the user to start the game */
            System.out.println("Do you want to play a guessing game with me?");
            String response = input.nextLine();
            if (askForClues(response) == null){response = nullResponse(askForClues(response));} //handles invalid (no-yes, no-no responses)
            if (askForClues(response) == false){ //exits if user no longer wants to continue with game
                System.out.println("Ok, exiting at your command. Run the program again if you decide to play");
                System.exit(0);
            }
            //if the response is neither null or false, then it's true, continue game

            /** Game intro */
            // System.out.println("Great! Let's get started. Enter your name (should be one word)");
            // String userName = input.nextLine();
            System.out.println("Think of an animal. I'll try to guess it🙂");
            System.out.println("I'll ask you a series of questions to guide me towards the answer");
            System.out.println("Let's goooo!");

            /** inner condition  */
            Boolean done = false;
            currentPosition = root; //sets the position tracker to the root of the tree
            /** runs until a full round of the game is completed */
            while (!(done)){
                /** Game logic */
                /** start at the root */
                //have special condition 
                if (currentPosition.getLeft() == null && currentPosition.getRight() == null){
                    System.out.println("Guess: " + currentPosition.getData());
                    done = true;
                    break;
                }
                System.out.println(currentPosition.getData());
                response = input.nextLine(); 
                if (askForClues(response) == null){response = nullResponse(askForClues(response));} //handles an invalid response
                if (askForClues(response) == true){
                    currentPosition = currentPosition.getLeft();
                }
                else if (askForClues(response) == false){
                    currentPosition = currentPosition.getRight();
                }
            }

            /** implement logic for helpMelearn(), hurray, and do you want to try again */
            System.out.println("Is my guess correct?"); 
            response = input.nextLine();
            if (askForClues(response) == null){response = nullResponse(askForClues(response));} //handles invalid response
            if (askForClues(response) == true){
                System.out.println("Hurrayyy!!\nRestarting.........."); 
                System.out.println("Would you like to play again?");
                response = input.nextLine();
                if (askForClues(response) == null){response = nullResponse(askForClues(response));} //handles invalid response
                continueCondition = askForClues(response);
            }
            else if (askForClues(response) == false){
                System.out.println("I got it wrong.\nPlease help me learn.");
                System.out.println("What was your animal?");
                String correctAnimal = input.nextLine();
                System.out.println("Type a YES or NO question that would distinguish " + correctAnimal + " from a " + currentPosition.getData());
                String newQuestion = input.nextLine();
                System.out.println("Would you answer yes to this question for a " + correctAnimal + "?");
                String preferredResponse = input.nextLine();
                System.out.println("Thanks for helping me learn\nWould you like to play again?");
                response = input.nextLine();
                if (askForClues(response) == null){response = nullResponse(askForClues(response));} //handles invalid response
                continueCondition = askForClues(response);
            }
            DecisionTree.writeIn(root, "test.txt");
        }
    }
}
