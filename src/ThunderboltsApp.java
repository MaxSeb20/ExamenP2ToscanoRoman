import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.util.ArrayList;
import java.util.Collections;

public class ThunderboltsApp extends JFrame {
    private final ListaThunderbolts lista = new ListaThunderbolts();

    public ThunderboltsApp() {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Gestion de Thunderbolts de Marvel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de pestañas
        JTabbedPane pestañas = new JTabbedPane();

        // Pestaña para registro de Thunderbolts
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(new GridLayout(7, 2));
        panelRegistro.setBackground(new Color(255, 255, 204)); // Fondo amarillo claro

        JLabel lblCodigo = new JLabel("Código:");
        JTextField txtCodigo = new JTextField();
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblHabilidad = new JLabel("Habilidad Principal:");
        JComboBox<String> cbHabilidad = new JComboBox<>(new String[]{"Combate Cuerpo a Cuerpo", "Tiro Preciso", "Tecnología Avanzada", "Sigilo", "Supervelocidad"});
        JLabel lblNivelRedencion = new JLabel("Nivel de Redención:");
        JComboBox<Integer> cbNivelRedencion = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        JLabel lblMision = new JLabel("Misión Asignada:");
        JComboBox<String> cbMision = new JComboBox<>(new String[]{"Rescate", "Infiltración", "Defensa", "Neutralización", "Recuperación de Objetos"});

        JButton btnAgregar = new JButton("Agregar Thunderbolt");
        estiloBoton(btnAgregar);

        panelRegistro.add(lblCodigo);
        panelRegistro.add(txtCodigo);
        panelRegistro.add(lblNombre);
        panelRegistro.add(txtNombre);
        panelRegistro.add(lblHabilidad);
        panelRegistro.add(cbHabilidad);
        panelRegistro.add(lblNivelRedencion);
        panelRegistro.add(cbNivelRedencion);
        panelRegistro.add(lblMision);
        panelRegistro.add(cbMision);
        panelRegistro.add(new JLabel());
        panelRegistro.add(btnAgregar);

        pestañas.addTab("Registro de Thunderbolts", panelRegistro);

        // Tabla de Thunderbolts
        String[] columnas = {"Código", "Nombre", "Habilidad Principal", "Nivel de Redención", "Misión Asignada"};
        JTable tablaThunderbolts = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaThunderbolts);
        scrollPane.getViewport().setBackground(new Color(245, 245, 245)); // Fondo gris claro

