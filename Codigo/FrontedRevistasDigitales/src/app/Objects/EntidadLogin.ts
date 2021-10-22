import { Persona } from './Persona/Persona';

export class EntidadLogin {
  constructor(
    public persona: Persona,
    public error: Error
  ) {}
}
