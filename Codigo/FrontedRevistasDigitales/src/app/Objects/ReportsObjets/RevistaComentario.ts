import { Comentario } from "../Revista/Comentario";
import { Revista } from "../Revista/Revista";

export class RevistaComentario {
    constructor(
        public revista:Revista,
        public comentarios: Comentario[]
    ){}
}