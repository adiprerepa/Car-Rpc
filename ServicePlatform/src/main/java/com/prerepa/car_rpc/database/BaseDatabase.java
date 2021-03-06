package com.prerepa.car_rpc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Base Database that is the super class for Database Implementations.
 * @param <DatabaseEntity>
 * @param <EntityIdentifier>
 */
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public abstract class BaseDatabase<DatabaseEntity extends com.prerepa.car_rpc.database.base_entities.DatabaseEntity,
        EntityIdentifier extends com.prerepa.car_rpc.database.base_entities.EntityIdentifier> {

    public Connection connection;
    protected boolean databaseAuthenticationStatus = false;

    protected BaseDatabase() { }

    /**
     * Initialize database connection - only done in super()
     * @param databaseUrl url of db (mysql)
     * @param databaseUsername username (with permissions on all databases)
     * @param databasePassword password
     */
    public BaseDatabase(String databaseUrl, String databaseUsername, String databasePassword) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting to mySQL database...");
            connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
            System.out.println("Connected to mySQL database successfully!");
            databaseAuthenticationStatus = true;
        } catch (SQLException sqlException) {
            System.err.println("An error happened with sql");
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Unable to find class");
        }
    }

    public abstract InsertionStatus insertIntoDatabase(DatabaseEntity entity);

    public abstract ArrayList<DatabaseEntity> retrieveEntities(EntityIdentifier entityIdentifier);

    public abstract void createTable();
}
