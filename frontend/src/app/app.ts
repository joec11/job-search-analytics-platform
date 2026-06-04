import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ApiService } from './services/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.html'
})
export class App implements OnInit {

  message = '';

  constructor(
    private api: ApiService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.api.health().subscribe(response => {
      this.message = `${response.status}\n${response.timestamp}`;
      this.cdr.detectChanges();
    });
  }
}
