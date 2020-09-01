import {Injectable} from '@angular/core';
import {AppConfig} from "./AppConfig";
import {HttpClient} from "@angular/common/http";
import {take} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  private configUrl = "assets/config.json"
  private config: AppConfig;

  constructor(private http: HttpClient) {
    this.loadConfig();
  }

  public loadConfig = () => {
    return new Promise(resolve =>
      this.http.get(this.configUrl).pipe(take(1)).subscribe((config: AppConfig) => {
        this.config = config;
        resolve(config);
      }));
  }

  public getTodoListsUrl() {
    return this.config.todoListsUrl;
  }

  public getTodoItemsUrl() {
    return this.config.todoItemsUrl;
  }

}
