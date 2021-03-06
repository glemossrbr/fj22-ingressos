import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { FotoModule } from './foto.module';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    FotoModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [ AppComponent ]
})

export class AppModule { }
