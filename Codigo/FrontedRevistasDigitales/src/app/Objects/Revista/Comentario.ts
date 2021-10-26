export class Comentario {
  constructor(
    public id:number,
    public contenido:string,
    public fechaComentario: string,
    public nombreRevista: string,
    public userName:string
  ){}
}