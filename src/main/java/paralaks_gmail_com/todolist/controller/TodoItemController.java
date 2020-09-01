package paralaks_gmail_com.todolist.controller;

import paralaks_gmail_com.todolist.model.TodoItem;
import paralaks_gmail_com.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todoitems")
public class TodoItemController {
  private TodoItemService todoItemService;

  @Autowired
  public TodoItemController(TodoItemService service) {
    todoItemService = service;
  }

  @RequestMapping("/")
  public List<TodoItem> list() {
    List<TodoItem> list = todoItemService.list(null);
    list.forEach(l -> l.setListId(l.getTodoList().getId()));
    return list;
  }

  @RequestMapping("/listId/{listId}")
  public List<TodoItem> listFor(@PathVariable Integer listId) {
    List<TodoItem> list = todoItemService.list(listId);
    list.forEach(l -> l.setListId(listId));
    return list;
  }

  @RequestMapping("/{id}")
  public TodoItem todoItem(@PathVariable Integer id) {
    return todoItemService.findOne(id).orElse(null);
  }

  @PostMapping("/add")
  public TodoItem add(@RequestBody TodoItem item) {
    return todoItemService.addOne(item);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Integer id) {
    return todoItemService.deleteOne(id);
  }
}
