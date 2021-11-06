import { NgModule } from '@angular/core';
import {MatPaginatorModule} from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';


const material = [
  MatPaginatorModule,
  MatTableModule,
  MatSortModule
];

@NgModule({
  imports: [material],
  exports: [ material ]
})

export class MaterialModule {

}