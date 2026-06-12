package examen.almudi.arturo;

import examen.almudi.arturo.dao.SocDAOImpl;
import examen.almudi.arturo.dao.IncidenteDAOImpl;
import examen.almudi.arturo.dao.InformeIncidenteDAOImpl;
import examen.almudi.arturo.beans.Soc;
import examen.almudi.arturo.beans.Incidente;
import examen.almudi.arturo.motores.MotorFactory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        IncidenteDAOImpl incidenteDAO = new IncidenteDAOImpl(
                MotorFactory.create(MotorFactory.POSTGRE));

        System.out.println("----------------------------------------------");
        System.out.println("[incidenteDAO.check()]");
        incidenteDAO.check();

        SocDAOImpl socDAO = new SocDAOImpl(
                MotorFactory.create(MotorFactory.POSTGRE));
        System.out.println("----------------------------------------------");
        System.out.println("[socDAO.findAll()]");
        Soc testsoc = socDAO.findAll().get(0);

        System.out.println("----------------------------------------------");
        System.out.println("[TEST 1] ADD INCIDENTE");
        Incidente test = new Incidente();
        test.setCodigoIncidente("INC-2026-TEST");
        test.setTipoIncidente("APAGON");
        test.setFechaDeteccion("02/06/2026");
        test.setEstado("EN_PROCESO");
        test.setSoc(testsoc);
        test.setAutorExamen("Arturo_Almudi_Marco_DAM3");
        incidenteDAO.add(test);
        System.out.println("inserta un incidente nuevo en la BD");
        System.out.println("metido: " + test.getCodigoIncidente() + " en " + testsoc.getNombre());

        System.out.println("----------------------------------------------");
        System.out.println("[TEST 2] UPDATE INCIDENTE");
        test.setEstado("CERRADO");
        incidenteDAO.update(1, test);
        System.out.println("actualiza un incidente que ya existe");
        System.out.println("id=1 -> estado ahora es: " + test.getEstado());

        System.out.println("----------------------------------------------");
        System.out.println("[TEST 3] FIND INCIDENTE");
        Incidente encontrado = incidenteDAO.find(1);
        System.out.println("busca un incidente por id y lo devuelve con su soc");
        System.out.println("id=1: " + encontrado.getCodigoIncidente()
                + " | " + encontrado.getTipoIncidente()
                + " | " + encontrado.getEstado()
                + " | soc: " + encontrado.getSoc().getNombre());

        System.out.println("----------------------------------------------");
        System.out.println("[TEST 4] FIND ALL INCIDENTES");
        ArrayList<Incidente> todos = incidenteDAO.findAll();
        System.out.println("trae todos los incidentes de la BD con su soc");
        System.out.println("hay " + todos.size() + " en total:");
        for (Incidente m : todos) {
            System.out.println("  " + m.getId() + ": " + m.getCodigoIncidente()
                    + " | " + m.getEstado()
                    + " | " + m.getSoc().getNombre());
        }

        System.out.println("----------------------------------------------");
        System.out.println("[TEST 5] FIND BY SOC");
        ArrayList<Incidente> porSoc = incidenteDAO.findBySoc(1);
        System.out.println("filtra los incidentes de un soc concreto");
        System.out.println(testsoc.getNombre() + " tiene " + porSoc.size() + " incidentes:");
        for (Incidente m : porSoc) {
            System.out.println("  " + m.getId() + ": " + m.getCodigoIncidente() + " | " + m.getEstado());
        }

        System.out.println("----------------------------------------------");
        System.out.println("[TEST 6] FIND WITH INFORME");
        Incidente conInforme = incidenteDAO.findWithInforme(1);
        System.out.println("trae el incidente con su soc y su informe usando triple join");
        System.out.println("incidente: " + conInforme.getCodigoIncidente()
                + " | soc: " + conInforme.getSoc().getNombre());
        System.out.println("informe: malware=" + conInforme.getInforme().isMalwareDetectado()
                + " | severidad=" + conInforme.getInforme().getNivelSeveridad()
                + " | " + conInforme.getInforme().getConclusion());

        System.out.println("----------------------------------------------");
        System.out.println("[BLOQUE 6] INCIDENTES CRITICOS");
        ArrayList<Incidente> criticos = incidenteDAO.findIncidentesCriticos();
        System.out.println("incidentes con malware, severidad mayor de 90 y soc en españa");
        System.out.println("encontrados " + criticos.size() + " resultados:");
        for (Incidente m : criticos) {
            System.out.println("  " + m.getCodigoIncidente()
                    + " | soc: " + m.getSoc().getNombre()
                    + " | severidad: " + m.getInforme().getNivelSeveridad()
                    + " | malware: " + m.getInforme().isMalwareDetectado());
        }

        System.out.println("----------------------------------------------");
        System.out.println("[BLOQUE 7] UPDATE DINAMICO");
        InformeIncidenteDAOImpl informeDAO = new InformeIncidenteDAOImpl(
                MotorFactory.create(MotorFactory.POSTGRE));
        System.out.println("actualiza solo los campos que le pases, los null no se tocan");
        informeDAO.updateDynamicInforme(1, null, 99, "ACTUALIZADO_DINAMICAMENTE");
        System.out.println("informe id=1: cambiados nivel_severidad y conclusion");
        informeDAO.updateDynamicInforme(2, true, null, null);
        System.out.println("informe id=2: cambiado solo malware_detectado");

        System.out.println("----------------------------------------------");
        System.out.println("[BLOQUE 8] ARQUITECTURA");
        ArrayList<Incidente> testArquitectura = incidenteDAO.findIncidentesCriticos();
        System.out.println("findIncidentesCriticos solo vive en IncidenteDAOImpl, no en AbstractDAO");
        System.out.println("estos son los resultados:");
        for (Incidente m : testArquitectura) {
            System.out.println("  " + m.getCodigoIncidente()
                    + " | soc: " + m.getSoc().getNombre()
                    + " | severidad: " + m.getInforme().getNivelSeveridad()
                    + " | " + m.getInforme().getConclusion());
        }
    }
}
