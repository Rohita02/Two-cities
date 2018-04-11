package com.mc.main.CityRoute.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.Scanner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rohita@icloud.com
 *
 */
@RestController
@RequestMapping("/connected")
public class CityController {
	private final static Logger log =Logger.getLogger(CityController.class.getName());
	List<List<String>> listOfRoutes = new ArrayList<>();
	
	/**
	 * @param origin
	 * @param destination
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String verifyCityRoute(@RequestParam("origin") String origin,
			@RequestParam("destination") String destination) {
		StringBuilder logText = new StringBuilder();
		logText.append("Origin [").append(origin);
		logText.append("], Destination [").append(destination).append("]");
		log.info(logText.toString());
		
		
		/* Load the file only when it is empty, first time */
		if (listOfRoutes.isEmpty()) {
			readFile();
		}
		boolean found = false;
		for (List<String> cityRoute : listOfRoutes) {
			if (cityRoute.containsAll(Arrays.asList(origin, destination))) {
				System.out.println("Hello A and B");
				found = true;
				break;
			}
		}
		if (found) {
			return "YES";
		}

		return "NO";
	}

	/**
	 * 
	 */
	private void readFile() {
		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:citys.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] cityArray = line.split(",");
				List<String> cityRoutes = new ArrayList<>();
				Collections.addAll(cityRoutes, cityArray);
				listOfRoutes.add(cityRoutes);
			}
			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("ListOfRoutes Size :"+listOfRoutes.size());
	}
}
