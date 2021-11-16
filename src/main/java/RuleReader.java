import java.util.regex.Matcher;
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
        String state1;
        String state2;
        String stackS1;
        String stackS2;
        String letter;
        Pattern pattern = Pattern.compile("[A-Z][0-9]? | [q-u][0-9]? | [a-z]");
        Pattern patternStack = Pattern.compile("[A-Z][0-9]? | [qu][0-9]? | [a-z]");
        Pattern patternState = Pattern.compile("[q-u][0-9]?");
        Pattern patternLetter = Pattern.compile("[a-z]");
        Matcher matcherStack = patternStack.matcher(str);
        Matcher matcherState = patternState.matcher(str);
        Matcher matcherLetter = patternLetter.matcher(str);
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            state1 = str.substring(matcher.start(), matcher.end());
        }
        if(matcher.find()) {
            letter = str.substring(matcher.start(), matcher.end());
        }
        if(matcher.find()) {
            stackS1 = str.substring(matcher.start(), matcher.end());
        }
        if(matcher.find()) {

        }
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
