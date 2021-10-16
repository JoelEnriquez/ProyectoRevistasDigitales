import { Persona } from './Persona';
import { PersonaEnum } from './PersonaEnum';

export class Admin extends Persona {
  constructor(
    userName: string,
    password: string,
    nombre: string,
    tipo: PersonaEnum,
  ){
    super(userName, password, nombre, tipo);
  }
}