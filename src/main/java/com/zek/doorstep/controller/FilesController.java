package com.zek.doorstep.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zek.doorstep.pojos.FileInfo;
import com.zek.doorstep.pojos.ResponseModel;
import com.zek.doorstep.service.FilesStorageService;
import com.zek.doorstep.util.CommonConstant;

/**
 * @author EK
 *
 */
@Controller
@CrossOrigin("*")
public class FilesController {
	@Autowired
	FilesStorageService storageService;
	
	@Value("${spring.file.upload}")
	private String downDir;
	
	@Value("${spring.file.uploadMax}")
	private int maxCount;
	
//	String downDir ="uploads";

	@PostMapping("/upload")
	public ResponseEntity<ResponseModel> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
//		try {
			String fileName = storageService.save(file);
			message = "Uploaded the file successfully: " + fileName; // file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(message, CommonConstant.SUCCESS, fileName));
//		} catch (Exception e) {
//			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
//					.body(new ResponseModel(message, CommonConstant.FAILED, null));
//		}
	}
	
	@PostMapping("/multiple/upload")
	public ResponseEntity<ResponseModel> multipleUpload(@RequestParam("files") MultipartFile[] files) {

        if (files.length > maxCount) {
            throw new RuntimeException("too many files");
        }
        List<FileInfo> uploadResponseList = new ArrayList<>();
        Arrays.asList(files)
                .stream()
                .forEach(file -> {
                    String fileName = storageService.save(file);

                    ///http://localhost:8081/download/abc.jpg
                    String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/download/")
                            .path(fileName)
                            .toUriString();

                    String contentType = file.getContentType();

                    FileInfo response = new FileInfo(fileName,url,contentType);
                    uploadResponseList.add(response);
                });

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel("Uploaded the file successfully"+files.length, CommonConstant.SUCCESS, uploadResponseList));
    }

	@GetMapping("/files")
	public ResponseEntity<List<FileInfo>> getListFiles() {
		List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
//			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
//		    FileTime fileTime = attr.creationTime();
			return new FileInfo(filename, url, filename.substring(filename.lastIndexOf('.') + 1));
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("/zipDownload")
	void zipDownload(@RequestParam("fileName") String[] files, HttpServletResponse response) throws IOException {
		// zipoutstream - zipentry+zipentry

		try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
			Arrays.asList(files).stream().forEach(file -> {
				Resource resource = storageService.load(file);

				ZipEntry zipEntry = new ZipEntry(resource.getFilename());

				try {
					zipEntry.setSize(resource.contentLength());
					zos.putNextEntry(zipEntry);

					StreamUtils.copy(resource.getInputStream(), zos);

					zos.closeEntry();
				} catch (IOException e) {
					System.out.println("some exception while ziping");
				}
			});
			zos.finish();
		}
		response.setStatus(200);
		response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=zipfile");
	}
}