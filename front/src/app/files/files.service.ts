import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class FilesService {

  isScanning: boolean
  requestParams: HttpParams

  constructor(private http: HttpClient) {
    this.isScanning=false;
  }

  setScanning(isScanning: boolean) {
    this.isScanning = isScanning;
  }

  submitPathForScanning(path: string) {
    return this.http.post<string>("http://localhost:8080/api/scan/path", path);
  }

  submitPathPartForSearching(pathPart: string) {
    this.requestParams = new HttpParams().append("pathPart" , pathPart);
    return this.http.get<string[]>("http://localhost:8080/api/search/path-part", {params: this.requestParams});
  }
}
