package com.enviro.assessment.grad001.thabangmmako;

import com.enviro.assessment.grad001.thabangmmako.model.AccountProfileRepository;
import com.enviro.assessment.grad001.thabangmmako.service.FileParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;

@SpringBootApplication
@EnableConfigurationProperties(com.enviro.assessment.grad001.thabangmmako.AppProperties.class)
public class Application {

	private final FileParser fileParser;
	private final AccountProfileRepository accountProfileRepository;
	private final String imageFolderPath;

	public Application(FileParser fileParser, AccountProfileRepository accountProfileRepository, String imageFolderPath) {
		this.fileParser = fileParser;
		this.accountProfileRepository = accountProfileRepository;
		this.imageFolderPath = imageFolderPath;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	public void init() {
		// Create image folder if it doesn't exist
		File imageFolder = new File(imageFolderPath);
		if (!imageFolder.exists()) {
			imageFolder.mkdirs();
		}

		// Clear existing data from the database
		accountProfileRepository.deleteAll();

		// Specify the path to your CSV file
		File csvFile = new File("service/csvFile.csv");
		fileParser.parseCSV(csvFile);
	}
}
