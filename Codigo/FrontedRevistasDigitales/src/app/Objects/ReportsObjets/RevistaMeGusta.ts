import { Revista } from '../Revista/Revista';
import { MeGusta } from '../Revista/MeGusta';
export class RevistaReaccion {
  constructor(
    public revista:Revista,
    public meGusta: MeGusta[]
  ){}
}