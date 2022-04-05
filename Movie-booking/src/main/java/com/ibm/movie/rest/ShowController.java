package com.ibm.movie.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.movie.entity.Show;
import com.ibm.movie.exception.ShowNotFoundException;
import com.ibm.movie.services.ShowService;

/**
 * @author Sayan Sarkar
 * @version 1.0
 * @category Show
 */
@CrossOrigin
@RestController
//@RequestMapping("/shows")
public class ShowController {

	@Autowired
	private ShowService showService;

	/**
	 * Stores a Show object in the Database.
	 * 
	 * @param show
	 * @param screenId
	 * @return Show
	 */
	@PostMapping(value = "/addshow", consumes =  "application/json")
	public Show addShow(@RequestBody Show show,@RequestParam(required = false) Integer screenId)  {

		return showService.addShow(show, screenId);
	}

	/**
	 * Removes persisted Show instance from the Database.
	 * 
	 * @param showId
	 * @return Show
	 */
	@DeleteMapping(value = "/delete/{showId}", produces = "application/json")
	public Show removeShow(@PathVariable int showId)  {

		Show show = showService.viewShow(showId);
		if (show == null) {
			System.out.println("Error");
		} else {
			show = showService.removeShow(showId);
		}
		return show;
	}

	/**
	 * Updates a existing Show record in the database.
	 * 
	 * @param show
	 * @param theatreId
	 * @param screenId
	 * @return Show
	 */
	@PutMapping(value = "/updateshow", produces = "application/json")
	public Show updateShow(@RequestBody Show show, @RequestParam(required = false) Integer theatreId,@RequestParam(required = false) Integer screenId)  {
		if (show == null) {
			System.out.println("Error");
		} else {
			show = showService.updateShow(show, theatreId);
		}
		return show;
	}

	/**
	 * Returns the record from the database using identifier - showId
	 * 
	 * @param showId
	 * @return Show
	 * @throws ShowNotFoundException
	 */
	@GetMapping(value = "/view/{showId}", produces = "application/json")
	public Show viewShow(@PathVariable int showId) throws  ShowNotFoundException {
		Show show = showService.viewShow(showId);
		if(show.getShowId() != showId)
			throw new ShowNotFoundException("Show with  " + showId + " doesn't exist");
		else
			show = showService.viewShow(showId);
		return show;
	}

	/**
	 * Return's the List of Shows existing from the Database
	 * 
	 * @return List<Show>
	 * @throws AccessForbiddenException
	 */
	@GetMapping(value = "/findallshow", produces = "application/json")
	public List<Show> viewShowList() {
		return showService.viewAllShows();
	}

	/**
	 * Returns the show list by localDate
	 * @param date
	 * @return date
	 */
	@GetMapping(value = "/date/{date}", produces = "application/json")
	public List<Show> viewShowByLocalDate(@PathVariable LocalDate date) {

		return showService.viewShowList(date);
	}
}
