package paralaks_gmail_com.todolist.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "todo_list")
public class TodoList {
  @Id
  @Nullable
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @JsonManagedReference
  @OneToMany(mappedBy = "todoList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<TodoItem> items;

  public TodoList() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<TodoItem> getItems() {
    return items;
  }

  public void setItems(List<TodoItem> items) {
    this.items = items;
    for (TodoItem item : this.items)
      item.setTodoList(this);
  }
}
