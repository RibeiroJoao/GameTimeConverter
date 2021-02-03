package com.company.gametimeconverter;

/**
 * It is a Function<String, String> that converts an input into an output. In this case gateTime formats.
 * Joao's implementation to a game time format converter. +Info: /src/main/resources/*.pdf
 *
 * @Author Joao Peixe Ribeiro
 */
public class RibeiroGameTimeConverter {
    private static final String REGEX_TO_VALIDATE = "(\\[)(" + Periods.toStringRegex() + ")(\\])( )(\\d{1,2}:[0-5][0-9]\\.\\d{3})";

    public String convertGameTime(String inputGameTime) {
        if (validFormat(inputGameTime)) {
            return translateValidatedGameTime(inputGameTime);
        }
        return "INVALID";
    }

    private boolean validFormat(String inputGameTime) {
        return inputGameTime.matches(REGEX_TO_VALIDATE);
    }

    private String translateValidatedGameTime(String inputGameTime) {
        String shortPeriod = inputGameTime.substring(1, 3);
        String inputTime = inputGameTime.substring(4).replaceFirst(" ", "");
        int minutes = getMinutes(inputTime.substring(0, inputTime.indexOf(':')));
        String completeTime;

        if (isOverTime(Periods.getPeriodFromString(shortPeriod), minutes)) {
            if (shortPeriod.equals(Periods.H1.toString())) {
                completeTime = checkOverTimeAndGetCompleteTime(45, minutes, inputTime);
            } else {
                completeTime = checkOverTimeAndGetCompleteTime(90, minutes, inputTime);
            }
        } else {
            completeTime = getTime(inputTime);
        }

        return (completeTime + " - " + getLongPeriod(shortPeriod));
    }

    private String checkOverTimeAndGetCompleteTime(int minuteMarker, int minutes, String inputTime) {
        int seconds = getSeconds(inputTime.substring(inputTime.indexOf(':') + 1));

        return minuteMarker + ":00 +" + String.format("%02d", (minutes - minuteMarker)) + ":" + String.format("%02d", seconds);
    }

    // Checks overTime in order to print extended time "+XX:XX"
    private boolean isOverTime(Periods period, int minutes) {
        boolean isOverTime = false;

        if (minutes >= 45 && (period == Periods.H1)) {
            isOverTime = true;
        } else if (minutes >= 90 && (period == Periods.H2 || period == Periods.FT)) {
            isOverTime = true;
        }

        return isOverTime;
    }

    private String getTime(String gameTime) {
        int minutes = getMinutes(gameTime.substring(0, gameTime.indexOf(':')));
        int seconds = getSeconds(gameTime.substring(gameTime.indexOf(':') + 1));

        return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }

    private int getSeconds(String inputSeconds) {
        return (int) Math.round(Double.parseDouble(inputSeconds.replace(" ", "")));
    }

    private int getMinutes(String inputMinutes) {
        return Integer.parseInt(inputMinutes);
    }

    private String getLongPeriod(String shortPeriod) {
        return Periods.of(shortPeriod);
    }
}