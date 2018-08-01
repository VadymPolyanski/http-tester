package com.onseo.ht.util;

public class Const {
    public static final Integer BAD_RESPONSE = -1;
    public static final Integer SUCCESS_RESPONSE = 200;
    public static final Integer OPTIMAL_POOL_SIZE = 32;
    public static Integer AVAILABLE_THREADS = Runtime.getRuntime().availableProcessors() + 1;


    static final String PROPERTY_FILE_NAME = "config.properties";

    private static final String REQUESTS_PER_SECOND_PROPERTY_NAME = "requests.per.second";
    private static final String URL_PROPERTY_NAME = "url";

    public static final String URL = PropertiesProvider.getProperty(URL_PROPERTY_NAME);
    public static final Integer REQUESTS_PER_SECOND = Integer.valueOf(PropertiesProvider.getProperty(REQUESTS_PER_SECOND_PROPERTY_NAME));
}
