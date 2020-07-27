import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameComponent } from './game/game.component';
import { ScoreComponent } from './score/score.component';
import { GameService } from './game/game.service';
import { ScoreService } from './score/score.service';

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    ScoreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [GameService,
    ScoreService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
