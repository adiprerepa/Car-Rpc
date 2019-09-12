package com.prerepa.car_rpc.database.base_entities;

public interface EntityIdentifier<Identifier> {

    Identifier getIdentifier();

    String getIdentifierName();
}
