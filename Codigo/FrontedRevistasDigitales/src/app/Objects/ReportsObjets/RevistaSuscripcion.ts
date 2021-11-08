import { Revista } from "../Revista/Revista";
import { Suscripcion } from "../Revista/Suscripcion";

export class RevistaSuscripcion {
    constructor(
        public revista:Revista,
        public listadoSuscripciones:Suscripcion[]
    ){}
}