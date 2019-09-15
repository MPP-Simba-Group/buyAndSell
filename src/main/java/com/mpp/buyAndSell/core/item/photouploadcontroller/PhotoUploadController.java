package com.mpp.buyAndSell.core.item.photouploadcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PhotoUploadController {
	//public static String uploadDirectory=System.getProperty("User.dir")+"/uploads";
	public static String uploadDirectory="C:\\Temp\\uploads\\";
	
	@RequestMapping("/photo")
	public String UploadPage(Model model) {
		return "uploadview";
	}
	@RequestMapping("/photo/upload")
	public String upload(Model model, @RequestParam("files") MultipartFile[] files) {
		
		StringBuilder fileNames=new StringBuilder();
		for(MultipartFile file:files) {
			//Path fileNameAndPath=Paths.get(uploadDirectory, file.getOriginalFilename());
			Path fileNameAndPath=Paths.get(uploadDirectory, "photo");
			fileNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
		
		return "uploadstatusview";
		
	}
}
