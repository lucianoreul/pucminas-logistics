import { Component, OnInit } from '@angular/core';
import { StockService } from "../../core/services/stock.service";
import {IStockModel} from "../../core/model/stock.model";
import {Router} from "@angular/router";
import { ToastrService } from 'ngx-toastr';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalJustifyComponent } from "./modal-justify/modal-justify.component";

@Component({
  selector: 'app-stock-view',
  templateUrl: './stock-view.component.html',
  styleUrls: ['./stock-view.component.scss']
})
export class StockViewComponent implements OnInit {

  stock: any;

  constructor(
    private stockService: StockService,
    private router: Router,
    private toastrService: ToastrService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.getById();
  }

  getById() {
    const id = parseInt(this.router.url.split('/')[3]);
    this.stockService.getById(id).subscribe({
      next: this.handleSuccess.bind(this),
      error: this.handleError.bind(this)
    })
  }

  handleSuccess(data: IStockModel) {
    this.stock = data;
  }

  handleError() {
    this.toastrService.error('Problema com a solicitação deste pedido.');
    this.router.navigateByUrl('/stock').then();
  }

  iniciateStock() {
    if (this.stock?.status === 1) {
      this.stock.status = 2;
      this.stockService.update(this.stock, this.stock.id).subscribe({
        next: this.handleIniciateSuccess.bind(this)
      })
    } else {
      this.toastrService.error('Este pedido já teve o seu status iniciado.');
    }
  }

  notFoundStock() {
    if (this.stock?.status === 1) {
      this.toastrService.error('Este pedido não foi iniciado');
    } else if (this.stock?.status === 2) {
      const modalRef = this.modalService.open(ModalJustifyComponent, { centered: true });
      modalRef.result.then((text) => {
        this.stock.status = 4;
        this.stock.justify = text;
        this.stockService.update(this.stock, this.stock.id).subscribe({
          next: this.handleJustifySuccess.bind(this)
        })
      }).catch(() => {});
    } else {
      this.toastrService.error('Este pedido ja foi concluido');
    }
  }

  concludeStock() {
    if (this.stock?.status === 2) {
      this.stock.status = 3;
      this.stockService.update(this.stock, this.stock.id).subscribe({
        next: this.handleConclude.bind(this)
      })
    }
  }

  handleIniciateSuccess() {
    this.toastrService.success('Este pedido foi iniciado.');
    this.router.navigateByUrl('/stock').then();
  }

  handleConclude() {
    this.toastrService.success('Este pedido foi concluido com sucesso.');
    this.router.navigateByUrl('/stock').then();
  }

  handleJustifySuccess() {
    this.toastrService.success('Este pedido foi justificado com sucesso.');
    this.router.navigateByUrl('/stock').then();
  }

  getStatus(status: number): string {
    switch (status) {
      case 1:
        return 'Aguardando Inicio';
      case 2:
        return 'Em progresso';
      case 3:
        return 'Concluido';
      case 4:
        return 'Com problema';
      default:
        return '';
    }
  }
}
