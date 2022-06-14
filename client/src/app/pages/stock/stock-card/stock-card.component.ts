import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { IStockModel } from '../../../core/model/stock.model';

@Component({
  selector: 'app-stock-card',
  templateUrl: './stock-card.component.html',
  styleUrls: ['./stock-card.component.scss']
})
export class StockCardComponent implements OnInit {

  @Input() stock: IStockModel | undefined;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToStockView() {
    this.router.navigateByUrl('stock/view/' + this.stock?.id).then();
  }

}
