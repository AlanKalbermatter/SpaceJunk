import com.github.javafaker.Faker;
import models.*;
import models.connections.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realizations.dao.impl.*;

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
        while( j != 1 ) {
            Agencia aPub = new Agencia();
            aPub.setNombre(faker.space().company());
            Publica publica = new Publica();
            publica.setNombre(aPub.getNombre());
            int ran8 = random.nextInt();
            if (ran8 < 0)
                ran8 = ran8 * -1;
            aPub.setNumeroPersonas(ran8);
            AgenciaDAO adao = new AgenciaDAO(conn);
            adao.create(aPub);
            PublicaDAO pdao = new PublicaDAO(conn);
            pdao.create(publica);

            //loop que crea tipos de naves publicas
            while(tipoNave != 1) {
                //crear tipo nave
                TipoNave tN = new TipoNave();
                int ran3 = random.nextInt();
                if (ran3 < 0 )
                    ran3 = ran3 * -1;
                tN.setCod(ran3);
                TipoNaveDAO tndao = new TipoNaveDAO(conn);
                tndao.create(tN);
                //crear naves publicas
                while (i != 1){
                    NaveEspacial nave = new NaveEspacial();
                    nave.setMision(faker.space().star());
                    nave.setMatricula(matricula++);
                    nave.setAgencia(aPub.getNombre());
                    nave.setTipoNave(tN.getCod());
                    NaveEspacialDAO naveDAO = new NaveEspacialDAO(conn);
                    naveDAO.create(nave);

                    //crear basura publica
                    while(o != 1){
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
                        OrbitaDAO orbitaDAO = new OrbitaDAO(conn);
                        orbitaDAO.create(orbita);

                        //crear basura
                        Basura basura = new Basura();
                        basura.setVelocity(random.nextDouble());
                        basura.setCod(b);
                        b++;
                        basura.setCoordFi(orbita.getCoordFi());
                        basura.setCoordR(orbita.getCoordR());
                        double ran6 = random.nextDouble();
                        if (ran6 < 0) {
                            ran6 = ran6 * -1;
                        }
                        basura.setSize(ran6);
                        double ran7 = random.nextDouble();
                        if (ran7 < 0) {
                            ran7 = ran7 * -1;
                        }
                        basura.setWeight(ran7);
                        BasuraDAO bdao = new BasuraDAO(conn);
                        bdao.create(basura);
                        o++;
                    }
                }
            }

            //loop que crea agencias privadas
            tipoNave = 0;
            while(agenPri != 1){
                Agencia aPriv = new Agencia();
                Privada privada = new Privada();
                aPriv.setNombre(faker.space().company());
                int ran9 = random.nextInt();
                if (ran9 < 0)
                    ran9 = ran9 * -1;
                aPriv.setNumeroPersonas(ran9);
                privada.setNombre(aPriv.getNombre());
                privada.setEsFiscalizadaPor(publica.getNombre());
                AgenciaDAO apdao = new AgenciaDAO(conn);
                apdao.create(aPriv);
                PrivadaDAO priDAO = new PrivadaDAO(conn);
                priDAO.create(privada);
                //loop que crea tipos de naves privadas

                while(tipoNave != 1) {

                    //crear naves privadas
                    while (ip != 1){
                        NaveEspacial nave = new NaveEspacial();
                        nave.setMision(faker.space().star());
                        nave.setMatricula(matricula++);
                        nave.setAgencia(aPriv.getNombre());
                        NaveEspacialDAO nedao = new NaveEspacialDAO(conn);
                        nedao.create(nave);
                        //crear basura privada

                        while(op != 1){
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
                            OrbitaDAO ordao = new OrbitaDAO(conn);
                            ordao.create(orbita);
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
                            BasuraDAO basdao = new BasuraDAO(conn);
                            basdao.create(basura);
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
    }
}
