package paralaks_gmail_com.todolist.controller;

import org.springframework.http.ResponseEntity;
import paralaks_gmail_com.todolist.model.TodoList;
import paralaks_gmail_com.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todolists")
public class TodoListController {
  private TodoListService todoListService;

  @Autowired
  public TodoListController(TodoListService service) {
    todoListService = service;
  }

  @GetMapping("/")
  public ResponseEntity<List<TodoList>> list() {
    return ResponseEntity.ok(todoListService.list());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TodoList> findOne(@PathVariable Integer id) {
    Optional<TodoList> list = todoListService.findOne(id);
    return list.isPresent() ? ResponseEntity.ok(list.get()) : ResponseEntity.notFound().build();
  }

  @PostMapping("/add")
  public TodoList addOne(@RequestBody TodoList list) {
    return todoListService.addOne(list);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteOne(@PathVariable Integer id) {
    if (id == null)
      return ResponseEntity.notFound().build();

    String result = todoListService.deleteOne(id);

    return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
  }
}
