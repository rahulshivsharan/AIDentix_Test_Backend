package com.sp.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserVO implements Serializable{
	
	@EqualsAndHashCode.Include
	private Integer userId;
	private String username;
	private String password;
	
	public UserVO() {}
	
	public UserVO(Integer userId, String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}
	
	public UserVO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
		
	
	
}
