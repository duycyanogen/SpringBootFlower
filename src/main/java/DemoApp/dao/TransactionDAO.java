package DemoApp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import Config.SQLConnect;
import DemoApp.model.*;
import Helpers.MappingHelper;
import Request.AccountRequest;
import Request.TransactionRequest;

@Repository
public class TransactionDAO {

//	@Autowired
//	private DataSourceConfig myBeanConnect;
	// @Autowired
	// private SQLConnect connect;

//	public ArrayList<Transaction> getAllTransaction() throws SQLException, ClassNotFoundException {
//		ArrayList<Transaction> listTransaction = new ArrayList<Transaction>();
//		Connection conn = SQLConnect.getConnection();
//		String callString = "{ ? = call dbo.sp_Transaction_GetAll() }";
//		try {
//			CallableStatement proc = conn.prepareCall(callString);
//			proc.registerOutParameter(1, Types.OTHER);
//			proc.execute();
//			ResultSet resultSet = proc.getResultSet();
//			while (resultSet.next()) {
//				ModelMapper modelMapper = new ModelMapper();
//				Transaction objTransaction = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Transaction.class);
//				listTransaction.add(objTransaction);
//			}
//			conn.commit();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return listTransaction;
//	}
//
//	public ArrayList<Transaction> getTransactionById(TransactionRequest request) throws SQLException, ClassNotFoundException {
//		ArrayList<Transaction> listTransaction = new ArrayList<Transaction>();
//		Connection conn = SQLConnect.getConnection();
//		String callString = "{ ? = call dbo.sp_Transaction_GetById(?) }";
//		try {
//			CallableStatement proc = conn.prepareCall(callString);
//			proc.registerOutParameter(1, Types.OTHER);
//			proc.setInt(2, request.getId());
//			proc.execute();
//			ResultSet resultSet = proc.getResultSet();
//			while (resultSet.next()) {
//				ModelMapper modelMapper = new ModelMapper();
//				Transaction objTransaction = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Transaction.class);
//				listTransaction.add(objTransaction);
//			}
//			conn.commit();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return listTransaction;
//	}
//
//	public ArrayList<Transaction> getTransactionByUser(TransactionRequest request) throws SQLException, ClassNotFoundException {
//		ArrayList<Transaction> listTransaction = new ArrayList<Transaction>();
//		Connection conn = SQLConnect.getConnection();
//		String callString = "{ ? = call dbo.sp_Transaction_GetByKey(?) }";
//		try {
//			CallableStatement proc = conn.prepareCall(callString);
//			proc.registerOutParameter(1, Types.OTHER);
//			proc.setString(2, request.getKeyword());
//			proc.execute();
//			ResultSet resultSet = proc.getResultSet();
//			while (resultSet.next()) {
//				ModelMapper modelMapper = new ModelMapper();
//				Transaction objTransaction = modelMapper.map(MappingHelper.MappingResultSetToObject(resultSet), Transaction.class);
//				listTransaction.add(objTransaction);
//			}
//			conn.commit();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//		return listTransaction;
//	}
	
	public void addTransaction(Connection conn, TransactionRequest request) throws SQLException, ClassNotFoundException {
		
		String callString = "{ call dbo.sp_Transaction_add(?,?,?,?,?,?,?,?,?) }";
		try {
			CallableStatement proc = conn.prepareCall(callString);
			
			proc.setInt(1, request.getUserID());
			proc.setString(2, request.getRecipientName());
			proc.setString(3, request.getRecipientEmail());
			proc.setString(4, request.getRecipientPhone());
			proc.setString(5, request.getRecipientAddress());
			proc.setDouble(6,request.getAmount());
			proc.setString(7, request.getMessage());
			proc.setString(8,request.getNote());
			proc.registerOutParameter(9, Types.INTEGER);
			proc.execute();
			int id = proc.getInt(9);
			request.setId(id);
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			conn.rollback();
			e.printStackTrace();
			throw e;
		} 
	}

	public ArrayList<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return null;
	}
}
