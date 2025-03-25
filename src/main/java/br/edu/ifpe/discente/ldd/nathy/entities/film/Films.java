package br.edu.ifpe.discente.ldd.nathy.entities.film;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "films")
@XmlAccessorType(XmlAccessType.FIELD)
public class Films {

    @XmlElement(name = "film")
    private List<Film> film = new ArrayList<>();

    public Films() {
    }

    public List<Film> getFilm() {
        return film;
    }

    public void setFilm(List<Film> film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return String.format("Films: (%s)", film);
    }

}
