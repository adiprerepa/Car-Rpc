package com.prerepa.car_rpc.database;

public class DatabaseCredentials {

    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;

    private DatabaseCredentials(String databaseUrl, String databaseUsername, String databasePassword) {
        this.databasePassword = databasePassword;
        this.databaseUrl = databaseUrl;
        this.databaseUsername = databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    public static class DatabaseCredentialsBuilder {

        private String databaseUrl;
        private String databaseUsername;
        private String databasePassword;

        public DatabaseCredentialsBuilder withDatabaseUrl(String databaseUrl) {
            this.databaseUrl = databaseUrl;
            return this;
        }

        public DatabaseCredentialsBuilder withDatabaseUsername(String databaseUsername) {
            this.databaseUsername = databaseUsername;
            return this;
        }

        public DatabaseCredentialsBuilder withDatabasePassword(String databasePassword) {
            this.databasePassword = databasePassword;
            return this;
        }

        public DatabaseCredentials build() {
            return new DatabaseCredentials(
                    databaseUrl, databaseUsername, databasePassword
            );
        }
    }
}
