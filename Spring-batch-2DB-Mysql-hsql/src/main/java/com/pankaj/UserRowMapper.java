package com.pankaj;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		User u=new User();
		
		u.setName(rs.getString("first_name"));
		
		System.out.println(u.toString());
		
		return u;
	}

}
