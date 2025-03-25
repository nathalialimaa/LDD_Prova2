package br.edu.ifpe.discente.ldd.nathy.questions;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.FileWriter;

public class LetraE {

    public static void main(String[] args) {
        try {
            // Usar TreeMap para manter as categorias em ordem alfabética automaticamente
            Map<String, Integer> categoryCount = new TreeMap<>();
            Map<String, String> categoryNames = new HashMap<>();

            // Leitura do category.xml para associar id ao nome das categorias
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader categoryReader = saxParser.getXMLReader();

            categoryReader.setContentHandler(new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equals("category")) {
                        String id = attributes.getValue("id");
                        String name = attributes.getValue("name");
                        categoryNames.put(id, name); // Mapeia id -> nome
                    }
                }
            });
            categoryReader.parse("category.xml");

            // Inicializa contagem com 0 para cada categoria no TreeMap
            categoryNames.values().forEach(name -> categoryCount.put(name, 0));

            // Leitura do film_category.xml para contar os filmes por categoria
            XMLReader filmCategoryReader = saxParser.getXMLReader();
            filmCategoryReader.setContentHandler(new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    if (qName.equals("film_category")) {
                        String categoryId = attributes.getValue("category_id");
                        String categoryName = categoryNames.get(categoryId);
                        categoryCount.put(categoryName, categoryCount.get(categoryName) + 1); // Incrementa a contagem
                    }
                }
            });
            filmCategoryReader.parse("film_category.xml");

            // Gerar HTML para a tabela
            try (FileWriter htmlWriter = new FileWriter("resolucaoQuestaoE.html")) {
                htmlWriter.write("<html>\n");
                htmlWriter.write("<head>\n<title>Relatório de Categorias</title>\n</head>\n");
                htmlWriter.write("<body>\n");
                htmlWriter.write("<table border=\"1\">\n");
                htmlWriter.write("\t<thead>\n");
                htmlWriter.write("\t\t<tr>\n");
                htmlWriter.write("\t\t\t<th>Categoria</th>\n");
                htmlWriter.write("\t\t\t<th>Quantidade</th>\n");
                htmlWriter.write("\t\t</tr>\n");
                htmlWriter.write("\t</thead>\n");
                htmlWriter.write("\t<tbody>\n");

                for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
                    htmlWriter.write("\t\t<tr>\n");
                    htmlWriter.write("\t\t\t<td>" + entry.getKey() + "</td>\n");
                    htmlWriter.write("\t\t\t<td>" + entry.getValue() + "</td>\n");
                    htmlWriter.write("\t\t</tr>\n");
                }

                htmlWriter.write("\t</tbody>\n");
                htmlWriter.write("</table>\n");
                htmlWriter.write("</body>\n");
                htmlWriter.write("</html>\n");
            }

            System.out.println("HTML gerado com sucesso em: resolucaoQuestaoE.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
