import java.util.ArrayList;

public class CFG {


    ArrayList<Rule> rules;

    public static class Rule{
        NTerm nTerm;
        private Character leftPart;
        private String rightPart;

        public Character getLeftPart() {
            return leftPart;
        }

        public String getRightPart() {
            return rightPart;
        }

        Rule(Character leftPart, String rightPart) {
            this.leftPart = leftPart;
            this.rightPart = rightPart;
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
