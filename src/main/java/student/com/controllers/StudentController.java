package student.com.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import student.com.dto.CourseDto;
import student.com.dto.StudentDto;
import student.com.models.CourseBean;
import student.com.service.CourseService;
import student.com.service.StudentService;



@Component
@ComponentScan("student.com.service")
@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	
	@RequestMapping(value="/addStudent",method=RequestMethod.GET)
	public ModelAndView addStudent(ModelMap m) {
		List<CourseDto> courseList = courseService.getAllCourse();
		m.addAttribute("courseList", courseList);		
		return new ModelAndView("addStudent", "studentDto", new StudentDto());
	}
	
	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudentProcess(@ModelAttribute("studentDto") @Validated StudentDto studentDto, BindingResult bs,
	        @RequestParam("courseId") String[] courseId, @RequestParam("photoImageInput") MultipartFile file, ModelMap m,
	        HttpSession session) {
		
		List<CourseDto> courseList = courseService.getAllCourse();
		
		if(bs.hasErrors()) {
			m.addAttribute("courseList",courseList);
			m.addAttribute("error","Invalid Student required");
			return "addStudent";
		}
		
	 
	 try {	
		
		 
		 if(file != null && !file.isEmpty()) {		
			 System.out.println("photo"+ studentDto.getPhotoImageInput());
			 byte[] photoBytes = studentDto.getPhotoImageInput().getBytes();	
			 System.out.println(photoBytes);
			 studentDto.setPhoto(photoBytes);
		 }else {
			 m.addAttribute("courseList", courseList);
			 m.addAttribute("error","Photo Required");
			 return "addStudent";
		 }
		 	 
		 
		 
		 	int studentCount = studentService.getCount();
			int nextSequence = studentCount+1;
			 String formattedStudentId = String.format("STU%03d", nextSequence);
			 studentDto.setStudentId(formattedStudentId);

			 Set<CourseBean> courses  = studentService.addCourseForStudent(courseId);
			 if(courses != null) {
			 studentDto.setCourses(courses);
			 }
			  int rs = studentService.addNewStudent(studentDto);
		 
	        if(rs == 0) {
	        	 m.addAttribute("courseList",courseList);
	            m.addAttribute("error", "Update Failed");
	            return "addStudent";
	        }

	        return "redirect:/studentlist";
	 }catch(IOException e) {
		 	System.out.print(e);
		 	 m.addAttribute("courseList",courseList);
	        m.addAttribute("error", "An error occurred while updating. Please try again later.");
	        return "addStudent";
	 }	 
	 
	}
	
	@RequestMapping(value = "/viewStudent", method = RequestMethod.GET)
	public String displayAllStudent(ModelMap m) {
		List<StudentDto> list = studentService.getAllStudent();
		if (list.size() == 0) {
			m.addAttribute("msg", " Student Data not Found");
		} else {
			m.addAttribute("studentList", list);

		}
		return "viewStudent";

	}
	
	
	@DeleteMapping("status/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        boolean success = studentService.updateStatus(id, 1); // Set delete status to 1
        if (success) {
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete student");
        }
    } 
		
	
/*	@RequestMapping(value="/updateStudent/{id}",method=RequestMethod.GET)
	public ModelAndView updateStudent(@PathVariable int id,ModelMap m,HttpSession session) {
	
		Student student = studentRepository.selectOneStudent(id);
		List<Course> list = courseRepository.selectAllCourse();
		m.addAttribute("courseLists",list);
		return new ModelAndView("update_Student","bean",student);
	}
	
	@RequestMapping(value="/updateStudentProcess", method=RequestMethod.POST)
	public String updateStudentProcess(@ModelAttribute("bean") @Validated Student student,BindingResult bs,@RequestParam("photo") MultipartFile file,ModelMap m,HttpSession session) {
		
		List<Course> courseLists = courseRepository.selectAllCourse();
		
		if(bs.hasErrors()) {
			m.addAttribute("courseLists",courseLists);
			m.addAttribute("error","Invalid Student required");
			return "update_Student";
		}	
		
		 try {					 
			 
			 if(file != null && !file.isEmpty()) {		
				 
			
				 
				 String imageName = file.getOriginalFilename();
				 
				 String imagePath="C:\\Thiha-Home\\ojt_batch_13_studentmvc\\Student_Jpa\\src\\main\\webapp\\WEB-INF\\image";
				 
				 File imageFile = new File(imagePath, imageName);
	             file.transferTo(imageFile);
	             student.setPhotoPath(imageName);
			 }else {
				 m.addAttribute("courseLists",courseLists);
				 m.addAttribute("error","Photo Required");
				 return "update_Student";
			 }
		 } catch (IOException e) {
		        System.out.print(e);
		    }	 

	    
	    int rs = studentRepository.updateStudent(student);
	    
	    if (rs == 0) {
	    	m.addAttribute("courseLists",courseLists);	
	        m.addAttribute("error", "Update Failed");
	        return "update_Student";
	    }
	    
	    return "redirect:/studentlist";

	}
*/	
	
	
		

}
