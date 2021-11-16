import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDAReader {

    public static String readStartSymbols(String str) {
        Pattern patternStartState = Pattern.compile("[q-u][0-9]?");
        Pattern patternStartStackS = Pattern.compile("[A-Z][0-9]?");
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
        Matcher matcherState = patternState.matcher(str);
        if(matcherState.find()) {
            state1 = str.substring(matcherState.start(), matcherState.end());
            str = str.substring(matcherState.end());
        }
        Matcher matcherLetter = patternLetter.matcher(str);
        if(matcherLetter.find()) {
            letter = str.substring(matcherLetter.start(), matcherLetter.end());
            str = str.substring(matcherLetter.end());
        }
        Matcher matcherStackS1 = patternStackS1.matcher(str);
        if(matcherStackS1.find()) {
            stackS1 = str.substring(matcherStackS1.start(), matcherStackS1.end());
            str = str.substring(matcherStackS1.end());
        }
        matcherState = patternState.matcher(str);
        if(matcherState.find()) {
            state2 = str.substring(matcherState.start(), matcherState.end());
            str = str.substring(matcherState.end());
        }
        Matcher matcherStackS2 = patternStackS2.matcher(str);
        if(matcherStackS2.find()) {
            stackS2 = str.substring(matcherStackS2.start(), matcherStackS2.end());
        } else {
            stackS2 = "";
        }
        return new PDA.Rule(state1, state2, letter, stackS1, stackS2);
    }
}
