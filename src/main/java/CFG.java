import java.util.ArrayList;

public class CFG {

    ArrayList<Rule> rules;



    public static class Rule{
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

}
