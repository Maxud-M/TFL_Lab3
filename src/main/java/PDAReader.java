import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDAReader {

    public static ArrayList<String> states = new ArrayList<String>(0);

    public static String readStartSymbols(String str) {
        String res = "";
        Pattern pattern = Pattern.compile("[q-u][0-9]?");
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            res = str.substring(matcher.start(), matcher.end()) + ":";
        }
        pattern = Pattern.compile("[A-Z][0-9]?");
        matcher = pattern.matcher(str);
        if(matcher.find()) {
            res += str.substring(matcher.start(), matcher.end());
        }
        return res;
    }

    public static PDA.Rule readRule(String str) {
        String state1 = "";
        String state2 = "";
        String stackS1 = "";
        char letter = '\1';
        Pattern patternState = Pattern.compile("[q-u][0-9]?");
        Pattern patternLetter = Pattern.compile("[a-z]");
        Pattern patternStackS1 = Pattern.compile("[A-Z][0-9]?");
        Matcher matcher = patternState.matcher(str);
        if(matcher.find()) {
            state1 = str.substring(matcher.start(), matcher.end());
            str = str.substring(matcher.end());
            if(!states.contains(state1)) {
                states.add(state1);
            }
        }
        if(str.charAt(1) != '!') {
            matcher = patternLetter.matcher(str);
            if (matcher.find()) {
                letter = str.substring(matcher.start(), matcher.end()).toCharArray()[0];
                str = str.substring(matcher.end());
            }
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
            if(!states.contains(state2)) {
                states.add(state2);
            }
        }
        matcher = patternStackS1.matcher(str);
        ArrayList<String> arrayOfStackS = new ArrayList<String>(0);
        while(matcher.find()) {
            arrayOfStackS.add(str.substring(matcher.start(), matcher.end()));
        }
        return new PDA.Rule(state1, state2, letter, stackS1, arrayOfStackS);
    }

    public static PDA readPDA(ArrayList<String> rules) {
        String[] startSymbols = PDAReader.readStartSymbols(rules.get(0)).split(":");
        String startState = startSymbols[0];
        String stackBottom = startSymbols[1];
        ArrayList<PDA.Rule> pdaRules = new ArrayList<PDA.Rule>(0);
        for(int i = 1; i < rules.size(); ++i) {
            pdaRules.add(PDAReader.readRule(rules.get(i)));
        }
        return new PDA(states, pdaRules, startState, stackBottom);

    }
}
