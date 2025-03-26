package br.edu.ifpe.discente.ldd.nathy.entities.category;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class Categories {

    @XmlElement(name = "category")
    private List<Category> categories = new ArrayList<>();

    public Categories() {
    }

    public Categories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return String.format("Categories: (%s)", categories);
    }

}
