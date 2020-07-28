import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameComponent } from './game/game.component';
import { GameService } from './service/game.service';
import { CategoryService } from './service/category.service';
import { MemeberService } from './service/memeber.service';
import { RoundService } from './service/round.service';
import { TeamService } from './service/team.service';
import { WordService } from './service/word.service';

@NgModule({
  declarations: [
    AppComponent,
    GameComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [GameService,
    CategoryService,
    MemeberService,
    RoundService,
    TeamService,
    WordService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
