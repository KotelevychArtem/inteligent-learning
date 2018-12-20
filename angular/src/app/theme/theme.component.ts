import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styles: [
      './bootstrap.min.css'
  ]
})
export class ThemeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
