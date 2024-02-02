package it.uninsubria.emotionalsongs.assembler.report;

import it.uninsubria.emotionalsongs.assembler.AssemblerImpl;
import it.uninsubria.emotionalsongs.entity.report.ReportEntity;
import it.uninsubria.emotionalsongs.model.report.Report;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe è responsabile della conversione tra l'entità ReportEntity e il modello Report.
 * Implementa l'interfaccia AssemblerImpl per definire le operazioni di conversione.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.assembler.AssemblerImpl
 * @see it.uninsubria.emotionalsongs.entity.report.ReportEntity
 * @see it.uninsubria.emotionalsongs.model.report.Report
 */
public class ReportAssembler implements AssemblerImpl<ReportEntity, Report> {

    /**
     * Costruttore della classe.
     */
    public ReportAssembler() { }

    /**
     * Converte un'istanza di ReportEntity in un'istanza di Report.
     * @param entity L'entità da convertire
     * @return Un report emozionale
     */
    @Override
    public Report toModel(ReportEntity entity) {
        return new Report(
                //entity.getIdCanzone(), entity.getNumUtenti(), entity.getMedie(), entity.getNote()
                entity.getIdCanzone(), entity.getStatiEmozionali()
        );
    }

    /**
     * Converte una lista di ReportEntity in una lista di Report
     * utilizzando il metodo toModel per ogni elemento della lista.
     * @param entityList La lista da convertire
     * @return Una lista di report
     */
    @Override
    public List<Report> toModel(List<ReportEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }

}
