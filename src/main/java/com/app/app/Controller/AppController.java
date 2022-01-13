package com.app.app.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.app.HomeController;
import com.app.app.Dao.UDao;
import com.app.app.Dao.UserDao;
import com.app.app.Dto.UserDto;
import com.google.protobuf.TextFormat.ParseException;

@Controller
public class AppController {
	UserDao userdao;
	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
	@RequestMapping("/Register")
	public String Register(Model model) {
		return "Register";
	}
	@RequestMapping("/Login")
	public String Login(Model model) {
		return "Login";
	}
	
	@ResponseBody
	@RequestMapping("/RegisterAct")
	public String RegisterAct(HttpServletRequest request) {
		UDao dao = sqlSession.getMapper(UDao.class);
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("user_id", request.getParameter("user_id").trim());
		map.put("password", request.getParameter("password").trim());
		map.put("nickname", request.getParameter("nickname").trim());
		map.put("name", request.getParameter("name").trim());
		
		try {
			dao.Register(map);
			String str = "<script>";
			str+="alert('Register success');";
			str+="window.location.href = '/bbs';";
			str+="</script>";
			return str;
		} catch (Exception e) {
			String str = "<script>";
			str+="alert('Duplicate ID');";
			str+="window.location.href = '/bbs/Register';";
			str+="</script>";
			return str;
			
		}
		
//		return "OK";
	}
	
	@ResponseBody
	@RequestMapping(value="/ID_Check", produces="text/plane")
	public String ID_Check(@RequestBody String paramData) throws ParseException {
		// 클라이언트가 보낸 ID값
		String ID = paramData.trim();
		System.out.println(ID);
		UDao dao = sqlSession.getMapper(UDao.class);
		UserDto dto = dao.Id_Check(ID);
		
		if(dto != null) {//결과 값이 있으면 아이디 존재
			return "-1";
		} else {
			System.out.println("null");
			return "0";
		}
	}
	
	@ResponseBody
	@RequestMapping("/LoginAct")
	public String LoginAct(HttpSession session, HttpServletRequest request) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		System.out.println("로그인 시도\nID:"+id+"\npassword:"+password);
		Map map = new HashMap();
		map.put("id", id);
		map.put("password", password);
		UDao dao = sqlSession.getMapper(UDao.class);
		UserDto dto = dao.Login(map);
		if(dto !=null) {
			session.setAttribute("user_id", id);
			String str = "<script>";
			str+="alert('Login success');";
			str+="window.location.href = '/bbs';";
			str+="</script>";
			return str;
		}
		else {
			session.setAttribute("user_id", id);
			String str = "<script>";
			str+="alert('Login fail');";
			str+="history.back();";
			str+="</script>";
			return str;
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/Logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		String str = "<script>";
		str+="alert('Logout success');";
		str+="window.location.href = '/bbs';";
		str+="</script>";
		return str;
	}
	

}
