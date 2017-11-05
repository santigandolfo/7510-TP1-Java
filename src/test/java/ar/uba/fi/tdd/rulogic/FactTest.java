package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.Fact;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FactTest {

    @Test
    public void replaceParameters() {
        Fact fact = new Fact("padre(X, Y).");
        ArrayList<String> newParameters = new ArrayList<String>(Arrays.asList("juan", "pepa"));
        HashMap<String,String> newHash = new HashMap<String, String>();
        newHash.put("X","juan");
        newHash.put("Y","pepa");
        fact.replaceParameters(newHash);
        Assert.assertEquals(newParameters, fact.getParameters());
    }


    @Test
    public void cloneTest() {
        Fact fact = new Fact("padre(X, Y).");
        Fact other = new Fact(fact);
        ArrayList<String> oldParameters = new ArrayList<String>(Arrays.asList("X", "Y"));
        ArrayList<String> newParameters = new ArrayList<String>(Arrays.asList("juan", "pepa"));
        HashMap<String,String> newHash = new HashMap<String, String>();
        newHash.put("X","juan");
        newHash.put("Y","pepa");
        fact.replaceParameters(newHash);
        Assert.assertEquals(newParameters, fact.getParameters());
        Assert.assertEquals(oldParameters, other.getParameters());
    }

}