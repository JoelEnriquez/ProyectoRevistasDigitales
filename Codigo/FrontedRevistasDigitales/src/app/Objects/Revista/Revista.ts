export class Revista {
  constructor(
    public nombre: string,
    public descripcion: string,
    public suscribir: boolean,
    public comentar: boolean,
    public reaccionar: boolean,
    public pago: boolean,
    public nombreCategoria: string,
    public userName: string,
    public costoSuscripcion?:number,
    public costoDia?: number
  ){}
}