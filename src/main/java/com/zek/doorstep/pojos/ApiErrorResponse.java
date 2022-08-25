/**
 * 
 */
package com.zek.doorstep.pojos;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author EK
 *
 */
@AllArgsConstructor
@Getter
public class ApiErrorResponse {
	private final HttpStatus status;
	private final String message;
	private final Instant timestamp;
}
