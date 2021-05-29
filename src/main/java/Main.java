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

        int indiceNavePrivada = 0;
        int pricipal = 0;
        int indiceTipoNave = 0;
        int indicieOrbitas = 0;
        int indiceAgenciaPrivada = 0;
        int cod = 1;
        int matricula = 0;
        int orbitaId = 1;
        int elipticaId = 1;
        int circularId = 1;
        double ran1 = random.nextDouble();
        if (ran1 < 0 )
            ran1 = ran1 * -1;


        //loop principal que crea agencias publicas
        while( pricipal < 10 ) {
            Agencia aPub = new Agencia();
            aPub.setNombre(faker.space().company() + " " + faker.space().distanceMeasurement());
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
            while(indiceTipoNave < 1) {
                //crear tipo nave
                TipoNave tN = new TipoNave();
                tN.setCod(cod++);
                TipoNaveDAO tndao = new TipoNaveDAO(conn);
                tndao.create(tN);
                //crear naves publicas
                    NaveEspacial nave = new NaveEspacial();
                    nave.setTipoNave(tN.getCod());
                    nave.setMatricula(matricula++);
                    nave.setMision(faker.space().star());
                    nave.setAgencia(aPub.getNombre());
                    NaveEspacialDAO naveDAO = new NaveEspacialDAO(conn);
                    naveDAO.create(nave);

                    //crear basura publica
                    while(indicieOrbitas < 1) {
                        //crear orbita
                        Eliptica eliptica = new Eliptica();
                        Orbita orbita = new Orbita();
                        orbita.setId(orbitaId);
                        orbitaId++;
                        double ran11 = random.nextDouble();
                        if (ran11 < 0 )
                            ran11 = ran11 * -1;
                        orbita.setCoordR(ran11);
                        double ran2 = random.nextDouble();
                        if (ran2 < 0)
                            ran2 = ran2 * -1;
                        orbita.setCoordFi(ran2);
                        eliptica.setId(elipticaId);
                        elipticaId++;
                        eliptica.setOrbitaId(orbita.getId());
                        eliptica.setExentricidad(random.nextInt());
                        OrbitaDAO orbitaDAO = new OrbitaDAO(conn);
                        orbitaDAO.create(orbita);
                        ElipticaDAO eDAO = new ElipticaDAO(conn);
                        eDAO.create(eliptica);

                        //crear basura
                        Basura basura = new Basura();
                        basura.setVelocity(random.nextDouble());
                        basura.setCod(nave.getTipoNave());
                        basura.setOrbitaId(eliptica.getId());
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
                        indicieOrbitas++;
                    }
                    indiceTipoNave++;
            }

            //loop que crea agencias privadas
            indicieOrbitas = 0;
            indiceTipoNave = 0;
            while(indiceAgenciaPrivada < 10){
                Agencia aPriv = new Agencia();
                Privada privada = new Privada();
                aPriv.setNombre(faker.space().company() + " " + faker.space().meteorite());
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

                while(indiceTipoNave < 1) {
                    //crear naves privadas
                    TipoNave tN = new TipoNave();
                    tN.setCod(cod++);
                    TipoNaveDAO tndao = new TipoNaveDAO(conn);
                    tndao.create(tN);
                    while (indiceNavePrivada != 1){
                        NaveEspacial navePriv = new NaveEspacial();
                        navePriv.setTipoNave(tN.getCod());
                        navePriv.setMision(faker.space().star());
                        navePriv.setMatricula(matricula++);
                        navePriv.setAgencia(aPriv.getNombre());
                        NaveEspacialDAO nedao = new NaveEspacialDAO(conn);
                        nedao.create(navePriv);
                        //crear basura privada

                        while(indicieOrbitas < 1){
                            //crear orbita
                            Orbita orbita = new Orbita();
                            orbita.setId(orbitaId);
                            orbitaId++;
                            double ran11 = random.nextDouble();
                            if (ran11 < 0 )
                                ran11 = ran11 * -1;
                            orbita.setCoordR(ran11);
                            double ran2 = random.nextDouble();
                            if (ran2 < 0)
                                ran2 = ran2 * -1;
                            orbita.setCoordFi(ran2);
                            Circular circular = new Circular();
                            circular.setId(circularId);
                            circularId++;
                            circular.setOrbitaId(orbita.getId());
                            circular.setGeoestacionaria(random.nextInt());
                            OrbitaDAO orbitaDAO = new OrbitaDAO(conn);
                            orbitaDAO.create(orbita);
                            CircularDAO cDAO = new CircularDAO(conn);
                            cDAO.create(circular);
                            OrbitaDAO ordao = new OrbitaDAO(conn);
                            ordao.create(orbita);
                            //crear basura
                            Basura basura = new Basura();
                            basura.setVelocity(random.nextDouble());
                            basura.setCod(navePriv.getTipoNave());
                            basura.setOrbitaId(circular.getId());
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
                            indicieOrbitas++;
                        }
                        indiceNavePrivada++;
                    }
                    indiceTipoNave++;
                }
                indiceAgenciaPrivada++;
            }
            pricipal++;
        }
    }
}
