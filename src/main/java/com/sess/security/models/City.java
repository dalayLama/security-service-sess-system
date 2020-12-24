package com.sess.security.models;

public class City {

    private final long id;

    private final String address;

    public City(long id, String address) {
        this.id = id;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

}
