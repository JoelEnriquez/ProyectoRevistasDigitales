import { Persona } from './Persona';
import { PersonaEnum } from './PersonaEnum';

export class Editor extends Persona{
  constructor(
    userName: string,
    password: string,
    nombre: string,
    tipo: PersonaEnum,
    public hobbies?: string,
    public descripcion?:string,
    public foto?: File | null
  ){
    super(userName, password, nombre, tipo);
  }
}