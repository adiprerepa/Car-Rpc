package com.prerepa.car_rpc.database.known_cars.entities;

import com.prerepa.car_rpc.database.base_entities.DatabaseEntity;

import java.time.Instant;

/**
 * Known Car Entity that we work with on Server-Side, all network
 * transactions occur with {@link com.car_rpc.generated.CarEntity}.
 */
public class KnownCarEntity implements DatabaseEntity {

    private Integer controllerKey;
    private String ipAddress;
    private Integer port;
    private Instant discoveryTime;
    private String name;

    /**
     * For builder
     * @param controllerKey controllerKey for database
     * @param ipAddress for connecting
     * @param port for connecting
     * @param name to identify unique cars with the same controllerKey
     */
    private KnownCarEntity(Integer controllerKey, String ipAddress, Integer port, String name) {
        this.controllerKey = controllerKey;
        this.discoveryTime = Instant.now();
        this.ipAddress = ipAddress;
        this.port = port;
        this.name = name;
    }

    private KnownCarEntity(Integer controllerKey, String ipAddress, Integer port, Instant discoveryTime, String name) {
        this(controllerKey, ipAddress, port, name);
        this.discoveryTime = discoveryTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public Instant getDiscoveryTime() {
        return discoveryTime;
    }

    public int getControllerKey() {
        return controllerKey;
    }

    public String getName() {
        return name;
    }

    /**
     * Builder for {@link KnownCarEntity}
     */
    public static class KnownCarEntityBuilder {

        private Integer controllerKey;
        private String ipAddress;
        private Integer port;
        private Instant discoveryTime;
        private String name;

        public KnownCarEntityBuilder withControllerKey(Integer controllerKey) {
            this.controllerKey = controllerKey;
            return this;
        }

        public KnownCarEntityBuilder withIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public KnownCarEntityBuilder withPort(Integer port) {
            this.port = port;
            return this;
        }

        public KnownCarEntityBuilder withTime(Instant discoveryTime) {
            this.discoveryTime = discoveryTime;
            return this;
        }

        public KnownCarEntityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public KnownCarEntity build() {
            if (discoveryTime == null) {
                return new KnownCarEntity(controllerKey, ipAddress, port, name);
            } else {
                return new KnownCarEntity(controllerKey, ipAddress, port, discoveryTime, name);
            }
        }


    }
}
