package com.book.collection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.book.collection.dto.BookDTO;
import com.book.collection.servlet.BookServlet;
import com.book.collection.util.DBUtil;

public class BookDAO {

	final static Logger LOGGER = Logger.getLogger(BookDAO.class);

	private static final String Q_SELECT_BOOK_BY_ID = "select * from book where id=?";

	public static BookDTO getBookById(int bookId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(Q_SELECT_BOOK_BY_ID);
			pstmt.setInt(1, bookId);
			rs = pstmt.executeQuery();
			BookDTO bookDTO = null;
			if (rs.next()) {
				bookDTO = new BookDTO();

				bookDTO.setName(rs.getString("name"));
				bookDTO.setBookTypeId(rs.getInt("book_type_id"));
				bookDTO.setCost(rs.getDouble("cost"));
				bookDTO.setDetail(rs.getString("detail"));
				bookDTO.setPublication(rs.getString("publication"));
				bookDTO.setImage(rs.getString("image"));
				bookDTO.setAuthor(rs.getString("author"));
			}
			return bookDTO;
		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}

	public static List<BookDTO> getBookByFilter(String name, String author, int bookTypeId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sbQuery = new StringBuilder(" select * from book ");
		boolean where = false;
		if (name != null) {
			if (!where) {
				sbQuery.append(" where ");
				where = true;
			} else {
				sbQuery.append(" and ");
			}
			sbQuery.append(" name like ?");
			LOGGER.debug(sbQuery);

		}
		if (author != null) {
			if (!where) {
				sbQuery.append(" where ");
				where = true;
			} else {
				sbQuery.append(" and ");
			}
			sbQuery.append(" author like ?");
			LOGGER.debug(sbQuery);

		}
		if (bookTypeId != 0) {
			if (!where) {
				sbQuery.append(" where ");
				where = true;
			} else {
				sbQuery.append(" and ");
			}
			sbQuery.append(" book_type_id = ?");
			LOGGER.debug(sbQuery);

		}

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(sbQuery.toString());
			int index = 1;
			if (name != null) {
				pstmt.setString(index, name);
				index++;
			}
			if (author != null) {
				pstmt.setString(index, author);
				index++;
			}
			if (bookTypeId != 0) {
				pstmt.setInt(index, bookTypeId);
			}
			LOGGER.debug(pstmt);

			rs = pstmt.executeQuery();
			List<BookDTO> bookDTOList = new ArrayList<>();

			while (rs.next()) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setId(rs.getInt("id"));
				bookDTO.setName(rs.getString("name"));
				bookDTO.setBookTypeId(rs.getInt("book_type_id"));
				bookDTO.setCost(rs.getDouble("cost"));
				bookDTO.setDetail(rs.getString("detail"));
				bookDTO.setPublication(rs.getString("publication"));
				bookDTO.setImage(rs.getString("image"));
				bookDTO.setAuthor(rs.getString("author"));
				bookDTOList.add(bookDTO);
			}
			return bookDTOList;
		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}

}
