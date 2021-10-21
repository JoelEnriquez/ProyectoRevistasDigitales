import { Component, OnInit } from '@angular/core';
import { Revista } from '../../Objects/Revista';
import { RevistaService } from '../../Services/revista.service';
import { Editor } from '../../Objects/Editor';
import { LocalStorageService } from '../../Services/local-storage.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Categoria } from '../../Objects/Categoria';
import { Etiqueta } from '../../Objects/Etiqueta';
import { RegistrarService } from '../../Services/registrar.service';

@Component({
  selector: 'app-revistas-propias',
  templateUrl: './revistas-propias.component.html',
  styleUrls: ['./revistas-propias.component.css']
})
export class RevistasPropiasComponent implements OnInit {

  _isInvalid: string = '';
  _isInvalidMessage: string = '';
  _listadoCategorias!: Categoria[];
  _editarRevistaForm!: FormGroup;
  _etiquetaForm!: FormGroup;
  _mensajeEdit!: string;
  _mostrarError: boolean = false;
  _mostrarExito: boolean = false;
  _cantidadInvalida: string = '';
  _listadoEtiquetas!: Etiqueta[];
  _cobroCheck!:boolean;

  _revistaToUploadPubli!:Revista;

  _editor: Editor;
  _listRevistas!: Revista[];
  _revistaToEdit!: Revista;

  constructor(
    private revistaService: RevistaService,
    private localService: LocalStorageService,
    private formBuilder: FormBuilder,
    private registerService: RegistrarService
  ) {
    this._editor = JSON.parse(`${this.localService.obtenerData('editor')}`);
    this.obtenerListaRevistas();
    this.obtenerListadoCategorias();
  }

  ngOnInit(): void {
    this._editarRevistaForm = this.formBuilder.group({
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

  obtenerListadoCategorias() {
    this.registerService.listadoCategorias('list_categories').subscribe(
      (listadoCategorias: Categoria[]) => {
        this._listadoCategorias = listadoCategorias;
      }
    )
  }

  obtenerListadoEtiquetas() {
    this.revistaService.etiquetasRevista(this._revistaToEdit.nombre,'etiquetas_revista').subscribe(
      (listadoEtiquetas: Etiqueta[]) => {
        this._listadoEtiquetas = listadoEtiquetas;
      }
    )
  }

  obtenerListaRevistas() {
    this.revistaService.revistasPropias(this._editor.userName, 'revistas_propias').subscribe(
      (listadoRevistas: Revista[]) => {
        this._listRevistas = listadoRevistas;
      }
    )
  }

  cambiarStatus(nombreRevista: string, campoModificar: string) {
    let revistaModificar = this.revistaPorNombre(nombreRevista);
    if (revistaModificar != undefined) {
      const valorActual = this.valorActualPorCampo(revistaModificar, campoModificar);
      const nuevoValor = (valorActual == true) ? false : true;
      this.revistaService.cambiarCampoRevista(nombreRevista, nuevoValor, campoModificar).subscribe(
        () => {
          //Actualizar el valor en el arreglo
          if (revistaModificar != null) {
            const indiceRevistaModificar = this.indiceRevistaModificar(revistaModificar)
            switch (campoModificar) {
              case 'suscribir':
                revistaModificar.suscribir = nuevoValor;
                break;
              case 'comentar':
                revistaModificar.comentar = nuevoValor;
                break;
              case 'reaccionar':
                revistaModificar.reaccionar = nuevoValor;
                break;
              default:
                break;
            }
            //Volver a insertar la revista al arreglo
            this._listRevistas[indiceRevistaModificar] = revistaModificar;
          }
        }
      );
    }
  }

  valorActualPorCampo(revista: Revista, campoModificar: string): boolean {
    let valorActual: boolean;
    switch (campoModificar) {
      case 'suscribir':
        valorActual = revista.suscribir;
        break;
      case 'comentar':
        valorActual = revista.comentar;
        break;
      default:
        valorActual = revista.reaccionar;
        break;
    }
    return valorActual;
  }

  revistaPorNombre(nombreRevista: string): Revista | undefined {
    return this._listRevistas.find(r => r.nombre == nombreRevista);
  }

  indiceRevistaModificar(revista: Revista): number {
    return this._listRevistas.indexOf(revista);
  }

  setRevistaToEdit(revista: Revista) {

    this._revistaToEdit = revista;

    this._editarRevistaForm.patchValue({
      nombre: revista.nombre,
      descripcion: revista.descripcion,
      cobro: revista.pago.toLocaleString(),
      costo: revista.costoSuscripcion,
      categoria: revista.nombreCategoria,
    });
    //Delete message edit 
    this._isInvalid = '';
    this._isInvalidMessage = '';
    this._cantidadInvalida = '';
    this._mostrarError = false;
    this._mostrarError = false;
    this._mensajeEdit = '';
    this.obtenerListadoEtiquetas();
  }

  setRevistaToUploadPubli(revista: Revista){
    this._revistaToUploadPubli = revista;
  }

  actualizarRevista() {
    if (this._editarRevistaForm.valid) {
      if (this._listadoEtiquetas.length <= 0) {
        this._isInvalid = 'is-invalid';
        this._isInvalidMessage = 'No se ha asignado ninguna etiqueta';
      } else {
        const nombre = this._revistaToEdit.nombre
        const descripcion = this._editarRevistaForm.value.descripcion;
        const pago = this._editarRevistaForm.value.cobro;
        const costo = this._editarRevistaForm.value.costo;
        const categoria = this._editarRevistaForm.value.categoria;
        const editor = this._editor.userName;
        if (costo != '' && costo <= 0) {
          this._cantidadInvalida = 'is-invalid';
        } else {
          const revista: Revista = new Revista(nombre, descripcion, this._revistaToEdit.suscribir, this._revistaToEdit.comentar, this._revistaToEdit.reaccionar, pago, categoria, editor);
          if (costo != '') {
            revista.costoSuscripcion = costo
          }
          this.revistaService.actualizarRevista(revista, this._listadoEtiquetas).subscribe(
            () => {
              this.limpiarErroresEtiqueta();
              //Setear revista modificada al array[]
              this._listRevistas[this.indiceRevistaModificar(this._revistaToEdit)] = revista;
              this._mostrarError = false;
              this._mostrarExito = true;
              this._cantidadInvalida = '';
              this._mensajeEdit = 'Se han actualizado los cambios correctamente';
            },
            (error: any) => {
              this._mostrarError = true;
              this._mostrarExito = false;
              this._mensajeEdit = error.error.message;
            }
          );
        }
      }
    }
  }

  resetForm() {
    this._editarRevistaForm.reset({
      nombre: '',
      descripcion: '',
      cobro: 'false',
      costo: '',
      categoria: null,
    });
  }

  limpiarErroresEtiqueta() {
    this._isInvalid = '';
    this._isInvalidMessage = '';
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

}
