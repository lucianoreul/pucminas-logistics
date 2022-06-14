import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { StockService } from '../../core/services/stock.service';
import { IStockModel } from '../../core/model/stock.model';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.scss']
})
export class StockComponent implements OnInit {

  stockList: Array<IStockModel> = [];

  constructor(
    private stockService: StockService,
    private toastService: ToastrService
  ) { }

  ngOnInit(): void {
    this.loadAll();
  }

  loadAll() {
    this.stockService.getAll().subscribe({
      next: this.handleSuccess.bind(this),
      error: this.handleError.bind(this)
    })
  }

  handleSuccess(data: any) {
    this.stockList = data;
  }

  handleError(error: any) {
    console.log(error);
    this.toastService.error('Problema ao listar os pedidos.')
  }

}
