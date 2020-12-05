package LogicaCalculadoraSimple;

public class Multiplicacion implements PluginInterface{
	private int param1,param2;
	@Override
	public String getPluginName() {
		return "Multiplicacion";
	}

	@Override
	public void setParameters(int param1, int param2) {
		this.param1=param1;
		this.param2=param2;
	}

	@Override
	public int getResult() throws ArithmeticException {
		return (param1*param2);
	}

}
