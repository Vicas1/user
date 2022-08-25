package com.zek.doorstep.pojos;

import java.time.Instant;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author EK
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInfo {

	private String name;
	private String url;
	private String type;
	private String parentFolder;
	private String shortPath;
	private Integer size;
	private LocalDateTime dateCreated;
	private LocalDateTime dateLastAccessed;
	private LocalDateTime dateLastModified;
	private Instant timestamp;
	
	public FileInfo(String fileName, String url, String contentType) {
		this.name = fileName;
		this.url = url;
		this.type = contentType;
		
}

}
