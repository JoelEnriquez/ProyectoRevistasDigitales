import { PersonaEnum } from './PersonaEnum';
export class Persona {
  constructor(
    public userName: String,
    public password: String,
    public nombre?: String,
    public tipo ?: PersonaEnum
  ) {}
}
