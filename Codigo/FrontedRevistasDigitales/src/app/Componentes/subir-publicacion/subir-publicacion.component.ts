import { Component, Input, OnInit } from '@angular/core';
import { Revista } from '../../Objects/Revista/Revista';
import { PublicacionService } from '../../Services/publicacion.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DateValidator } from '../../Objects/Herramientas/DateValidator';
import { Publicacion } from '../../Objects/Revista/Publicacion';

@Component({
  selector: 'app-subir-publicacion',
  templateUrl: './subir-publicacion.component.html',
  styleUrls: ['./subir-publicacion.component.css']
})
export class SubirPublicacionComponent implements OnInit {

  @Input() _revistaToUploadFile!: Revista;

  _mensaje: string = '';
  _mostrarError: boolean = false;
  _mostrarExito: boolean = false;
  _subirPublicacionForm!: FormGroup;
  _archivoElegido!: File | null;
  _archivoInvalido: string = '';

  constructor(private publicacionService: PublicacionService,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this._subirPublicacionForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      fecha: ['', Validators.required],
      file: [null, Validators.required]
    })
  }

  subirPublicacion() {
    if (this._archivoElegido != null) {
      const nombre = this._subirPublicacionForm.value.nombre;
      const fecha = this._subirPublicacionForm.value.fecha.toLocaleString();
      const publicacion = new Publicacion(nombre, fecha, this._revistaToUploadFile.nombre,'');
      this.publicacionService.insertarPublicacion(publicacion, this._archivoElegido).subscribe(
        () => {
          this._mostrarExito = true;
          this._mostrarError = false;
          this._mensaje = 'Se ha subida la publicacion correctamente';

        },
        (error) => {
          console.log(error);
          this._mensaje = error.error.message;
          this._mostrarError = true;
          this._mostrarExito = false;
        }
      );
    }
  }

  cargarArchivo(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this._archivoElegido = files.item(0); //Guardamos en el atributo de la clase
      if (this._archivoElegido?.type != 'application/pdf') {
        this._archivoInvalido = 'is-invalid';
        this._archivoElegido = null;
        this._subirPublicacionForm.patchValue({
          file: null
        })
      } else {
        this._archivoInvalido = '';
      }
    }
  }

  resetInputs() {
    this._subirPublicacionForm.reset({
      nombre: '',
      fecha: '',
      file: null
    });
    this._archivoElegido = null;
  }

}
