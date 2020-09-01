package paralaks_gmail_com.todolist.service;

import paralaks_gmail_com.todolist.model.TodoList;

import java.util.List;
import java.util.Optional;

public interface TodoListService {
  List<TodoList> list();

  Optional<TodoList> findOne(Integer id);

  TodoList addOne(TodoList todoList);

  String deleteOne(Integer id);
}
