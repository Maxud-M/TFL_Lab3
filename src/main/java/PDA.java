import java.util.ArrayList;

public class PDA {

    ArrayList<Rule> rules;
    String startState;
    String stackBottom;

    PDA(ArrayList<String> )

    public static class Rule{ //<state1, letter, stackS1> -> <state2, stackS2>
        private String state1;
        private String state2;
        private char letter;
        private String stackS1;
        private String stackS2;

        Rule(String state1, String state2, char letter, String stackS1, String stackS2) {
            this.state1 = state1;
            this.state2 = state2;
            this.stackS1 = stackS1;
            this.stackS2 = stackS2;
            this.letter = letter;
        }

        public char getLetter() {
            return letter;
        }

        public String getStackS1() {
            return stackS1;
        }

        public String getStackS2() {
            return stackS2;
        }

        public String getState1() {
            return state1;
        }

        public String getState2() {
            return state2;
        }

        

        public static Rule readRule(String str) {
            str = str.replaceAll(" ", "");
            int itr = 1;
            String state1;
            String state2;
            String stackS1;
            String stackS2;
            char letter;

        }
    }
}
