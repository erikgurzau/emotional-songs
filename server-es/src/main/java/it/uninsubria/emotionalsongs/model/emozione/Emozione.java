package it.uninsubria.emotionalsongs.model.emozione;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class Emozione implements Serializable {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("idAssegnazione")
        private Integer id_assegnazione;

        @JsonProperty("idStatoEmozionale")
        private Integer id_stato_emozionale;

        @JsonProperty("intensita")
        private Integer intensita;

        @JsonProperty("nota")
        private String nota;

        public Emozione(){}

        public Emozione(Integer id, Integer id_assegnazione, Integer id_stato_emozionale, Integer intensita, String nota){

            this.id=id;
            this.id_assegnazione = id_assegnazione;
            this.id_stato_emozionale=id_stato_emozionale;
            this.intensita=intensita;
            this.nota=nota;

        }


        public void setId(Integer id){
            this.id = id;
        }

        public Integer getId(){return id;}

        public Integer getIdAssegnazione() {
            return id_assegnazione;
        }

        public void setIdAssegnazione(Integer id_assegnazione) {
            this.id_assegnazione = id_assegnazione;
        }

        public Integer getIdStatoEmozionale() {
            return id_stato_emozionale;
        }

        public void setIdStatoEmozionaleEntity(Integer id_stato_emozionale) {
            this.id_stato_emozionale = id_stato_emozionale;
        }

        public Integer getIntensita() {
            return intensita;
        }

        public void setIntensita(Integer intensita) {
            this.intensita = intensita;
        }

        public String getNota() {
            return nota;
        }

        public void setNota(String nota) {
            this.nota = nota;
        }

    }
