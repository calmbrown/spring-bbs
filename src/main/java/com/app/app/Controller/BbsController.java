package com.app.app.Controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.app.Dao.BDao;
import com.app.app.Dao.BbsDao;
import com.app.app.Dto.BbsDto;

@Controller
public class BbsController {
	@Autowired
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);
	
	@RequestMapping("/write")
	public String Write(Model model, HttpSession session, HttpServletResponse response ) throws IOException {
		if(session.getAttribute("user_id") == null) {
			System.out.println("로그인세션 없음");
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>alert('로그인 하렴 '); history.back();</script>");
			out.flush();
		
		}
	return "write";
		
	}
	
	@RequestMapping("/WriteAct")
	public String Write(HttpSession session, HttpServletRequest request) {
		Map map = new HashMap();
		map.put("bbs_title", request.getParameter("bbs_title"));
		map.put("bbs_content", request.getParameter("bbs_content"));
		map.put("author", session.getAttribute("user_id"));
		BDao dao = sqlSession.getMapper(BDao.class);
		dao.Write(map);
		return "index";
		
		
	}
	
	@RequestMapping("/bbs_list")
	public String bbs(Model model) {
		BDao dao = sqlSession.getMapper(BDao.class);
		ArrayList<BbsDto> list = dao.list();
		model.addAttribute("list",list);
		return "bbs_list";
	}
	
	@RequestMapping("/bbs_list/{bbs_id}")
	public String bbs_detail(@PathVariable String bbs_id, Model model) {
		BDao dao = sqlSession.getMapper(BDao.class);
		BbsDto detail = dao.detail(bbs_id);
		model.addAttribute("detail",detail);
		return "bbs_detail";
	}
	
	@RequestMapping("/bbs_list/edit/{bbs_id}")
	public String bbs_edit(@PathVariable String bbs_id, Model model) {
		BDao dao = sqlSession.getMapper(BDao.class);
		BbsDto detail = dao.detail(bbs_id);
		model.addAttribute("detail",detail);
		return "bbs_edit";
	}
	
	@ResponseBody
	@RequestMapping("/EditAct")
	public String EditAct(HttpSession session, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		Map map = new HashMap();
		map.put("bbs_title", request.getParameter("bbs_title"));
		map.put("bbs_content", request.getParameter("bbs_content"));
		map.put("bbs_id", request.getParameter("bbs_id"));
		BDao dao = sqlSession.getMapper(BDao.class);
		dao.Edit(map);
		String str="<script>";
		str+="location.href='/bbs/bbs_list/';";
		str+="</script>";
		return str;
		
	}
	
	@ResponseBody
	@RequestMapping("/Delete")
	public String bbs_delete(HttpServletRequest request, Model model) {
		BDao dao = sqlSession.getMapper(BDao.class);
		dao.Delete(request.getParameter("bbs_id"));
		String str="<script>";
		str+="location.href='/bbs/bbs_list/';";
		str+="</script>";
		return str;
		
	}
}
