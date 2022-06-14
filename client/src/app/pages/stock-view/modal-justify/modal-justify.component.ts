import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-justify',
  templateUrl: './modal-justify.component.html',
  styleUrls: ['./modal-justify.component.scss']
})
export class ModalJustifyComponent implements OnInit {

  justify: any;

  constructor(private activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  confirm() {
    this.activeModal.close(this.justify);
  }

  cancel() {
    this.activeModal.dismiss('cancel');
  }

  selectJustify(text: string) {
    this.justify = text;
  }

  checkJustify(text: string): boolean {
    return this.justify === text;
  }

}
