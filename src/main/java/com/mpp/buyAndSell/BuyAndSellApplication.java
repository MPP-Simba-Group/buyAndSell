package com.mpp.buyAndSell;
import com.mpp.buyAndSell.core.post.photouploadcontroller.PhotoUploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.io.File;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class BuyAndSellApplication {

	public static void main(String[] args) {
		new File(PhotoUploadController.uploadDirectory).mkdir();
		SpringApplication.run(BuyAndSellApplication.class, args);
	}

}
