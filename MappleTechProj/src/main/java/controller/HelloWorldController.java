package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc
/**
 * The Class HelloWorldController.
 */
@Controller
public class HelloWorldController {
	
	/**
	 * Show message.
	 *
	 * @param name the name
	 * @return the model and view
	 */
	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") 
			String name)
	{
		System.out.println("Controller");
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message","Hello ");
		mv.addObject("name", name);
		return mv;
	}

}
