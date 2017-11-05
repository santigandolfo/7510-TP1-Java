package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDatabaseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {

    private List<Fact> facts;
    private List<Rule> rules;
    private Verificator verificator;

    public List<Fact> getFacts() {
        return facts;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public Database(){
        this.facts = new ArrayList<Fact>();
        this.rules = new ArrayList<Rule>();
        verificator = new Verificator();
    }

    public void generateParsedDatabase(ArrayList<String> database) throws InvalidDatabaseException {
        if (isInvalidDatabase(database)) {
            throw new InvalidDatabaseException();
        }
        for (String aDatabase : database) {
            if (verificator.isFact(aDatabase)) {
                this.facts.add(new Fact(aDatabase));
            } else if (verificator.isRule(aDatabase)) {
                this.rules.add(new Rule(aDatabase));
            }
        }
    }

    private boolean isInvalidDatabase(List<String> database) {
        for (String entry : database) {
            if (!(new Verificator().isDatabaseEntry(entry))) {
                return true;
            }
        }
        return false;
    }

    public boolean queryElementsAreInDatabase(Element query) {
        System.out.println("4");
        if (factTypesIncludesQuery(query)) {
            return factsIncludeElement(query);
        } else if (ruleTypesIncludesQuery(query)) {
            System.out.println("5");
            return ruleFactsIncludeQuery(query);
        }
        return false;
    }

    private Rule findRuleWithSameType(Element query) {
        System.out.println("7");
        for (Rule rule : rules) {
            if (rule.isOfSameType(query)) {
                return rule;
            }
        }
        return null;
    }

    private HashMap<String, String> createParametersMap(ArrayList<String> ruleParameters, ArrayList<String> queryParameters){
        HashMap<String, String> map = new HashMap<String, String>();
        int i = 0;
        while (i < ruleParameters.size() && i < queryParameters.size()) { //for safety i will check on both sizes
            map.put(ruleParameters.get(i), queryParameters.get(i));
            i++;
        }
        return map;
    }

    private boolean ruleFactsIncludeQuery(Element query) {
        System.out.println("6");
        Rule rule = findRuleWithSameType(query);
        HashMap<String, String> map = createParametersMap(rule.getParameters(), query.getParameters());
        for (Fact fact : rule.getFacts()) {
            System.out.println("8");
            Fact newFact = new Fact(fact);
            newFact.replaceParameters(map);
            System.out.println(newFact.mElement);
            System.out.println(newFact.mType);
            System.out.println(newFact.mParameters);
            if (!factsIncludeElement(newFact)) {
                System.out.println("10");
                return false;
            }
        }
        return true;

    }

    private boolean ruleTypesIncludesQuery(Element query) {
        for (Rule rule : rules) {
            if (rule.isOfSameType(query)) {
                return true;
            }
        }
        return false;
    }

    private boolean factTypesIncludesQuery(Element query) {
        for (Fact fact : facts) {
            if (fact.isOfSameType(query)) {
                return true;
            }
        }
        return false;
    }

    private boolean factsIncludeElement(Element element) {
        for (Fact fact : facts) {
            if (fact.equals(element)) {
                return true;
            }
        }
        return false;
    }

}
