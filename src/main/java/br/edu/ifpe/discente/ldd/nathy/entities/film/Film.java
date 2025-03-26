package br.edu.ifpe.discente.ldd.nathy.entities.film;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "id", "title",
        "description",
        "releaseYear", "languageId", "rentalDuration",
        "rentalRate", "length", "replacementCost", "rating"
})
public class Film {

    // id="1" title="Academy Dinosaur"
    // description="A Epic Drama of a Feminist And a Mad Scientist who must Battle a
    // Teacher in The Canadian Rockies"
    // release_year="2006" language_id="1" rental_duration="6"
    // rental_rate="0.99" length="86" replacement_cost="20.99" rating="PG"

    @XmlAttribute(name = "id")
    private Integer id;

    @XmlAttribute(name = "title")
    private String title;

    @XmlAttribute(name = "description")
    private String description;

    @XmlAttribute(name = "release_year")
    private Integer releaseYear;

    @XmlAttribute(name = "language_id")
    private String languageId;

    @XmlAttribute(name = "rental_duration")
    private Integer rentalDuration;

    @XmlAttribute(name = "rental_rate")
    private Double rentalRate;

    @XmlAttribute(name = "length")
    private Integer length;

    @XmlAttribute(name = "replacement_cost")
    private Double replacementCost;

    @XmlAttribute(name = "rating")
    private String rating;

    public Film() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(Double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear
                + ", language_id=" + languageId + ", rentalDuration=" + rentalDuration + ", rentalRate=" + rentalRate
                + ", length=" + length + ", replacementCost=" + replacementCost + ", rating=" + rating + "]";
    }

}
