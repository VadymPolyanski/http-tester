package com.onseo.ht.util;

import com.onseo.ht.service.PropertiesService;

public class Const {
    public static final Integer BAD_RESPONSE = -1;
    public static final Integer SUCCESS_RESPONSE = 200;
    public static final Integer MAX_POOL_SIZE = 512;
    public static Integer AVAILABLE_THREADS = Runtime.getRuntime().availableProcessors() + 1;


    public static final String PROPERTY_FILE_NAME = "config.properties";

    private static final String REQUESTS_PER_SECOND_PROPERTY_NAME = "requests.per.second";
    private static final String URL_PROPERTY_NAME = "url";

    public static final String URL = PropertiesService.getProperty(URL_PROPERTY_NAME);
    public static final Integer REQUESTS_PER_SECOND = Integer.valueOf(PropertiesService.getProperty(REQUESTS_PER_SECOND_PROPERTY_NAME));
}
