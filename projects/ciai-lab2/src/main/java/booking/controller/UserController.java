package booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import booking.model.User;
import booking.repository.UserRepository;
import booking.util.UserNotFoundException;;

@Controller
@RequestMapping(value="/users")
public class UserController {
	@Autowired
	UserRepository users;

	// GET  /users 			- the list of users
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("users", users.findAll());
		return "users/index";
	}

	// GET  /users.json 			- the list of users
	@RequestMapping(method=RequestMethod.GET, produces={"text/plain","application/json"})
	public @ResponseBody Iterable<User> indexJSON(Model model) {
		return users.findAll();
	}

	// GET  /users/new			- the form to fill the data for a new User
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "users/create";
	}

	// POST /users         	- creates a new User
	@RequestMapping(method=RequestMethod.POST)
	public String saveIt(@ModelAttribute User user, Model model) {
		users.save(user);
		model.addAttribute("user", user);
		return "redirect:/users";
	}
	/********************************************/

	// GET  /users/{id} 		- the user with identifier {id}
	@RequestMapping(value="{id}", method=RequestMethod.GET) 
	public String show(@PathVariable("id") long id, Model model) {
		User user = users.findOne(id);
		if( user == null )
			throw new UserNotFoundException();
		model.addAttribute("user", user );
		//model.addAttribute("rooms", user.getRooms()); 
		return "users/show";
	}

	// GET  /users/{id}.json 		- the user with identifier {id}
	@RequestMapping(value="{id}", method=RequestMethod.GET, produces={"text/plain","application/json"})
	public @ResponseBody User showJSON(@PathVariable("id") long id, Model model) {
		User user = users.findOne(id);
		if( user == null )
			throw new UserNotFoundException();
		return user;
	}

	// GET  /users/{id}/edit 	- the form to edit the user with identifier {id}
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(@PathVariable("id") long id, Model model) {
		model.addAttribute("user", users.findOne(id));
		return "users/edit";
	}

	// POST /users/{id} 	 	- update the user with identifier {id}
	@RequestMapping(value="{id}", method=RequestMethod.POST)
	public String editSave(@PathVariable("id") long id, User user, Model model) {
		users.save(user);
		return "redirect:/";
	}
}
