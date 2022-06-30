package com.christina.assetmanagement.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    Available("A", "Available"),
    Assigned("S", "Assigned"),
    Recovered("R", "Recovered");

    private String code;
    private String name;

    Status(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
