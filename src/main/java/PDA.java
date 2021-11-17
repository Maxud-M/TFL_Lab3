import java.util.ArrayList;

public class PDA {

    ArrayList<Rule> rules;
    String startState;
    String stackBottom;

    CFG toCFG() {
        for(int i = 0; i < )
        CFG cfg = new CFG();
        return cfg;
    }


    PDA(ArrayList<String> rules) {
        String[] startSymbols = PDAReader.readStartSymbols(rules.get(0)).split(":");
        startState = startSymbols[0];
        stackBottom = startSymbols[1];
        for(int i = 1; i < rules.size(); ++i) {
            this.rules.add(PDAReader.readRule(rules.get(i)));
        }
    }

    public static class Rule{ //<state1, letter, stackS1> -> <state2, stackS2>
        private String state1;
        private String state2;
        private String letter;
        private String stackS1;
        private ArrayList<String> stackS2;

        Rule(String state1, String state2, String letter, String stackS1, ArrayList<String> stackS2) {
            this.state1 = state1;
            this.state2 = state2;
            this.stackS1 = stackS1;
            this.stackS2 = stackS2;
            this.letter = letter;
        }

        public String getLetter() {
            return letter;
        }

        public String getStackS1() {
            return stackS1;
        }

        public ArrayList<String> getStackS2() {
            return stackS2;
        }

        public String getState1() {
            return state1;
        }

        public String getState2() {
            return state2;
        }

    }
}
