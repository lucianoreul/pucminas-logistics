import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from '../../app.constants';
import { IStockModel } from '../model/stock.model';

@Injectable({ providedIn: 'root' })
export class StockService {

  private resourceUrl = SERVER_API_URL + '/api/stock';

  constructor(private http: HttpClient) {}

  create(stock: IStockModel): Observable<IStockModel> {
    return this.http.post<IStockModel>(this.resourceUrl, stock);
  }

  getById(id: any): Observable<IStockModel>{
    return this.http.get<IStockModel>(`${this.resourceUrl}/${id}`);
  }

  getAll(): Observable<Array<IStockModel>> {
    return this.http.get<Array<IStockModel>>(`${this.resourceUrl}`);
  }

  update(stock: IStockModel, id: any): Observable<any> {
    return this.http.put(`${this.resourceUrl}/${id}`, stock)
  }
}
