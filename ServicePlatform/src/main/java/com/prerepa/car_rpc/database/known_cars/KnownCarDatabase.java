package com.prerepa.car_rpc.database.known_cars;

import com.google.common.annotations.VisibleForTesting;
import com.prerepa.car_rpc.database.BaseDatabase;
import com.prerepa.car_rpc.database.DatabaseCredentials;
import com.prerepa.car_rpc.database.InsertionStatus;
import com.prerepa.car_rpc.database.known_cars.entities.CarEntityIdentifier;
import com.prerepa.car_rpc.database.known_cars.entities.KnownCarEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;

/**
 * Known Car Database which inherits from {@link BaseDatabase} using
 * {@link KnownCarEntity} and {@link CarEntityIdentifier} as Generic Constructors.
 */
public class KnownCarDatabase extends BaseDatabase<KnownCarEntity, CarEntityIdentifier<Integer>> {

    private String tableName;
    private static String queryKey_controllerKey = "controllerKey";
    private static String queryKey_ipAddress = "lastIP";
    private static String queryKey_lastControlled = "lastControlled";
    private static String queryKey_port = "lastPort";
    private static String queryKey_name = "name";

    @VisibleForTesting
    protected KnownCarDatabase() {
        // no op for testing
    }

    /**
     * The super() call is for initializing the connection with jdbc,
     * standard across all databases.
     * @param url
     * @param databaseUsername
     * @param databasePassword
     * @param tableName
     */
    private KnownCarDatabase(String url, String databaseUsername, String databasePassword, String tableName) {
        super(url, databaseUsername, databasePassword);
        this.tableName = tableName;
        /*
        If we were able to authenticate in super(), try to create a table.
         */
        if (super.databaseAuthenticationStatus) {
            createTable();
        }
    }

    /**
     * Insert an entity into the database
     * @param entity to insert
     * @return insertionStatus
     */
    @Override
    public InsertionStatus insertIntoDatabase(KnownCarEntity entity) {
        try {
            Statement statement = super.connection.createStatement();
            String insertStatement = String.format("INSERT INTO known_cars(%s, %s, %s, %s, %s) values (%d, '%s', %d, '%s', '%s');",
                    queryKey_controllerKey, queryKey_ipAddress, queryKey_port, queryKey_lastControlled, queryKey_name,
                    entity.getControllerKey(), entity.getIpAddress(), entity.getPort(), entity.getDiscoveryTime().toString(), entity.getName());
            statement.executeUpdate(insertStatement);
            return InsertionStatus.OK;
        } catch (SQLException s) {
            s.printStackTrace();
            return InsertionStatus.UNABLE_TO_INSERT;
        }
    }

    /**
     * Retrieve Known car entities with given {@link CarEntityIdentifier}.
     * @param entityIdentifier Identifier
     * @return ArrayList of CarEntities
     */
    @Override
    public ArrayList<KnownCarEntity> retrieveEntities(CarEntityIdentifier<Integer> entityIdentifier) {
        ArrayList<KnownCarEntity> retrievedCars = new ArrayList<>();
        try {
            Statement statement = super.connection.createStatement();
            // retrieves id-specific values
            String retrievalStatement = String.format("SELECT * FROM %s where `%s` = %d;", tableName, entityIdentifier.getIdentifierName(), entityIdentifier.getIdentifier());
            ResultSet retrievedResults = statement.executeQuery(retrievalStatement);
            while (retrievedResults.next()) {
                String ipAddress = retrievedResults.getString(queryKey_ipAddress);
                Integer port = retrievedResults.getInt(queryKey_port);
                String lastTime = retrievedResults.getString(queryKey_lastControlled);
                String name = retrievedResults.getString(queryKey_name);
                KnownCarEntity knownCarEntity = new KnownCarEntity.KnownCarEntityBuilder()
                        .withIpAddress(ipAddress)
                        .withPort(port)
                        .withTime(Instant.parse(lastTime))
                        .withName(name)
                        .withControllerKey(entityIdentifier.getIdentifier())
                        .build();
                retrievedCars.add(knownCarEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // we check this function when we call it, if there was an error
            // retrieving stuff
           retrievedCars = null;
        }
        return retrievedCars;
    }

    /**
     * Create a table - called only if we could authenticate,
     */
    @Override
    public void createTable() {
        String createTableInsert = String.format("create table %s (id smallint auto_increment, %s BIGINT, %s varchar(40), %s int, %s varchar(100), %s varchar(100), constraint pk primary key (id));",
                tableName, queryKey_controllerKey, queryKey_ipAddress, queryKey_port, queryKey_lastControlled, queryKey_name);
        try {
            Statement statement = super.connection.createStatement();
            statement.executeUpdate(createTableInsert);
        } catch (SQLSyntaxErrorException e) {
            // the error here would be that the table already exists
            // no - op
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Database builder.
     */
    public static class KnownCarDatabaseBuilder {

        DatabaseCredentials databaseCredentials;
        String tableName;

        public KnownCarDatabaseBuilder withDatabaseCredentials(DatabaseCredentials databaseCredentials) {
            this.databaseCredentials = databaseCredentials;
            return this;
        }

        public KnownCarDatabaseBuilder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public KnownCarDatabase build() {
            if (databaseCredentials.getDatabaseUsername() == null && databaseCredentials.getDatabasePassword() == null) {
                databaseCredentials.setDatabaseUsername("root");
                databaseCredentials.setDatabasePassword("");
            } else if (databaseCredentials.getDatabasePassword() == null) {
                databaseCredentials.setDatabasePassword("");
            } else if (databaseCredentials.getDatabaseUrl() == null) {
                databaseCredentials.setDatabaseUrl("no url - should throw error");
            } else if (tableName == null) {
                tableName = "known_cars";
            }
            return new KnownCarDatabase(
                    databaseCredentials.getDatabaseUrl(), databaseCredentials.getDatabaseUsername(), databaseCredentials.getDatabasePassword(), tableName
            );
        }
    }
}
