package com.book.collection.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.book.collection.dto.EnquiryDTO;
import com.book.collection.util.DBUtil;

public class EnquiryDAO {
	
private static final String Q_INSERT_ENQUIRY_INFO = "insert into enquiry (customer_detail_id, enquiry, datetime) value(?, ?, ?)";
	
	public static int bookEnquiry(EnquiryDTO enquiryDTO) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(Q_INSERT_ENQUIRY_INFO);
			pstmt.setInt(1, enquiryDTO.getCustomerId());
			pstmt.setString(2, enquiryDTO.getEnquiry());
			pstmt.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));		
			return pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}
	
}
