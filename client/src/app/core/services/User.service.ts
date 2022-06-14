import {Injectable} from "@angular/core";
import {SERVER_API_URL} from "../../app.constants";
import {HttpClient} from "@angular/common/http";

@Injectable({ providedIn: 'root' })
export class UserService {

  private resourceUrl = SERVER_API_URL + '/api/user';

  constructor(private http: HttpClient) {}

  getUser() {

  }
}
