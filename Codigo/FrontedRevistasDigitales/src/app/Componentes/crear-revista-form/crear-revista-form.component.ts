import { Component, OnInit } from '@angular/core';
import { Editor } from '../../Objects/Editor';
import { Categoria } from '../../Objects/Categoria';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Etiqueta } from '../../Objects/Etiqueta';
import { LocalStorageService } from '../../Services/local-storage.service';
import { RegistrarService } from '../../Services/registrar.service';
import { RevistaService } from '../../Services/revista.service';
import { Revista } from '../../Objects/Revista';

@Component({
  selector: 'app-crear-revista-form',
  templateUrl: './crear-revista-form.component.html',
  styleUrls: ['./crear-revista-form.component.css']
})
export class CrearRevistaFormComponent implements OnInit {

  editor: Editor;
  _isInvalid: string = '';
  _isInvalidMessage: string = '';
  _listadoCategorias!: Categoria[];
  _registerRevistaForm!: FormGroup;
  _etiquetaForm!: FormGroup;
  _mensaje!: string;
  _mostrarError: boolean = false;
  _mostrarExito: boolean = false;
  _cantidadInvalida: string = '';
  _listadoEtiquetas: Etiqueta[];

  constructor(private formBuilder: FormBuilder,
    private localStorage: LocalStorageService,
    private registerService: RegistrarService,
    private crearRevista: RevistaService) {
    this.editor = JSON.parse(`${this.localStorage.obtenerData('editor')}`);
    this.obtenerListadoCategorias();
    this._listadoEtiquetas = new Array();
  }

  ngOnInit(): void {
    this._registerRevistaForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      cobro: ['false', Validators.required],
      costo: [''],
      categoria: [null, Validators.required],
    });

    this._etiquetaForm = this.formBuilder.group({
      etiqueta: ['', Validators.required]
    })
  }

  agregarEtiqueta() {
    if (this._etiquetaForm.valid) {
      const etiqueta: Etiqueta = new Etiqueta(this._etiquetaForm.value.etiqueta);
      if (this.indiceEtiqueta(etiqueta) == -1) {
        this._listadoEtiquetas.push(etiqueta);
        this.limpiarErroresEtiqueta();
        this._etiquetaForm.patchValue({
          etiqueta: ''
        })
      } else {
        //Ya existe dicha etiqueta en el arreglo
        this._isInvalid = 'is-invalid';
        this._isInvalidMessage = 'Ya existe dicha etiqueta';
      }
    }
  }

  eliminarEtiqueta(etiqueta: Etiqueta) {
    this._listadoEtiquetas.splice(this.indiceEtiqueta(etiqueta), 1);
    this.limpiarErroresEtiqueta();
  }

  indiceEtiqueta(etiqueta: Etiqueta): number {
    return this._listadoEtiquetas.findIndex(e => e.nombre == etiqueta.nombre);
  }

  limpiarErroresEtiqueta() {
    this._isInvalid = '';
    this._isInvalidMessage = '';
  }

  obtenerListadoCategorias() {
    this.registerService.listadoCategorias('list_categories').subscribe(
      (listadoCategorias: Categoria[]) => {
        this._listadoCategorias = listadoCategorias;
      }
    )
  }

  registrarRevista() {
    if (this._registerRevistaForm.valid) {
      if (this._listadoEtiquetas.length<=0) {
        this._isInvalid = 'is-invalid';
        this._isInvalidMessage = 'No se ha asignado ninguna etiqueta';
      } else {
        const nombre = this._registerRevistaForm.value.nombre;
        const descripcion = this._registerRevistaForm.value.descripcion;
        const pago = this._registerRevistaForm.value.cobro;
        const costo = this._registerRevistaForm.value.costo;
        const categoria = this._registerRevistaForm.value.categoria;
        const editor = this.editor.userName;
        if (costo != '' && costo <= 0) {
          this._cantidadInvalida = 'is-invalid';
        } else {
          const revista: Revista = new Revista(nombre, descripcion, true, true, true, pago, categoria, editor);
          if (costo != '') {
            revista.costoSuscripcion = costo
          }
          this.crearRevista.crearRevista(revista,this._listadoEtiquetas).subscribe(
            () => {
              this.resetForm();
              this.limpiarErroresEtiqueta();
              this._mostrarError = false;
              this._mostrarExito = true;
              this._cantidadInvalida = '';
              this._listadoEtiquetas = [];
              this._mensaje = 'Se ha solicitado la creacion de la Revista "' + revista.nombre + '" correctamente';
            },
            (error: any) => {
              this._mostrarError = true;
              this._mostrarExito = false;
              this._mensaje = error.error.message;
            }
          );
        }
      }
    }
  }

  resetForm() {
    this._registerRevistaForm.reset({
      nombre: '',
      descripcion: '',
      cobro: 'false',
      costo: '',
      categoria: null,
    });
  }

}
