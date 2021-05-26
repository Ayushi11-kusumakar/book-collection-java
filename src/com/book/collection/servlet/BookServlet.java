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

import com.book.collection.dao.BookDAO;
import com.book.collection.dto.BookDTO;
import com.book.collection.util.RestResponse;

@WebServlet("/bookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static Logger LOGGER = Logger.getLogger(BookServlet.class);

	public BookServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Method: GET");
		String action = request.getParameter("action");

		switch (action) {

		case "getBookById":
			getBookById(request, response);
			break;
		case "getAllBooksByFilter":
			getAllBooksByFilter(request, response);
			break;
		
		default:
			RestResponse.errorResponse(response, "No operation found.");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Method: POST");
		String action = request.getParameter("action");

		switch (action) {
		
		default:
			RestResponse.errorResponse(response, "No operation found.");
			break;
		}

	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Method: PUT");
		String action = request.getParameter("action");

		switch (action) {
		
		default:
			RestResponse.errorResponse(response, "No operation found.");
			break;
		}

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Method: DELETE");
	}

	protected void getBookById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Method: getBookById");

		try {
			String bookId = request.getParameter("id");

			BookDTO bookDTO = BookDAO.getBookById(Integer.parseInt(bookId));

			if (bookDTO == null) {
				LOGGER.info("No data found.");
				RestResponse.errorResponse(response, "No data found.");
				return;
			}

			RestResponse.successResponseWithBody(response, bookDTO);
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}

	protected void getAllBooksByFilter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Method: getAllBooksByFilter");

		try {
			String name = request.getParameter("name");
			String author = request.getParameter("author");
			String bookTypeId = request.getParameter("bookTypeId");


			List<BookDTO> getAllBookInfo = BookDAO.getBookByFilter(name, author, Integer.parseInt(bookTypeId));

			if (getAllBookInfo == null) {
				LOGGER.info("No data found.");
				RestResponse.errorResponse(response, "No data found.");
				return;
			}

			RestResponse.successResponseWithBody(response, getAllBookInfo);
			return;

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
	}

}
