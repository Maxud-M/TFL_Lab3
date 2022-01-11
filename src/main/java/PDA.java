import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;


public class PDA {

    private ArrayList<String> states;
    private ArrayList<Rule> rules;
    private String startState;
    private String stackBottom;



    private void addPopingRule(ArrayList<CFG.Rule> cfgRules, PDA.Rule rule, HashSet<CFG.NTerm> cfgNTerms) {
        CFG.NTerm nTerm = new CFG.NTerm(rule.state1, rule.state2, rule.popSymbol);
        cfgNTerms.add(nTerm);
        cfgRules.add(new CFG.Rule(
                nTerm,
                rule.letter,
                new ArrayList<CFG.NTerm>(0))
        );
    }

    private ArrayList<CFG.NTerm> getNTerms(int[] variations, Rule rule, HashSet<CFG.NTerm> cfgNTerms) {
        ArrayList<CFG.NTerm> res = new ArrayList<CFG.NTerm>(0);
        int m = rule.pushSymbols.size();
        CFG.NTerm nTerm;
        if(m > 1) {
            nTerm = new CFG.NTerm(
                    rule.state2,
                    states.get(variations[1]),
                    rule.pushSymbols.get(0)
            );
            cfgNTerms.add(nTerm);
            res.add(nTerm);
            for (int i = 1; i < m - 1; ++i) {
                nTerm = new CFG.NTerm(
                        states.get(variations[i]),
                        states.get(variations[i + 1]),
                        rule.pushSymbols.get(i)
                );
                cfgNTerms.add(nTerm);
                res.add(nTerm);
            }

            nTerm = new CFG.NTerm(
                    states.get(variations[m - 1]),
                    states.get(variations[0]),
                    rule.pushSymbols.get(m - 1)
            );
            cfgNTerms.add(nTerm);
            res.add(nTerm);
        } else {
            nTerm = new CFG.NTerm(
                    rule.state2,
                    states.get(variations[0]),
                    rule.pushSymbols.get(0)
            );
            cfgNTerms.add(nTerm);
            res.add(nTerm);
        }
        return res;
    }

    private void addPushRule(ArrayList<CFG.Rule> cfgRules, Rule rule, HashSet<CFG.NTerm> cfgNTerms) {
        int n = states.size();
        int m  = rule.pushSymbols.size();
        int variations[] = new int[(n > m)? n: m];
        for(int i = 0; i < variations.length; ++i) {
            variations[i] = 0;
        }
        CFG.NTerm nTerm = new CFG.NTerm(rule.state1, states.get(variations[0]), rule.popSymbol);
        cfgRules.add(new CFG.Rule(
                nTerm,
                rule.letter,
                getNTerms(variations, rule, cfgNTerms))
        );

        while(Variation.nextSet(variations, n, m)) {
            nTerm = new CFG.NTerm(rule.state1, states.get(variations[0]), rule.popSymbol);
            cfgRules.add(new CFG.Rule(
                    nTerm,
                    rule.letter,
                    getNTerms(variations, rule, cfgNTerms))
            );
        }
    }

    CFG toCFG() {
        ArrayList<CFG.Rule> cfgRules = new ArrayList<CFG.Rule>(0);
        HashSet<CFG.NTerm> cfgNTerms = new HashSet<CFG.NTerm>(0);

        //cfgRules.add(new CFG.Rule(new CFG.NTerm(true), ' ', new ArrayList<CFG.NTerm>(Arrays.asList(new CFG.NTerm(startState, startState, stackBottom)))));
        for(int i = 0; i < states.size(); ++i) {
            CFG.NTerm nTerm = new CFG.NTerm(startState, states.get(i), stackBottom);
            cfgNTerms.add(nTerm);
            cfgRules.add(new CFG.Rule(
                    new CFG.NTerm(true),
                    ' ',
                    new ArrayList<CFG.NTerm>(Arrays.asList(nTerm)))
            );
        }

        for(Rule rule: rules) {
            if(rule.pushSymbols.size() == 0) {
                addPopingRule(cfgRules, rule, cfgNTerms);
            } else {
                addPushRule(cfgRules, rule, cfgNTerms);
            }
        }

        ArrayList<CFG.NTerm> used = new ArrayList<CFG.NTerm>(0);
        deleteNTermsWithoutRule(cfgRules, cfgNTerms);
        while(updateRules(cfgNTerms, cfgRules)) {
            deleteNTermsWithoutRule(cfgRules, cfgNTerms);
        }
        cfgNTerms = reachableNTerms(cfgRules, new CFG.NTerm(true), used);
        updateRules(cfgNTerms, cfgRules);
        cfgNTerms = deleteNonGenerativeNTerms(cfgRules, cfgNTerms);
        updateRules(cfgNTerms, cfgRules);
        return new CFG(unionRule(cfgNTerms, cfgRules));
    }

