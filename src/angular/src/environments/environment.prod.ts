import {HttpHeaders} from "@angular/common/http";

export const environment = {
  production: true
};
export const restPath = {
  restPath: 'http://localhost:8080/FootballLeague/api'
}
export const option = {
  headers: new HttpHeaders().set('Content-Type', 'application/json')
}

