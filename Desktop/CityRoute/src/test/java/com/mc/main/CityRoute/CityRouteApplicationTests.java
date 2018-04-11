package com.mc.main.CityRoute;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mc.main.CityRoute.controller.CityController;

/**
 * @author rohita@icloud.com
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRouteApplicationTests {

	@Autowired
	CityController controller;
	
	/**
	 * 
	 */
	@Test
	public void contextLoads() {
	}

	@Test
	public void testReadFile() {
		String actual = controller.verifyCityRoute("Boston", "Newark");
		String actual1 = controller.verifyCityRoute("Philadelphia", "Albany");
		assertEquals("YES", actual);
		assertEquals("NO", actual1);
	}
}
