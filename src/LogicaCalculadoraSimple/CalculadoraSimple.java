package LogicaCalculadoraSimple;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CalculadoraSimple {
	File dir;
	ArrayList<PluginInterface> plugins;
	ArrayList<String> nombrePlugins;
	
	public CalculadoraSimple() {
		dir= new File("./bin/plugins");
		plugins = new ArrayList<PluginInterface>();
		nombrePlugins = new ArrayList<String>();
	}
	
	//Este fragmento de codigo contiene partes extraidas de https://javaranch.com/journal/200607/Plugins.html
	public void getPlugins() {
		ClassLoader cl = new PluginClassLoader(dir);
		String[] files = dir.list();
		nombrePlugins.clear();
		plugins.clear();
		if(files!=null) {
			for (int i=0; i<files.length; i++) {
				try {
					if (! files[i].endsWith(".class"))
						continue;
					Class c = cl.loadClass(files[i].substring(0, files[i].indexOf(".")));
					Class[] intf = c.getInterfaces();
					for (int j=0; j<intf.length; j++) {
						if (intf[j].getName().contentEquals("LogicaCalculadoraSimple.PluginInterface")) {
							PluginInterface pf = (PluginInterface) c.getDeclaredConstructor().newInstance();
							plugins.add(pf);
							nombrePlugins.add(pf.getPluginName());
							continue;
						}
					}
				} catch(Exception ex1) {
					JOptionPane.showMessageDialog(null, ex1.getClass().getSimpleName());
				}
			}
		}
	}
	
	public ArrayList<String> getPluginsName(){
		return nombrePlugins;
	}
	
	public int runPlugin(int p1, int p2, String nombre) {
		int cantPlugs= plugins.size();
		int res=0;
		boolean encontre=false;
		for(int i=0; i<cantPlugs && !encontre;i++) {
			PluginInterface pf = plugins.get(i);
			if(pf.getPluginName().equals(nombre)) {
				encontre=true;
				pf.setParameters(p1, p2);
				res= pf.getResult();
			}
		}
		return res;
	}
}
