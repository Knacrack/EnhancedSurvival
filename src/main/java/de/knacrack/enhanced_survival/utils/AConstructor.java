package de.knacrack.enhanced_survival.utils;

public abstract class AConstructor implements IRegistrator {

    private final String nameOfConstructor;



    public AConstructor(String name) {
        nameOfConstructor = name;
    }



    public String getName() {
        return nameOfConstructor;
    }
}
