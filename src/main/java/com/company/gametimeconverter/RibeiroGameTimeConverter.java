package com.company.gametimeconverter;

/**
 * It is a Function<String, String> that converts an input into an output. In this case gateTime formats.
 * Joao's implementation to a game time format converter. +Info: /src/main/resources/*.pdf
 *
 * @Author Joao Peixe Ribeiro
 */
public class RibeiroGameTimeConverter {
    private static final String REGEX_TO_VALIDATE = "(\\[)(" + Periods.toStringRegex() + ")(\\])( )(\\d{1,2}:[0-5][0-9]\\.\\d{3})";

    /**
     * Apply function. Converts given gameTime into wanted format.
     *
     * @param inputGameTime given by user.
     * @return game time in wanted format. "INVALID" if input does not follow wanted regex.
     */
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
        Periods shortPeriod = Periods.getPeriodFromString(inputGameTime.substring(1, 3));
        String inputTime = inputGameTime.substring(4).replaceFirst(" ", "");
        int minutes = getMinutes(inputTime.substring(0, inputTime.indexOf(':')));
        int seconds = getSeconds(inputGameTime.substring(inputGameTime.indexOf(':') + 1));
        String completeTime;

        if (isOverTime(shortPeriod, minutes)) {
            if (shortPeriod == Periods.H1) {
                completeTime = checkOverTimeAndGetCompleteTime(45, minutes, seconds);
            } else {
                completeTime = checkOverTimeAndGetCompleteTime(90, minutes, seconds);
            }
        } else {
            completeTime = getTimeAsOutput(minutes, seconds);
        }

        return (completeTime + " - " + shortPeriod.getLongPeriod());
    }

    private int getMinutes(String inputMinutes) {
        return Integer.parseInt(inputMinutes);
    }

    private int getSeconds(String inputSeconds) {
        return (int) Math.round(Double.parseDouble(inputSeconds.replace(" ", "")));
    }

    private boolean isOverTime(Periods period, int minutes) {
        // Checks overTime in order to print extended time "+XX:XX" or not
        boolean isOverTime = false;

        if (minutes >= 45 && (period == Periods.H1)) {
            isOverTime = true;
        } else if (minutes >= 90 && (period == Periods.H2 || period == Periods.FT)) {
            isOverTime = true;
        }

        return isOverTime;
    }

    private String checkOverTimeAndGetCompleteTime(int minuteMarker, int minutes, int seconds) {
        return minuteMarker + ":00 +" + getTimeAsOutput((minutes - minuteMarker), seconds);
    }

    private String getTimeAsOutput(int minutes, int seconds) {
        return String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }
}