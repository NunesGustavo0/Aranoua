package model;

public class Recursos {
    private int numeroTombo;
    private String equipamento;
    private String marca;
    private String modelo;

    public Recursos() {
    }

    public Recursos(String equipamento, String marca, String modelo) {
        this.equipamento = equipamento;
        this.marca = marca;
        this.modelo = modelo;
    }

    public Recursos(int numeroTombo ,String equipamento, String marca, String modelo) {
        this.numeroTombo = numeroTombo;
        this.equipamento = equipamento;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }
    public int getNumeroTombo() {
        return numeroTombo;
    }
    public String getEquipamento() {
        return equipamento;
    }
    public String getMarca() {
        return marca;
    }


    public void setEquipamento(String equipamento) {this.equipamento = equipamento;}
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setNumeroTombo(int numeroTombo) {
        this.numeroTombo = numeroTombo;
    }

    public String toString() {
        return "Recurso{"+
                "equipamento =" + equipamento + '\'' +
                "marca =" + marca + '\'' +
                "modelo =" + modelo + '\'' +
                "}";
    }

}