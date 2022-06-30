package com.christina.assetmanagement.model;

public enum Status {
    Available("A","Available"),
    Assigned("S","Assigned"),
    Lost("L","Lost"),
    Recovered("R","Recovered");

    private String code;
    private String name;

    Status(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
