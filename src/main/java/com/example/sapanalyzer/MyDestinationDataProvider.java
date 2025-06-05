package com.example.sapanalyzer;

import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyDestinationDataProvider implements DestinationDataProvider {
    private Map<String, Properties> destinations = new HashMap<>();
    private DestinationDataEventListener listener;

    public void addDestination(String destName, Properties properties) {
        destinations.put(destName, properties);
        if (listener != null) {
            listener.updated(destName);
        }
    }

    @Override
    public Properties getDestinationProperties(String destName) {
        return destinations.get(destName);
    }

    @Override
    public void setDestinationDataEventListener(DestinationDataEventListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean supportsEvents() {
        return true;
    }
}
