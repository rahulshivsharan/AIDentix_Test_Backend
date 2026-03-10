package com.sp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sp.exception.UserNotFoundException;
import com.sp.repo.UserRepository;
import com.sp.vo.UserVO;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<UserVO> fetchUsers() throws RuntimeException{
		try {
			logger.info("fetching all users");
			return this.userRepository.fetchAllUsers();
		}catch(Exception e) {
			logger.error("Error while fetching user list "+e.getMessage());
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("Not able to fetch Users")					
					.append(", exception '").append(e.getMessage()).append("'");
			throw new RuntimeException(errorMsg.toString());
		}
		
	}
	
	public int createUser(UserVO userVO) throws RuntimeException{
		try {
			logger.info("creating new user ");
			return this.userRepository.createUser(userVO);
		}catch(Exception e) {
			logger.error("Error while creating user "+e.getMessage());
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("Not able to create new User")					
					.append(", exception '").append(e.getMessage()).append("'");
			throw new RuntimeException(errorMsg.toString());
		}
		
	}
	
	public UserVO fetchUser(Integer userId) throws UserNotFoundException{
		try {
			logger.info("get a user ");
			return this.userRepository.getUser(userId);
		}catch(Exception e) {
logger.error(e.getMessage());
			
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("Not able to find user with userId '")
					.append(userId)
					.append("', exception '").append(e.getMessage()).append("'");
			
			throw new UserNotFoundException(errorMsg.toString());
		}
		
	}
	
	public void batchInsertUsers(List<UserVO> userList) throws RuntimeException{
		try {
			logger.info("create mulitple users ");
			this.userRepository.batchInsertUsers(userList);
		}catch(Exception e) {
			logger.error("Not able to batch insert users "+e.getMessage());
			StringBuffer errorMsg = new StringBuffer();
			errorMsg.append("Not able to batch insert users")					
					.append(", exception '").append(e.getMessage()).append("'");
			throw new RuntimeException(errorMsg.toString());
		}
		
	}
}
