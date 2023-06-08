package dao;

import java.util.List;


import util.DaoHelper;
import vo.Board;
import vo.Customer;

public class BoardDao {

//	// 새 게시물을 전달받아서 테이블에 저장한다.
//	public void insertBoard(Board board) {
//		DaoHelper.update("boardDao.insertBoard", board.getNo(),
//												 board.getTitle(),
//												 board.getContent(),
//												 board.getReadCnt(),
//												 board.getCommentCnt(),
//												 board.getUpdateDate(),
//												 board.getCreateDate());
//	}
//	
//	// 전체 게시물을 조회해서 반환한다.
//	public List<Board> getBoards(int begin, int end) {
//		return DaoHelper.selectList("boardDao.getBoards", rs -> {
//			Board board = new Board();
//			board.setNo(rs.getInt("board_no"));
//			board.setTitle(rs.getString("board_title"));
//			board.setReadCnt(rs.getInt("board_read_cnt"));
//			board.setCommentCnt(rs.getInt("board_comment_cnt"));
//			board.setUpdateDate(rs.getDate("board_update_date"));
//			board.setCreateDate(rs.getDate("board_create_date"));
//			
//			return board;
//		}, begin, end);
//	}
//	
//	// 전달받은 게시물번호에 해당하는 게시물을 삭제한다.
//	public void deleteBoardByNo(int no) {
//		DaoHelper.update("boardDao.deleteBoardByNo", no);
//	}
//	
//	// 게시물을 수정한다.
//	
//	// 게시물 번호로 해당 게시물을 조회한다.
//	public Board getBoardByNo(int boardNo) {
//		return DaoHelper.selectOne("boardDao.getBoardByNo", rs -> {
//			Board board = new Board();
//			board.setNo(rs.getInt("board_no"));
//			board.setTitle(rs.getString("board_title"));
//			board.setContent(rs.getString("board_content"));
//			board.setUpdateDate(rs.getDate("board_update_date"));
//			
//			return board;
//		}, boardNo);
//	}
	
	public void insertBoard(Board board) {
		DaoHelper.update("boardDao.insertBoard", board.getTitle(),
											   board.getContent(),
											   board.getCustomer().getId(),
											   board.getFilename());	
	}
	
	public List<Board> getBoards() {
		return DaoHelper.selectList("boardDao.getBoards", rs -> {
			Board board = new Board();
			board.setNo(rs.getInt("board_no"));
			board.setTitle(rs.getString("board_title"));
			board.setReadCnt(rs.getInt("board_read_cnt"));
			board.setCommentCnt(rs.getInt("board_comment_cnt"));
			board.setUpdateDate(rs.getDate("board_update_date"));
			board.setCreateDate(rs.getDate("board_create_date"));
			board.setDeleted(rs.getString("board_deleted"));
			board.setFilename(rs.getString("board_filename"));
			
			Customer customer = new Customer();
			customer.setId(rs.getString("cust_id"));
			customer.setName(rs.getString("cust_name"));
			board.setCustomer(customer);
			
		return board;
		});
	}
	
	public Board getBoardByNo(int no) {
		return DaoHelper.selectOne("boardDao.getBoardByNo", rs -> {
			Board board = new Board();
			board.setNo(rs.getInt("board_no"));
			board.setTitle(rs.getString("board_title"));
			board.setContent(rs.getString("board_content"));
			board.setCommentCnt(rs.getInt("board_comment_cnt"));
			board.setReadCnt(rs.getInt("board_read_cnt"));
			board.setDeleted(rs.getString("board_deleted"));
			board.setUpdateDate(rs.getDate("board_update_date"));
			board.setCreateDate(rs.getDate("board_create_date"));
			Customer customer = new Customer();
			customer.setId(rs.getString("cust_id"));
			customer.setName(rs.getString("cust_name"));
			board.setCustomer(customer);
			
			return board;
		}, no);
	}
}
