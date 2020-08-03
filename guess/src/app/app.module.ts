import { DataService } from "./service/data.service";
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { GameComponent } from "./game/game.component";
import { CategoryService } from "./service/category.service";
import { WordService } from "./service/word.service";
import { IntroComponent } from "./intro/intro.component";

@NgModule({
  declarations: [AppComponent, GameComponent, IntroComponent],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [CategoryService, WordService, DataService],
  bootstrap: [AppComponent],
})
export class AppModule {}
