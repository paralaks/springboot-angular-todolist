import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TodoList} from "../components/todo-list/TodoList";
import {Observable} from "rxjs";
import {AppConfigService} from "./app-config.service";
import {TodoItem} from "../components/todo-item/TodoItem";

const HTTP_OPTIONS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  constructor(private config: AppConfigService, private http: HttpClient) {
  }

  public getTodoLists() {
    return this.http.get(this.config.getTodoListsUrl()) as Observable<TodoList[]>;
  };

  public getTodoListItem(todoListId: number) {
    return this.http.get(this.config.getTodoListsUrl() + todoListId) as Observable<TodoList>;
  };

  public addTodoList(listName: string) {
    return this.http.post(this.config.getTodoListsUrl() + 'add', {name: listName}, HTTP_OPTIONS) as Observable<TodoList>;
  };


  public getTodoItems(listId: number) {
    return this.http.get(this.config.getTodoItemsUrl() + 'listId/' + listId) as Observable<TodoItem[]>;
  };

  public addTodoItem(todoItem: TodoItem) {
    return this.http.post(this.config.getTodoItemsUrl() + 'add', todoItem, HTTP_OPTIONS) as Observable<TodoItem>;
  };

  public deleteTodoItem(itemId: number) {
    return this.http.delete(this.config.getTodoItemsUrl() + itemId) as Observable<string>;
  };

  public updateTodoItem(todoItem: TodoItem) {
    return this.addTodoItem(todoItem);
  };

}
