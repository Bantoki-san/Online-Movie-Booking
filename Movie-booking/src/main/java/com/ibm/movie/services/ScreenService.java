package com.ibm.movie.services;

import java.util.List;

import com.ibm.movie.entity.Screen;
import com.ibm.movie.exception.ScreenNotFoundException;


/**
 * @author Mouli Roy
 * @version 1.0
 * @category ScreenService
 *
 */
public interface ScreenService {
	public Screen addScreen(Screen screen) throws ScreenNotFoundException;

	public List<Screen> viewScreenList() throws ScreenNotFoundException;

	public Screen updateScreen(Screen s);

	public Screen viewScreen(int screenId) throws ScreenNotFoundException;
}
