package models;

public class NaveEspacial {
    private int matricula;
    private String mision;
    private int tipoNave;
    private String agencia;
    public NaveEspacial(){}


    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public int getTipoNave() {
        return tipoNave;
    }

    public void setTipoNave(int tipoNave) {
        this.tipoNave = tipoNave;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
}
