import {Component, OnInit} from '@angular/core';
import {TodoService} from "../../services/todo.service";
import {TodoList} from "./TodoList";
import {take} from "rxjs/operators";
import {Observable} from "rxjs";

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.sass']
})
export class TodoListComponent implements OnInit {
  public todoLists: Observable<TodoList[]>;
  public newName = "";

  constructor(private  todoService: TodoService) {
  }

  loadTodoLists = () => {
    this.todoLists = this.todoService.getTodoLists().pipe(take(1));
  };

  ngOnInit(): void {
    this.loadTodoLists();
  }

  public addList(): void {
    this.todoService.addTodoList(this.newName).pipe(take(1)).subscribe(this.loadTodoLists);
    this.newName = "";
  };
}
