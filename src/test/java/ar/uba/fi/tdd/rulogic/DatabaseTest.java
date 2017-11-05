package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDatabaseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class DatabaseTest {

    @Test(expected = InvalidDatabaseException.class)
    public void invalidFactInDatabase() throws InvalidDatabaseException {
        ArrayList<String> databaseList = new ArrayList<String>(Arrays.asList(
                "varon(juan).",
                "varon(pepe).",
                "varon(hector).",
                "varon(roberto, )",
                "varon(alejandro)",
                "mujer(maria).",
                "mujer(cecilia).",
                "padre(juan, pepe).",
                "padre(juan, pepa).",
                "padre(hector, maria).",
                "padre(roberto, alejandro).",
                "padre(roberto, cecilia).",
                "hijo(X, Y) :- varon(X), padre(Y, X).",
                "hija(X, Y) :- mujer(X), padre(Y, X)."
        ));
        Database database = new Database();
        database.generateParsedDatabase(databaseList);
    }

    @Test(expected = InvalidDatabaseException.class)
    public void invalidRuleInDatabase() throws InvalidDatabaseException {
        ArrayList<String> databaseList = new ArrayList<String>(Arrays.asList(
                "varon(juan).",
                "varon(pepe).",
                "varon(hector).",
                "varon(roberto, )",
                "varon(alejandro)",
                "mujer(maria).",
                "mujer(cecilia).",
                "padre(juan, pepe).",
                "padre(juan, pepa).",
                "padre(hector, maria).",
                "padre(roberto, alejandro).",
                "padre(roberto, cecilia).",
                "hijo(X, Y) :- varon(X) padre(Y X)",
                "hija(X, Y) :- mujer(X), padre(Y, X)."
        ));
        Database database = new Database();
        database.generateParsedDatabase(databaseList);
    }

    @Test
    public void generateParsedDatabase() throws Exception, InvalidDatabaseException {
        ArrayList<String> databaseList = new ArrayList<String>(Arrays.asList(
                "varon(juan).",
                "varon(pepe).",
                "varon(hector).",
                "varon(roberto).",
                "varon(alejandro).",
                "mujer(maria).",
                "mujer(cecilia).",
                "padre(juan, pepe).",
                "padre(juan, pepa).",
                "padre(hector, maria).",
                "padre(roberto, alejandro).",
                "padre(roberto, cecilia).",
                "hijo(X, Y) :- varon(X), padre(Y, X).",
                "hija(X, Y) :- mujer(X), padre(Y, X)."
        ));
        Database database = new Database();
        database.generateParsedDatabase(databaseList);
        Assert.assertEquals(12,database.getFacts().size());
        Assert.assertEquals(2,database.getRules().size());
    }

    @Test
    public void queryTypeIsNotInDatabase() throws Exception, InvalidDatabaseException {
        ArrayList<String> databaseList = new ArrayList<String>(Arrays.asList(
                "varon(juan).",
                "varon(pepe).",
                "varon(hector).",
                "varon(roberto).",
                "varon(alejandro).",
                "mujer(maria).",
                "mujer(cecilia).",
                "padre(juan, pepe).",
                "padre(juan, pepa).",
                "padre(hector, maria).",
                "padre(roberto, alejandro).",
                "padre(roberto, cecilia).",
                "hijo(X, Y) :- varon(X), padre(Y, X).",
                "hija(X, Y) :- mujer(X), padre(Y, X)."
        ));
        Database database = new Database();
        database.generateParsedDatabase(databaseList);
        Element element = new Element("indefinido(juana)");
        Assert.assertFalse(database.queryElementsAreInDatabase(element));
    }

    @Test
    public void queryFactTypeIsInDatabaseButNotTheFact() throws Exception, InvalidDatabaseException {
        ArrayList<String> databaseList = new ArrayList<String>(Arrays.asList(
                "varon(juan).",
                "varon(pepe).",
                "varon(hector).",
                "varon(roberto).",
                "varon(alejandro).",
                "mujer(maria).",
                "mujer(cecilia).",
                "padre(juan, pepe).",
                "padre(juan, pepa).",
                "padre(hector, maria).",
                "padre(roberto, alejandro).",
                "padre(roberto, cecilia).",
                "hijo(X, Y) :- varon(X), padre(Y, X).",
                "hija(X, Y) :- mujer(X), padre(Y, X)."
        ));
        Database database = new Database();
        database.generateParsedDatabase(databaseList);
        Element element = new Element("varon(juana)");
        Assert.assertFalse(database.queryElementsAreInDatabase(element));
    }

    @Test
    public void queryRuleTypeIsInDatabaseButNotTheRuleFacts() throws Exception, InvalidDatabaseException {
        ArrayList<String> databaseList = new ArrayList<String>(Arrays.asList(
                "varon(juan).",
                "varon(pepe).",
                "varon(hector).",
                "varon(roberto).",
                "varon(alejandro).",
                "mujer(maria).",
                "mujer(cecilia).",
                "padre(juan, pepe).",
                "padre(juan, pepa).",
                "padre(hector, maria).",
                "padre(roberto, alejandro).",
                "padre(roberto, cecilia).",
                "hijo(X, Y) :- varon(X), padre(Y, X).",
                "hija(X, Y) :- mujer(X), padre(Y, X)."
        ));
        Database database = new Database();
        System.out.println("1");
        database.generateParsedDatabase(databaseList);
        System.out.println("2");
        Element element = new Element("hijo(juan, pepe)");
        System.out.println("3");
        Assert.assertFalse(database.queryElementsAreInDatabase(element));
    }

    @Test
    public void queryFactIsInDatabase() throws Exception, InvalidDatabaseException {
        ArrayList<String> databaseList = new ArrayList<String>(Arrays.asList(
                "varon(juan).",
                "varon(pepe).",
                "varon(hector).",
                "varon(roberto).",
                "varon(alejandro).",
                "mujer(maria).",
                "mujer(cecilia).",
                "padre(juan, pepe).",
                "padre(juan, pepa).",
                "padre(hector, maria).",
                "padre(roberto, alejandro).",
                "padre(roberto, cecilia).",
                "hijo(X, Y) :- varon(X), padre(Y, X).",
                "hija(X, Y) :- mujer(X), padre(Y, X)."
        ));
        Database database = new Database();
        database.generateParsedDatabase(databaseList);
        Element element = new Element("varon(juan)");
        Assert.assertTrue(database.queryElementsAreInDatabase(element));
    }

    @Test
    public void queryRuleIsInDatabase() throws Exception, InvalidDatabaseException {
        ArrayList<String> databaseList = new ArrayList<String>(Arrays.asList(
                "varon(juan).",
                "varon(pepe).",
                "varon(hector).",
                "varon(roberto).",
                "varon(alejandro).",
                "mujer(maria).",
                "mujer(cecilia).",
                "padre(juan, pepe).",
                "padre(juan, pepa).",
                "padre(hector, maria).",
                "padre(roberto, alejandro).",
                "padre(roberto, cecilia).",
                "hijo(X, Y) :- varon(X), padre(Y, X).",
                "hija(X, Y) :- mujer(X), padre(Y, X)."
        ));
        Database database = new Database();
        System.out.println("1");
        database.generateParsedDatabase(databaseList);
        System.out.println("2");
        Element element = new Element("hijo(pepe, juan)");
        System.out.println("3");
        Assert.assertTrue(database.queryElementsAreInDatabase(element));
    }

}