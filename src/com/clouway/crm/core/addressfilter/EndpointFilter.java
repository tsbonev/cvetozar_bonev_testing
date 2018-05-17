package com.clouway.crm.core.addressfilter;

public class EndpointFilter {
    private final Endpoint[] endpoints;

    public EndpointFilter(Endpoint... endpoints) {
        this.endpoints = endpoints;
    }

    public boolean shouldFilter(String url) {
        for (Endpoint each : endpoints) {
            if (each.matches(url)) {
                return true;
            }
        }
        return false;
    }
}
