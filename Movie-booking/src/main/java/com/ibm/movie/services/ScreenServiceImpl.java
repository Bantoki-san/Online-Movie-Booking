package com.ibm.movie.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.movie.entity.Screen;
import com.ibm.movie.exception.ScreenNotFoundException;
import com.ibm.movie.repo.ScreenRepository;

/**
 * @author Mouli Roy
 * @version 1.0
 * @category ScreenService
 *
 */
@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenRepository screenRepository;

	/**
	 * Add Screen object in database
	 *
	 * @param screen
	 * @return screen
	 * @throws ScreenNotFoundException
	 */
	@Override
	public Screen addScreen(Screen screen) throws ScreenNotFoundException {

		screenRepository.saveAndFlush(screen);
		return screen;
	}

	/**
	 * View screen List
	 *
	 * @return screen
	 * @throws ScreenNotFoundException
	 */
	@Override
	public List<Screen> viewScreenList() throws ScreenNotFoundException {
		List<Screen> screen = screenRepository.findAll();
		if (screen.size() == 0)
			throw new ScreenNotFoundException("No screens found");
		return screen;
	}

	/**
	 * View screen by screenID
	 *
	 * @param screenId
	 * @return screen
	 * @throws ScreenNotFoundException
	 */
	@Override
	public Screen viewScreen(int screenId) throws ScreenNotFoundException {
		return screenRepository.findById(screenId).get();
	}

	/**
	 * Update screen
	 *
	 * @param screen
	 * @return screen
	 */
	@Override
	public Screen updateScreen(Screen screen) {
		screenRepository.saveAndFlush(screen);
		return screen;
	}

}
