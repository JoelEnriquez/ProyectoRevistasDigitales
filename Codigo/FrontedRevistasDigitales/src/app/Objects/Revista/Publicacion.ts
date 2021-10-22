export class Publicacion {
  constructor(
    public nombrePublicacion: string,
    public fechaPublicacion: string,
    public nombreRevista: string,
    public pathArchivo:string,
    public id?:number
  ){}

}