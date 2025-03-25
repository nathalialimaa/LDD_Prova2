package br.edu.ifpe.discente.ldd.nathy.questions;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class letraD {
    public static void main(String[] args) {
        try {
            // Caminho do arquivo XML
            File file = new File("category.xml");

            // Caminho para o arquivo HTML de saída
            File htmlFile = new File("resolucaoQuestaoD.html");
            FileWriter htmlWriter = new FileWriter(htmlFile);

            // Cria o XMLInputFactory para ler o XML
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(file));

            // Cria o XMLOutputFactory para gerar o HTML
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(htmlWriter);

            // Configura o escritor para adicionar indentação
            writer.writeStartDocument("UTF-8", "1.0");
            writer.writeCharacters("\n");
            writer.writeStartElement("html");
            writer.writeCharacters("\n\t");
            writer.writeStartElement("body");
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("ol"); // Início da lista ordenada

            // Lê o arquivo XML
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("category")) {
                        // Recupera os atributos id e name
                        Attribute idAttr = startElement.getAttributeByName(new javax.xml.namespace.QName("id"));
                        Attribute nameAttr = startElement.getAttributeByName(new javax.xml.namespace.QName("name"));

                        // Escreve o item da lista em HTML
                        writer.writeCharacters("\n\t\t\t");
                        writer.writeStartElement("li");
                        writer.writeAttribute("id", idAttr.getValue());
                        writer.writeCharacters(nameAttr.getValue());
                        writer.writeEndElement(); // </li>
                    }
                }
            }

            // Fim da lista ordenada e do documento HTML
            writer.writeCharacters("\n\t\t");
            writer.writeEndElement(); // </ol>
            writer.writeCharacters("\n\t");
            writer.writeEndElement(); // </body>
            writer.writeCharacters("\n");
            writer.writeEndElement(); // </html>
            writer.writeEndDocument(); // Finaliza o documento

            writer.flush(); // Garante que tudo seja gravado
            writer.close(); // Fecha o writer
            htmlWriter.close(); // Fecha o FileWriter


        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("HTML gerado com sucesso em: resolucaoQuestaoF.html");
    }
}
