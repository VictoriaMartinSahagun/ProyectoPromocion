package LogicaCalculadoraSimple;

public interface PluginInterface {
	/**
	 * Consulta el nombre del plugin
	 * @return	String nombre
	 */
	public String getPluginName();
	/**
	 * Establece los parametros del plugin
	 * @param param1 int 
	 * @param param2 int
	 */
	public void setParameters (int param1, int param2);
	/**
	 * Consulta el resultado de la operacion realizada por el plugin
	 * @return int resultado
	 * @throws ArithmeticException en caso de que no pueda realizarse la operacion.
	 */
	public int getResult() throws ArithmeticException;
}
