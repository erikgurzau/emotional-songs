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

public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportAssembler reportAssembler;

    public ReportService() {
        reportRepository = new ReportRepository();
        reportAssembler = new ReportAssembler();
    }

    public Report getReport(Integer id) {
        Logger.info("ReportService : getReport ");
        ReportEntity report = reportRepository.getReport(id);
        return reportAssembler.toModel(report);
    }

}
