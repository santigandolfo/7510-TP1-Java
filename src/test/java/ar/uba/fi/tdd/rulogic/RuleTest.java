package ar.uba.fi.tdd.rulogic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class RuleTest {

    @Test
    public void getFacts() {
        Fact fact1 = new Fact("varon(X).");
        Fact fact2 = new Fact("hermano(X, Z).");
        Fact fact3 = new Fact("padre(Z, Y).");
        Rule rule = new Rule("tio(X, Y, Z):- varon(X),\thermano(X, Z),padre(Z, Y).");
        ArrayList<Fact> parameters = new ArrayList<Fact>(Arrays.asList(fact1, fact2, fact3));
        Assert.assertTrue(rule.getFacts().get(0).equals(fact1));
        Assert.assertTrue(rule.getFacts().get(1).equals(fact2));
        Assert.assertTrue(rule.getFacts().get(2).equals(fact3));
    }

}