        pestañas.addTab("Listado de Thunderbolts", scrollPane);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtCodigo.getText());
                    String nombre = txtNombre.getText();
                    String habilidad = (String) cbHabilidad.getSelectedItem();
                    int nivelRedencion = (Integer) cbNivelRedencion.getSelectedItem();
                    String mision = (String) cbMision.getSelectedItem();

                    Thunderbolt thunderbolt = new Thunderbolt(codigo, nombre, habilidad, nivelRedencion, mision);
                    lista.agregarThunderbolt(thunderbolt);

                    tablaThunderbolts.setModel(new javax.swing.table.DefaultTableModel(
                            lista.obtenerDatosThunderbolts(),
                            columnas
                    ));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un código válido.");
                }
            }
        });

        // Pestaña para búsqueda de Thunderbolts
        JPanel panelBusqueda = new JPanel(new BorderLayout());
        panelBusqueda.setBackground(new Color(204, 229, 255)); // Fondo azul claro

        JPanel panelBusquedaSuperior = new JPanel(new GridLayout(1, 2));
        panelBusquedaSuperior.setBackground(new Color(204, 229, 255));
        JLabel lblBuscarCodigo = new JLabel("Código a buscar:");
        JTextField txtBuscarCodigo = new JTextField();

        JButton btnBuscar = new JButton("Buscar Thunderbolt");
        estiloBoton(btnBuscar);

        JTextArea txtResultadoBusqueda = new JTextArea(5, 20);
        txtResultadoBusqueda.setEditable(false);
        txtResultadoBusqueda.setBackground(new Color(255, 255, 240)); // Fondo amarillo claro
        JScrollPane scrollResultadoBusqueda = new JScrollPane(txtResultadoBusqueda);

        panelBusquedaSuperior.add(lblBuscarCodigo);
        panelBusquedaSuperior.add(txtBuscarCodigo);
        panelBusqueda.add(panelBusquedaSuperior, BorderLayout.NORTH);
        panelBusqueda.add(btnBuscar, BorderLayout.CENTER);
        panelBusqueda.add(scrollResultadoBusqueda, BorderLayout.SOUTH);

        pestañas.addTab("Búsqueda de Thunderbolts", panelBusqueda);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(txtBuscarCodigo.getText());
                    Thunderbolt thunderbolt = lista.buscarThunderbolt(codigo);
                    if (thunderbolt != null) {
                        txtResultadoBusqueda.setText("Código: " + thunderbolt.getCodigo() + "\n" +
                                "Nombre: " + thunderbolt.getNombre() + "\n" +
                                "Habilidad Principal: " + thunderbolt.getHabilidadPrincipal() + "\n" +
                                "Nivel de Redención: " + thunderbolt.getNivelRedencion() + "\n" +
                                "Misión Asignada: " + thunderbolt.getMisionAsignada());
                    } else {
                        JOptionPane.showMessageDialog(null, "Thunderbolt no encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un código válido.");
                }
            }
        });

        // Pestaña para filtrado y ordenamiento
        JPanel panelFiltrado = new JPanel(new BorderLayout());
        panelFiltrado.setBackground(new Color(224, 255, 255)); // Fondo azul muy claro

        JLabel lblFiltrarHabilidad = new JLabel("Filtrar por Habilidad Principal:");
        JComboBox<String> cbFiltrarHabilidad = new JComboBox<>(new String[]{"Combate Cuerpo a Cuerpo", "Tiro Preciso", "Tecnología Avanzada", "Sigilo", "Supervelocidad"});
        JButton btnFiltrar = new JButton("Filtrar y Ordenar");
        estiloBoton(btnFiltrar);

        JTable tablaFiltrada = new JTable();
        JScrollPane scrollFiltrada = new JScrollPane(tablaFiltrada);

        JPanel panelFiltroTop = new JPanel();
        panelFiltroTop.setBackground(new Color(224, 255, 255));
        panelFiltroTop.add(lblFiltrarHabilidad);
        panelFiltroTop.add(cbFiltrarHabilidad);
        panelFiltroTop.add(btnFiltrar);

        panelFiltrado.add(panelFiltroTop, BorderLayout.NORTH);
        panelFiltrado.add(scrollFiltrada, BorderLayout.CENTER);

        pestañas.addTab("Filtrado y Ordenamiento", panelFiltrado);

        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String habilidadSeleccionada = (String) cbFiltrarHabilidad.getSelectedItem();
                ArrayList<Thunderbolt> listaFiltrada = lista.filtrarPorHabilidad(habilidadSeleccionada);
                listaFiltrada = lista.ordenarPorNivelRedencion(listaFiltrada);

                Object[][] datosFiltrados = new Object[listaFiltrada.size()][5];
                for (int i = 0; i < listaFiltrada.size(); i++) {
                    Thunderbolt t = listaFiltrada.get(i);
                    datosFiltrados[i][0] = t.getCodigo();
                    datosFiltrados[i][1] = t.getNombre();
                    datosFiltrados[i][2] = t.getHabilidadPrincipal();
                    datosFiltrados[i][3] = t.getNivelRedencion();
                    datosFiltrados[i][4] = t.getMisionAsignada();
                }

                tablaFiltrada.setModel(new javax.swing.table.DefaultTableModel(
                        datosFiltrados,
                        columnas
                ));
            }
        });

        // Pestaña para conteo de misiones por habilidad
        JPanel panelConteo = new JPanel(new BorderLayout());
        panelConteo.setBackground(new Color(255, 239, 213)); // Fondo color durazno claro

        JTextArea txtConteoMisiones = new JTextArea(10, 30);
        txtConteoMisiones.setEditable(false);
        txtConteoMisiones.setBackground(new Color(255, 250, 240)); // Fondo crema claro

        JButton btnConteo = new JButton("Contar Misiones por Habilidad");
        estiloBoton(btnConteo);

        panelConteo.add(new JScrollPane(txtConteoMisiones), BorderLayout.CENTER);
        panelConteo.add(btnConteo, BorderLayout.SOUTH);

        pestañas.addTab("Conteo de Misiones", panelConteo);

        btnConteo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtConteoMisiones.setText("");
                for (String habilidad : new String[]{"Combate Cuerpo a Cuerpo", "Tiro Preciso", "Tecnología Avanzada", "Sigilo", "Supervelocidad"}) {
                    int totalMisiones = lista.contarMisionesPorHabilidad(lista.getCabeza(), habilidad);
                    txtConteoMisiones.append(habilidad + ": " + totalMisiones + " misiones\n");
                }
            }
        });

        add(pestañas);
    }

    private void estiloBoton(JButton boton) {
        boton.setFocusPainted(false);
        boton.setBackground(new Color(50, 50, 50)); // Fondo gris oscuro
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThunderboltsApp app = new ThunderboltsApp();
            app.setVisible(true);
        });
    }
}
