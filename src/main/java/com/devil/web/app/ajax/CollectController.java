package com.devil.web.app.ajax;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.devil.domain.Badge;
import com.devil.domain.Collect;
import com.devil.domain.User;
import com.devil.service.CollectService;

@Controller("ajax.collectController")
@RequestMapping("/ajax/collect")
public class CollectController {
  @Autowired CollectService collectService;
  
  @PostMapping("updateOrder")
  public String updateOrder(String order, HttpSession session, HttpServletRequest request) throws Exception {
    String[] orders = order.split(",");
    List<Collect> collects = new ArrayList<>();
    for (int i = 0; i < orders.length; i++) {
      Collect collect = new Collect();
      collect.setUser((User) session.getAttribute("loginUser"));
      collect.setBadge(new Badge().setNo(Integer.parseInt(orders[i])));
      collect.setOrder(i);
      collects.add(collect);
    }
    collectService.updateAllOrder(collects);
    return "redirect:" + request.getHeader("Referer");
  }
}
