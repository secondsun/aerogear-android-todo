/*
 * Copyright (c) RedHat, 2012.
 */

package org.aerogear.android;

/**
 * AeroGear utility class containing useful methods for lifecycle, etc.
 *
 * TODO: This is just a sketch - needs fleshing out and should live in separate project
 */
public class AeroGear {
    private static String apiKey;
    private static String rootUrl;

    public static void initialize(String apiKey, String rootUrl) {
        AeroGear.apiKey = apiKey;
        AeroGear.rootUrl = rootUrl;
    }
}
