package com.paper.auxiliary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "moduleconfig")
public class EnvironmentConfig {

    private String pyinterpreter;
    private String pyconfigfile;
    private String similarity;
    private String correction;
    private String theme;

    @Value("file:D:/config.json")
    private Resource res;

    private static EnvironmentConfig ENVIRONMENTCONFIG;
    public static EnvironmentConfig getInstance()
    {
        if(ENVIRONMENTCONFIG == null)
        {
            ENVIRONMENTCONFIG = new EnvironmentConfig();
        }
        return ENVIRONMENTCONFIG;
    }

    public String getPyinterpreter() {
        return pyinterpreter;
    }

    public void setPyinterpreter(String pyinterpreter) {
        this.pyinterpreter = pyinterpreter;
    }

    public String getPyconfigfile() {
        return pyconfigfile;
    }

    public void setPyconfigfile(String pyconfigfile) {
        this.pyconfigfile = pyconfigfile;
    }

    public String getSimilarity() {
        return similarity;
    }

    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
