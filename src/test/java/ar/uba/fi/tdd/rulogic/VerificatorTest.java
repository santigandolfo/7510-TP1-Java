package ar.uba.fi.tdd.rulogic;

import org.junit.Assert;
import org.junit.Test;

public class VerificatorTest {
    @Test
    public void noDotAtEndIsNotFact() {
        String factString = "varon(juan)";
        Assert.assertFalse(new Verificator().isFact(factString));
    }

    @Test
    public void wrongElementInParenthesesIsNotFact() {
        String factString = "varon(juan, )";
        Assert.assertFalse(new Verificator().isFact(factString));
    }

    @Test
    public void factWithOneElementIsFact() {
        String factString = "varon(juan).";
        Assert.assertTrue(new Verificator().isFact(factString));
    }

    @Test
    public void factWithTwoElementsIsFact() {
        String factString = "varon(juan, pepa).";
        Assert.assertTrue(new Verificator().isFact(factString));
    }

    @Test
    public void noDotAtEndIsNotRule() {
        String ruleString = "hijo(X, Y) :- varon(X), padre(Y, X)";
        Assert.assertFalse(new Verificator().isRule(ruleString));
    }

    @Test
    public void wrongElementInParenthesesIsNotRule() {
        String ruleString = "hijo(X, Y) :- varon(X), padre(Y, ).";
        Assert.assertFalse(new Verificator().isRule(ruleString));
    }

    @Test
    public void ruleWithOneElementIsRule() {
        String ruleString = "hijo(X, Y) :- varon(X).";
        Assert.assertTrue(new Verificator().isRule(ruleString));
    }

    @Test
    public void ruleWithMultipleElementsIsRule() {
        String ruleString = "hijo(X, Y) :- varon(X), mujer(Y), padre(Y, X).";
        Assert.assertTrue(new Verificator().isRule(ruleString));
    }

    @Test
    public void queryIsNeitherFactNorRule() {
        String queryString = "hijo(X, Y) varon(X), mujer(Y), padre(Y, X).";
        Assert.assertFalse(new Verificator().isRule(queryString));
    }

    @Test
    public void queryRuleIsBadFormatedWithDotAtEnd() {
        String queryString = "hijo(X, Y) :- varon(X), mujer(Y), padre(Y, X).";
        Assert.assertFalse(new Verificator().isQuery(queryString));
    }

    @Test
    public void queryFactIsBadFormatedWithDotAtEnd() {
        String queryString = "hijo(juan, pepa).";
        Assert.assertFalse(new Verificator().isQuery(queryString));
    }

    @Test
    public void queryIsAFact() {
        String queryString = "hijo(juan, pepa)";
        Assert.assertTrue(new Verificator().isQuery(queryString));
    }

    @Test
    public void queryIsARule() {
        String queryString = "hijo(X, Y) :- varon(X), mujer(Y), padre(Y, X)";
        Assert.assertTrue(new Verificator().isQuery(queryString));
    }

    @Test
    public void databaseEntryIsNeitherFactNorRule() {
        String databaseEntryString = "hijo(X, Y) varon(X), mujer(Y), padre(Y, X)";
        Assert.assertFalse(new Verificator().isDatabaseEntry(databaseEntryString));
    }

    @Test
    public void databaseEntryRuleIsBadFormatedWithNoDotAtEnd() {
        String databaseEntryString = "hijo(X, Y) :- varon(X), mujer(Y), padre(Y, X)";
        Assert.assertFalse(new Verificator().isDatabaseEntry(databaseEntryString));
    }

    @Test
    public void databaseEntryFactIsBadFormatedWithNoDotAtEnd() {
        String databaseEntryString = "hijo(juan, pepa)";
        Assert.assertFalse(new Verificator().isDatabaseEntry(databaseEntryString));
    }

    @Test
    public void databaseEntryIsAFact() {
        String databaseEntryString = "hijo(juan, pepa).";
        Assert.assertTrue(new Verificator().isDatabaseEntry(databaseEntryString));
    }

    @Test
    public void databaseEntryIsARule() {
        String databaseEntryString = "hijo(X, Y) :- varon(X), mujer(Y), padre(Y, X).";
        Assert.assertTrue(new Verificator().isDatabaseEntry(databaseEntryString));
    }

}