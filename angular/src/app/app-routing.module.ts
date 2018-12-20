import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ThemeComponent }      from './theme/theme.component';
import { ModeComponent } from './mode/mode.component';
import { LectureComponent } from './lecture/lecture.component';


const routes: Routes = [
  { path: 'theme', component: ThemeComponent },
  { path: 'lecture', component: LectureComponent },
  { path: 'mode', component: ModeComponent },
  { path: '', component: ThemeComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
