import { Revista } from "../Revista/Revista";
import { Suscripcion } from "../Revista/Suscripcion";

export class GananciaEditor {
    constructor(
        public revista: Revista,
        public suscripciones: Suscripcion[]
    ){}
}