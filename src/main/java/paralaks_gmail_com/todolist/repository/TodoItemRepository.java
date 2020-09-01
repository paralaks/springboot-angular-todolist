package paralaks_gmail_com.todolist.repository;

import paralaks_gmail_com.todolist.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Integer> {
}
