package br.edu.ifpe.discente.ldd.nathy.questions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.edu.ifpe.discente.ldd.nathy.entities.filmCategory.FilmCategories;
import br.edu.ifpe.discente.ldd.nathy.entities.filmCategory.FilmCategory;

public class LetraC {

    public static void main(String[] args) {
        try {
            JAXBContext contextFilmCategories = JAXBContext.newInstance(FilmCategories.class);
            Unmarshaller jaxbUnmarshallerFilmCategories = contextFilmCategories.createUnmarshaller();
            JAXBElement<FilmCategories> filmCategoriesXml = (JAXBElement<FilmCategories>) jaxbUnmarshallerFilmCategories
                    .unmarshal(new StreamSource("film_category.xml"), FilmCategories.class);
            FilmCategories filmCategories = filmCategoriesXml.getValue();

            // System.out.println(filmCategories);

            for (FilmCategory fc : filmCategories.getFilmCategory()) {
                System.out.println(fc);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
