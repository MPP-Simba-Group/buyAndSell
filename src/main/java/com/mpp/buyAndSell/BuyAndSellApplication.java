package com.mpp.buyAndSell;
import com.mpp.buyAndSell.core.item.photouploadcontroller.PhotoUploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class BuyAndSellApplication {

	public static void main(String[] args) {
		new File(PhotoUploadController.uploadDirectory).mkdir();
		SpringApplication.run(BuyAndSellApplication.class, args);
	}

}
