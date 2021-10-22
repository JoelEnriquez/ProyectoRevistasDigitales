import { Component, OnInit } from '@angular/core';
import { ValoresInicialesService } from '../../Services/valores-iniciales.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RedirigirService } from '../../Services/redirigir.service';
import { Rutas } from '../../Objects/Rutas';
import { ValoresGlobales } from '../../Objects/ValoresGlobales';

@Component({
  selector: 'app-valores-iniciales',
  templateUrl: './valores-iniciales.component.html',
  styleUrls: ['./valores-iniciales.component.css']
})
export class ValoresInicialesComponent implements OnInit {

  _valoresInicialesForm!: FormGroup;
  _mostrarError:boolean = false;
  _mensaje: string = "";

  constructor(private valoresService: ValoresInicialesService,
    private formBuilder: FormBuilder,
    private redirigirService: RedirigirService) {
      this.comprobarValoresIniciales();
    }

  ngOnInit(): void {
    this._valoresInicialesForm = this.formBuilder.group({
      ganancia: ['', Validators.required],
      anuncio_texto: ['', Validators.required],
      anuncio_imagen: ['', Validators.required],
      anuncio_video:['', Validators.required]
    })
  }

  comprobarValoresIniciales(){
    this.valoresService.verificarValoresGlobales().subscribe(
      (existenciaValores:number) => {
        if (existenciaValores==1) {
          this.redirigirService.redirigir(Rutas.LOGIN);
        } else {
          this._mostrarError = false;
        }
      },
      (error) => {
        this._mensaje = error.error.message;
        this._mostrarError = true
      }
    );
  }

  asignarValores(){
    if (this._valoresInicialesForm.valid) {
      const ganancia = this._valoresInicialesForm.value.ganancia;
      const anuncio_texto = this._valoresInicialesForm.value.anuncio_texto;
      const anuncio_imagen = this._valoresInicialesForm.value.anuncio_imagen;
      const anuncio_video = this._valoresInicialesForm.value.anuncio_video;
      if (anuncio_texto==0 || anuncio_video==0 || anuncio_imagen==0) {
        this._mensaje = "Ingresa un costo para los anuncios mayor a 0";
        this._mostrarError = true
      } else if (ganancia<1 || ganancia>99) {
        this._mensaje = "Ingresa una ganancia en el intervalo valido";
        this._mostrarError = true
      } else {
        const valoresGlobales = new ValoresGlobales(ganancia, anuncio_texto, anuncio_imagen, anuncio_video);
        this.valoresService.ingresarValores(valoresGlobales).subscribe(
          () => {
            this.redirigirService.redirigir(Rutas.LOGIN);
            this._mostrarError = false;
          },
          (error) => {
            this._mensaje = error.error.message;
            this._mostrarError = true;
          }
        )
      }
      
    }
  }

}
