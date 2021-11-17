import java.util.ArrayList;

public class CFG {


    ArrayList<Rule> rules;

    CFG(ArrayList<Rule> rules) {
        this.rules = rules;
    }
    public static class Rule{
        NTerm nTerm;
        private Character letter;
        private ArrayList<NTerm> nTerms;

        public Character letter() {
            return letter;
        }

        public NTerm getNTerm() {
            return nTerm;
        }

        public ArrayList<NTerm> getNTerms() {
            return nTerms;
        }

        Rule(NTerm nTerm, char letter, ArrayList<NTerm> nTerms) {
            this.nTerm = nTerm;
            this.letter = letter;
            this.nTerms = nTerms;
        }



    }

    public class NTerm{
        private String state1;
        private String state2;
        private String stackS;

        public String getState1() {
            return state1;
        }

        public String getState2() {
            return state2;
        }

        public String getStackS() {
            return stackS;
        }

        NTerm(String state1, String state2, String stackS) {
            this.state1 = state1;
            this.state2 = state2;
            this.stackS = stackS;
        }
    }

}
