package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.demo.dto.RegisterUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.helper.Message;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("userDto", userDto);
		return "register";
	}
	
	@PostMapping("/register_user")
	public String register(@Valid @ModelAttribute("userDto") RegisterUserDto userDto, BindingResult bindingResult, Model model, HttpSession session) {
		
		try {
			if(!userDto.getPassword().equals(userDto.getConfirmPassword())) {
				System.out.println("password not matched");
				throw new Exception("password not match");
			}
			if(bindingResult.hasErrors()) {
				// issue with reload
				// return "redirect:/register";
				return "register";
				
			}
			
			 User user = new User();
			 user.setUsername(userDto.getUsername());
			 user.setEmail(userDto.getEmail());
			 user.setPassword(userDto.getPassword());
			//userService.saveUser(user);
			 System.out.println("user registered");
			//registerUser(userDto);
			
			session.setAttribute("message", new Message("Successfully Registered", "alert-success"));
			
			return "redirect:/";
		}
		catch (Exception e) {
			session.setAttribute("message", new Message("Registration Failed "+e.getMessage(), "alert-danger"));
			model.addAttribute("userDto", userDto);
			// return "register";
			return "redirect:/register";
		}
	}
	
	private void registerUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		userService.saveUser(user);
		System.out.println("user registered");
	}
}
