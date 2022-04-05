package com.ibm.movie.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.movie.entity.Screen;
import com.ibm.movie.entity.Show;
import com.ibm.movie.repo.ScreenRepository;
import com.ibm.movie.repo.ShowRepository;

/**
 * @author Sayan Sarkar
 * @version 1.0
 * @category Show
 *
 */
@Service
public class ShowServiceImpl implements ShowService {
	@Autowired
	private ShowRepository showrepository;
	@Autowired
	private ScreenRepository screenRepository;

	/**
	 *Add show object
	 *@param show
	 *@param screenId
	 *@return show
	 */
	@Override
	public Show addShow(Show show, Integer screenId) {
		Screen screen = new Screen();
		if (screenId != null) {
			screen = screenRepository.getById(screenId);
			show.setScreen(screen);
		}
		showrepository.saveAndFlush(show);
		return show;
	}

	/**
	 *Update show object
	 *@param show
	 *@param screenId
	 *@return show
	 */
	@Override
	public Show updateShow(Show show, Integer screenId) {
		Screen screen = new Screen();
		if (screenId != null) {
			screen = screenRepository.getById(screenId);
			show.setScreen(screen);
		}
		showrepository.saveAndFlush(show);
		return show;
	}

	/**
	 *Remove show object
	 *@param showid
	 *@return show
	 */
	@Override
	public Show removeShow(int showid) {
		Show show = showrepository.findById(showid).get();
		showrepository.delete(show);
		return show;
	}

	/**
	 *View show list
	 *@param showid
	 *@return show
	 */
	@Override
	public Show viewShow(int showid) {
		return showrepository.findById(showid).get();
	}

	/**
	 *List of shows
	 *@return show
	 */
	@Override
	public List<Show> viewAllShows() {
		return showrepository.findAll();
	}

	/**
	 *List of shows by localDate
	 *@param date
	 *@return show
	 */
	@Override
	public List<Show> viewShowList(LocalDate date) {
		List<Show> shList = new ArrayList<>();
		for (Show show : showrepository.findAll()) {
			if (show.getShowDate() != null && show.getShowDate().isEqual(date)) {
				shList.add(show);
			}
		}
		return shList;
	}

}
