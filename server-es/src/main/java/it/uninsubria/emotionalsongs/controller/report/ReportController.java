package it.uninsubria.emotionalsongs.controller.report;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.playlist.Playlist;
import it.uninsubria.emotionalsongs.model.report.Report;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.service.playlist.PlaylistService;
import it.uninsubria.emotionalsongs.service.report.ReportService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReportController extends Controller implements ApiConfig {
    private final ReportService reportService;

    public ReportController() {
        reportService = SharedService.getReportService();
    }

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        String sessionId = super.getSessionId(exchange);
        Logger.info(this.getClass().getSimpleName() + ": " + path + " " + method);

        //if (!sessioneService.hasSessioneAttiva(sessionId)) {
        //  sendResponse(exchange, Costanti.ErrorCode.SESSION_NON_VALIDA, Costanti.ErrorCode.SESSION_NON_VALIDA.getStatusCode());
        //}
        if (ReportApi.GET_REPORT.match(path, method)) {
            Logger.info(this.getClass().getSimpleName() + ": gestisciGetReport");
            Map<String, String> pathVariables = Utils.getPathVariables(ReportApi.GET_REPORT.getPath(), path);
            Integer id = Integer.valueOf(pathVariables.get("id"));
            gestisciGetReport(exchange, id);
        }
        else sendResponse(exchange, Costanti.ErrorCode.PAGE_NOT_FOUND, Costanti.ErrorCode.PAGE_NOT_FOUND.getStatusCode());
    }

    private void gestisciGetReport(HttpExchange exchange, Integer canzoneId) throws IOException {
        Report report = reportService.getReport(canzoneId);
        sendResponse(exchange, report, Costanti.StatusCode.OK);
    }
}
