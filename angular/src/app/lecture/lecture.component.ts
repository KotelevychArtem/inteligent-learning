import { AfterViewInit, Component, ElementRef, ViewChild, OnInit } from '@angular/core';
import { HttpClientModule }    from '@angular/common/http';

import { Lecture } from './lecture';
import { LectureService } from './lecture.service';



@Component({
  selector: 'app-lecture',
  templateUrl: './lecture.component.html',
  styleUrls: ['./lecture.component.css']
})
export class LectureComponent implements OnInit, AfterViewInit {

  @ViewChild('content') myDiv: ElementRef;

  imagesUrl = "http://192.168.0.106:8080/api/image"
  
  lecture: Lecture;
  constructor(private lectureService: LectureService) { }

  ngOnInit() {
    this.getLecture(6);
  }

   ngAfterViewInit() {
        console.log(this.myDiv.nativeElement.innerHTML);
    }

  getLecture(themeId): void {
    this.lectureService.getLecture(themeId)
    .subscribe(lecture => {
      console.log(lecture);
      this.lecture = lecture
      console.log(this.myDiv);
      //this.myDiv.nativeElement.innerHTML = lecture.steps[0].content.split("{{imagesUrl}}").join(this.imagesUrl);
    });
  }

}
