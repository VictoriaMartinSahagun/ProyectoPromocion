package GuiCalculadoraSimple;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import LogicaCalculadoraSimple.CalculadoraSimple;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Label;
import javax.swing.JTextField;

public class Gui extends JFrame{
	private ArrayList<String> nombrePlugs;
	private CalculadoraSimple calculadora;
	private JTextField primerParam;
	private JTextField segundoParam;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Gui() {
		
		//Frame
		setTitle("Calculadora simple");
		setIconImage(new ImageIcon(this.getClass().getResource("/img/iconoCalculadora.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 355);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Paneles
		JPanel panelCarga = new JPanel();
		getContentPane().add(panelCarga, BorderLayout.SOUTH);
		panelCarga.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelCalculadora = new JPanel();
		getContentPane().add(panelCalculadora, BorderLayout.CENTER);
		panelCalculadora.setLayout(null);
		
		//Labels
		JLabel Nombre = new JLabel("Calculadora Simple de Enteros");
		Nombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(Nombre, BorderLayout.NORTH);
		
		JLabel primerParametro = new JLabel("Parametro 1");
		primerParametro.setBounds(10, 118, 102, 14);
		panelCalculadora.add(primerParametro);
		
		JLabel SegundoParametro = new JLabel("Parametro 2");
		SegundoParametro.setBounds(130, 118, 102, 14);
		panelCalculadora.add(SegundoParametro);
		

		JLabel resultado = new JLabel();
		resultado.setHorizontalAlignment(SwingConstants.CENTER);
		resultado.setBounds(23, 211, 191, 22);
		panelCalculadora.add(resultado);
		
		Label textoOperacion = new Label("Seleccione la operacion a realizar");
		textoOperacion.setBounds(261, 110, 191, 22);
		panelCalculadora.add(textoOperacion);
		
		JLabel textoConvenciones = new JLabel("<html><center>Ante la seleccion de una operacion de un unico parametro se tendra en cuenta el parametro 1, sin embargo, el parametro 2 debera contener un valor numerico que sera ignorado");
		textoConvenciones.setHorizontalAlignment(SwingConstants.CENTER);
		textoConvenciones.setBounds(56, 23, 348, 69);
		panelCalculadora.add(textoConvenciones);
		
		//Calculadora y cargas
		calculadora = new CalculadoraSimple();
		calculadora.getPlugins();
		nombrePlugs=calculadora.getPluginsName();
		
		//TextFields para parametros
		primerParam = new JTextField();
		primerParam.setBounds(10, 138, 67, 20);
		panelCalculadora.add(primerParam);
		primerParam.setColumns(10);
		
		segundoParam = new JTextField();
		segundoParam.setColumns(10);
		segundoParam.setBounds(130, 138, 67, 20);
		panelCalculadora.add(segundoParam);
		
		//Choice
		Choice opcionesPlugins = new Choice();
		opcionesPlugins.setBounds(261, 138, 191, 20);
		cargarOpcionesPlugins(opcionesPlugins);
		panelCalculadora.add(opcionesPlugins);
		
		//Botones
		JButton accionarOperaciones = new JButton("Realizar Operacion");
		accionarOperaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Realizar operacion con Plugin seleccionado
				int resObt=0;
				try {
					String pluginSeleccionado = opcionesPlugins.getItem(opcionesPlugins.getSelectedIndex());
					int param1 = Integer.parseInt(primerParam.getText());
					int param2 = Integer.parseInt(segundoParam.getText());
					resObt = calculadora.runPlugin(param1, param2, pluginSeleccionado);
					resultado.setText("Resultado obtenido: "+resObt);
				}catch(NumberFormatException ex1) {
					resultado.setText("Parametros no numericos");
				}catch(ArithmeticException ex2) {
					resultado.setText("Parametros invalidos");
				}catch(Exception ex3) {
					resultado.setText("Ninguna opcion fue seleccionada");
				}
			}
		});
		accionarOperaciones.setBounds(261, 211, 191, 22);
		panelCalculadora.add(accionarOperaciones);
		
		JButton botonAct = new JButton("Actualizar");
		botonAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cargo nuevo Plugins
				calculadora.getPlugins();
				nombrePlugs=calculadora.getPluginsName();
				cargarOpcionesPlugins(opcionesPlugins);
			}
		});
		panelCarga.add(botonAct);
	}
	
	//Cargo las opciones de plugins disponibles en la lista para elegir
	private void cargarOpcionesPlugins(Choice opciones) {
		opciones.removeAll();
		int fin= nombrePlugs.size();
		for(int i=0;i<fin;i++) {
			opciones.add(nombrePlugs.get(i));
		}
	}
}
