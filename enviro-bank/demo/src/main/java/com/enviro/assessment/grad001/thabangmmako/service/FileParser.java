package com.enviro.assessment.grad001.thabangmmako.service;

import java.io.File;
import java.net.URI;
public interface FileParser {

    void parseCSV(File csvFile);
    //File convertCSVDataToImage(String base64ImageData);

    File convertCSVDataToImage(String base64ImageData, String imageFormat);

    URI createImageLink(File fileImage);
}
