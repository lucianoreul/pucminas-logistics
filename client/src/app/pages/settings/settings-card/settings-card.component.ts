import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-settings-card',
  templateUrl: './settings-card.component.html',
  styleUrls: ['./settings-card.component.scss']
})
export class SettingsCardComponent implements OnInit {

  @Input() iconUrl: string | undefined;

  @Input() title: string | undefined;

  @Input() url: string | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
