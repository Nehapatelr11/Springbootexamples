package com.example.web.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.dto.UserDetails;
import com.example.business.service.UserService;


@RestController
@RequestMapping("/api")
public class UserRestController {

 	@Autowired
	UserService userService;
 	
 	@RequestMapping(value="/users", method=RequestMethod.GET)
 	public ResponseEntity<List<UserDetails>>  retriveAllUser()
 	{
 		List<UserDetails> users = userService.retriveAllUsers();
 		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDetails>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDetails>>(users, HttpStatus.OK);
 	}
 	
 	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
 	public ResponseEntity<UserDetails> retriveUser(@PathVariable(value="id") int userId)
 	{
 		UserDetails user=userService.retriveUserDetails(userId);
 		
 		if (user == null) {
			return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDetails>(user, HttpStatus.OK);
 		
 	}
 	
 	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> deleteUser(@PathVariable(value="id") int userId)
 	{
 		UserDetails user=userService.retriveUserDetails(userId);
		if (user == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			 userService.deleteUser(userId);
			return new ResponseEntity<Void>(HttpStatus.GONE);
		}
 	}
 	
 	@RequestMapping(value="/user", method=RequestMethod.PUT)
 	public ResponseEntity<Void> updateUser(@RequestBody UserDetails userDetail)
 	{
 		UserDetails existingUser = userService.retriveUserDetails(userDetail.getUserId());
		if (existingUser == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			userService.updateUser(userDetail);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
 	}
 	
 	@RequestMapping(value="/user", method=RequestMethod.POST)
 	public ResponseEntity<UserDetails> saveUser(@RequestBody UserDetails userDetail)
 	{	
 		userService.saveUser(userDetail);
 		return new ResponseEntity<UserDetails>(userDetail, HttpStatus.CREATED);
 	}
 	
}
