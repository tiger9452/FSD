import { Component, ViewChild, ElementRef } from '@angular/core';
import { SelectItem, MessageService } from 'primeng/api';
import {NGXLogger} from 'ngx-logger';

interface dict {
  name: string;
  code: string;
}

const categories = [
  {
    category: [
      { label: '19/Jan' },
      { label: '19/Feb' },
      { label: '19/Mar' },
      { label: '19/Apr' },
      { label: '19/May' },
      { label: '19/Jun' },
      { label: '19/Jul' },
      { label: '19/Aug' },
      { label: '19/Sep' },
      { label: '19/Oct' },
      { label: '19/Nov' },
      { label: '19/Dec' },
    ],
  },
];
// STEP 3- Construct the dataset comprising multiple series
const dataset = [
  {
    seriesname: 'State Bank of India',
    data: [
      { value: '16000' },
      { value: '20000' },
      { value: '18000' },
      { value: '19000' },
      { value: '15000' },
      { value: '21000' },
      { value: '16000' },
      { value: '20000' },
      { value: '17000' },
      { value: '25000' },
      { value: '19000' },
      { value: '23000' },
    ],
  },
  {
    seriesname: 'IBM',
    data: [
      { value: '5000' },
      { value: '6000' },
      { value: '7000' },
      { value: '8000' },
      { value: '9000' },
      { value: '6000' },
      { value: '5000' },
      { value: '8000' },
      { value: '4000' },
      { value: '1000' },
      { value: '2000' },
      { value: '3000' },
    ],
  },
];

const data = {
  chart: {
    theme: 'fusion',
    caption: 'Compare Company Stock Price',
    subcaption: 'BSE: 2019-2020',
    xAxisname: 'Time in Month',
    yAxisName: 'Stock Price (In USD)',
    formatnumberscale: '1',
    plottooltext: '<b>$dataValue</b> USD for <b>$seriesName</b> in $label',
    numberPrefix: '$',
    plotFillAlpha: '80',
    divLineIsDashed: '1',
    divLineDashLen: '1',
    divLineGapLen: '1',
  },
  categories: categories,
  dataset: dataset,
};

@Component({
  selector: 'app-root',
  templateUrl: './compare.component.html',
  styleUrls: ['./compare.component.scss']
})
export class CompareComponent {
  width = 920;
  height = 400;
  type = 'mscolumn3d';
  dataFormat = 'json';
  dataSource = data;

  items: string[] = ['BSE', 'NSE'];
  stockExchanges: SelectItem[];
  companies: SelectItem[];
  selectedExchange: SelectItem;
  selectedCompanies: string[];
  selectedPeriod: string = 'M';
  fromDate: Date;
  toDate: Date;

  @ViewChild('chooseTwoCompanies', {read: ElementRef}) chooseTwoCompanies: ElementRef;
  
  constructor(private msgSrv: MessageService,
              private logger: NGXLogger) {
    this.stockExchanges = [
      { label: 'Bombay Stock Exchange', value: { id: 1, name: 'BSE', code: 'B' } },
      { label: 'National Stock Exchange', value: { id: 2, name: 'NSE', code: 'N' } },
    ];

    this.companies = [
      { label: 'IBM', value: 'I' },
      { label: 'State Bank of India', value: 'S' },
      { label: 'Google', value: 'G' },
      { label: 'Microsoft', value: 'M' },
      { label: 'Apple', value: 'A' },
      { label: 'Facebook', value: 'F' },
    ];

    this.selectedExchange = this.stockExchanges[1].value;

    // let arrayOfValues = ['I','G'];
    // this.selectedCompanies = this.companies.filter(a => arrayOfValues.includes(a.value)).map(a => a.value);
    // this.selectedCompanies = this.companies.slice(0,2).map(a => a.value);
  }

  generateChart(): void {
    if (this.chooseTwoCompanies.nativeElement.innerText === 'Choose' 
        || this.chooseTwoCompanies.nativeElement.innerText.indexOf(",") === -1) {
      this.msgSrv.add({severity:'success', summary:'Error Message', detail:'Only support to choose two companies!'});
    }
    // TODO: i can use these data to generate the chart in later..
    this.logger.log(this.selectedExchange);
    this.logger.log(this.selectedCompanies);
    this.logger.log(this.selectedPeriod);
    this.logger.log(this.fromDate);
    this.logger.log(this.toDate);
  }

}
