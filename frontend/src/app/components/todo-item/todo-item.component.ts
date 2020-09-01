import {Component, OnInit} from '@angular/core';
import {TodoService} from "../../services/todo.service";
import {take} from "rxjs/operators";
import {TodoItem} from "./TodoItem";
import {ActivatedRoute} from "@angular/router";
import {TodoList} from "../todo-list/TodoList";
import {Observable} from "rxjs";

@Component({
  selector: 'app-todo-item',
  templateUrl: './todo-item.component.html',
  styleUrls: ['./todo-item.component.sass']
})
export class TodoItemComponent implements OnInit {
  public todoItems: TodoItem[] = [];
  public todoList: Observable<TodoList>;
  public newName = "";
  public listId: number;

  constructor(private  todoService: TodoService, private router: ActivatedRoute) {
  }

  loadTodoList = () => {
    this.todoList = this.todoService.getTodoListItem(this.listId).pipe(take(1));
  };

  ngOnInit(): void {
    this.listId = +this.router.snapshot.paramMap.get('listId');
    this.loadTodoList();
  }

  public addItem(): void {
    const newTodoItem = {listId: this.listId, todo: this.newName, complete: false} as TodoItem;
    this.todoService.addTodoItem(newTodoItem).pipe(take(1))
      .subscribe(this.loadTodoList);
    this.newName = "";
  };

  public flipComplete(item: TodoItem) {
    item.complete = !item.complete;
    item.listId = this.listId;
    this.todoService.updateTodoItem(item).pipe(take(1))
      .subscribe(this.loadTodoList);
  }

  public deleteItem(item: TodoItem) {
    if (window.confirm("Are you sure you want to delete: " + item.todo + "?"))
      this.todoService.deleteTodoItem(item.id).pipe(take(1))
        .subscribe(this.loadTodoList);
  }

  public updateItem(item: TodoItem) {
    const newTodo = window.prompt("Please enter new text to update item", item.todo);
    if (newTodo.trim() == "")
      alert("Bad input. Can not update todo item");
    else {
      item.todo = newTodo;
      item.listId = this.listId;

      this.todoService.updateTodoItem(item).pipe(take(1))
        .subscribe(this.loadTodoList);
    }
  }
}
