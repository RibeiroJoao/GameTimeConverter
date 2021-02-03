package com.company.gametimeconverter;

/**
 * Beware that the short Periods length is expected to be 2, in GameTimeConverterApplication.extractLongPeriod().
 *
 * @Author Joao Peixe Ribeiro
 */
enum Periods {
    PM("PRE_MATCH"), H1("FIRST_HALF"), HT("HALF_TIME"), H2("SECOND_HALF"), FT("FULL_TIME");

    private String longPeriod;

    Periods(String longPeriod) {
        this.longPeriod = longPeriod;
    }

    private String getLongPeriod() {
        return this.longPeriod;
    }

    static String of(String shortPeriod) {
        for (Periods period : Periods.values()) {
            if (period.toString().equals(shortPeriod)) {
                return period.getLongPeriod();
            }
        }
        return null;
    }

    static Periods getPeriodFromString(String shortPeriod) {
        for (Periods period : Periods.values()) {
            if (period.toString().equals(shortPeriod)) {
                return period;
            }
        }
        return null;
    }

    static String toStringRegex() {
        StringBuilder strRegex = new StringBuilder();
        String prefix = "";
        for (Periods period : Periods.values()) {
            strRegex.append(prefix);
            prefix = "|";
            strRegex.append(period.toString());
        }

        return strRegex.toString();
    }
}