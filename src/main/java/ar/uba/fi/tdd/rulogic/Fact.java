package ar.uba.fi.tdd.rulogic;

import java.util.ArrayList;
import java.util.HashMap;

public class Fact extends Element implements Cloneable {

    public Fact(String element) {
        super(element);
        System.out.println("IM CREATING FACT");
    }

    public Fact(Fact another) {
        this.mElement = another.mElement;
        this.mType = another.mType;
        this.mParameters = new ArrayList<String>(another.mParameters);
    }

    public void replaceParameters(HashMap<String, String> map) {
        System.out.println("9");
        System.out.println(map);
        System.out.println(mParameters);
        for (HashMap.Entry<String, String> entry : map.entrySet()){
            if(mParameters.indexOf(entry.getKey())>=0)
                mParameters.set(mParameters.indexOf(entry.getKey()), entry.getValue());
        }

    }
}
