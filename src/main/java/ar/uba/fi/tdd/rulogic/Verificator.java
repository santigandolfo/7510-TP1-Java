package ar.uba.fi.tdd.rulogic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verificator {

    public boolean isFact(String element) {
        Pattern factFormat = Pattern.compile("\\w+\\(([^)]+)\\)\\.");
        Matcher matcher = factFormat.matcher(element);
        return matcher.matches();
    }

    public boolean isRule(String element) {
        Pattern ruleFormat = Pattern.compile("^[a-z]+\\(([A-Z]+, )*[A-Z]+\\) :- (([a-z]+\\(([A-Z]+, )*[A-Z]+\\)), )*([a-z]+\\(([A-Z]+, )*[A-Z]+\\))\\.");
        Matcher matcher = ruleFormat.matcher(element);
        return matcher.matches();
    }

    public boolean isQuery(String element) {
        Pattern factFormat = Pattern.compile("\\w+\\(([^)]+)\\)");
        Pattern ruleFormat = Pattern.compile("^[a-z]+\\(([A-Z]+, )*[A-Z]+\\) :- (([a-z]+\\(([A-Z]+, )*[A-Z]+\\)), )*([a-z]+\\(([A-Z]+, )*[A-Z]+\\))");
        Matcher matcherFact = factFormat.matcher(element);
        Matcher matcherRule = ruleFormat.matcher(element);
        return (matcherFact.matches() || matcherRule.matches());
    }

    public boolean isDatabaseEntry(String element) {
        Pattern factFormat = Pattern.compile("\\w+\\(([^)]+)\\)\\.");
        Pattern ruleFormat = Pattern.compile("^[a-z]+\\(([A-Z]+, )*[A-Z]+\\) :- (([a-z]+\\(([A-Z]+, )*[A-Z]+\\)), )*([a-z]+\\(([A-Z]+, )*[A-Z]+\\))\\.");
        Matcher matcherFact = factFormat.matcher(element);
        Matcher matcherRule = ruleFormat.matcher(element);
        return (matcherFact.matches() || matcherRule.matches());
    }
}
