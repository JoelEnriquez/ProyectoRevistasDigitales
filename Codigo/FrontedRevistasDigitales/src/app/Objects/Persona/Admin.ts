
import { PersonaEnum } from './PersonaEnum';
import { Persona } from './Persona';

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