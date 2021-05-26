package com.book.collection.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.book.collection.dto.CustomerDetailDTO;
import com.book.collection.util.DBUtil;

public class CustomerDetailDAO {

	private static final String Q_INSERT_CUSTOMER_INFO = "insert into customer_detail (name, address, email,  gender, mobile_number, password) value(?, ?, ?, ?, ?, ?)";

	private static final String Q_SELECT_CUSTOMER_LOGIN = "select * from customer_detail where email=? and password=?";


	public static CustomerDetailDTO getCustomerLogin(String email, String password) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(Q_SELECT_CUSTOMER_LOGIN);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			CustomerDetailDTO dto = null;

			if (rs.next()) {
				dto = new CustomerDetailDTO();
				dto.setId(rs.getInt("id"));

				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));
				dto.setGender(rs.getString("gender").charAt(0));
				dto.setEmail(rs.getString("email"));
				dto.setMobileNumber(rs.getString("mobile_number"));
				dto.setPassword(rs.getString("password"));
			}
			return dto;
		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}

	public static int getCustomerRegistration(CustomerDetailDTO customerDetailDTO) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(Q_INSERT_CUSTOMER_INFO);
			pstmt.setString(1, customerDetailDTO.getName());
			pstmt.setString(2, customerDetailDTO.getAddress());
			pstmt.setString(3, customerDetailDTO.getEmail());
			pstmt.setString(4, String.valueOf(customerDetailDTO.getGender()));
			pstmt.setString(5, customerDetailDTO.getMobileNumber());
			pstmt.setString(6, customerDetailDTO.getPassword());

			return pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}


}
