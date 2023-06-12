package com.enviro.assessment.grad001.thabangmmako.controller;

import com.enviro.assessment.grad001.thabangmmako.model.AccountProfile;
import com.enviro.assessment.grad001.thabangmmako.model.AccountProfileRepository;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {

    private final AccountProfileRepository accountProfileRepository;
    private final String imageFolderPath;

    public ImageController(AccountProfileRepository accountProfileRepository, String imageFolderPath) {
        this.accountProfileRepository = accountProfileRepository;
        this.imageFolderPath = imageFolderPath;
    }

    @GetMapping("/{name}/{surname}/{fileName}")
    public FileSystemResource getHttpImageLink(@PathVariable String name, @PathVariable String surname, @PathVariable String fileName) {
        Optional<AccountProfile> accountProfileOptional = accountProfileRepository.findByNameAndSurname(name, surname);
        if (accountProfileOptional.isPresent()) {
            AccountProfile accountProfile = accountProfileOptional.get();
            String filePath = imageFolderPath + File.separator + fileName;
            File imageFile = new File(filePath);
            if (imageFile.exists()) {
                return new FileSystemResource(imageFile);
            }
        }
        return null;
    }
}
