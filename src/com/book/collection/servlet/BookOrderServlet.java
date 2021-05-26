package com.book.collection.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.book.collection.dao.BookOrderDAO;
import com.book.collection.dto.BookOrderDTO;
import com.book.collection.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/bookOrderServlet")
public class BookOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	final static Logger LOGGER = Logger.getLogger(BookOrderServlet.class);

	public BookOrderServlet() {
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
		case "getBookedOrderByCustomerId":
			getBookedOrderByCustomerId(request, response);
			break;

		default:
			RestResponse.errorResponse(response, "No operation found.");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: POST");
		String action = request.getParameter("action");

		switch (action) {
		case "bookOrder":
			insertBookOrderInfo(request, response);
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
	
	protected void insertBookOrderInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Method: getStudentRegistration");

		try {

			BookOrderDTO bookOrderDTO = objectMapper.readValue(request.getInputStream(), BookOrderDTO.class);
			LOGGER.info(request.getInputStream());

			int count = BookOrderDAO.bookOrder(bookOrderDTO);

			if (count == 0) {
				LOGGER.info("Unable to create bookOrder info.");
				RestResponse.errorResponse(response, "Unable to create bookOrder info.");
				return;
			}

			RestResponse.successResponseWithMessage(response, "BookOrder created successfully.");
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}


	protected void getBookedOrderByCustomerId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("Method: getBookedOrderByCustomerId");

		try {
			String customerDetail = request.getParameter("customer_detail_id");

			List<BookOrderDTO> getAllBookOrders = BookOrderDAO.getBookedOrderByCustomerId(Integer.parseInt(customerDetail));

			if (getAllBookOrders == null) {
				LOGGER.info("No data found.");
				RestResponse.errorResponse(response, "No data found.");
				return;
			}

			RestResponse.successResponseWithBody(response, getAllBookOrders);
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}

}
