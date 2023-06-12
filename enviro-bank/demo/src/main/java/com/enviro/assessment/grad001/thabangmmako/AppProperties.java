package com.enviro.assessment.grad001.thabangmmako;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String imageFolderPath;

    public String getImageFolderPath() {
        return imageFolderPath;
    }

    public void setImageFolderPath(String imageFolderPath) {
        this.imageFolderPath = imageFolderPath;
    }
}

