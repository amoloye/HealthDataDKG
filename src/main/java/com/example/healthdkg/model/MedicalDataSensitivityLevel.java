package com.example.healthdkg.model;

public enum MedicalDataSensitivityLevel {

    CLOSED(0),
    OPEN_TO_SPECIFIC_DOCTOR(1),
    OPEN_TO_MEDICAL_SPECIALITY(2),
    OPEN_TO_EVERYONE(3);

    private final int value;

    MedicalDataSensitivityLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MedicalDataSensitivityLevel fromValue(int value) {
        for (MedicalDataSensitivityLevel level : values()) {
            if (level.getValue() == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid MedicalDataSensitivityLevel value: " + value);
    }
}