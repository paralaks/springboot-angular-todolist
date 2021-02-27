package paralaks_gmail_com.todolist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import paralaks_gmail_com.todolist.model.TodoItem;
import paralaks_gmail_com.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todoitems")
public class TodoItemController {
  private TodoItemService todoItemService;

  @Autowired
  public TodoItemController(TodoItemService service) {
    todoItemService = service;
  }

  private ResponseEntity<List<TodoItem>> getTodoItemListResponse(Integer listId) {
    Optional<List<TodoItem>> list = todoItemService.list(listId);

    return list.isPresent() ? ResponseEntity.ok(list.get()) : ResponseEntity.notFound().build();
  }

  @GetMapping("/")
  public ResponseEntity<List<TodoItem>> list() {
    return getTodoItemListResponse(null);
  }

  @GetMapping("/listId/{listId}")
  public ResponseEntity<List<TodoItem>> listFor(@PathVariable Integer listId) {
    return getTodoItemListResponse(listId);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TodoItem> todoItem(@PathVariable Integer id) {
    Optional<TodoItem> todoItem = todoItemService.findOne(id);

    return todoItem.isPresent() ? ResponseEntity.ok(todoItem.get()) : ResponseEntity.notFound().build();
  }

  @PostMapping("/add")
  public ResponseEntity<TodoItem> add(@RequestBody TodoItem item) {
    try {
      return ResponseEntity.status(HttpStatus.CREATED).body(todoItemService.addOne(item));
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Integer id) {
    if (id == null)
      ResponseEntity.notFound().build();

    String result = todoItemService.deleteOne(id);

    return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
  }
}
