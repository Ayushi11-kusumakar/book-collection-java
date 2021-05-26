package com.book.collection.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.book.collection.dao.ReviewDAO;
import com.book.collection.dto.ReviewDTO;
import com.book.collection.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/reviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	final static Logger LOGGER = Logger.getLogger(ReviewServlet.class);

	public ReviewServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: GET");
		String action = request.getParameter("action");


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: POST");
		String action = request.getParameter("action");

		switch (action) {
		case "customerReview":
			customerReview(request, response);
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

	protected void customerReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: customerReview");

		try {

			ReviewDTO reviewDTO = objectMapper.readValue(request.getInputStream(), ReviewDTO.class);
			LOGGER.info(request.getInputStream());

			int count = ReviewDAO.review(reviewDTO);
			if (count == 0) {
				LOGGER.info("Unable to feed customer review.");
				RestResponse.errorResponse(response, "Unable to feed customer review.");
				return;
			}

			RestResponse.successResponseWithMessage(response, "Save customer review successfuly");
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}


}
