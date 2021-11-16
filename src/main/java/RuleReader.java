public class RuleReader {
    public static int itr;
    public static String readStateOrStackS(String str) {
        int startOfRes = itr;
        int endOfRes = itr;
        String res = "";
        return res;
    }

    public static PDA.Rule readRule(String str) {
        itr = 1;
        str = str.replaceAll(" ", "");
        String state1 = readStateOrStackS(str);
        itr++;
        char letter = (str.charAt(itr) == '!')? 
        return new PDA.Rule(state1, state2, letter, stackS1, stackS2);
    }
}
