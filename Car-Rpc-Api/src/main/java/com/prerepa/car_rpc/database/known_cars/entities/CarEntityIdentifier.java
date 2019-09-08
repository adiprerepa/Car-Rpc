package com.prerepa.car_rpc.database.known_cars.entities;

import com.prerepa.car_rpc.database.base_entities.EntityIdentifier;

public class CarEntityIdentifier<Identifier> implements EntityIdentifier {

    private Identifier identifier;

    private CarEntityIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public String getIdentifierName() {
        return "controllerKey";
    }
}
