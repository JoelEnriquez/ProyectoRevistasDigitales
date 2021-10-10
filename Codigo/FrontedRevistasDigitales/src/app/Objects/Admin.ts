import { Persona } from './Persona';
import { PersonaEnum } from './PersonaEnum';

export class Admin extends Persona {
  constructor(
    userName: String,
    password: String,
    nombre: String,
    tipo: PersonaEnum,
  ){
    super(userName, password, nombre, tipo);
  }
}