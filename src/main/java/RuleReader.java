import java.util.regex.Pattern;

public class RuleReader {
    public static int itr;
    public static String readStateOrStackS(String str) {
        int startOfRes = itr;
        int endOfRes = itr + 1;
        if(str.charAt(endOfRes) == )
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
        Pattern patternStack = Pattern.compile("[A-Z][0-9]?");
        Pattern patterState = Pattern.compile("[q-u][0-9]?");
        Pattern patterLetter = Pattern.compile("[a-z]");
        Matcher matcherStack = _
       /* itr = 1;
        str = str.replaceAll(" ", "");
        String state1 = readStateOrStackS(str);
        String letter = (str.charAt(itr) == '!')? str.substring(itr, itr + 2):str.substring(itr, itr + 1);
        String stackS1 = readStateOrStackS(str);
        while(str.charAt(itr) != '<') {
            itr++;
        }
        itr++;
        String state2 = readStateOrStackS(str);
        int stackS2start = itr;
        while(str.charAt(itr) != '>') {
            itr++;
        }
        String stackS2 = str.substring(stackS2start, itr);

        return new PDA.Rule(state1, state2, letter, stackS1, stackS2);*/
    }
}
