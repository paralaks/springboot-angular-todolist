package paralaks_gmail_com.todolist.repository;

import paralaks_gmail_com.todolist.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
}
