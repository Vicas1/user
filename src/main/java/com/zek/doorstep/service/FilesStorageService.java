/**
 * 
 */
package com.zek.doorstep.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zek.doorstep.configs.EnvironmentConfiguration;
import com.zek.doorstep.exception.MyFileNotFoundException;
import com.zek.doorstep.pojos.FileInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * @author EK
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
//@PropertySource("classpath:application.properties")
public class FilesStorageService {
	private final EnvironmentConfiguration environmentConfiguration;
	
//	@NotNull @Value("${file.upload.dir}") 
//	String uploadDir;
	
//	@Autowired
//	private Environment env;
	
//	@Value("${spring.file.upload}")
	private String downDir = "uploads";//= env.getProperty("file.down-dir");
	
	private Path root = Paths.get(downDir);

//	@Autowired
//	 public FilesStorageService() {
//		init();
//	}
	
	
	
	
	public void init() {
		try {
			this.downDir = environmentConfiguration.getUpload();
			log.info(downDir);
			this.root = Paths.get(downDir);
			log.info(root.toString());
			log.info(root.toAbsolutePath().normalize().toString());
			if (!Files.exists(root.toAbsolutePath().normalize())) 
			Files.createDirectory(root.toAbsolutePath().normalize());
		} catch (IOException e) {
//			log.error(e.getLocalizedMessage());
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	public String save(MultipartFile file) {
		try {
			if (!Files.exists(root.toAbsolutePath().normalize())) {
		        Files.createDirectories(root.toAbsolutePath().normalize());
		    }
			String fileName = System.currentTimeMillis()+"_"+StringUtils.cleanPath(file.getOriginalFilename());
			Files.copy(file.getInputStream(), this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	public FileInfo create(MultipartFile file) {
		FileInfo fileInfo = null;
		try {
			if (!Files.exists(root.toAbsolutePath().normalize())) {
		        Files.createDirectories(root.toAbsolutePath().normalize());
		    }
			String fileName = System.currentTimeMillis()+"_"+StringUtils.cleanPath(file.getOriginalFilename());
			Files.copy(file.getInputStream(), this.root.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
//			fileInfo = new FileInfo(fileName, this.downDir +File.pathSeparatorChar+fileName, file.getContentType(),null,  file.getSize(), created, lastAccessed, lastModified, System.currentTimeMillis());
			
			return fileInfo;
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	
	public Resource load(String filename) {
		try {
			Path file = root.toAbsolutePath().resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new MyFileNotFoundException("Could not load the files!");
		}
	}
	
//	 @GetMapping("/downloadFromDB/{fileName}")
//	    ResponseEntity<byte[]> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {
//
//	        FileDocument doc = docFileDao.findByFileName(fileName);
//
//	        String mimeType = request.getServletContext().getMimeType(doc.getFileName());
//
//	        return ResponseEntity.ok()
//	                .contentType(MediaType.parseMediaType(mimeType))
////	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName="+resource.getFilename())
//	                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + doc.getFileName())
//	                .body(doc.getDocFile());
//	    }
	
	
}
