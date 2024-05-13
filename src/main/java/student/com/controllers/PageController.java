package student.com.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import student.com.service.CourseService;
import student.com.service.StudentService;
import student.com.service.UserService;
import student.com.utils.Helper;

@Controller
@AllArgsConstructor
public class PageController {
	@Autowired
	UserService  userService;
	@Autowired
	CourseService  courseService;
	@Autowired
	StudentService  studentService;
	@GetMapping({"/"})
	public String home() {
		return "home";
	}
	
	@GetMapping("/dashboard")
	public ModelAndView dashboard() {
	    Map<String, Integer> counts = new HashMap<>();

	    // Retrieve counts and store in the map
	    int userCount = userService.getUserCount();
	   // int courseCount = courseService.getCourseCount();
	    counts.put("userCount", userCount);
	   // counts.put("courseCount", courseCount);

	    // Pass the map to the dashboard view
	    return new ModelAndView("dashboard", "counts", counts);
	}
	
}