    private HashMap<CFG.NTerm, ArrayList<CFG.Rule>> unionRule(HashSet<CFG.NTerm> cfgNTerms, ArrayList<CFG.Rule> cfgRules) {
        HashMap<CFG.NTerm, ArrayList<CFG.Rule>> res = new HashMap<CFG.NTerm, ArrayList<CFG.Rule>>(0);
        cfgNTerms.add(new CFG.NTerm(true));
        Object[] nTermsArray = cfgNTerms.toArray();
        for(int i = 0; i < nTermsArray.length; ++i) {
            res.put((CFG.NTerm)nTermsArray[i], new ArrayList<CFG.Rule>(0));
        }
        for(int i = 0; i < nTermsArray.length; ++i) {
            for(CFG.Rule rule: cfgRules) {
                if(rule.getNTerm().equals(nTermsArray[i])) {
                    res.get(nTermsArray[i]).add(rule);
                }
            }
        }
        return res;
    }

    private void deleteNTermsWithoutRule(ArrayList<CFG.Rule> cfgRules, HashSet<CFG.NTerm> cfgNTerms) {
        HashMap<CFG.NTerm, ArrayList<CFG.Rule>> rules = unionRule(cfgNTerms, cfgRules);
        ArrayList<CFG.NTerm> removeList = new ArrayList<CFG.NTerm>(0);
        for(CFG.NTerm key: rules.keySet()) {
            ArrayList<CFG.Rule> v = rules.get(key);
            if(v.size() == 0) {
                removeList.add(key);
            }
        }
        cfgNTerms.removeAll(removeList);
    }

    private HashSet<CFG.NTerm> deleteNonGenerativeNTerms(ArrayList<CFG.Rule> cfgRules, HashSet<CFG.NTerm> cfgNTerm) {
        HashMap<CFG.NTerm, ArrayList<CFG.Rule>> rules = unionRule(cfgNTerm, cfgRules);
        HashSet<CFG.NTerm> generative = new HashSet<CFG.NTerm>(0);
        for(CFG.NTerm key: rules.keySet()) {
            ArrayList<CFG.Rule> value = rules.get(key);
            for(int i = 0; i < value.size(); ++i) {
                if(value.get(i).getNTerms().size() == 0) {
                    generative.add(key);
                    break;
                }
            }
        }
        boolean changed;
        do {
            changed = false;
            boolean isGenerative = true;
            for(CFG.NTerm key: rules.keySet()) {
                ArrayList<CFG.Rule> value = rules.get(key);
                for(CFG.Rule rule: value) {
                    isGenerative = true;
                    for(CFG.NTerm nTerm: rule.getNTerms()) {
                        if(!generative.contains(nTerm)) {
                            isGenerative = false;
                            break;
                        }
                    }
                    if(isGenerative) {
                        break;
                    }
                }
                if(isGenerative) {
                    changed = true;
                    generative.add(key);
                }
            }
            if(changed) {
                for(CFG.NTerm nTerm: generative){
                    rules.remove(nTerm);
                }
            }
        } while(changed);
        return generative;
    }

    private boolean updateRules(HashSet<CFG.NTerm> nTerms, ArrayList<CFG.Rule> cfgRules) {
        ArrayList<CFG.Rule> removeRules = new ArrayList<CFG.Rule>(0);
        for(CFG.Rule rule: cfgRules) {
            if(!rule.nTerm.isStartNTerm() && !nTerms.contains(rule.nTerm)) {
                removeRules.add(rule);
                continue;
            }
            for(CFG.NTerm nTerm: rule.getNTerms()) {
                if(!nTerms.contains(nTerm)) {
                    removeRules.add(rule);
                    break;
                }
            }
        }
        cfgRules.removeAll(removeRules);
        return !removeRules.isEmpty();
    }

    private HashSet<CFG.NTerm> reachableNTerms(ArrayList<CFG.Rule> cfgRules, CFG.NTerm nTerm, ArrayList<CFG.NTerm> used) {
        used.add(nTerm);
        HashSet<CFG.NTerm> res = new HashSet<CFG.NTerm>(0);
        for(CFG.Rule rule: cfgRules) {
            if(rule.getNTerm().equals(nTerm)) {
                for(CFG.NTerm nTerm1: rule.getNTerms()) {
                    res.add(nTerm1);
                }
            }
        }
        HashSet<CFG.NTerm> tmp = (HashSet<CFG.NTerm>) res.clone();
        for(CFG.NTerm nTerm1: tmp) {
            if(!used.contains(nTerm1)) {
                res.addAll(reachableNTerms(cfgRules, nTerm1, used));
            }
        }
        return res;
    }

    PDA(ArrayList<String> states, ArrayList<Rule> rules, String startState, String stackBottom) {
        this.states = states;
        this.rules = rules;
        this.startState = startState;
        this.stackBottom = stackBottom;
    }




    public static class Rule{ //<state1, letter, popSymbol> -> <state2, pushSymbols>
        private String state1;
        private String state2;
        private char letter;
        private String popSymbol;
        private ArrayList<String> pushSymbols;

        Rule(String state1, String state2, char letter, String popSymbol, ArrayList<String> pushSymbols) {
            this.state1 = state1;
            this.state2 = state2;
            this.popSymbol = popSymbol;
            this.pushSymbols = pushSymbols;
            this.letter = letter;
        }

        public char getLetter() {
            return letter;
        }

        public String getPopSymbol() {
            return popSymbol;
        }

        public ArrayList<String> getPushSymbols() {
            return pushSymbols;
        }

        public String getState1() {
            return state1;
        }

        public String getState2() {
            return state2;
        }

    }
}
