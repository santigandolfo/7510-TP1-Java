package ar.uba.fi.tdd.rulogic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule extends Element{

    private ArrayList<Fact> mFacts;

    public Rule(String element) {
        super(element);
        element = element.replaceAll("\\s+","").replaceAll("\t", "");
        mFacts = generateFacts(element);
    }

    private ArrayList<Fact> generateFacts(String element) {
        element = element.replaceAll("\\s+","").replaceAll("\t", "");
        String rawFactsString = element.substring(element.indexOf(":-") + ":-".length(), element.indexOf("."));
        ArrayList<Fact> factsList = new ArrayList<Fact>();
        Pattern p = Pattern.compile("\\w+\\(([^)]+)\\)");
        Matcher m = p.matcher(rawFactsString);
        while(m.find()){
            factsList.add(new Fact(m.group()));
        }
        return factsList;
    }

    public ArrayList<Fact> getFacts() {
        return mFacts;
    }
}