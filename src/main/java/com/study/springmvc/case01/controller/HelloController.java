package com.study.springmvc.case01.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springmvc.case01.controller.entity.User;

@Controller
@RequestMapping("/case01/hello")
public class HelloController {

	@RequestMapping("/welcome")
	@ResponseBody
	public String welcome() {
		return "Hello SpringMVC "+new Date();
	}
	
	@RequestMapping(value = {"/sayhi","hi"}, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String sayHi(@RequestParam(value ="who") String who,
						@RequestParam(value="age", required = false, defaultValue = "0") Integer age) {
		//return "歡迎 "+ who+ " 你好"+ "你的年齡 :"+ age;
		//Test:http://localhost:8080/springmvc/mvc/case01/hello/sayhi?who=Webster
		//http://localhost:8080/springmvc/mvc/case01/hello/sayhi?who=Webster&age=30
		return String.format("歡迎 %s;你的年齡 :%d",who, age);
	}
	
	//@RequestMapping(path= {"/bmi"}, method= {RequestMethod.GET})
	@GetMapping(path= {"bmi"})
	@ResponseBody
	public String calculatorMethod(@RequestParam(value = "h", required = false) Optional<Double> h, 
								   @RequestParam(value = "w", required = false) Optional<Double> w) {
		String result="None";
		if(h.isPresent() && w.isPresent()) {
			double bmi=w.get()/Math.pow(h.get()/100, 2);
			result=String.format("%.2f", bmi);
		}		
		return String.format("bmi = %s", result);
		//http://localhost:8080/springmvc/mvc/case01/hello/bmi?h=170.0&w=60.0
	}
	
	@GetMapping(path = {"/exam/{score}"})
	@ResponseBody
	public String examScore(@PathVariable(value = "score") Integer score) {
		return String.format("Your Scores : %d;you're : %s", score,(score>=60)?"Pass":"Fail");
		//http://localhost:8080/springmvc/mvc/case01/hello/exam/75
	}
	
	@GetMapping(path={"/calc/{exp}"})
	@ResponseBody
	public String arithmeticCalc(@PathVariable(value = "exp") String exp, 
								 @RequestParam(value = "x", required = false, defaultValue = "0") Integer x,
								 @RequestParam(value = "y", required = false, defaultValue = "0") Integer y) {
		int result=0;
		switch (exp) {
		case "add":
			result=x+y;
			break;
		case "sub":
			result=x-y;
			break;
		default:
			return "result : exp value error";			
		}
		return String.format("result :%2d %s % 2d = %5d", x,(exp.equals("add"))?"+":"-",y,result);
		/*http://localhost:8080/springmvc/mvc/case01/hello//calc/add?x=30&y=20
		 * http://localhost:8080/springmvc/mvc/case01/hello//calc/sub?x=30&y=20
		 * http://localhost:8080/springmvc/mvc/case01/hello/calc/div
		 */
	}
	
	@GetMapping(path= {"/name*/java?"})
	@ResponseBody
	public String anyPath() {
		return "Path OK !!!";
		/*
		 * http://localhost:8080/springmvc/mvc/case01/hello/nameTony/java8
		 * http://localhost:8080/springmvc/mvc/case01/hello/nameTaipei/java7
		 * 任意一致;不能沒有
		 */
	}
	
	@GetMapping(path = {"/age"})
	@ResponseBody
	public String getAge(@RequestParam(value = "age") Integer[] ageList) {
		double ageOfAvg=Arrays.asList(ageList).stream().mapToInt(Integer::intValue).average().getAsDouble();
		return String.format("Avg of age : %.1f", ageOfAvg);
		/*
		 * http://localhost:8080/springmvc/mvc/case01/hello/age?age=18&age=19&age=20
		 */
	}
	
	@GetMapping(path= {"/javaexam"})
	@ResponseBody
	public String getJavaExam(@RequestParam("score") List<Integer> score) {
		IntSummaryStatistics stat=score.stream().mapToInt(Integer::intValue).summaryStatistics();
		return String.format("maxScore :%3d, minScore :%3d, average :%.2f, sumScore :%3d", stat.getMax(), stat.getMin(), stat.getAverage(),stat.getSum());
		//http://localhost:8080/springmvc/mvc/case01/hello/javaexam?score=80&score=70&score=90&score=60
	}
	/*
	@GetMapping(path= {"/javaexam/{exp}"})
	@ResponseBody
	public String getJavaExam(@PathVariable(value = "exp") String exp,
							  @RequestParam(value = "score", required = false, defaultValue = "0") Integer[] score) {
		
		double result=0;
		switch (exp) {
		case "max":
			result=Arrays.asList(score).stream().max(Integer::compare).get();
			break;
		case "min":
			result=Arrays.asList(score).stream().min(Integer::compare).get();
			break;
		case "avg":
			result=Arrays.asList(score).stream().mapToInt(Integer::intValue).average().getAsDouble();
			break;			
		default:
			return "result : exp value error";
		}
		return String.format("%s = %.2f", exp, result);
		//http://localhost:8080/springmvc/mvc/case01/hello/javaexam/avg?score=80&score=70&score=90&score=60
	}*/
	
	@GetMapping(path= {"/user"})
	@ResponseBody
	public String getUser(User user) {
		return user.toString();
		//http://localhost:8080/springmvc/mvc/case01/hello/user?name=John&age=18
	}
	
	@GetMapping(path= {"/person"})
	@ResponseBody
	public String getPerson(@RequestParam Map<String, String> person) {		
		return person.toString();
		//http://localhost:8080/springmvc/mvc/case01/hello/person?name=Mary&score=100&age=18&pass=true
	}
	
	@GetMapping(path= {"/sessioninfo"}, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String getSessionInfo(HttpSession httpSession) {
		String sessionKey="firstAccessTime";
		Object time=httpSession.getAttribute(sessionKey);
		if(time == null) {
			time=new Date();
			httpSession.setAttribute(sessionKey, time);
		}
		return String.format("firstAccessTime :%s\nsessionId: %s", time, httpSession.getId());
		//http://localhost:8080/springmvc/mvc/case01/hello/sessioninfo
	}
}