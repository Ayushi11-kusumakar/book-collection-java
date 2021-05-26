package com.book.collection.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.book.collection.dto.APIResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestResponse {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static void successResponseWithBody(HttpServletResponse response, Object body) throws IOException {

		APIResponseDTO apiResponseDTO = new APIResponseDTO();
		apiResponseDTO.setStatus("success");
		apiResponseDTO.setData(body);

		response.reset();
		response.setContentType("application/json");
		response.getWriter().write(objectMapper.writeValueAsString(apiResponseDTO));
		response.setStatus(HttpServletResponse.SC_OK);
	}

	public static void successResponseWithMessage(HttpServletResponse response, String message) throws IOException {
		APIResponseDTO apiResponseDTO = new APIResponseDTO();
		apiResponseDTO.setStatus("success");
		apiResponseDTO.setMessage(message);

		response.reset();
		response.setContentType("application/json");
		response.getWriter().write(objectMapper.writeValueAsString(apiResponseDTO));
		response.setStatus(HttpServletResponse.SC_OK);
	}

	public static void errorResponse(HttpServletResponse response, String message) throws IOException {
		APIResponseDTO apiResponseDTO = new APIResponseDTO();
		apiResponseDTO.setStatus("error");
		apiResponseDTO.setMessage(message);

		response.reset();
		response.setContentType("application/json");
		response.getWriter().write(objectMapper.writeValueAsString(apiResponseDTO));
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}
}
