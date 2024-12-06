public class mainCountTest {

    public int steps(int noOfSteps){
        return jumps(noOfSteps,1) + jumps(noOfSteps,2) + jumps(noOfSteps,3);
    }

    public int jumps(int noOfSteps, int choice){
        if (noOfSteps < choice){
            return 0;
        }
        else if (noOfSteps == choice){
            return 1;
        }
        return jumps(noOfSteps - choice,1) + jumps(noOfSteps - choice,2) + jumps(noOfSteps - choice,3);
    }


    public static void main(String[] args) {
        mainCountTest test = new mainCountTest();
        System.out.println(test.steps(50));
    }
}
