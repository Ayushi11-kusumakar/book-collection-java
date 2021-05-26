package com.book.collection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.book.collection.dto.BookCartDTO;
import com.book.collection.dto.WishListDTO;
import com.book.collection.util.DBUtil;

public class BookCartDAO {
	
	private static final String Q_INSERT_BOOK_CART_INFO = "insert into book_cart (book_id, customer_id) value(?, ?)";

	private static final String Q_INSERT_WISHLIST_INFO = "insert into wishlist ( book_id, customer_detail_id) value( ?, ?)";

	
	public static int bookCart(BookCartDTO cartDTO) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(Q_INSERT_BOOK_CART_INFO);
			pstmt.setInt(1, cartDTO.getBookId());
			pstmt.setInt(2, cartDTO.getCustomerId());
			

			return pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}
	
	public static int wishlistBook(WishListDTO wishListDTO) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(Q_INSERT_WISHLIST_INFO);
			pstmt.setInt(1, wishListDTO.getBookId());
			pstmt.setInt(2, wishListDTO.getCustomerId());
			

			return pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}
	

}
