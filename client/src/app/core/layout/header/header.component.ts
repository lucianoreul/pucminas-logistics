import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common'

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Output() public sidenavToggle = new EventEmitter();

  constructor(private router: Router, private location: Location) { }

  ngOnInit(): void {
  }

  onToggleSidenav() {
    this.sidenavToggle.emit();
  }

  isHome() {
    return this.router.url === '/home';
  }

  isStockOperation() {
    return this.router.url === '/stock-operation';
  }

  isStockConcluded() {
    return this.router.url === '/stock-concluded';
  }

  isStockForm() {
    return this.router.url === '/stock/new';
  }

  isStockView() {
    return this.router.url.includes('/stock/view/');
  }

  isUser() {
    return this.router.url.includes('/user');
  }

  isSettings() {
    return this.router.url === '/settings';
  }

  refreshPage() {
    window.location.reload();
  }

  hideMenuButton() {
    return this.isHome() || this.isStockOperation() || this.isSettings() || this.isStockConcluded();
  }

  back() {
    this.location.back();
  }
}
