package kz.nixwins.periodictable.model;

public class ElementBuilder {

    private Integer id;
    private String symbol;
    private String name;
    private String latinName;
    private Integer atomicNumber;
    private Double atomicWeight;
    private String electronConfiguration;
    private String electronEnergyLevel;
    private String group;
    private String subGroup;
    private Integer period;
    private Integer row;
    private String elementType;
    private String electronEnergyType;
    private String oxide;
    private String volatileHydrogenid;
    private String  electronFormula;
    private String  valence;

    public Element build(){ return  new Element(this);}

    public ElementBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public ElementBuilder symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public ElementBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ElementBuilder latinName(String latinName) {
        this.latinName = latinName;
        return this;
    }

    public ElementBuilder atomicNumber(Integer atomicNumber) {
        this.atomicNumber = atomicNumber;
        return this;
    }

    public ElementBuilder atomicWeight(Double atomicWeight) {
        this.atomicWeight = atomicWeight;
        return this;
    }

    public ElementBuilder electronConfiguration(String electronConfiguration) {
        this.electronConfiguration = electronConfiguration;
        return this;
    }

    public ElementBuilder electronEnergyLevel(String electronEnergyLevel) {
        this.electronEnergyLevel = electronEnergyLevel;
        return this;
    }

    public ElementBuilder group(String group) {
        this.group = group;
        return this;
    }

    public ElementBuilder subGroup(String subGroup) {
        this.subGroup = subGroup;
        return this;
    }

    public ElementBuilder period(Integer period) {
        this.period = period;
        return this;
    }

    public ElementBuilder row(Integer row) {
        this.row = row;
        return this;
    }

    public ElementBuilder elementType(String elementType) {
        this.elementType = elementType;
        return this;
    }

    public ElementBuilder electronEnergyType(String electronEnergyType) {
        this.electronEnergyType = electronEnergyType;
        return this;
    }

    public ElementBuilder oxide(String oxide) {
        this.oxide = oxide;
        return this;
    }

    public ElementBuilder volatileHydrogenid(String volatileHydrogenid) {
        this.volatileHydrogenid = volatileHydrogenid;
        return this;
    }

    public ElementBuilder electronFormula(String electronFormula){
        this.electronFormula = electronFormula;
        return this;
    }

    public ElementBuilder valence(String valence){
        this.valence = valence;
         return this;
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

    public String getElectronFormula() {
        return electronFormula;
    }

    public String getValence() {
        return valence;
    }
}