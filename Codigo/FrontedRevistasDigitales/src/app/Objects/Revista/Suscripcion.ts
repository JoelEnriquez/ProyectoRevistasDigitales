export class Suscripcion {
  constructor(
    public fechaSuscripcion :string,
    public suscripcionActiva :boolean,
    public nombreRevista :string,
    public userName:string,
    public tipoPago?:string,
    public cantidadTiempo?:number,
    public fechaCaducidad?:string,
  ){}
}