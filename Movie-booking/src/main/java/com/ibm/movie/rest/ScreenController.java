package com.ibm.movie.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.entity.Screen;
import com.ibm.movie.exception.ScreenNotFoundException;
import com.ibm.movie.services.ScreenService;

/**
 * @author Arijit Chakraborty
 * @version 1.0
 * @category Screen
 */
@CrossOrigin
@RestController
//@RequestMapping("/screens")
public class ScreenController {

	@Autowired
	private ScreenService screenService;

	/**
	 * Stores a screen object in database
	 * 
	 * @param screen
	 * @return addScreen
	 * @throws ScreenNotFoundException
	 */	
	@PostMapping(value = "/addscreen", consumes = "application/json")
	public Screen addAScreen(@RequestBody Screen screen)throws ScreenNotFoundException {
		return screenService.addScreen(screen);
	}

	/**
	 * return list of screen from database
	 * 
	 * @return screenList
	 * @throws ScreenNotFoundException
	 */
	@GetMapping(value = "/findallscreen", produces = "application/json")
	public List<Screen> viewScreenList() throws  ScreenNotFoundException {
		return screenService.viewScreenList();
	}
	
	/**
	 * View screen from the identifier screenId
	 * 
	 * @param screenId
	 * @return screenList
	 * @throws ScreenNotFoundException
	 */
	@GetMapping(value = "/viewScreen/{screenId}", produces = "application/json")
	public Screen viewScreen(@PathVariable int screenId) throws ScreenNotFoundException {
		Screen screen = screenService.viewScreen(screenId);
		if(screen.getScreenId() != screenId)
			throw new ScreenNotFoundException("Screen with this " + screenId + " doesn't exist");
		else
			screen = screenService.viewScreen(screenId);
		return screen;
	}
	
	/**
	 * updates the screen with screen instance
	 * 
	 * @param screen
	 * @return updatedScreen details
	 * @throws ScreenNotFoundException
	 */
	@PutMapping(value = "/updatescreen", produces = "application/json")
	public Screen updateScreen(@RequestBody Screen screen) throws  ScreenNotFoundException {
		if (screen == null) {
			System.out.println("Error");
		}
		return screenService.updateScreen(screen);
	}
}
