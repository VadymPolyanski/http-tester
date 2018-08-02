package com.onseo.ht.service;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class PropertiesService {
    private final String PROPERTY_FILE_NAME = "app.conf";

    private final String RPS_PROPERTY_NAME = "rps.number";
    private final String URL_PROPERTY_NAME = "url.name";
    private final String POOL_SIZE_PROPERTY_NAME = "httpPool.size";

    private Config config = ConfigFactory.parseResources(PROPERTY_FILE_NAME);

    public Integer getRps() {
        return config.getInt(RPS_PROPERTY_NAME);
    }

    public String gerURL() {
        return config.getString(URL_PROPERTY_NAME);
    }

    public Integer gerMaxPoolSize() {
        return config.getInt(POOL_SIZE_PROPERTY_NAME);
    }
}
