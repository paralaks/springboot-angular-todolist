package paralaks_gmail_com.todolist.controller;

import paralaks_gmail_com.todolist.model.TodoList;
import paralaks_gmail_com.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolists")
public class TodoListController {
  private TodoListService todoListService;

  @Autowired
  public TodoListController(TodoListService service) {
    todoListService = service;
  }

  @RequestMapping("/")
  public List<TodoList> list() {
    return todoListService.list();
  }

  @RequestMapping("/{id}")
  public TodoList findOne(@PathVariable Integer id) {
    return todoListService.findOne(id).orElse(null);
  }

  @PostMapping("/add")
  public TodoList addOne(@RequestBody TodoList list) {
    return todoListService.addOne(list);
  }

  @DeleteMapping("/{id}")
  public String deleteOne(@PathVariable Integer id) {
    return todoListService.deleteOne(id);
  }
}
