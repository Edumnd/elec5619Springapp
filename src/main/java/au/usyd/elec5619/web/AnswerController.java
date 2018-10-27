package au.usyd.elec5619.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import au.usyd.elec5619.domain.Answers;
import au.usyd.elec5619.domain.Questions;
import au.usyd.elec5619.domain.SubQA;
import au.usyd.elec5619.service.AnswerManager;
import au.usyd.elec5619.service.QuestionManager;


@Controller
@RequestMapping(value="/**")
public class AnswerController {

	@Autowired
	private  AnswerManager answerManager;
	@Autowired
	private QuestionManager questionManager;
	
	@RequestMapping(value="/addanswers", method=RequestMethod.POST)
	public String addanswers(HttpServletRequest request,HttpServletResponse response)  throws Exception, IOException {
		ArrayList<SubQA> sub = new ArrayList<SubQA>();
		Answers answer = new Answers(Integer.parseInt(request.getParameter("questionID")),0,request.getParameter("Answers"),false,Integer.parseInt(request.getParameter("userid")),sub);
		answerManager.addanswer(answer);
		return "redirect:questiondetails/"+Integer.parseInt(request.getParameter("questionID"));
	}
	
	@RequestMapping(value="/UserQuestion/{id}", method=RequestMethod.GET)
	public ModelAndView showrecipedetails(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
		Questions question = questionManager.getquestionbyID(id);
		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("question", question);
		List<Answers> answerslist = answerManager.getanswersbyID(id);
		myModel.put("answers", answerslist);
		request.getSession().setAttribute("username", "xxx");
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");
		return new ModelAndView("UserQuestion","model",myModel);
	}
	
	@RequestMapping(value="/addsub/{id}", method=RequestMethod.POST)
	public String addsub(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response)  throws Exception, IOException {
		SubQA sub = new SubQA(request.getParameter("Sub"),1);
		System.out.println(id);
		
		answerManager.addsub(id,sub);
		return "home";
	}
}