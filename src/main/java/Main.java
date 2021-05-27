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
        int ip = 0;
        int j = 0;
        int matricula = 100;
        int tipoNave = 0;
        int o = 0;
        int op = 0;
        int b = 1;
        int agenPri = 0;
//        //crear orbita
//        Orbita orbita = new Orbita();
//        double ran1 = random.nextDouble();
//        if (ran1 < 0 )
//            ran1 = ran1 * -1;
//        orbita.setCoordR(ran1);
//        double ran2 = random.nextDouble();
//        if (ran2 < 0)
//            ran2 = ran2 * -1;
//        orbita.setCoordFi(ran2);





        //loop principal que crea agencias publicas
        while( j != 7 ) {
            Agencia aPub = new Agencia();
            aPub.setNombre(faker.space().company());
            Publica publica = new Publica();
            publica.setNombre(aPub.getNombre());

            //loop que crea tipos de naves publicas
            while(tipoNave != 10) {

                //crear tipo nave
                TipoNave tN = new TipoNave();
                int ran3 = random.nextInt();
                if (ran3 < 0 )
                    ran3 = ran3 * -1;
                tN.setCod(ran3);
                //crear naves publicas
                while (i != 130){
                    NaveEspacial nave = new NaveEspacial();
                    nave.setMission(faker.space().star());
                    nave.setMatricula(matricula++);
                    nave.setAgencia(aPub.getNombre());

                    //crear basura publica
                    while(o != 1000){
                        //crear orbita
                        Orbita orbita = new Orbita();
                        double ran1 = random.nextDouble();
                        if (ran1 < 0 )
                            ran1 = ran1 * -1;
                        orbita.setCoordR(ran1);
                        double ran2 = random.nextDouble();
                        if (ran2 < 0)
                            ran2 = ran2 * -1;
                        orbita.setCoordFi(ran2);
                        //crear basura
                        Basura basura = new Basura();
                        basura.setVelocity(random.nextDouble());
                        basura.setCod(b);
                        basura.setCoordFi(orbita.getCoordFi());
                        basura.setCoordR(orbita.getCoordR());
                        double ran6 = random.nextDouble();
                        if (ran6 < 0)
                            ran6 = ran6 * -1;
                        basura.setSize(ran6);
                        double ran7 = random.nextDouble();
                        if (ran7 < 0)
                            ran7 = ran7 * -1;
                        basura.setWeight(ran7);
                        b++;
                    }
                }
            }

            //loop que crea agencias privadas
            tipoNave = 0;
            while(agenPri != 24){
                Agencia aPriv = new Agencia();
                Privada privada = new Privada();
                aPriv.setNombre(faker.space().company());
                privada.setNombre(aPriv.getNombre());
                privada.setEsFiscalizadaPor(publica.getNombre());
                //loop que crea tipos de naves privadas

                while(tipoNave != 10) {

                    //crear naves privadas
                    while (ip != 460){
                        NaveEspacial nave = new NaveEspacial();
                        nave.setMission(faker.space().star());
                        nave.setMatricula(matricula++);
                        nave.setAgencia(aPriv.getNombre());
                        //crear basura privada

                        while(op != 100){
                            //crear orbita
                            Orbita orbita = new Orbita();
                            double ran1 = random.nextDouble();
                            if (ran1 < 0 )
                                ran1 = ran1 * -1;
                            orbita.setCoordR(ran1);
                            double ran2 = random.nextDouble();
                            if (ran2 < 0)
                                ran2 = ran2 * -1;
                            orbita.setCoordFi(ran2);
                            //crear basura
                            Basura basura = new Basura();
                            basura.setVelocity(random.nextDouble());
                            basura.setCod(b);
                            b++;
                            basura.setCoordFi(orbita.getCoordFi());
                            basura.setCoordR(orbita.getCoordR());
                            double ran6 = random.nextDouble();
                            if (ran6 < 0)
                                ran6 = ran6 * -1;
                            basura.setSize(ran6);
                            double ran7 = random.nextDouble();
                            if (ran7 < 0)
                                ran7 = ran7 * -1;
                            basura.setWeight(ran7);

                            op++;
                        }
                        ip++;
                    }
                    tipoNave++;
                }
                agenPri++;
            }
            j++;
        }

        //crear tipo de nave
        TipoNave tn = new TipoNave();
        tn.setCod(random.nextInt());



        
    }
}
