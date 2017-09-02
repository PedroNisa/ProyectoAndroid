package miranda.com.olivesservices.conexion;

/**
 * Created by root on 6/12/16.
 */
public class Entrada {


    public String idEntrada;
    public int kilogramos;
    public String cliente;
    public String fecha;
    public String escandallo;


    public Entrada(String idEntrada, int kilogramos, String cliente, String fecha, String escandallo) {
        this.idEntrada = idEntrada;
        this.kilogramos = kilogramos;
        this.cliente = cliente;
        this.fecha = fecha;
        this.escandallo = escandallo;
    }

    public String getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(String idEntrada) {
        this.idEntrada = idEntrada;
    }

    public int getKilogramos() {
        return kilogramos;
    }

    public void setKilogramos(int kilogramos) {
        this.kilogramos = kilogramos;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEscandallo() {
        return escandallo;
    }

    public void setEscandallo(String escandallo) {
        this.escandallo = escandallo;
    }
}
