package com.book.collection.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.book.collection.dto.ReviewDTO;
import com.book.collection.util.DBUtil;

public class ReviewDAO {
	
	private static final String Q_INSERT_REVIEW_INFO = "insert into review (book_id, customer_detail_id, review, datetime) value(?, ?, ?, ?)";
	
	public static int review(ReviewDTO reviewDTO) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(Q_INSERT_REVIEW_INFO);
			pstmt.setInt(1, reviewDTO.getBookId());
			pstmt.setInt(2, reviewDTO.getCustomerId());
			pstmt.setString(3, reviewDTO.getReview());
			pstmt.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));

			return pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}
	
	
}
