import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;

public class ListaThunderbolts {
    private Nodo cabeza;

    // Agregar Thunderbolt a la lista
    public void agregarThunderbolt(Thunderbolt thunderbolt) {
        if (buscarThunderbolt(thunderbolt.getCodigo()) == null) {
            Nodo nuevoNodo = new Nodo(thunderbolt);
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
            JOptionPane.showMessageDialog(null, "Thunderbolt agregado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "El código ya existe. No se puede registrar el Thunderbolt.");
        }
    }

    // Buscar Thunderbolt por código
    public Thunderbolt buscarThunderbolt(int codigo) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.thunderbolt.getCodigo() == codigo) {
                return actual.thunderbolt;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    // Obtener datos para JTable
    public Object[][] obtenerDatosThunderbolts() {
        int size = contarNodos();
        Object[][] datos = new Object[size][5];
        Nodo actual = cabeza;
        int index = 0;

        while (actual != null) {
            Thunderbolt thunderbolt = actual.thunderbolt;
            datos[index][0] = thunderbolt.getCodigo();
            datos[index][1] = thunderbolt.getNombre();
            datos[index][2] = thunderbolt.getHabilidadPrincipal();
            datos[index][3] = thunderbolt.getNivelRedencion();
            datos[index][4] = thunderbolt.getMisionAsignada();
            index++;
            actual = actual.siguiente;
        }
        return datos;
    }

    // Método para filtrar Thunderbolts por habilidad
    public ArrayList<Thunderbolt> filtrarPorHabilidad(String habilidad) {
        ArrayList<Thunderbolt> filtrados = new ArrayList<>();
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.thunderbolt.getHabilidadPrincipal().equals(habilidad)) {
                filtrados.add(actual.thunderbolt);
            }
            actual = actual.siguiente;
        }
        return filtrados;
    }

    // Método para ordenar por nivel de redención usando burbuja
    public ArrayList<Thunderbolt> ordenarPorNivelRedencion(ArrayList<Thunderbolt> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getNivelRedencion() > lista.get(j + 1).getNivelRedencion()) {
                    Collections.swap(lista, j, j + 1);
                }
            }
        }
        return lista;
    }

    // Contar nodos en la lista
    public int contarNodos() {
        int contador = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    // Método recursivo para contar misiones por habilidad
    public int contarMisionesPorHabilidad(Nodo nodo, String habilidad) {
        if (nodo == null) {
            return 0;
        }
        int contar = nodo.thunderbolt.getHabilidadPrincipal().equals(habilidad) ? 1 : 0;
        return contar + contarMisionesPorHabilidad(nodo.siguiente, habilidad);
    }

    // Getter para la cabeza de la lista
    public Nodo getCabeza() {
        return cabeza;
    }
}

