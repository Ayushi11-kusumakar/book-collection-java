package com.book.collection.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.book.collection.dao.EnquiryDAO;
import com.book.collection.dto.EnquiryDTO;
import com.book.collection.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/enquiryServlet")
public class EnquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	final static Logger LOGGER = Logger.getLogger(EnquiryServlet.class);

	public EnquiryServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: GET");
		String action = request.getParameter("action");

		switch (action) {
		
		default:
			RestResponse.errorResponse(response, "No operation found.");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: POST");
		String action = request.getParameter("action");

		switch (action) {
		case "enquiry":
			bookEnquiry(request, response);
			break;

		

		default:
			RestResponse.errorResponse(response, "No operation found.");
			break;

		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: PUT");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: DELETE");
	}


	protected void bookEnquiry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: bookEnquiry");

		try {

			EnquiryDTO enquiryDTO = objectMapper.readValue(request.getInputStream(), EnquiryDTO.class);
			LOGGER.info(request.getInputStream());

			int count = EnquiryDAO.bookEnquiry(enquiryDTO);

			if (count == 0) {
				LOGGER.info("Unable to enquire for book.");
				RestResponse.errorResponse(response, "Unable to enquire for book.");
				return;
			}

			RestResponse.successResponseWithMessage(response, "Enquiry for book successfully completed.");
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}


}
