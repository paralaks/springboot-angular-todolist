package paralaks_gmail_com.todolist.service.impl;

import paralaks_gmail_com.todolist.model.TodoItem;
import paralaks_gmail_com.todolist.repository.TodoItemRepository;
import paralaks_gmail_com.todolist.repository.TodoListRepository;
import paralaks_gmail_com.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemServiceImpl implements TodoItemService {
  private TodoItemRepository todoItemRepository;
  private TodoListRepository todoListRepository;

  @Autowired
  public TodoItemServiceImpl(TodoItemRepository itemRepository, TodoListRepository listRepository) {
    todoItemRepository = itemRepository;
    todoListRepository = listRepository;
  }

  @Override
  public List<TodoItem> list(Integer listId) {

    if (listId == null)
      return todoItemRepository.findAll();

    TodoItem todoItem = new TodoItem();
    todoItem.setListId(listId);
    Example<TodoItem> todoItemExample = Example.of(todoItem);

    return todoItemRepository.findAll(todoItemExample);
  }

  @Override
  public Optional<TodoItem> findOne(Integer id) {
    return todoItemRepository.findById(id);
  }

  @Override
  public TodoItem addOne(TodoItem item) {
    item.setTodoList(todoListRepository.findById(item.getListId()).orElse(null));
    return todoItemRepository.save(item);
  }

  @Override
  public String deleteOne(Integer id) {
    todoItemRepository.deleteById(id);
    return "{\"message\": \"Todo item deleted successfully.\"}";
  }
}
