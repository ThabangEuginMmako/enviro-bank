package com.enviro.assessment.grad001.thabangmmako.service;

import com.enviro.assessment.grad001.thabangmmako.model.AccountProfileRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


@Service

public class FileParserImpl implements FileParser {

    private final AccountProfileRepository accountProfileRepository;
    private final String imageFolderPath;

    public FileParserImpl(AccountProfileRepository accountProfileRepository, @Value("${image.folder.path}") String imageFolderPath) {
        this.accountProfileRepository = accountProfileRepository;
        this.imageFolderPath = imageFolderPath;
    }

    @Override
    public void parseCSV(File csvFile) {
        try {
            List<String> lines = Files.readAllLines(csvFile.toPath());
            for (String line : lines) {
                String[] data = line.split(",");
                String name = data[0];
                String surname = data[1];
                String imageFormat = data[2];
                String imageData = data[3];

                File imageFile = convertCSVDataToImage(imageData, imageFormat);
                URI httpImageLink = createImageLink(imageFile);

                com.enviro.assessment.grad001.thabangmmako.model.AccountProfile accountProfile = new com.enviro.assessment.grad001.thabangmmako.model.AccountProfile();
                accountProfile.setName(name);
                accountProfile.setSurname(surname);
                accountProfile.setHttpImageLink(httpImageLink.toString());

                accountProfileRepository.save(accountProfile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public File convertCSVDataToImage(String base64ImageData) {
//        return null;
//    }

    @Override
    public File convertCSVDataToImage(String base64ImageData, String imageFormat) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64ImageData);
            String fileName = UUID.randomUUID() + "." + imageFormat;
            File imageFile = new File(imageFolderPath + File.separator + fileName);
            FileUtils.writeByteArrayToFile(imageFile, imageBytes);
            return imageFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public URI createImageLink(File imageFile) {
        try {
            return imageFile.toURI();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
