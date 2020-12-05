package LogicaCalculadoraSimple;

public class RaizCuadrada implements PluginInterface{
	private int param1,param2;
	@Override
	public String getPluginName() {
		return "Raiz cuadrada";
	}

	@Override
	public void setParameters(int param1, int param2) {
		this.param1=param1;
		this.param2=0;
	}

	@Override
	public int getResult() {
		if(param1>=0) {
			return (int) Math.sqrt(param1);
		}else {
			throw new ArithmeticException();
		}
	}

}
