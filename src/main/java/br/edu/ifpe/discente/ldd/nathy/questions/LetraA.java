package br.edu.ifpe.discente.ldd.nathy.questions;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import br.edu.ifpe.discente.ldd.nathy.entities.film.Film;
import br.edu.ifpe.discente.ldd.nathy.entities.film.Films;

public class LetraA {

    public static void main(String[] args) {
        try {
            JAXBContext contextFilms = JAXBContext.newInstance(Films.class);
            Unmarshaller jaxbUnmarshallerFilms = contextFilms.createUnmarshaller();
            JAXBElement<Films> filmsXml = (JAXBElement<Films>) jaxbUnmarshallerFilms
                    .unmarshal(new StreamSource("film.xml"), Films.class);
            Films films = filmsXml.getValue();

            for (Film f : films.getFilm()) {
                System.out.println(f);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
