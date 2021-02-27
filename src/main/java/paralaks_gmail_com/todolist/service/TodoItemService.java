package paralaks_gmail_com.todolist.service;

import paralaks_gmail_com.todolist.model.TodoItem;

import java.util.List;
import java.util.Optional;

public interface TodoItemService {
  Optional<List<TodoItem>> list(Integer listId);

  Optional<TodoItem> findOne(Integer id);

  TodoItem addOne(TodoItem item);

  String deleteOne(Integer id);
}
