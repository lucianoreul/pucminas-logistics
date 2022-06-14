import { Component, OnInit, Input } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-settings-card',
  templateUrl: './settings-card.component.html',
  styleUrls: ['./settings-card.component.scss']
})
export class SettingsCardComponent implements OnInit {

  @Input() iconUrl: string | undefined;

  @Input() title: string | undefined;

  @Input() url: any;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToPage() {
    if (this.url !== null || this.url !== '') {
      this.router.navigateByUrl(this.url).then();
    }
  }

}
