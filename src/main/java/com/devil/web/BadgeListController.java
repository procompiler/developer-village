package com.devil.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devil.domain.Badge;
import com.devil.service.BadgeService;

@Controller
public class BadgeListController  {
	BadgeService badgeService;
	
	 public BadgeListController(BadgeService badgeService) {
		    this.badgeService = badgeService;
		  }
	@RequestMapping("/badge/list")
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	response.setContentType("text/html;charset=UTF-8");
	
      String keyword = request.getParameter("keyword");
      List<Badge> list = badgeService.list(keyword);
 
      request.setAttribute("list", list);
      return "/badge/list.jsp";
    
  }

  @RequestMapping("/badge/form")
  public String execute2(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return "/badge/form.jsp";
  }
	
	
}