package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.exceptions.InvalidDatabaseException;
import ar.uba.fi.tdd.rulogic.exceptions.InvalidQueryException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class InterpreterTest {

    private ArrayList<String> database;
    private Interpreter interpreter;

    @Before
    public void setUp() {
        this.database = new ArrayList<String>(Arrays.asList(
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


    }

    @Test(expected = InvalidQueryException.class)
    public void invalidQuery() throws InvalidDatabaseException, InvalidQueryException {
        this.interpreter = new Interpreter(database);
        this.interpreter.checkQuery("hijo(X, Y) :- varon(X), mujer(Y), padre(Y, X).");

    }

    @Test
    public void queryFactNotInInDatabase() throws InvalidDatabaseException, InvalidQueryException {
        this.interpreter = new Interpreter(database);
        Assert.assertFalse(this.interpreter.checkQuery("varon(lucas)"));

    }

    @Test
    public void queryFactInInDatabase() throws InvalidDatabaseException, InvalidQueryException {
        this.interpreter = new Interpreter(database);
        Assert.assertTrue(this.interpreter.checkQuery("varon(juan)"));

    }

    @Test
    public void queryRuleNotInInDatabase() throws InvalidDatabaseException, InvalidQueryException {
        this.interpreter = new Interpreter(database);
        Assert.assertFalse(this.interpreter.checkQuery("hijo(juan, pepe)"));

    }

    @Test
    public void queryRuleInInDatabase() throws InvalidDatabaseException, InvalidQueryException {
        this.interpreter = new Interpreter(database);
        Assert.assertTrue(this.interpreter.checkQuery("hijo(pepe, juan)"));

    }
}