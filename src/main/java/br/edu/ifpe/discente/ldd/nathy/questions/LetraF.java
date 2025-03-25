package br.edu.ifpe.discente.ldd.nathy.questions;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;

public class LetraF {
    public static void main(String[] args) {
        try {
            // Usar TreeMap para manter as categorias e os filmes em ordem alfabética automaticamente
            Map<String, String> categoryMap = new TreeMap<>();
            Map<String, String> filmMap = new TreeMap<>();
            Map<String, String> filmCategoryMap = new TreeMap<>();

            // 1. Ler category.xml para preencher o Map categoryMap
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document categoryDoc = builder.parse(new File("category.xml"));
            NodeList categories = categoryDoc.getElementsByTagName("category");
            for (int i = 0; i < categories.getLength(); i++) {
                Element category = (Element) categories.item(i);
                String id = category.getAttribute("id");
                String name = category.getAttribute("name");
                categoryMap.put(id, name);
            }

            // 2. Ler film.xml para preencher o Map filmMap
            Document filmDoc = builder.parse(new File("film.xml"));
            NodeList films = filmDoc.getElementsByTagName("film");
            for (int i = 0; i < films.getLength(); i++) {
                Element film = (Element) films.item(i);
                String id = film.getAttribute("id");
                String title = film.getAttribute("title");
                filmMap.put(id, title);
            }

            // 3. Ler film_category.xml para preencher o Map filmCategoryMap
            Document filmCategoryDoc = builder.parse(new File("film_category.xml"));
            NodeList filmCategories = filmCategoryDoc.getElementsByTagName("film_category");
            for (int i = 0; i < filmCategories.getLength(); i++) {
                Element filmCategory = (Element) filmCategories.item(i);
                String filmId = filmCategory.getAttribute("film_id");
                String categoryId = filmCategory.getAttribute("category_id");
                filmCategoryMap.put(filmId, categoryId);
            }

            // 4. Gerar o HTML em ordem alfabética
            try (FileWriter htmlWriter = new FileWriter("resolucaoQuestaoF.html")) {
                htmlWriter.write("<html>\n");
                htmlWriter.write("<head>\n<title>Relatório de Filmes e Categorias</title>\n</head>\n");
                htmlWriter.write("<body>\n");
                htmlWriter.write("<table border=\"1\">\n");
                htmlWriter.write("\t<thead>\n");
                htmlWriter.write("\t\t<tr>\n");
                htmlWriter.write("\t\t\t<th>Filme</th>\n");
                htmlWriter.write("\t\t\t<th>Categoria</th>\n");
                htmlWriter.write("\t\t</tr>\n");
                htmlWriter.write("\t</thead>\n");
                htmlWriter.write("\t<tbody>\n");

                for (Map.Entry<String, String> entry : filmCategoryMap.entrySet()) {
                    String filmId = entry.getKey();
                    String categoryId = entry.getValue();
                    String filmName = filmMap.get(filmId);
                    String categoryName = categoryMap.get(categoryId);

                    htmlWriter.write("\t\t<tr>\n");
                    htmlWriter.write("\t\t\t<td>" + filmName + "</td>\n");
                    htmlWriter.write("\t\t\t<td>" + categoryName + "</td>\n");
                    htmlWriter.write("\t\t</tr>\n");
                }

                htmlWriter.write("\t</tbody>\n");
                htmlWriter.write("</table>\n");
                htmlWriter.write("</body>\n");
                htmlWriter.write("</html>\n");
            }

            System.out.println("HTML gerado com sucesso em: resolucaoQuestaoF.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
