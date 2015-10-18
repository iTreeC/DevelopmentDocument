package com.turing.activity.service.impl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.turing.activity.service.api.Service;
import com.turing.activity.service.api.Status;
import com.turing.activity.service.impl.ServiceImpl;
import com.turing.activity.service.impl.StatusImpl;

public class TestServiceImpl {
	Status status = new StatusImpl();
	Service service = new ServiceImpl();
	
	@Test
	public void testMatch() {
		List<Integer> shopList = service.match();
		for(int i=0; i<shopList.size(); i++){
			System.out.println(shopList.get(i));
		}
	}

}
