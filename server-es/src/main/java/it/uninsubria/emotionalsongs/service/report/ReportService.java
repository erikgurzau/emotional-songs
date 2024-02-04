package it.uninsubria.emotionalsongs.service.report;

import it.uninsubria.emotionalsongs.assembler.report.ReportAssembler;
import it.uninsubria.emotionalsongs.entity.report.ReportEntity;
import it.uninsubria.emotionalsongs.model.report.Report;
import it.uninsubria.emotionalsongs.repository.report.ReportRepository;
import it.uninsubria.emotionalsongs.utils.Logger;

/**
 * Questa classe fornisce dei servizi per la gestione dei report emozionali.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.assembler.report.ReportAssembler
 * @see it.uninsubria.emotionalsongs.entity.report.ReportEntity
 * @see it.uninsubria.emotionalsongs.model.report.Report
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
     * Recupera un report emozinale relativo alla canzone con l'ID specificato.
     * @param id L'ID della canzone di cui ottenere il report
     * @return Il report corrispondente alla canzone con l'ID specificato, se presente; altrimenti, {@code null}
     */
    public Report getReport(Integer id) {
        Logger.info("ReportService : getReport ");
        ReportEntity report = reportRepository.getReport(id);
        return reportAssembler.toModel(report);
    }

}