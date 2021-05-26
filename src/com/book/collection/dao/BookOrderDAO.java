package com.book.collection.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.book.collection.dto.BookOrderDTO;
import com.book.collection.util.DBUtil;

public class BookOrderDAO {
	
	private static final String Q_INSERT_BOOK_ORDER_INFO = "insert into book_order (customer_detail_id, book_id, cost,  booking_date_time, total_cost, transaction_status, payment_type, delivery_address, city_name, quantity) value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


	private static final String Q_SELECT_BOOK_BY_CUSTOMER_ID = "select * from book_order where customer_detail_id=?";

	
	public static int bookOrder(BookOrderDTO bookOrderDTO) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(Q_INSERT_BOOK_ORDER_INFO);
			pstmt.setInt(1, bookOrderDTO.getCustomerId());
			pstmt.setInt(2, bookOrderDTO.getBookId());
			pstmt.setDouble(3, bookOrderDTO.getCost());
			pstmt.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setDouble(5, bookOrderDTO.getTotalCost());
			pstmt.setString(6, bookOrderDTO.getTransactionStatus());
			pstmt.setInt(7, bookOrderDTO.getPaymentTypeId());
			pstmt.setString(8, bookOrderDTO.getDeliveryAddress());
			pstmt.setString(9, bookOrderDTO.getCityName());
			pstmt.setInt(10, bookOrderDTO.getQuantity());

			return pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}
	
	public static List<BookOrderDTO> getBookedOrderByCustomerId(int customerId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getLocalDBConnection();
			pstmt = conn.prepareStatement(Q_SELECT_BOOK_BY_CUSTOMER_ID);
			pstmt.setInt(1, customerId);
			rs = pstmt.executeQuery();
			List<BookOrderDTO> customerBookDTOList = new ArrayList<>();
			while (rs.next()) {
				BookOrderDTO bookOrderDTO = new BookOrderDTO();

				bookOrderDTO.setCost(rs.getDouble("cost"));
				bookOrderDTO.setTransactionStatus(rs.getString("transaction_status"));
				bookOrderDTO.setBookingDate(rs.getDate("booking_date_time"));
				bookOrderDTO.setCityName(rs.getString("city_name"));
				bookOrderDTO.setDeliveryAddress(rs.getString("delivery_address"));
				bookOrderDTO.setQuantity(rs.getInt("quantity"));
				bookOrderDTO.setTotalCost(rs.getDouble("total_cost"));
				bookOrderDTO.setPaymentTypeId(rs.getInt("payment_type"));
				bookOrderDTO.setCustomerId(rs.getInt("customer_detail_id"));
				bookOrderDTO.setBookId(rs.getInt("book_id"));
				customerBookDTOList.add(bookOrderDTO);
			}

			return customerBookDTOList;
		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}
}
