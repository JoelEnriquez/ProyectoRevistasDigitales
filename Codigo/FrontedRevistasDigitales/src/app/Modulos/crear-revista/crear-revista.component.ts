import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Editor } from '../../Objects/Editor';
import { LocalStorageService } from '../../Services/local-storage.service';
import { Categoria } from '../../Objects/Categoria';
import { RegistrarService } from '../../Services/registrar.service';
import { Revista } from '../../Objects/Revista';
import { CreateRevistaService } from '../../Services/create-revista.service';

@Component({
  selector: 'app-crear-revista',
  templateUrl: './crear-revista.component.html',
  styleUrls: ['./crear-revista.component.css']
})
export class CrearRevistaComponent implements OnInit {

  editor: Editor;
  _listadoCategorias!: Categoria[];
  _registerRevistaForm!: FormGroup;
  _mensaje!: string;
  _mostrarError:boolean = false;
  _mostrarExito: boolean = false;

  constructor(private formBuilder: FormBuilder,
    private localStorage: LocalStorageService,
    private registerService: RegistrarService,
    private crearRevista: CreateRevistaService) {
    this.editor = JSON.parse(`${this.localStorage.obtenerData('editor')}`);
    this.obtenerListadoCategorias();
   }

  ngOnInit(): void {
    this._registerRevistaForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      cobro: ['false', Validators.required],
      costo: [''],
      categoria: [null,Validators.required],
    });
  }

  obtenerListadoCategorias(){
    this.registerService.listadoCategorias('list_categories').subscribe(
      (listadoCategorias: Categoria[]) => {
        this._listadoCategorias = listadoCategorias;
      }
    )
  }

  registrarRevista(){
    if(this._registerRevistaForm.valid){
      const nombre = this._registerRevistaForm.value.nombre;
      const descripcion = this._registerRevistaForm.value.descripcion;
      const pago = this._registerRevistaForm.value.cobro;
      const costo = this._registerRevistaForm.value.costo;
      const categoria  = this._registerRevistaForm.value.categoria;
      const editor = this.editor.userName;

      const revista: Revista = new Revista(nombre, descripcion,true, true, true, pago,categoria,editor);
      if (costo!='') {revista.costoSuscripcion = costo}
      this.crearRevista.crearRevista(revista).subscribe(
        () => {
          this.resetForm();
          this._mostrarError = false;
          this._mostrarExito = true;
          this._mensaje = 'Se ha solicitado la creacion de la Revista "'+revista.nombre+'" correctamente';
        },
        (error: any) => {
          this._mostrarError = true;
          this._mostrarExito = false;
          this._mensaje = error.error.message;
        }
      );
    }
  }

  resetForm(){
    this._registerRevistaForm.reset({
      nombre: '',
      descripcion: '',
      cobro: 'false',
      costo: '',
      categoria: null,
    });
  }

  
}
