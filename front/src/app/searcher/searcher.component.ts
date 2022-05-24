import {Component, OnInit} from '@angular/core';
import {FilesService} from "../files/files.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-searcher',
  templateUrl: './searcher.component.html',
  styleUrls: ['./searcher.component.css']
})
export class SearcherComponent implements OnInit {

  pathPart: string
  foundPaths: string[]

  constructor(private filesService: FilesService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.pathPart = ''
  }

  searchPathPart(pathPart: string) {
    this.foundPaths = []
    if (pathPart.length == 0) {
      this.snackBar.open("No path given", "ok", {duration: 5000, panelClass: ['red-snackbar']});
      this.clearPathPart();
      return
    }
    this.filesService.submitPathPartForSearching(pathPart).subscribe(paths => {
      if (paths.length == 0) {
        this.snackBar.open("Nothing found", "ok", {duration: 5000, panelClass: ['red-snackbar']});
        this.clearPathPart();
        return
      }
      this.foundPaths = paths;
      this.snackBar.open("Paths found", "ok", {duration: 5000, panelClass: ['blue-snackbar']});
    }, error => {
      this.snackBar.open(error.message, "ok", {duration: 5000, panelClass: ['red-snackbar']});
    });
    this.clearPathPart();
  }

  clearPathPart() {
    this.pathPart = '';
  }
}
