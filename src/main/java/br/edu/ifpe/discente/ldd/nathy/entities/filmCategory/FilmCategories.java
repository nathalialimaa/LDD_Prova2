package br.edu.ifpe.discente.ldd.nathy.entities.filmCategory;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "film_categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class FilmCategories {

    @XmlElement(name = "film_category")
    private List<FilmCategory> filmCategory = new ArrayList<>();

    public FilmCategories() {
    }

    public List<FilmCategory> getFilmCategory() {
        return filmCategory;
    }

    public void setFilmCategory(List<FilmCategory> filmCategory) {
        this.filmCategory = filmCategory;
    }

    @Override
    public String toString() {
        return String.format("Film Categories: (%s)", filmCategory);
    }
}
