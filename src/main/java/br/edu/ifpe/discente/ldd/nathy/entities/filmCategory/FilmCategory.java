package br.edu.ifpe.discente.ldd.nathy.entities.filmCategory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "filmId", "categoryId" })
public class FilmCategory {

    @XmlAttribute(name = "film_id")
    private Integer filmId;

    @XmlAttribute(name = "category_id")
    private Integer categoryId;

    public FilmCategory() {
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "FilmCategory [filmId=" + filmId + ", categoryId=" + categoryId + "]";
    }

}
