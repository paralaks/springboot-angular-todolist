package paralaks_gmail_com.todolist.service.impl;

import paralaks_gmail_com.todolist.model.TodoList;
import paralaks_gmail_com.todolist.repository.TodoListRepository;
import paralaks_gmail_com.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListServiceImpl implements TodoListService {
  private TodoListRepository todoListRepository;


  @Autowired
  public TodoListServiceImpl(TodoListRepository repository) {
    todoListRepository = repository;
  }


  @Override
  public List<TodoList> list() {
    return todoListRepository.findAll();
  }

  @Override
  public Optional<TodoList> findOne(Integer id) {
    return todoListRepository.findById(id);
  }

  @Override
  public TodoList addOne(TodoList todoList) {
    return todoListRepository.save(todoList);
  }

  @Override
  public String deleteOne(Integer id) {
    todoListRepository.deleteById(id);
    return "{\"message\": \"Todo list deleted successfully.\"}";
  }
}
