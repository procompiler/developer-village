package com.devil.web.app;

import java.beans.PropertyEditorSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalControllerAdvice {

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    // String ==> java.sql.Date
    binder.registerCustomEditor(
        java.util.Date.class,
        new PropertyEditorSupport() {
          @Override
          public void setAsText(String text) throws IllegalArgumentException {
            try {
              setValue(java.sql.Date.valueOf(text));
            } catch (Exception e) {
              throw new IllegalArgumentException(e);
            }
          }
        });
  }
}


