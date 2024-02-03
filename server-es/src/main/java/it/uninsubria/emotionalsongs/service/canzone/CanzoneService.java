package it.uninsubria.emotionalsongs.service.canzone;

import it.uninsubria.emotionalsongs.assembler.canzone.CanzoneAssembler;
import it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity;
import it.uninsubria.emotionalsongs.model.canzone.Canzone;
import it.uninsubria.emotionalsongs.model.pagina.Pagina;
import it.uninsubria.emotionalsongs.repository.canzone.CanzoneRepository;

import java.util.List;
import java.util.Optional;

/**
 * Questa classe fornisce dei servizi per la gestione delle canzoni.
 * @author Erik Gurzau (749400, VA)
 * @author Alessia Metaj (738945, VA)
 * @author Sara Biavaschi (748698, VA)
 * @version 1.0.0
 * @see it.uninsubria.emotionalsongs.assembler.canzone.CanzoneAssembler
 * @see it.uninsubria.emotionalsongs.entity.canzone.CanzoneEntity
 * @see it.uninsubria.emotionalsongs.model.canzone.Canzone
 * @see it.uninsubria.emotionalsongs.model.pagina.Pagina
 * @see it.uninsubria.emotionalsongs.repository.canzone.CanzoneRepository
 */
public class CanzoneService {

    /**
     * Repository delle canzoni.
     */
    private final CanzoneRepository canzoneRepository;

    /**
     * Assemblatore delle canzoni.
     */
    private final CanzoneAssembler canzoneAssembler;

    /**
     * Costruisce un nuovo servizio delle canzoni inizializzando il repository e l'assemblatore.
     */
    public CanzoneService() {
        canzoneRepository = new CanzoneRepository();
        canzoneAssembler = new CanzoneAssembler();
    }

    /**
     * Permette di ottenere una lista paginata di tutte le canzoni.
     * @param pagina La pagina delle canzoni da ottenere.
     * @return Una lista di canzoni nella pagina specificata.
     */
    public List<Canzone> getAll(Pagina<Canzone> pagina) {
        List<CanzoneEntity> canzoniList = canzoneRepository.getAll(pagina.getNumeroPagina(), pagina.getDimensionePagina());
        return canzoneAssembler.toModel(canzoniList);
    }

    /**
     * Recupera una canzone tramite il suo ID.
     * @param id L'ID della canzone da recuperare.
     * @return La canzone corrispondente all'ID specificato, se presente; altrimenti, null.
     */
    public Canzone getCanzoneById(Integer id) {
        Optional<CanzoneEntity> optionalUtente = canzoneRepository.findById(id);
        return optionalUtente.isPresent() ? canzoneAssembler.toModel(optionalUtente.get()) : null;
    }

    /**
     * Recupera una lista di canzoni tramite il loro titolo.
     * @param ricerca Il titolo della canzone da cercare.
     * @return Una lista di canzoni con il titolo specificato.
     */
    public List<Canzone> getCanzoneByTitolo(String ricerca) {
        List<CanzoneEntity> canzoni = canzoneRepository.findByTitolo(ricerca);
        return canzoneAssembler.toModel(canzoni);
    }

    /**
     * Recupera una lista di canzoni tramite il loro autore e anno di pubblicazione.
     * @param autore L'autore della canzone da cercare.
     * @param anno   L'anno di pubblicazione della canzone da cercare.
     * @return La lista di canzoni relative  all'autore e all'anno specificati.
     */
    public List<Canzone> getCanzoneByAutoreAnno(String autore, Integer anno) {
        List<CanzoneEntity> canzoni = canzoneRepository.findByAutoreAnno(autore, anno);
        return canzoneAssembler.toModel(canzoni);
    }

    /**
     * Fornisce il numero totale di canzoni presenti.
     * @return Il numero totale di canzoni nel repository.
     */
    public Integer getTotaleCanzoni() {
        return canzoneRepository.getTotaleCanzoni();
    }
}
