package it.uninsubria.emotionalsongs.service.report;

import it.uninsubria.emotionalsongs.assembler.playlist.PlaylistAssembler;
import it.uninsubria.emotionalsongs.assembler.report.ReportAssembler;
import it.uninsubria.emotionalsongs.entity.playlist.PlaylistEntity;
import it.uninsubria.emotionalsongs.entity.report.ReportEntity;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;
import it.uninsubria.emotionalsongs.model.report.Report;
import it.uninsubria.emotionalsongs.repository.playlist.PlaylistRepository;
import it.uninsubria.emotionalsongs.repository.report.ReportRepository;
import it.uninsubria.emotionalsongs.utils.Logger;

import java.util.List;

/**
 * Questa classe fornisce dei servizi per la gestione dei report.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 * @see it.uninsubria.emotionalsongs.assembler.playlist.PlaylistAssembler
 * @see it.uninsubria.emotionalsongs.assembler.report.ReportAssembler
 * @see it.uninsubria.emotionalsongs.entity.playlist.PlaylistEntity
 * @see it.uninsubria.emotionalsongs.entity.report.ReportEntity
 * @see it.uninsubria.emotionalsongs.model.playlist.Playlist
 * @see it.uninsubria.emotionalsongs.model.report.Report
 * @see it.uninsubria.emotionalsongs.repository.playlist.PlaylistRepository
 * @see it.uninsubria.emotionalsongs.repository.report.ReportRepository
 * @see it.uninsubria.emotionalsongs.utils.Logger
 */
public class ReportService {

    /**
     * Repository dei report.
     */
    private final ReportRepository reportRepository;

    /**
     * Assemblatore dei report.
     */
    private final ReportAssembler reportAssembler;

    /**
     * Costruisce un nuovo servizio di gestione dei report inizializzando il repository e l'assemblatore.
     */
    public ReportService() {
        reportRepository = new ReportRepository();
        reportAssembler = new ReportAssembler();
    }

    /**
     * Recupera un report tramite il suo ID.
     * @param id L'ID del report da recuperare.
     * @return Il report corrispondente all'ID specificato se presente, altrimenti null.
     */
    public Report getReport(Integer id) {
        Logger.info("ReportService : getReport ");
        ReportEntity report = reportRepository.getReport(id);
        return reportAssembler.toModel(report);
    }

}
