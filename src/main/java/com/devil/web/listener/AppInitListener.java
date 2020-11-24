package com.devil.web.listener;

import java.util.Map;
import com.devil.context.ApplicationContextListener;

public class AppInitListener implements ApplicationContextListener {
  @Override
  public void contextInitialized(Map<String,Object> context) {
    System.out.println("DEVIL에 오신 것을 환영합니다!");

  }


  @Override
  public void contextDestroyed(Map<String,Object> context) {
    System.out.println("DEVIL을 종료합니다!");
  }


}
