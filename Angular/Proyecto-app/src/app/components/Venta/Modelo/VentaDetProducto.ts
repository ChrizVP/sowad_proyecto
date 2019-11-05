
export class VentaDetProducto{
ventaDetProducto_id:number;
ventaCabProducto: {
    ventaCab_id:number;
	estado:String;
	total:Float32Array;
	cliente_id:number;
	user_id:number;
    fecha:Date;
};
producto: {
    producto_id:number;
	cantidad:number;
	color:String;
    nombre:String;
    precio:Float32Array;
};
cantidad:number;
subTotal:Float32Array;
}