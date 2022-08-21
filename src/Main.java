
//    ZÁSADNÍ PROBLÉM - JAK ZACHYTIT A SPÍŠE PŘEDEJÍT SITUACI, KDY plantList == null
//    "kvetiny-spatne-datum.txt" --- PROBLÉMEM JE TO, ŽE SE BĚH PROGRAMU ZASEKNE DOKUD MANUÁLNĚ NESHODÍM BĚH PROCEDURY 'Stop Main'

import java.time.LocalDate;
import java.util.ListIterator;

public class Main {

//  public static final String FILENAME = "kvetiny-spatne-frekvence.txt";
//  public static final String FILENAME = "kvetiny-spatne-datum.txt";
    public static final String FILENAME = "kvetiny.txt";

    public static void main(String[] args) {


        System.out.println("************************( S T A R T )************************");
        // Načteme obsah textového souboru (včetně ošetření vzniku možných chyb
        PlantList plantList = null;
        try {
            plantList = PlantList.importFromFile(FILENAME);
        }
        catch (PlantException e) {
            System.err.println("Soubor " + FILENAME + " se nepodařiol správně načíst !\n" + e.getLocalizedMessage());
        }
        System.out.println("************************( KONEC NAČÍTÁNÍ DAT Z TEXTOVÉHO SOUBORU )************************");

        try {
            if (plantList == null) {
                System.err.println("************************( SEZNAM KVĚTIN NENÍ VYTVOŘEN - PROGRAM SKONČÍ )************************");
        }
        else {
            Plant plant_4 = new Plant("První květina", "Poznámka", LocalDate.parse("2022-08-01"), LocalDate.parse("2022-08-03"), 1);
            Plant plant_3 = new Plant("Druhá květina", LocalDate.parse("2022-08-08"), 2);
            Plant plant_1 = new Plant("Třetí květina");
            Plant plant_0 = new Plant("");  // CO S TÍM? teoreticky může být prázdný řetězec správné, logoicky nesmysl
            // Přidání několika objektů do seznamu
            plantList.addPlant(plant_4);
            plantList.addPlant(plant_3);
            plantList.addPlant(plant_1);
            plantList.addPlant(plant_0);
            }
        }
        // Ošetření vyhozených vyjímek které mohly vzniknout v předchozím bloku programového kódu
        // Použil jsem vlastní třídu výjímek
        // Uznávám že popis "Chyby !" není nic moc ....
        catch (PlantException e) {
            System.err.println("Chyby !! " + e.getLocalizedMessage());
        }
        catch (NullPointerException e) {
            System.err.println("Chyby !!!!! " + e.getLocalizedMessage());
        }

        if (plantList != null) {
            for (Plant plant : plantList.getList()) {
                System.out.println( plant.getName() + " " +
                                    plant.getNotes() + " " +
                                    plant.getPlanted() + " " +
                                    plant.getFrequencyOfWatering() + " " +
                                    plant.getWatering()
                                   );
            }
            System.out.println("************************( VYPSÁN ROZŠÍŘENÝ SEZNAM KVĚTIN )************************");

            // Ke zkoušení rušení prvku kolekce (toho posledního bez názvu)
            try {
                int i = plantList.rangeOfPlanList() - 1;
                plantList.removePlant(plantList.getPlantFromIndex(i));
            }
            // Ošetření vyhozených vyjímek které mohly vzniknout v předchozím bloku programového kódu
            // Použil jsem vlastní třídu výjímek
            catch (PlantException e) {
                System.err.println("Chyby! " + e.getLocalizedMessage());
            }

            for (Plant plant : plantList.getList()) {
                System.out.println( plant.getName() + " " +
                                    plant.getNotes() + " " +
                                    plant.getPlanted() + " " +
                                    plant.getFrequencyOfWatering() + " " +
                                    plant.getWatering()
                                   );
            }
            System.out.println("************************( VYPSÁN SEZNAM KVĚTIN PO SMAZÁNÍ POSLEDNÍHHO PRVKU SERZNAMU )************************");

            // Pomocná proměnná
            Plant plant = new Plant("");

            // Zkoušení zadávání indexu mimo rozsah pole
            try {
                // Pomocná proměnná
                // index prvku, který chci z kolekce přečíst (a vypsat na monitor)
                int i = 3;
                plant = plantList.getPlantFromIndex(i);
                System.out.println( "Index prvku: " + i + " Popis prvku: " +
                                    plant.getName() + " " +
                                    plant.getNotes() + " " +
                                    plant.getPlanted() + " " +
                                    plant.getFrequencyOfWatering() + " " +
                                    plant.getWatering()
                                  );

            }
            catch (PlantException | IndexOutOfBoundsException e) {
                System.err.println("Prvek pole se nepodařilo načíst! \n" + e.getLocalizedMessage());
            }
            System.out.println("************************( VYPSÁNÍ JEDNOHO PRVKU ZE SEZNAMU KVĚTIN )************************");

            // KOSTRBATÉ PROVEDENÍ V CYKLU WHILE
            // Pomocná proměnná pro počítadlo v cyklus while
            int i = 0;
            // Výpis listu květin s číslem indexu a datem doporučené zálivky
            while (i < plantList.rangeOfPlanList()) {
                try {
                    plant = plantList.getPlantFromIndex(i);
                    System.out.println(i + 1 + " " + plant.getWateringInfo());
                }
                catch (PlantException e) {
                    System.err.println("Prvek pole se nepodařilo načíst! \n" + e.getLocalizedMessage());
                }
                finally {
                    // Zvyšování počitadla průchodů v cyklu o 1
                    // Sice v případě výjimky by se mělo z cyklu vyskočit ale raději volím tuto cestu jak se nezacyklit
                    i++;
                }
            }
            System.out.println("************************( VYPSÁNÍ DOPORUČENÉHO DATA DALŠÍ ZÁLIVKY - while )************************");

            // SNAD O NĚCO ZAJÍMAVĚJŠÍ PROVEDENÍ PŘES ListIteraror
            for (ListIterator<Plant> it = plantList.getList().listIterator(); it.hasNext(); ) {
                Plant plant2 = it.next();
                System.out.println(" " + plant2.getWateringInfo());
            }
            System.out.println("************************( VYPSÁNÍ DOPORUČENÉHO DATA DALŠÍ ZÁLIVKY - ListIterator )************************");

            // SNAD ELEGANTNĚJŠÍ PROVEDENÍ PŘES CYKLUS FOREACH
            for (Plant plant2 : plantList.getList()) {
                System.out.println(" - " + plant2.getWateringInfo());
            }
            System.out.println("************************( VYPSÁNÍ DOPORUČENÉHO DATA DALŠÍ ZÁLIVKY - foreach )************************");

            // DOPLNĚNO (dle návrhu lektora) - do seznamu různých způsobů výpisu kolekce ještě megakrátký zápis s využítím Stream API:
            plantList.getList().forEach(plant2 -> System.out.println(" -- " + plant2.getWateringInfo()));
            System.out.println("************************( VYPSÁNÍ DOPORUČENÉHO DATA DALŠÍ ZÁLIVKY - Stream API )************************");

        }
    }
}