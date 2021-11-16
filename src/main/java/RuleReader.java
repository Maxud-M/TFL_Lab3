public class RuleReader {
    public static int itr;
    public static String readStateOrStackS(String str) {
        int startOfRes = itr;
        int endOfRes = itr;
        String res = "";
        return res;
    }

    public static String readLetter(String str) {
        if(str.charAt(itr) == '!') {
            String res = str.substring(itr, itr + 2);
            itr += 3;
            return res;
        } else {
            String res = str.substring(itr, itr + 1);
            itr += 2;
            return res;
        }
    }

    public static PDA.Rule readRule(String str) {
        itr = 1;
        str = str.replaceAll(" ", "");
        String state1 = readStateOrStackS(str);
        String letter = (str.charAt(itr) == '!')? str.substring(itr, itr + 2):str.substring(itr, itr + 1);
        return new PDA.Rule(state1, state2, letter, stackS1, stackS2);
    }
}
