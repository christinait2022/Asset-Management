package com.christina.assetmanagement.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status,String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
       return status == null? null:status.getCode();
    }

    @Override
    public Status convertToEntityAttribute(String code) {
        if(code == null){
            return null;
        }
        for(Status status : Status.values()){
            if(status.getCode().equals(code)){
                return status;
            }
        }
        throw new IllegalArgumentException("Status could not found.");
    }
}
