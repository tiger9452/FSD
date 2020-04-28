import { Component, OnInit, Input } from '@angular/core';
import { CompanyService } from '../../services/company.service';
import { Company, Dict } from '../../models/company';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {

  @Input() withAction: boolean = false;
  filteredCompanies: any[];
  company: string;
  companies: Dict[];
  cols: any[];

  constructor(private companyService: CompanyService) {}

  ngOnInit() {
    this.companyService.getCompanies().then((companies) => (this.companies = companies.data));

    this.cols = [
      { field: 'name', header: 'Name' },
      { field: 'code', header: 'Code' },
      { field: 'price', header: 'Price' },
      { field: 'ceo', header: 'CEO' },
      { field: 'sector', header: 'Sector' },
    ];
  }

  filteredCompany(event) {
    let query = event.query || event.name;
    this.companyService.getCompanies().then((companies) => {
      this.filteredCompanies = this.filterData(query, companies);
    });
  }

  filterData(query, companies: Company): Dict[] {
    let filtered: Dict[] = [];
    for (let i = 0; i < companies.data.length; i++) {
      let company = companies.data[i];
      if (company.name.toLowerCase().indexOf(query?.toLowerCase()) == 0) {
        filtered.push(company);
      }
    }
    if (filtered.length > 0) {
      this.companies = filtered;
    } else {
      this.companies = companies.data;
    }
    
    return filtered;
  }
}
