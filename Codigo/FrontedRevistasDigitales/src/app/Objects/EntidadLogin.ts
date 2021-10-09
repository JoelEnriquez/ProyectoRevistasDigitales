import { Persona } from './Persona';
export class EntidadLogin {
  constructor(
    public persona: Persona,
    public error: Error
  ) {}
}
