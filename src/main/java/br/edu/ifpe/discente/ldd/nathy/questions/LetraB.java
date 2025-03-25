package br.edu.ifpe.discente.ldd.nathy.questions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.edu.ifpe.discente.ldd.nathy.entities.category.Categories;
import br.edu.ifpe.discente.ldd.nathy.entities.category.Category;

public class LetraB {

    public static void main(String[] args) {
        try {
            JAXBContext contextCategories = JAXBContext.newInstance(Categories.class);
            Unmarshaller jaxbUnmarshallerCategories = contextCategories.createUnmarshaller();
            JAXBElement<Categories> categoriesXml = (JAXBElement<Categories>) jaxbUnmarshallerCategories
                    .unmarshal(new StreamSource("category.xml"), Categories.class);
            Categories categories = categoriesXml.getValue();

            System.out.println(categories);

            for (Category c : categories.getCategories()) {
                System.out.println(c);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
