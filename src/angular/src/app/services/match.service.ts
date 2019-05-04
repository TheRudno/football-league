import { Injectable } from '@angular/core';
import {Observable, throwError} from 'rxjs';
import {Match, MatchAdapter} from '../shared/match.model';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {restPath} from 'src/environments/environment';
import {catchError, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  private restPath = restPath.restPath + '/match/';
  // private matches: Match[] = [];

  constructor(
    private http: HttpClient,
    private matchAdapter: MatchAdapter
  ) { }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code. The response body may contain clues as to what went wrong,
      console.error(`Backend returned code ${error.status}, ` + `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(error);
  }

  getMatches(): Observable<Match[]> {
    return this.http.get(this.restPath).pipe(
      map((data: any) => data.matches.map(item => this.matchAdapter.adapt(item))),
      catchError(this.handleError)
    );
  }

  getMatchResults(): Observable<string[]> {
    return this.http.get(this.restPath + 'result').pipe(
      map((data: any) => data.map( item => item)),
      catchError(this.handleError)
    );
  }



}
