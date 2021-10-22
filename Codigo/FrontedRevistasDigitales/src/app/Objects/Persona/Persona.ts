import { PersonaEnum } from './PersonaEnum';
export class Persona {
  constructor(
    public userName: string,
    public password: String,
    public nombre?: string,
    public tipo ?: PersonaEnum
  ) {}
}
