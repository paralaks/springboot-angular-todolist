package paralaks_gmail_com.todolist.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController {
  private static final String ERROR_PATH = "/error";

  @Override
  public String getErrorPath() {
    return ERROR_PATH;
  }

  @RequestMapping("/error")
  public String errorPage() {
    return "Page was not found!";
  }
}
