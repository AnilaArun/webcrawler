package com.webcrawler.apiproject.enums;

/**
 * Enumeration of the travel location names
 */
public enum Location {
    BHD("Belfast George Best City"), LHR("London Heathrow"),COK("Kochi"),
    DXB("Dubai International"),BOS("Boston Logan International"), BWI("Baltimore Washington International"),
    DFW("Dallas Ft. Worth International"), ORY("Paris Orly"), LGW("Gatwick"), LTN("Luton"), DUB("Dublin"),
    ES("Spain"), BCN("Barcelona"), FLR("Florance"), MXP("Milan Malpensa"), DOH("Doha");

    private final String value;

    Location(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
