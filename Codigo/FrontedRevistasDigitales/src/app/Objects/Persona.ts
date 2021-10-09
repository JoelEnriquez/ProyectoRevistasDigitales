import { PersonaEnum } from './PersonaEnum';
export class Persona {
  constructor(
    public codigo: string,
    public password: string,
    public nombre?: string,
    public tipo ?: PersonaEnum
  ) {}
}
