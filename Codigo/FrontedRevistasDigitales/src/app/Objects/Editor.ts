import { Persona } from './Persona';
import { PersonaEnum } from './PersonaEnum';

export class Editor extends Persona{
  constructor(
    userName: String,
    password: String,
    nombre: String,
    tipo: PersonaEnum,
    public hobbies?: String,
    public descripcion?:String,
    public foto?: File | null
  ){
    super(userName, password, nombre, tipo);
  }
}