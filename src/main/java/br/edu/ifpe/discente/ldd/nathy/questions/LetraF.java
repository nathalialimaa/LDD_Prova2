package br.edu.ifpe.discente.ldd.nathy.questions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class LetraF {

    public static void main(String[] args) {
        try {
            // Parse dos arquivos XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Carregar os arquivos XML de filmes e categorias
            Document filmDocument = builder.parse(new File("film.xml"));
            Document categoryDocument = builder.parse(new File("category.xml"));
            Document filmCategoryDocument = builder.parse(new File("film_category.xml"));

            // Obter todos os filmes e suas informações
            NodeList films = filmDocument.getElementsByTagName("film");
            NodeList categories = categoryDocument.getElementsByTagName("category");
            NodeList filmCategories = filmCategoryDocument.getElementsByTagName("film_category");

            // Map para associar o id da categoria com o nome da categoria
            Map<Integer, String> categoryMap = new HashMap<>();
            // Map para associar o id do filme com suas categorias
            Map<Integer, List<Integer>> filmToCategories = new HashMap<>();

            // Preencher o map de categorias
            for (int i = 0; i < categories.getLength(); i++) {
                Element categoryElement = (Element) categories.item(i);
                int categoryId = Integer.parseInt(categoryElement.getAttribute("id"));
                String categoryName = categoryElement.getAttribute("name");
                categoryMap.put(categoryId, categoryName);
            }

            // Preencher o map de filmes e suas categorias
            for (int i = 0; i < filmCategories.getLength(); i++) {
                Element filmCategoryElement = (Element) filmCategories.item(i);
                int filmId = Integer.parseInt(filmCategoryElement.getAttribute("film_id"));
                int categoryId = Integer.parseInt(filmCategoryElement.getAttribute("category_id"));

                // Relaciona o filme com sua(s) categoria(s)
                filmToCategories
                        .computeIfAbsent(filmId, k -> new ArrayList<>())
                        .add(categoryId);
            }

            // Gerar o conteúdo HTML para a tabela
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<html>\n")
                    .append("<head><title>Filmes e Categorias</title></head>\n")
                    .append("<body>\n")
                    .append("<table border='1'>\n")
                    .append("<thead>\n")
                    .append("<tr>\n")
                    .append("<th>Filme</th>\n")
                    .append("<th>Categoria</th>\n")
                    .append("</tr>\n")
                    .append("</thead>\n")
                    .append("<tbody>\n");

            // Para cada filme no arquivo films.xml, obtém suas categorias associadas
            for (int i = 0; i < films.getLength(); i++) {
                Element filmElement = (Element) films.item(i);
                int filmId = Integer.parseInt(filmElement.getAttribute("id"));
                String filmTitle = filmElement.getAttribute("title");

                // Obter as categorias do filme a partir do map de relacionamentos
                List<Integer> categoryIds = filmToCategories.getOrDefault(filmId, Collections.emptyList());
                for (int categoryId : categoryIds) {
                    String categoryName = categoryMap.getOrDefault(categoryId, "Unknown Category");

                    // Gerar uma linha da tabela para cada filme e categoria
                    htmlContent.append("<tr>\n")
                            .append("<td>").append(filmTitle).append("</td>\n")
                            .append("<td>").append(categoryName).append("</td>\n")
                            .append("</tr>\n");
                }
            }

            htmlContent.append("</tbody>\n")
                    .append("</table>\n")
                    .append("</body>\n")
                    .append("</html>");

            // Escrever o conteúdo HTML gerado em um arquivo
            try (FileWriter writer = new FileWriter("src/main/java/br/edu/ifpe/discente/ldd/nathy/html/letraF.html")) {
                writer.write(htmlContent.toString());
                System.out.println("Arquivo HTML gerado com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao escrever o arquivo HTML: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}