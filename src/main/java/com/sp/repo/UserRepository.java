package com.sp.repo;

import java.sql.PreparedStatement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sp.vo.UserVO;

@Repository
public class UserRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
	
	private final JdbcTemplate jdbcTemplate;
	
	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	} 
	
	private final RowMapper<UserVO> userRowMapper = (rs, rowNum) -> {
		UserVO userObject = new UserVO(	rs.getInt("id"), 
										rs.getString("username"), 
										rs.getString("password"));
		return userObject;
	};
	
	
	public List<UserVO> fetchAllUsers() throws Exception{
		logger.info("Fetching all users");
		String sqlTxt = "select id, username, password from userstbl";
		return this.jdbcTemplate.query(sqlTxt, userRowMapper);
	}
	
	public Integer createUser(UserVO userVO) throws Exception{
		logger.info("create new user with username %s ",userVO.getUsername());
		String sql = "INSERT INTO USERSTBL (USERNAME, PASSWORD) VALUES(?,?)";
		return this.jdbcTemplate.update(sql,userVO.getUsername(), userVO.getPassword());
	}
	
	public UserVO getUser(Integer userId) throws Exception{
		logger.info("get a user with user id %d",userId);
		String sqlTxt = "select id, username, password from userstbl where id = ?";
		return this.jdbcTemplate.queryForObject(sqlTxt, new Object[] { userId}, userRowMapper);
	}
	
	public void batchInsertUsers(List<UserVO> userList) throws Exception{
		logger.info("insert multiple user's using batch inserts");
		String sql = "INSERT INTO USERSTBL (USERNAME, PASSWORD) VALUES(?,?)";
		
		jdbcTemplate.batchUpdate(sql, userList, userList.size(), (PreparedStatement ps, UserVO vo) -> {
			ps.setString(1, vo.getUsername());
			ps.setString(2, vo.getPassword());			
		});
	}
}
