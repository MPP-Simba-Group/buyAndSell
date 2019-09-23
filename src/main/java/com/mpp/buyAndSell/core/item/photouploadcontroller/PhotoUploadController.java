package com.mpp.buyAndSell.core.item.photouploadcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/photo/")
@CrossOrigin
public class PhotoUploadController {
	public static String uploadDirectory="F:\\Projects\\java-projects\\MPP-Project\\buy-and-sell-ui\\BuyAndSell-UI\\src\\assets";

	@PostMapping("upload")
	public String upload(Model model, @RequestParam("file") MultipartFile file) {
		
		StringBuilder fileNames=new StringBuilder();
		Path fileNameAndPath=Paths.get(uploadDirectory, file.getOriginalFilename());
		new File(uploadDirectory).mkdirs();
		fileNames.append(file.getOriginalFilename());
		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return fileNameAndPath.toString();
	}
}
