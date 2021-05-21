import com.github.javafaker.Faker;
import models.*;
import models.connections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        Connection conn = DBConnection.getConnection();
        Faker faker = new Faker();
        Random random = new Random();

        int i = 0;
        int j = 0;
        int matricula = 100;
        int tipoNave = 0;
            //crear orbita
            Orbita orbita = new Orbita();
            orbita.setCoordR(random.nextDouble());
            orbita.setCoordFi(random.nextDouble());


            //loop principal que crea agencias publicas
            while( j != 7 ) {
                Agencia aPub = new Agencia();
                aPub.setNombre(faker.space().company());
                Publica publica = new Publica();
                publica.setNombre(aPub.getNombre());
                //loop que crea tipos de naves publicas
                while(tipoNave != 10) {
                    //crear naves publicas
                    while (i != 130){
                        NaveEspacial nave = new NaveEspacial();
                        nave.setMission(faker.space().star());
                        nave.setMatricula(matricula++);
                        //crear basura publica
                    }
                }

                while(j != 24){
                    Agencia aPriv = new Agencia();
                    Privada privada = new Privada();
                    aPriv.setNombre(faker.space().company());
                    privada.setNombre(aPriv.getNombre());
                    privada.setEsFiscalizadaPor(publica.getNombre());
                    //loop que crea tipos de naves privadas

                    while(tipoNave != 10) {

                        //crear naves privadas
                        while (i != 460){
                            NaveEspacial nave = new NaveEspacial();
                            nave.setMission(faker.space().star());
                            nave.setMatricula(matricula++);
                            nave.setAgencia
                            //crear basura privada
                        }
                    }
                }
            }

            //crear tipo de nave
            TipoNave tn = new TipoNave();
            tn.setCod(random.nextInt());



        
    }
}
