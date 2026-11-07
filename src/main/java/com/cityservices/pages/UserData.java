package com.cityservices.pages;

public record UserData(
        String email1,
        String email2,
        String email3,
        String email4,
        String email5
) {
    public static final UserData USERS = new UserData(
            "john@example.com", "mary.clerk@city.gov", "supervisor@city.gov", "field.agent@city.gov", "admin@city.gov"
    );
}