package kz.nixwins.periodictable.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by nixwins on 11/23/16.
 */

public class Element implements Comparable, Serializable{

    private Integer id;
    private String  symbol;
    private String  name;
    private String  latinName;
    private Integer atomicNumber;
    private Double  atomicWeight;
    private String  electronConfiguration;
    private String  electronEnergyLevel;
    private String  group;
    private String  subGroup;
    private Integer period;
    private Integer row;
    private String  elementType;
    private String  electronEnergyType;
    private String  oxide;
    private String  volatileHydrogenid;
    private String  electronFormula;
    private String  valence;



    public Element(ElementBuilder elementBuilder) {

        this.id                         = elementBuilder.getId();
        this.symbol                     = elementBuilder.getSymbol();
        this.name                       = elementBuilder.getName();
        this.latinName                  = elementBuilder.getLatinName();
        this.atomicNumber               = elementBuilder.getAtomicNumber();
        this.atomicWeight               = elementBuilder.getAtomicWeight();
        this.electronConfiguration      = elementBuilder.getElectronConfiguration();
        this.electronEnergyLevel        = elementBuilder.getElectronEnergyLevel();
        this.group                      = elementBuilder.getGroup();
        this.subGroup                   = elementBuilder.getSubGroup();
        this.period                     = elementBuilder.getPeriod();
        this.row                        = elementBuilder.getRow();
        this.elementType                = elementBuilder.getElementType();
        this.electronEnergyType         = elementBuilder.getElectronEnergyType();
        this.oxide                      = elementBuilder.getOxide();
        this.volatileHydrogenid         = elementBuilder.getVolatileHydrogenid();
        this.electronFormula            = elementBuilder.getElectronFormula();
        this.valence                    = elementBuilder.getValence();
    }

    @Override
    public int compareTo(Object o) {

        Element element = (Element) o;
        int compareInt = this.atomicNumber.compareTo(element.atomicNumber);
        if(compareInt < 0) return  1;  // this.atomicNumber bigger
        if(compareInt > 0) return  -1; // element.atomicNumber bigger
        return 0; //equals

    }

    public String getElectronFormula() {
        return electronFormula;
    }

    public String getValence() {
        return valence;
    }

    public Integer getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getLatinName() {
        return latinName;
    }

    public Integer getAtomicNumber() {
        return atomicNumber;
    }

    public Double getAtomicWeight() {
        return atomicWeight;
    }

    public String getElectronConfiguration() {
        return electronConfiguration;
    }

    public String getElectronEnergyLevel() {
        return electronEnergyLevel;
    }

    public String getGroup() {
        return group;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public Integer getPeriod() {
        return period;
    }

    public Integer getRow() {
        return row;
    }

    public String getElementType() {
        return elementType;
    }

    public String getElectronEnergyType() {
        return electronEnergyType;
    }

    public String getOxide() {
        return oxide;
    }

    public String getVolatileHydrogenid() {
        return volatileHydrogenid;
    }

    public List<String> getFormulaAsList(){

        List<String> formula = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(electronFormula, " ");

        while (stringTokenizer.hasMoreTokens()){
            formula.add(stringTokenizer.nextToken());
        }
        return formula;
    }
}
