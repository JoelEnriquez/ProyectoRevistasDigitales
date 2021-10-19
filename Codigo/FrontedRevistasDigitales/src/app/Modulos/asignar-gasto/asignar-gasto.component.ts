import { Component, OnInit } from '@angular/core';
import { Revista } from '../../Objects/Revista';
import { CreateRevistaService } from '../../Services/create-revista.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-asignar-gasto',
  templateUrl: './asignar-gasto.component.html',
  styleUrls: ['./asignar-gasto.component.css']
})
export class AsignarGastoComponent implements OnInit {

  _asignarCostoForm!: FormGroup;
  _cantidadValida: string = '';
  _inputError:string = '';
  _mostrarExito: boolean = false;
  _mensaje!: string;
  _listRevistas!: Revista[];
  _revistaAsignar!: string;
  constructor(private createRevista : CreateRevistaService,
    private formBuilder: FormBuilder) { 
    this.obtenerListaRevistas();
  }

  ngOnInit(): void {
    this._asignarCostoForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      costo: ['', Validators.required],
    });
  }

  obtenerListaRevistas(){
    this.createRevista.revistasSinGastoDiario().subscribe(
      (listadoRevistas: Revista[]) => {
        this._listRevistas = listadoRevistas;
      }
    )
  }

  aginarCosto(nombre: string){
    this._revistaAsignar = nombre;
    this._asignarCostoForm.patchValue({
      nombre: nombre
    })
  }

  guardarCosto(){
    if (this._asignarCostoForm.valid) {
      const nombre:string = this._asignarCostoForm.value.nombre;
      const costo:number = this._asignarCostoForm.value.costo;
      if (costo<=0) {
        this._cantidadValida = 'is-invalid';
      } else {
        this.createRevista.asignarCostoDiario(nombre,costo).subscribe(
          () => {
            this.resetForm();
            this._mostrarExito = true;
            this._mensaje = 'Se ha asignado el costo diario correctamente';
            this.obtenerListaRevistas();
          }
        );
      }
    }
  }

  resetForm(){
    this._asignarCostoForm.reset({
      nombre: '',
      costo:'',
    });
  }

  cambiarStatus(nombreRevista: string){
    console.log(nombreRevista);
  }
}
