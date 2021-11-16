public class PDA {
    private class Rule{ //<state1, letter, stackS1> -> <state2, stackS2>
        public String state1;
        public String state2;
        public char letter;
        public String stackS1;
        public String stackS2;

        Rule(String state1, String state2, char letter, String stackS1, String stackS2) {
            this.state1 = state1;
            this.state2 = state2;
            this.stackS1 = stackS1;
            this.stackS2 = stackS2;
            this.letter = letter;
        }

        
    }
}
