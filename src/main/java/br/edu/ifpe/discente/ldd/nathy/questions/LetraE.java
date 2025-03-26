package br.edu.ifpe.discente.ldd.nathy.questions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LetraE extends DefaultHandler {

    private Map<Integer, Integer> categoryFilmCount = new HashMap<>();
    private Map<Integer, String> categoryNames = new HashMap<>();
    private boolean isCategory = false;
    private boolean isFilmCategory = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("category")) {
            setCategory(true);
            String id = attributes.getValue("id");
            String name = attributes.getValue("name");
            categoryNames.put(Integer.parseInt(id), name);
        } else if (qName.equalsIgnoreCase("film_category")) {
            isFilmCategory = true;
            String categoryId = attributes.getValue("category_id");

            // Agora incrementa o contador de filmes para a categoria
            int categoryIdInt = Integer.parseInt(categoryId);
            categoryFilmCount.put(categoryIdInt, categoryFilmCount.getOrDefault(categoryIdInt, 0) + 1);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("film_category") && isFilmCategory) {
            // Quando encontramos uma tag <film_category>, contamos um filme para a
            // categoria associada
            isFilmCategory = false;
        }
    }

    public boolean isCategory() {
        return isCategory;
    }

    public void setCategory(boolean isCategory) {
        this.isCategory = isCategory;
    }

    public Map<Integer, Integer> getCategoryFilmCount() {
        return categoryFilmCount;
    }

    public Map<Integer, String> getCategoryNames() {
        return categoryNames;
    }

    public static void main(String[] args) {
        try {
            // Criando o parser SAX
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            // Criando o handler para processar o XML
            LetraE handler = new LetraE();

            // Caminho para o arquivo XML de categorias e film_categories
            File categoriesFile = new File("category.xml");
            File filmCategoriesFile = new File("film_category.xml");

            // Parse para o primeiro arquivo (categories.xml)
            saxParser.parse(categoriesFile, handler);
            // Parse para o segundo arquivo (film_category.xml)
            saxParser.parse(filmCategoriesFile, handler);

            // Obtendo os dados
            Map<Integer, Integer> filmCounts = handler.getCategoryFilmCount();
            Map<Integer, String> categoryNames = handler.getCategoryNames();

            // Gerando o HTML com a tabela
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<html>\n")
                    .append("<head><title>Letra E</title></head>\n")
                    .append("<body>\n")
                    .append("<table border='1'>\n")
                    .append("<thead>\n")
                    .append("<tr>\n")
                    .append("<th>Categoria</th>\n")
                    .append("<th>Quantidade</th>\n")
                    .append("</tr>\n")
                    .append("</thead>\n")
                    .append("<tbody>\n");

            for (Integer categoryId : categoryNames.keySet()) {
                String categoryName = categoryNames.get(categoryId);
                int filmCount = filmCounts.getOrDefault(categoryId, 0);
                htmlContent.append("<tr>\n")
                        .append("<td>").append(categoryName).append("</td>\n")
                        .append("<td>").append(filmCount).append("</td>\n")
                        .append("</tr>\n");
            }

            htmlContent.append("</tbody>\n")
                    .append("</table>\n")
                    .append("</body>\n")
                    .append("</html>");

            // Escrevendo o HTML em um arquivo
            try (FileWriter writer = new FileWriter("src/main/java/br/edu/ifpe/discente/ldd/nathy/html/letraE.html")) {
                writer.write(htmlContent.toString());
                System.out.println("Arquivo HTML gerado com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao escrever o arquivo: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
