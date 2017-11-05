package ar.uba.fi.tdd.rulogic;

import java.util.ArrayList;
import java.util.Arrays;

class Element {

    protected String mElement;
    protected String mType;
    protected ArrayList<String> mParameters;

    public Element(){

    }

    public Element(String element) {
        System.out.println("IM CREATING ELEMENT: "+element);
        element = element.replaceAll("\\s+","").replaceAll("\t", "");
        System.out.println("FORMATED ELEMENT: "+element);
        this.mElement = element;
        this.mType = element.substring(0, element.indexOf("("));
        this.mParameters = new ArrayList<String>(Arrays.asList(element.substring(element.indexOf("(") + 1, element.indexOf(")")).split(",")));
    }

    String getType() {
        return mType;
    }

    ArrayList<String> getParameters() {
        return mParameters;
    };

    boolean isOfSameType(Element other) {
        return (this.getType().equals(other.getType()));
    }

    boolean equals(Element other){
        return (this.isOfSameType(other) && this.getParameters().equals(other.getParameters()));
    }
}
