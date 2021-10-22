import { Usuario } from './Usuario';
import { Categoria } from '../Revista/Categoria';
import { Etiqueta } from '../Revista/Etiqueta';

export class EditUsuario {
  constructor(
    public usuario: Usuario,
    public listadoCategorias: Categoria[],
    public listadoEtiquetas ?: Etiqueta[]
  ){}
}