package com.book.collection.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.book.collection.dao.BookCartDAO;
import com.book.collection.dto.BookCartDTO;
import com.book.collection.dto.WishListDTO;
import com.book.collection.util.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/bookCartServlet")
public class BookCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	final static Logger LOGGER = Logger.getLogger(BookCartServlet.class);

	public BookCartServlet() {
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
		case "bookCart":
			insertBookCartInfo(request, response);
			break;
		case "wishlist":
			insertWishListInfo(request, response);
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
	
	protected void insertBookCartInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Method: insertBookCartInfo");

		try {

			BookCartDTO bookCartDTO = objectMapper.readValue(request.getInputStream(), BookCartDTO.class);
			LOGGER.info(request.getInputStream());

			int count = BookCartDAO.bookCart(bookCartDTO);

			if (count == 0) {
				LOGGER.info("Unable to create bookCart info.");
				RestResponse.errorResponse(response, "Unable to create bookCart info.");
				return;
			}

			RestResponse.successResponseWithMessage(response, "BookCart created successfully.");
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}


	protected void insertWishListInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Method: insertWishListInfo");

		try {

			WishListDTO wishListDTO = objectMapper.readValue(request.getInputStream(), WishListDTO.class);
			LOGGER.info(request.getInputStream());

			int count = BookCartDAO.wishlistBook(wishListDTO);

			if (count == 0) {
				LOGGER.info("Unable to create wishlist info.");
				RestResponse.errorResponse(response, "Unable to create wishlist info.");
				return;
			}

			RestResponse.successResponseWithMessage(response, "Wishlist created successfully.");
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}

}
