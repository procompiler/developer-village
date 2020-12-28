package com.devil.web.app.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.devil.domain.Collect;
import com.devil.service.CollectService;

@Controller("ajax.collectController")
@RequestMapping("/ajax/collect")
@SessionAttributes("loginUser")
public class CollectController {
  @Autowired CollectService collectService;
  
  @PostMapping("updateOrder")
  public void updateOrder(Collect collect) throws Exception {
    collectService.updateOrder(collect);
  }
}
