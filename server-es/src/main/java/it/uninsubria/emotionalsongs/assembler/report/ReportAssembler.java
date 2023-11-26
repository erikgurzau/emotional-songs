package it.uninsubria.emotionalsongs.assembler.report;

import it.uninsubria.emotionalsongs.assembler.AssemblerImpl;
import it.uninsubria.emotionalsongs.entity.playlist.PlaylistEntity;
import it.uninsubria.emotionalsongs.entity.report.ReportEntity;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;
import it.uninsubria.emotionalsongs.model.report.Report;

import java.util.List;
import java.util.stream.Collectors;

public class ReportAssembler implements AssemblerImpl<ReportEntity, Report> {
    /*
     * Costruttore della classe.
     */
    public ReportAssembler() { }

    /*
     * Converte un'istanza di ReportEntity in un'istanza di Report.
     */
    @Override
    public Report toModel(ReportEntity entity) {
        return new Report(
                //entity.getIdCanzone(), entity.getNumUtenti(), entity.getMedie(), entity.getNote()
                entity.getIdCanzone(), entity.getStatiEmozionali()
        );
    }

    /*
     * Converte una lista di ReportEntity in una lista di Report
     * utilizzando il metodo toModel per ogni elemento della lista.
     */
    @Override
    public List<Report> toModel(List<ReportEntity> entityList) {
        return entityList.stream().map(this::toModel).collect(Collectors.toList());
    }


}
