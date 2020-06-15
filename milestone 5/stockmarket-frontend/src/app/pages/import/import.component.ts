import { Component, OnInit } from '@angular/core';
import { SelectItem, MessageService } from 'primeng/api';

@Component({
  selector: 'app-import',
  templateUrl: './import.component.html',
  styleUrls: ['./import.component.scss'],
})
export class ImportComponent implements OnInit {

  showInfo: boolean = false;
  stockExchanges: SelectItem[];
  selectedExchange: SelectItem;
  myfile: any[] = [];

  constructor(private messageService: MessageService) {
    this.stockExchanges = [
      { label: 'Bombay Stock Exchange', value: { id: 1, name: 'BSE', code: 'B' } },
      { label: 'National Stock Exchange', value: { id: 2, name: 'NSE', code: 'N' } },
    ];
  }

  onUpload(event) {
    for (let file of event.files) {
      this.myfile.push(file);
    }

    this.messageService.add({
      severity: 'info',
      summary: 'File Uploaded',
      detail: '',
    });
  }

  showUploadInfo():void {
    this.showInfo = !this.showInfo;
  }

  ngOnInit(): void {}
}
