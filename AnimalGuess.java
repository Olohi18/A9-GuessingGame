import java.util.Scanner;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AnimalGuess{
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
     * Takes ain aresponse and determines if it's valid
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

        /** main loop-- loops while the condition to continue is true */
        while (continueCondition){
            //sample decision trees
            DecisionTree root = new DecisionTree("Is it a mammal?");
            DecisionTree left = new DecisionTree("Is it a cow");
            DecisionTree right = new DecisionTree("Is it a crocodile");
            root.setLeft(left);
            root.setRight(right);
            DecisionTree currentPosition = null;

            //initialize scanner to read user's input
            Scanner input = new Scanner(System.in);

            /** requests the user to start the game */
            System.out.println("Do you want to play a guessing game with me?");
            String response = input.nextLine();
            if (askForClues(response) == null){response = nullResponse(askForClues(response)); //handles invalid (no-yes, no-no responses)
            }if (askForClues(response) == false){ //exits if user no longer wants to continue with game
                System.out.println("Ok, exiting at your command. Run the program again if you decide to play");
                System.exit(0);
            }
            //if the response is neither null or false, then it's true, continue game

            /** Game intro */
            // System.out.println("Great! Let's get started. Enter your name (should be one word)");
            // String userName = input.nextLine();
            System.out.println("Think of an animal. I'll try to guess it🙂");
            System.out.println("I'll ask you a series of questions to guide me towards the answer");
            System.out.println("Enter any value to the command line when you've thought of an animal");
            response = input.nextLine();

            /** Game starts */
            currentPosition = root; //sets the start of the clues to the root of the clue tree
            System.out.println(currentPosition.getData()); //prints the question at the root
            response = input.nextLine(); //solicits a response from the user

            /** handles invalid response */
            if (askForClues(response) == null){response = nullResponse(askForClues(response));}

            /** while the response is a yes, move to the left node */
            while (askForClues(response) == true){
                /** if there's a left node, move to it and ask the question there */
                if (currentPosition.getLeft() != null){
                    System.out.println(currentPosition.getData() + " has left");//debugging aside--- delete this
                    currentPosition = root.getLeft();
                    System.out.println(currentPosition.getData());
                    response = input.nextLine();
                    /** handles invalid response */
                    if (askForClues(response) == null){response = nullResponse(askForClues(response));}
                }
                /** if there's no node, you are at a guess */
                else{
                    //implement code to add new question to file or shout hurray
                    if (askForClues(response) == null){response = nullResponse(askForClues(response));}/** handles invalid response */
                    /** Guess is correct */
                    if (askForClues(response) == true){
                        System.out.println("Hurray!!! Would you like to play again? ");
                        response = input.nextLine();
                        /** handles invalid response */   
                        if (askForClues(response) == null){response = nullResponse(askForClues(response));}                   
                        /** restart the game */
                        if (askForClues(response) == true ){
                            System.out.println("Restarting game.... ");
                            continueCondition = true;
                            break;
                        }
                        /** end the game */
                        else if (askForClues(response) == false){
                            System.out.println("Exiting game! Thanks for playing!");
                            System.exit(0);
                        }
                    }
                    /** Guess is incorrect */
                    else if (noSet.contains(response)){
                        // helpMeLearn()
                        String learningSuggestions;
                        System.out.println("I got it wrong");
                        System.out.println("Please help me to learn");
                        System.out.println("What was your animal?");
                        learningSuggestions = input.nextLine();
                    }
                }
            }
            /** if no, move to the right */
            while (askForClues(response) == false){
                if (currentPosition.getRight() != null){
                    currentPosition = root.getRight();
                    System.out.println(currentPosition.getData());
                    response = input.nextLine();
                    /** handles invalid response */
                    if (askForClues(response) == null){response = nullResponse(askForClues(response));}

                }else{
                    if (askForClues(response) == null){response = nullResponse(askForClues(response));}/** handles invalid response */
                    /** Guess is correct */
                    if (askForClues(response) == true){
                        System.out.println("Hurray!!! Would you like to play again? ");
                        response = input.nextLine();
                        /** handles invalid response */   
                        if (askForClues(response) == null){response = nullResponse(askForClues(response));}                   
                        /** restart the game */
                        if (askForClues(response) == true ){
                            System.out.println("Restarting game.... ");
                            continueCondition = true;
                            break;
                        }
                        /** end the game */
                        else if (askForClues(response) == false){
                            System.out.println("Exiting game! Thanks for playing!");
                            System.exit(0);
                        }
                    }   
                }
            }

        }
    }
}