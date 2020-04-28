import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'currency'
})
export class Currency implements PipeTransform {

  transform(value: number, floats?: number): string {
    return value.toFixed(isNaN(floats) ? 2 : floats);
  }

}