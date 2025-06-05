package com.example.sapanalyzer;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.ext.Environment;
import com.sap.conn.jco.ext.DestinationDataProvider;
import java.util.Properties;

public class SAPConnector {
    private String system;
    private String client;
    private String user;
    private String password;
    private String lang;

    private String destName;

    public SAPConnector(String system, String client, String user, String password, String lang) {
        this.system = system;
        this.client = client;
        this.user = user;
        this.password = password;
        this.lang = lang;
        this.destName = "MY_DEST";
        registerDestination();
    }

    private void registerDestination() {
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "localhost");
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "00");
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, client);
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, user);
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, password);
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, lang);
        MyDestinationDataProvider myProvider = new MyDestinationDataProvider();
        Environment.registerDestinationDataProvider(myProvider);
        myProvider.addDestination(destName, connectProperties);
    }

    public void ping() throws JCoException {
        JCoDestination dest = JCoDestinationManager.getDestination(destName);
        JCoFunction ping = dest.getRepository().getFunction("RFC_PING");
        if (ping == null) {
            throw new RuntimeException("RFC_PING not found");
        }
        ping.execute(dest);
    }

    public void mockCall() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
