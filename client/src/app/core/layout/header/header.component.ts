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

  isStock() {
    return this.router.url === '/stock';
  }

  isStockForm() {
    return this.router.url === '/stock/new';
  }

  isStockView() {
    return this.router.url.includes('/stock/view/');
  }

  isSettings() {
    return this.router.url === '/settings';
  }

  goToStockForm() {
    this.router.navigateByUrl('stock/new');
  }

  hideMenuButton() {
    return this.isHome() || this.isStock() || this.isSettings();
  }

  back() {
    this.location.back();
  }
}
