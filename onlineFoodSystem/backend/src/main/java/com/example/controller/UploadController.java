package com.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UploadController {

 
	 @RequestMapping("/upload")
	    @ResponseBody      
	    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
		 File fileee = null;
	        if (!file.isEmpty()) {
	            try {      
	                /*    
	                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到    
	                 * d:/files大家是否能实现呢？ 等等;    
	                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；    
	                 * 3、文件格式; 4、文件大小的限制;    
	                 */      
	            	fileee = new File("D:\\Users\\张雨轩\\eclipse-workspace\\onlineFoodSystem\\src\\main\\resources\\static\\" + file.getOriginalFilename());
	                BufferedOutputStream out = new BufferedOutputStream(      
	                        new FileOutputStream(fileee));      
	                System.out.println(file.getName());  
	                System.out.println(fileee.getAbsolutePath());
	                out.write(file.getBytes());      
	                out.flush();      
	                out.close();      
	            } catch (FileNotFoundException e) {      
	                e.printStackTrace();      
	                return "上传失败," + e.getMessage();      
	            } catch (IOException e) {      
	                e.printStackTrace();      
	                return "上传失败," + e.getMessage();      
	            }      
	      
	            return file.getOriginalFilename();      
	      
	        } else {      
	            return "上传失败，因为文件是空的.";      
	        }      
	    }      
}
