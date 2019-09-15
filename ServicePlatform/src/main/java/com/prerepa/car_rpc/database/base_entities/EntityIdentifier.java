package com.prerepa.car_rpc.database.base_entities;

/**
 * Identifier type for pulling from the mysql database.
 * @param <Identifier>
 * @author aditya
 */
public interface EntityIdentifier<Identifier> {

    Identifier getIdentifier();

    String getIdentifierName();
}
