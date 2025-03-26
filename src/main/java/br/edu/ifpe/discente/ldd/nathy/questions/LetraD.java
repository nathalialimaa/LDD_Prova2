package br.edu.ifpe.discente.ldd.nathy.questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class LetraD {

    public static void main(String[] args) {
        try {
            // lendo o documento
            XMLInputFactory xmlif = XMLInputFactory.newFactory();
            XMLStreamReader xsr = xmlif.createXMLStreamReader(new FileReader("category.xml"));

            // escrita do documento
            XMLOutputFactory xmlof = XMLOutputFactory.newFactory();
            XMLStreamWriter xsw = xmlof.createXMLStreamWriter(
                    new FileWriter("src/main/java/br/edu/ifpe/discente/ldd/nathy/html/letraD.html"));

            xsw.writeStartDocument();
            xsw.writeStartElement("html");
            xsw.writeStartElement("head");
            xsw.writeStartElement("title");
            xsw.writeCharacters("Letra D");
            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.writeStartElement("body");
            xsw.writeStartElement("ol");

            while (xsr.hasNext()) {
                switch (xsr.next()) {
                    case XMLStreamReader.START_ELEMENT:
                        String qName = xsr.getLocalName();
                        if (qName.equals("category")) {
                            Integer id = Integer.parseInt(xsr.getAttributeValue(null, "id"));
                            String name = xsr.getAttributeValue(null, "name");

                            xsw.writeStartElement("li");
                            xsw.writeAttribute("id", id.toString());
                            xsw.writeCharacters(name);
                            xsw.writeEndElement();
                        }
                        break;

                    default:
                        break;
                }
            }

            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.writeEndElement();
            xsw.writeEndDocument();

            xsr.close();
            xsw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (XMLStreamException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
