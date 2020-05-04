import {Component, OnInit} from '@angular/core';
import {HttpClient , HttpEventType , HttpResponse} from '@angular/common/http';
import {InjectionNames, EntryFactory, BpmnModeler, PropertiesPanelModule, CamundaModdleDescriptor, ElementTemplates, CamundaPropertiesProvider, OriginalPaletteProvider } from "../bpmn-js/bpmn-js";
import { DomSanitizer } from '@angular/platform-browser';
import { Observable } from 'rxjs';
import { UploadFileService } from 'src/app/services/upload-file.service';

@Component({
  selector: 'app-modelisation',
  templateUrl: './modelisation.component.html',
  styleUrls: ['./modelisation.component.scss']
})
export class ModelisationComponent implements OnInit {

title = 'Angular/BPMN';
modeler;
blob;
fileUrl;
selectedFiles: FileList;
currentFile: File;
progress = 0;
message = '';

fileInfos: Observable<any>;

  constructor(private http: HttpClient, private sanitizer: DomSanitizer , private uploadService: UploadFileService) {
  }
  ngOnInit(): void {
    this.modeler = new BpmnModeler({
      container: '#canvas',
      width: '100vw',
      height: '100vh',
      additionalModules: [
        {[InjectionNames.elementTemplates]: ['type', ElementTemplates.elementTemplates[1]]},
        {[InjectionNames.propertiesProvider]: ['type', CamundaPropertiesProvider.propertiesProvider[1]]},
        {[InjectionNames.originalPaletteProvider]: ['type', OriginalPaletteProvider]},
        PropertiesPanelModule
      ],
      propertiesPanel: {
        parent: '#properties'
      },
      moddleExtensions: {
        camunda: CamundaModdleDescriptor
      }
    });
  }

  handleError(err: any) {
    if (err) {
      console.warn('Ups, error: ', err);
    }
  }

  load(): void {
    const url = '../assets/bpmn/initial.bpmn';
    this.http.get(url, {
      headers: {observe: 'response'}, responseType: 'text'
    }).subscribe(
      (x: any) => {
        console.log('Fetched XML, now importing: ', x);
        this.modeler.importXML(x, this.handleError);
      },
      this.handleError
    );
  }
  save(): void {
    this.modeler.saveXML((err: any, xml: any) =>
    this.blob  = new Blob([xml], { type: 'application/octet-stream' }));
   this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(this.blob));

  }
  selectFile(event) {
    this.selectedFiles = event.target.files;
  }


  upload() {
    this.progress = 0;
    this.modeler.saveXML((err: any, xml: any) =>
    this.blob  = new Blob([xml], { type: 'application/octet-stream' }));
   this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(this.blob));
   console.log(this.fileUrl)
       this.currentFile = this.blob;
    this.uploadService.uploadFile(this.currentFile).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          this.fileInfos = this.uploadService.getFiles();
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });

    this.selectedFiles = undefined;
  }

}


