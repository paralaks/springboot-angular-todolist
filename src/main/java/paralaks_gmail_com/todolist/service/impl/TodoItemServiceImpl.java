package paralaks_gmail_com.todolist.service.impl;

import paralaks_gmail_com.todolist.model.TodoItem;
import paralaks_gmail_com.todolist.model.TodoList;
import paralaks_gmail_com.todolist.repository.TodoItemRepository;
import paralaks_gmail_com.todolist.repository.TodoListRepository;
import paralaks_gmail_com.todolist.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
  public Optional<List<TodoItem>> list(Integer listId) {
    if (listId == null)
      return Optional.of(todoItemRepository.findAll());

    Optional<TodoList> list = todoListRepository.findById(listId);
    return list.map(TodoList::getItems).or(Optional::empty);
  }

  @Override
  public Optional<TodoItem> findOne(Integer id) {
    return todoItemRepository.findById(id);
  }

  @Override
  public TodoItem addOne(TodoItem item) {
    Optional<TodoList> list = todoListRepository.findById(item.getListId());
    if (!list.isPresent())
      throw new IllegalArgumentException("List not found");

    item.setTodoList(list.get());
    return todoItemRepository.save(item);
  }

  @Override
  public String deleteOne(Integer id) {
    if (!todoItemRepository.findById(id).isPresent())
      return null;

    todoItemRepository.deleteById(id);
    return "{\"message\": \"Todo item deleted successfully.\"}";
  }
}
