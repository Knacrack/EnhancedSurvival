package de.knacrack.enhanced_survival.utils;

import de.knacrack.enhanced_survival.utils.register.IRegister;

public abstract class AConstructor {

    private final String nameOfConstructor;



    public AConstructor(String name) {
        nameOfConstructor = name;
    }



    public String getName() {
        return nameOfConstructor;
    }
}
