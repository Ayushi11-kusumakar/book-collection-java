package com.book.collection.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.book.collection.dao.CustomerDetailDAO;
import com.book.collection.dto.CustomerDetailDTO;
import com.book.collection.dto.LoginDTO;
import com.book.collection.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/customerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	final static Logger LOGGER = Logger.getLogger(CustomerServlet.class);

	public CustomerServlet() {
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
		case "login":
			getCustomerLogin(request, response);
			break;

		case "registration":
			getCustomerRegistration(request, response);
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

	protected void getCustomerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: getCustomerLogin");

		try {

			LoginDTO loginDTO = objectMapper.readValue(request.getInputStream(), LoginDTO.class);

			CustomerDetailDTO customerDetailDTO = CustomerDetailDAO.getCustomerLogin(loginDTO.getEmail(), loginDTO.getPassword());

			if (customerDetailDTO == null) {
				LOGGER.info("Invalid username and password.");
				RestResponse.errorResponse(response, "Invalid username and password.");
				return;
			}

			RestResponse.successResponseWithBody(response, customerDetailDTO);
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}

	protected void getCustomerRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: getCustomerRegistration");

		try {

			CustomerDetailDTO customerDetailDTO = objectMapper.readValue(request.getInputStream(), CustomerDetailDTO.class);
			LOGGER.info(request.getInputStream());

			int count = CustomerDetailDAO.getCustomerRegistration(customerDetailDTO);

			if (count == 0) {
				LOGGER.info("Unable to create customer account.");
				RestResponse.errorResponse(response, "Unable to create customer account.");
				return;
			}

			RestResponse.successResponseWithMessage(response, "Account created successfully.");
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}


}
