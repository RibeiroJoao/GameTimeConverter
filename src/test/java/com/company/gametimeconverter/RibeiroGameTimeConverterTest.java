package com.company.gametimeconverter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RibeiroGameTimeConverterTest {

    private RibeiroGameTimeConverter dateConverter;

    @Before
    public void setup() {
        dateConverter = new RibeiroGameTimeConverter();
    }

    @Test
    public void testTranslate_weirdBehaviour_utf8() {
        //TODO clarify requirements: What is the correct format of the '-' char
        assertNotEquals("00:00 – PRE_MATCH", dateConverter.convertGameTime("[PM] 0:00.000"));
    }

    @Test
    public void testTranslate_checkEnumsWithContent() {
        //TODO clarify requirements: Must I verify these?
        assertNotEquals("00:00 - PRE_MATCH", dateConverter.convertGameTime("[PM] 1:00.000"));
        assertNotEquals("45:00 +00:00 - HALF_TIME", dateConverter.convertGameTime("[HT] 45:10.000"));
        assertNotEquals("90:00 +00:00 - FULL_TIME", dateConverter.convertGameTime("[FT] 90:10.000"));
    }

    @Test
    public void testTranslate_pmZero_success() {
        assertEquals("00:00 - PRE_MATCH", dateConverter.convertGameTime("[PM] 0:00.000"));
    }

    @Test
    public void testTranslate_h1Quarter_success() {
        assertEquals("00:15 - FIRST_HALF", dateConverter.convertGameTime("[H1] 0:15.025"));
    }

    @Test
    public void testTranslate_h1Normal_success() {
        assertEquals("03:08 - FIRST_HALF", dateConverter.convertGameTime("[H1] 3:07.513"));
    }

    @Test
    public void testTranslate_h1LastQuarter_success() {
        assertEquals("45:00 +00:00 - FIRST_HALF", dateConverter.convertGameTime("[H1] 45:00.001"));
    }

    @Test
    public void testTranslate_h1ThreeQuartersPlus_success() {
        assertEquals("45:00 +01:16 - FIRST_HALF", dateConverter.convertGameTime("[H1] 46:15.752"));
    }

    @Test
    public void testTranslate_htThreeQuarters_success() {
        assertEquals("45:00 - HALF_TIME", dateConverter.convertGameTime("[HT] 45:00.000"));
    }

    @Test
    public void testTranslate_h2ThreeQuarters_success() {
        assertEquals("45:01 - SECOND_HALF", dateConverter.convertGameTime("[H2] 45:00.500"));
    }

    @Test
    public void testTranslate_h2Ninety_success() {
        assertEquals("90:00 +00:01 - SECOND_HALF", dateConverter.convertGameTime("[H2] 90:00.908"));
    }

    @Test
    public void testTranslate_ft_success() {
        assertEquals("90:00 +00:00 - FULL_TIME", dateConverter.convertGameTime("[FT] 90:00.000"));
    }

    @Test
    public void testTranslate_ftPlus_success() {
        assertEquals("90:00 +00:02 - FULL_TIME", dateConverter.convertGameTime("[FT] 90:02.400"));
    }

    @Test
    public void testTranslate_noPeriod_invalid() {
        assertEquals("INVALID", dateConverter.convertGameTime("90:00"));
    }

    @Test
    public void testTranslate_periodNotValid_invalid() {
        assertEquals("INVALID", dateConverter.convertGameTime("[H3] 90:00.000"));
    }

    @Test
    public void testTranslate_wrongFormat_invalid() {
        assertEquals("INVALID", dateConverter.convertGameTime("[PM] -10:00.000"));
    }

    @Test
    public void testTranslate_noFormat_invalid() {
        assertEquals("INVALID", dateConverter.convertGameTime("FOO"));
    }
}