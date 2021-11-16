import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDAReader {

    public static String readStartSymbols(String str) {
        String res = "";
        Pattern pattern = Pattern.compile("[q-u][0-9]? | [A-Z][0-9]?");
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            res = str.substring(matcher.start(), matcher.end()) + ":";
        }
        if(matcher.find()) {
            res += str.substring(matcher.start(), matcher.end());
        }
        return res;
    }

    public static PDA.Rule readRule(String str) {
        String state1 = "";
        String state2 = "";
        String stackS1 = "";
        String stackS2 = "";
        String letter = "";
        Pattern patternState = Pattern.compile("[q-u][0-9]?");
        Pattern patternLetter = Pattern.compile("[a-z] | !!");
        Pattern patternStackS1 = Pattern.compile("[A-Z][0-9]?");
        Pattern patternStackS2 = Pattern.compile("([A-Z][0-9]?)*");
        Matcher matcher = patternState.matcher(str);
        if(matcher.find()) {
            state1 = str.substring(matcher.start(), matcher.end());
            str = str.substring(matcher.end());
        }
        matcher = patternLetter.matcher(str);
        if(matcher.find()) {
            letter = str.substring(matcher.start(), matcher.end());
            str = str.substring(matcher.end());
        }
        matcher = patternStackS1.matcher(str);
        if(matcher.find()) {
            stackS1 = str.substring(matcher.start(), matcher.end());
            str = str.substring(matcher.end());
        }
        matcher = patternState.matcher(str);
        if(matcher.find()) {
            state2 = str.substring(matcher.start(), matcher.end());
            str = str.substring(matcher.end());
        }
        matcher = patternStackS2.matcher(str);
        if(matcher.find()) {
            stackS2 = str.substring(matcher.start(), matcher.end());
        } else {
            stackS2 = "";
        }
        return new PDA.Rule(state1, state2, letter, stackS1, stackS2);
    }
}
