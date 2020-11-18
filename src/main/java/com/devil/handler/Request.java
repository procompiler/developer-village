package com.devil.handler;

import java.util.Map;

public class Request {
  String commandPath;
  Map<String,Object> context;

  public Request(String commandPath, Map<String,Object> context) {
    this.commandPath = commandPath;
    this.context = context;
  }

  public String getCommandPath() {
    return commandPath;
  }

  public Map<String, Object> getContext() {
    return context;
  }

}
