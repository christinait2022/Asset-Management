package com.christina.assetmanagement.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * AttributeConverter for Status Enum
 *
 * @author Christina
 * @date 2022/07/01
 */
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    /**
     * for insert or update
     *
     * @param status
     * @return {@code String}
     */
    @Override
    public String convertToDatabaseColumn(Status status) {
        return status == null ? null : status.getCode();
    }

    /**
     * for selete
     *
     * @param code
     * @return {@code Status}
     */
    @Override
    public Status convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        for (Status status : Status.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status could not found.");
    }
}
