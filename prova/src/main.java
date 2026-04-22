import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.management.StringValueExp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {

    static JSONArray series = null;
    static JSONObject idiomes = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(new FileReader("src/tvs.json"));
        } catch (IOException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        series = (JSONArray) obj;
        Object obj2 = null;
        try {
            obj2 = parser.parse(new FileReader("src/languages.json"));
        } catch (IOException | ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        idiomes = (JSONObject) obj2;
        main p = new main();
        p.menu();
    }

    public void menu() {
        int opcio = -1;
        while (opcio != 0) {
            System.out.println("\n=== MENU ===");
            System.out.println("2. Total de series");
            System.out.println("3. Noms i noms originals");
            System.out.println("4. Idiomes originals");
            System.out.println("5. Països d'origen");
            System.out.println("6. Gèneres");
            System.out.println("7. Info Breaking Bad");
            System.out.println("8. Millor serie per puntuació");
            System.out.println("9. Pitjor serie per puntuació");
            System.out.println("10. Número de series en producció");
            System.out.println("11. Canvia com es desen els idiomes originals");
            System.out.println("12. Canvia idiomes en fitxer");
            System.out.println("0. Sortir");
            System.out.print("Tria: ");
            opcio = Integer.parseInt(sc.nextLine());

            switch (opcio) {
                case 2:
                    opcio2();
                    break;
                case 3:
                    opcio3();
                    break;
                case 4:
                    opcio4();
                    break;
                case 5:
                    opcio5();
                    break;
                case 6:
                    opcio6();
                    break;
                case 7:
                    opcio7();
                    break;
                case 8:
                    opcio8();
                    break;
                case 9:
                    opcio9();
                    break;
                case 10:
                    opcio10();
                    break;
                case 11:
                    opcio11();
                    break;
                case 12:
                    opcio12();
                    break;
                case 0:
                    System.out.println("Sortint...");
                    break;
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
        }
    }

    public void opcio2() {
        int num = series.size();
        System.out.println("Total de series: " + num);
    }

    public void opcio3() {
        for (Object c : series) {
            JSONObject s = (JSONObject) c;
            String name = (String) s.get("name");
            String originalName = (String) s.get("original_name");
            System.out.println("Nom: " + name + " Nom original: " + originalName);
        }
    }

    public void opcio4() {
        for (Object c : series) {
            JSONObject s = (JSONObject) c;
            String originalLang = (String) s.get("original_language");
            System.out.println("Idioma original: " + originalLang);
        }
    }

    public void opcio5() {
        for (Object c : series) {
            JSONObject s = (JSONObject) c;
            JSONArray OriginCountry = (JSONArray) s.get("origin_country");
            for (Object pais : OriginCountry) {
                System.out.println("Pais d'origen: " + pais);
            }
        }
    }

    public void opcio6() {
        for (Object c : series) {
            JSONObject s = (JSONObject) c;
            JSONArray generes = (JSONArray) s.get("genres");
            System.out.print("Generes: ");
            for (Object gen : generes) {
                JSONObject g = (JSONObject) gen;
                System.out.print(g.get("name") + " ");
            }
            System.out.println();
        }
    }

    public void opcio7() {
        for (Object c : series) {
            JSONObject s = (JSONObject) c;
            String name = (String) s.get("name");
            if (name.equals("Breaking Bad")) {
                String originalName = (String) s.get("original_name");
                System.out.println("Nom: " + name + " Nom original: " + originalName);
                String originalLang = (String) s.get("original_language");
                System.out.println("Idioma original: " + originalLang);
                JSONArray OriginCountry = (JSONArray) s.get("origin_country");
                for (Object pais : OriginCountry) {
                    System.out.println("Pais d'origen: " + pais);
                }
                JSONArray generes = (JSONArray) s.get("genres");
                System.out.print("Generes: ");
                for (Object gen : generes) {
                    JSONObject g = (JSONObject) gen;
                    System.out.print(g.get("name") + " ");
                }
                System.out.println();
                String overView = (String) s.get("overview");
                System.out.println(overView);
                double voteAverage = ((Number) s.get("vote_average")).doubleValue();
                System.out.println("Puntuació: " + voteAverage);
            }
        }
    }

    public void opcio8() {
        double max = 0;
        double voteAverage = 0;
        String name = "";
        for (Object c : series) {

            JSONObject s = (JSONObject) c;
            voteAverage = ((Number) s.get("vote_average")).doubleValue();
            if (voteAverage > max) {
                max = voteAverage;
                name = (String) s.get("name");
            }

        }
        System.out.println("Puntuació mes gran: " + max + " de la serie " + name);
    }

    public void opcio9() {
        double min = 10;
        double voteAverage = 0;
        String name = "";
        for (Object c : series) {

            JSONObject s = (JSONObject) c;
            voteAverage = ((Number) s.get("vote_average")).doubleValue();
            if (voteAverage < min) {
                min = voteAverage;
                name = (String) s.get("name");
            }

        }
        System.out.println("Puntuació mes petita: " + min + " de la serie " + name);
    }

    public void opcio10() {
        int num = 0;
        boolean producció = false;

        for (Object c : series) {

            JSONObject s = (JSONObject) c;
            producció = ((Boolean) s.get("in_production")).booleanValue();
            if (producció) {
                num++;
            }

        }
        System.out.println("Hi ha " + num + " series en producció");
    }

    public void opcio11(){
        String lang ="";
         for (Object c : series) {
            JSONObject s = (JSONObject) c;
            lang = ((String) (String) s.get("original_language"));
           String nomIdioma = (String) idiomes.get(lang)   ;
           if(nomIdioma!=null){
            s.put("original_language", nomIdioma);
           
           }
        }
        System.out.println("Fet!");

        }
           public void opcio12(){
    

        }
    }
