package it.uninsubria.emotionalsongs.controller.report;

import com.sun.net.httpserver.HttpExchange;
import it.uninsubria.emotionalsongs.config.ApiConfig;
import it.uninsubria.emotionalsongs.controller.Controller;
import it.uninsubria.emotionalsongs.model.report.Report;
import it.uninsubria.emotionalsongs.service.SharedService;
import it.uninsubria.emotionalsongs.service.report.ReportService;
import it.uninsubria.emotionalsongs.service.sessione.SessioneService;
import it.uninsubria.emotionalsongs.utils.Costanti;
import it.uninsubria.emotionalsongs.utils.Logger;
import it.uninsubria.emotionalsongs.utils.Utils;

import java.io.IOException;
import java.util.Map;

/**
 * Questa classe è responsabile della gestione delle richieste HTTP relative alle playlist.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 2.0.0
 * @see it.uninsubria.emotionalsongs.config.ApiConfig
 * @see it.uninsubria.emotionalsongs.controller.Controller
 * @see it.uninsubria.emotionalsongs.model.report.Report
 * @see it.uninsubria.emotionalsongs.service.SharedService
 * @see it.uninsubria.emotionalsongs.service.report.ReportService
 * @see it.uninsubria.emotionalsongs.service.sessione.SessioneService
 * @see it.uninsubria.emotionalsongs.utils.Costanti
 * @see it.uninsubria.emotionalsongs.utils.Logger
 * @see it.uninsubria.emotionalsongs.utils.Utils
 */
public class ReportController extends Controller implements ApiConfig {

    /**
     * Istanza del servizio relativo ai report emozionali.
     */
    private final ReportService reportService;

    /**
     * Istanza del servizio di sessione.
     */
    private final SessioneService sessioneService;

    /**
     * Costruttore della classe.
     */
    public ReportController() {
        reportService = SharedService.getReportService();
        sessioneService = SharedService.getSessioneService();
    }

    /**
     * Seleziona il metodo specifico che deve gestire la richiesta HTTP.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @throws IOException se occorrono errori di tipo I/O
     */
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

    /**
     * Gestisce la richiesta di visualizzazione del report emozionale relativo alla canzone con l'ID fornito.
     * @param exchange Oggetto contenente la richiesta HTTP del client e usato per inviare la risposta
     * @param canzoneId L'ID della canzone
     */
    private void gestisciGetReport(HttpExchange exchange, Integer canzoneId) throws IOException {
        Report report = reportService.getReport(canzoneId);
        sendResponse(exchange, report, Costanti.StatusCode.OK);
    }

}