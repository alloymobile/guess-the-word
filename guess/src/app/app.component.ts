import { Component, OnInit } from "@angular/core";
import { filter } from "rxjs/operators";
import { map } from "rxjs/operators";
import "jquery";
declare var $: any;

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})
export class AppComponent {
  title = "guess";
}
