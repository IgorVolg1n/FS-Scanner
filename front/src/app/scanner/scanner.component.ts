import {Component, OnInit} from '@angular/core';
import {FilesService} from "../files/files.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-scanner',
  templateUrl: './scanner.component.html',
  styleUrls: ['./scanner.component.css']
})
export class ScannerComponent implements OnInit {

  path: string

  constructor(private filesService: FilesService,
              private snackBar: MatSnackBar) {
  }

  ngOnInit() {
  }

  scanPath(path: string) {
    this.filesService.setScanning(true);
    this.filesService.submitPathForScanning(path).subscribe(() => {
      this.snackBar.open("Scan complete", "ok", {duration: 5000, panelClass: ['blue-snackbar']});
      this.filesService.setScanning(false);
    }, error => {
      this.snackBar.open(error.message, "ok", {duration: 5000, panelClass: ['red-snackbar']});
      this.filesService.setScanning(false);
    });
    this.clearPath();
  }

  clearPath() {
    this.path = '';
  }
}
