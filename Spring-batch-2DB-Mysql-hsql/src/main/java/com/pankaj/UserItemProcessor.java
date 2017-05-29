package com.pankaj;

import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<User, User>{

	@Override
	public User process(User item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}
	
	

}
