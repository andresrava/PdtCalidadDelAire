package com.enumerados;

public class Enumerados {

	public enum Estado {HABILITADO , BORRADO}
	public enum Obligatoria { SI , NO }
	public enum Booleano { TRUE , FALSE }
	public enum Ingreso {NORMAL , MASIVO }
	
	public enum TipoDatoEnum 
	{
		STRING("String",0), BOOLEAN("Boolean",1), INTEGER("Integer",2), FLOAT("Float",3);
		private String nombreTipoDato;
		private int numero;
		private TipoDatoEnum (String nombre, int numero) {
			this.numero = numero;
			this.nombreTipoDato = nombre;
		}
		public String getTipoDato() 
		{return nombreTipoDato;
		}
		public int getNumero()
		{return numero;
		}
}
}
