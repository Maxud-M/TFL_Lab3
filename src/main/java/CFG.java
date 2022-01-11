import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CFG {

    public static final String START_SYMBOL = "S";

    HashMap<NTerm, ArrayList<Rule>> rules;


    private HashMap<NTerm, String> renameNTerms() {
        HashMap<NTerm, String> names = new HashMap<NTerm, String>(0);
        for(NTerm nTerm: rules.keySet()) {
            if (!nTerm.isStartNTerm){
                names.put(nTerm, NTerm.NameGenerator.nextName());
            }
        }
        return names;
    }


    public void printCFG() {
        System.out.println("CFG Rules: ");

        HashMap<NTerm, String> namesOfNTerms = renameNTerms();
        for(NTerm key: rules.keySet()) {
            ArrayList<Rule> values = rules.get(key);
            if(values.size() == 0) {
                continue;
            }
            String leftPart = "";
            String rightPart = "";
            if(key.isStartNTerm()) {
                leftPart = START_SYMBOL;
            } else {
                leftPart = namesOfNTerms.get(key);
            }
            for(int i = 0; i < values.size(); ++i) {
                rightPart += (values.get(i).getLetter() == '\1')? "" : String.valueOf(values.get(i).getLetter());
                for (NTerm nTerm : values.get(i).getNTerms()) {
                    rightPart += namesOfNTerms.get(nTerm);
                }
                if(i != values.size() - 1) {
                    rightPart += " | ";
                }
            }
            System.out.println(leftPart + " -> " + rightPart);
        }
    }


    CFG(HashMap<NTerm, ArrayList<Rule>> rules) {
        this.rules = rules;
    }
    public static class Rule{
        NTerm nTerm;
        private Character letter;
        private ArrayList<NTerm> nTerms;

        public Character getLetter() {
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

        Rule(NTerm nTerm, char letter) {
            this.nTerm = nTerm;
            this.letter = letter;
        }

        @Override
        public String toString() {
            String leftPart = "";
            String rightPart = "";
            if(nTerm.isStartNTerm()) {
                leftPart = START_SYMBOL;
            } else {
                leftPart = "[" + nTerm.state1 + ", " + nTerm.stackS + ", " + nTerm.state2 + "]";
            }
            rightPart = String.valueOf(letter);
            for(NTerm nTerm: nTerms) {
                rightPart += "[" + nTerm.state1 + ", " + nTerm.stackS + ", " + nTerm.state2 + "]";
            }
            return leftPart + " -> " + rightPart;
        }



    }

    public static class NTerm{
        private boolean isStartNTerm = false;
        private String state1 = "";
        private String state2 = "";
        private String stackS = "";

        @Override
        public String toString() {
            return "[" + state1 + ", " + stackS + ", " + state2 + "]";
        }

        @Override
        public boolean equals(Object o) {
            if(o == this) {
                return true;
            }

            if(!(o instanceof NTerm)) {
                return false;
            }

            NTerm n = (NTerm)o;
            if(n.getState1().equals(this.getState1()) && n.getState2().equals(this.getState2()) && n.getStackS().equals(this.getStackS())) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = 61 * 17 + (state1 == null? 0 : state1.hashCode());
            hash = 61 * 17 + (state2 == null? 0 : state2.hashCode());
            hash = 61 * 17 + (stackS == null? 0 : stackS.hashCode());
            return hash;
        }

        public boolean isStartNTerm() {
            return this.isStartNTerm;
        }
        public String getState1() {
            return state1;
        }

        public String getState2() {
            return state2;
        }

        public String getStackS() {
            return stackS;
        }

        NTerm(boolean isStartNTerm) {
            this.isStartNTerm = isStartNTerm;
        }



        NTerm(String state1, String state2, String stackS) {
            this.state1 = state1;
            this.state2 = state2;
            this.stackS = stackS;
        }



        public static class NameGenerator{
            static char currentChar = 'A';
            static int currentDigit = 0;

            public static String nextName() {
                String name = currentChar + String.valueOf(currentDigit);
                currentDigit = (currentDigit + 1) % 10;
                if(currentDigit == 0) {
                    currentChar++;
                }
                return name;
            }
        }
    }

}
