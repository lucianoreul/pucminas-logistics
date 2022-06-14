import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { StockService } from '../../core/services/stock.service';

@Component({
  selector: 'app-stock-form',
  templateUrl: './stock-form.component.html',
  styleUrls: ['./stock-form.component.scss']
})
export class StockFormComponent implements OnInit {

  form: FormGroup;
  isSaving = false;

  constructor(
    private fb: FormBuilder,
    private stockService: StockService,
    private router: Router,
    private toastService: ToastrService
  ) {
    this.form = this.fb.group({
      drawing: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(10)]],
      location: ['', Validators.required],
      quantity: ['', Validators.required],
      description: [''],
      mdr: [''],
      partial: [false],
      status: [1]
    });
  }

  ngOnInit(): void {
  }

  save() {
    const obj = this.form.value;
    this.stockService.create(obj).subscribe({
      next: this.handleSuccess.bind(this),
      error: this.handleError.bind(this)
    })
  }

  handleSuccess() {
    this.toastService.success('Pedido criado com sucesso.');
    this.router.navigateByUrl('/stock').then();
  }

  handleError(error: any) {
    console.log(error);
    this.toastService.error('Falha ao criar um pedido.');
  }

  removeSpace(name: any) {
    if (this.form.get(name)?.value !== null) {
      const value = this.form.get(name)?.value.trim().replace(/\s+/g, ' ');
      this.form.get(name)?.setValue(value);
    }
  }
}
