package ar.uba.fi.tdd.rulogic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ElementTest {
    @Test
    public void getTypeOfElementWithOneParameter() {
        Element element = new Element("padre(juan).");
        Assert.assertEquals("padre", element.getType());
    }

    @Test
    public void getTypeOfElementWithMultipleParameters() {
        Element element = new Element("padre(juan, pepa).");
        Assert.assertEquals("padre", element.getType());
    }

    @Test
    public void getTypeOfOtherElementWithMultipleParameters() {
        Element element = new Element("tio(X, Y, Z):- varon(X),\thermano(X, Z),padre(Z, Y).");
        Assert.assertEquals("tio", element.getType());
    }

    @Test
    public void getParametersOfElementWithOneParameter() {
        Element element = new Element("padre(juan).");
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("juan")), element.getParameters());
    }

    @Test
    public void getParametersOfElementWithMultipleParameters() {
        Element element = new Element("padre(juan, pepa).");
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("juan", "pepa")), element.getParameters());
    }

    @Test
    public void getParametersOfOtherElementWithMultipleParameters() {
        Element element = new Element("tio(X, Y, Z):- varon(X),\thermano(X, Z),padre(Z, Y).");
        Assert.assertEquals(new ArrayList<String>(Arrays.asList("X", "Y", "Z")), element.getParameters());
    }

    @Test
    public void isNotOfSameType() {
        Element element = new Element("madre(juan, pepa).");
        Element other = new Element("padre(juan, pepa).");
        Assert.assertFalse(element.isOfSameType(other));
    }

    @Test
    public void isOfSameType() {
        Element element = new Element("padre(juan, pepa).");
        Element other = new Element("padre(marcelo, julio).");
        Assert.assertTrue(element.isOfSameType(other));
    }

    @Test
    public void doesntEquals() {
        Element element = new Element("padre(juan, pepa).");
        Element other = new Element("padre(marcelo, julio).");
        Assert.assertFalse(element.equals(other));
    }

    @Test
    public void equals() {
        Element element = new Element("padre(juan, pepa).");
        Element other = new Element("padre(juan, pepa).");
        Assert.assertTrue(element.equals(other));
    }

}