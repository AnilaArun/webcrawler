package com.webcrawler.apiproject.enums;

public enum Location {
    BHD("Belfast George Best City"), LHR("London Heathrow"),COK("Kochi"),
    DXB("Dubai International"),BOS("Boston Logan International"), BWI("Baltimore Washington International"),
    DFW("Dallas Ft. Worth International");

    private final String value;

    Location(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
