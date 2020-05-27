import { TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http/';
import {LoggerTestingModule} from 'ngx-logger/testing';
import { UserService } from './user.service';

describe('UserService', () => {
  let service: UserService;

  // var originalTimeout;

  // beforeEach(function() {
  //     originalTimeout = jasmine.DEFAULT_TIMEOUT_INTERVAL;
  //     jasmine.DEFAULT_TIMEOUT_INTERVAL = 10000;
  // });

  // afterEach(function() {
  //   jasmine.DEFAULT_TIMEOUT_INTERVAL = originalTimeout;
  // });

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, LoggerTestingModule],
      providers: [UserService]
  });
    service = TestBed.inject(UserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('#getUsers should return user array', (done: DoneFn) => {
    service.getUsers().subscribe(
      res => {
        expect(Array.isArray(res)).toBe(true);
        expect(res.length).toBe(2);
        done();
    });
});
});
