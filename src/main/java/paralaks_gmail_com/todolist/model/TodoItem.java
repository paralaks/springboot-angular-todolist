package paralaks_gmail_com.todolist.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "todo_item")
public class TodoItem {
  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "list_id")
  private TodoList todoList;
  private transient Integer listId;

  private String todo;
  private Boolean complete;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTodo() {
    return todo;
  }

  public void setTodo(String todo) {
    this.todo = todo;
  }

  public Boolean isComplete() {
    return complete;
  }

  public void setComplete(Boolean complete) {
    this.complete = complete;
  }

  public TodoList getTodoList() {
    return todoList;
  }

  public void setTodoList(TodoList list) {
    this.listId = list == null ? null : list.getId();
    this.todoList = list;
  }

  public Integer getListId() {
    return listId;
  }

  public void setListId(Integer listId) {
    this.listId = listId;
  }
}
