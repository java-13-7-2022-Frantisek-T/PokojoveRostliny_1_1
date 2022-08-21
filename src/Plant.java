/*
Připravte třídu pro ukládání informací o pokojových rostlinách (plant).
U každé rostliny budeme mít uložen:
    - název (name),
    - poznámky (notes),
    - datum, kdy byly zasazena (planted),
    - datum poslední zálivky (watering)
    - běžnou frekvenci zálivky ve dnech (frequency of watering)
Vytvořte tři konstruktory:
    - jeden pro nastavení všech atributů
    - druhý nastaví jako poznámku prázdný řetězec a datum poslední zálivky nastaví na dnešní datum.
    - třetí nastaví totéž co druhý a navíc výchozí frekvenci zálivky na 7 dnů a datum zasazení na dnešní datum. (Uživatel tedy bude zadávat pouze název rostliny.)
Vytvořte výchozí přístupové metody pro všechny atributy.
Připravte metodu getWateringInfo(), která vrátí
    - název květiny,
    - datum poslední zálivky
    - datum doporučené další zálivky.
    (Metoda vrátí textový řetězec, obsahující požadované informace.)
*/

/*
Ošetři zadávání frekvence zálivky — pokud je parametrem 0 nebo záporné číslo, systém vyhodí výjimku třídy PlantException s vhodným popisem.
Obdobně ošetřete zadávání data poslední zálivky — nesmí být starší než datum zasazení rostliny.
 */

import java.time.LocalDate;

public class Plant {

    // Atributy třídy Plant
    // (jsou deklarovány jako soukromé, tudíž se obsluhují výhradně přes metody třídy Plant, do které patří)
    private String      name;                   // název rostliny
    private String      notes;                  // poznámky
    private LocalDate   planted;                // datum kdy byla zasazena
    private LocalDate   watering;               // datum poslední zálivky
    private int         frequencyOfWatering;    // běžná frekvence zálivky


    // Konstruktor třídy Plant
    // který provede nastavení všech atributů třídy Plant podle uživatelem zadaných hodnot
    public Plant (String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {

        this ( name, planted, frequencyOfWatering );
        // Ošetření případných chyb v zadání některých parametrů konstruktoru
        try {
            if (frequencyOfWatering<=0) {
                throw new PlantException (" ** Chybné zadání běžné frekvence zálivky (" + frequencyOfWatering + ") - musí se jednat o kladné číslo ! \n");
            }
            if ((planted.compareTo(watering)) > 0) {
                throw new PlantException (" ** Datum poslední zálivky (" + watering+ ") nesmí být dřívější jak datum zasazení (" + planted + ") ! \n");
            }
            this.notes = notes;
            this.watering = watering;
        }
        catch (PlantException e) {
            throw new PlantException (" -- konstruktor " +Plant.class.getName()+ " ukončen při výskytu chyby -- " + e.getLocalizedMessage() + "\n");
        }
    }

    // Konstruktor třídy Plant
    // který provede nastavení
    //  - poznámky coby prázdného řetězce
    //  - datum poslední zálivky nastaví na dnešní datum
    // (Uživatel tedy bude zadávat název rostliny, datum zasazení a výchozí frekvenci zálivky - viz parametry tohoto konstruktoru)
    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantException {

        this (name);
        // Ošetření případných chyb v zadání některých parametrů konstruktoru
        try {
            if (frequencyOfWatering<=0) {
                throw new PlantException (" * Chybné zadání běžné frekvence zálivky (" + frequencyOfWatering + ") - musí se jednat o kladné číslo ! \n");
            }
            if ((planted.compareTo(LocalDate.now())) >0) {
                throw new PlantException (" * Datum poslední zálivky (" + LocalDate.now() + ") nesmí být dřívější jak datum zasazení (" + planted + ") ! \n");
            }
            this.planted = planted;
            this.frequencyOfWatering = frequencyOfWatering;
        }
        catch (PlantException e) {
            throw new PlantException (" - konstruktor ukončen při výskytu chyby - " + e.getLocalizedMessage() + "\n");
        }
    }

    // Konstruktor třídy Plant
    // který provede nastavení
    //  - poznámky coby prázdného řetězce
    //  - datum zasazení nastaví na dnešní datum
    //  - datum poslední zálivky nastaví na dnešní datum
    //  - výchozí frekvenci zálivky nastaví na 7 dnů
    // (Uživatel tedy bude zadávat pouze název rostliny - což je jediným parametrem tohoto konstruktoru.)
    public Plant(String name) {
        // Zde není ošetření případných chyb v zadání některých parametrů konstruktoru provedeno
        // - tudíž jméno květiny může být i prázdný řetězec
        this.name = name;
        this.notes = "";
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = 7;
    }


    // Výchozí přístupová metoda pro předání atributu name.
    public String getName() {
        return name;
    }

    // Výchozí přístupová metoda pro nastavení atributu name.
    public void setName(String name) {
        this.name = name;
    }

    // Výchozí přístupová metoda pro předání atributu notes.
    public String getNotes() {
        return notes;
    }

    // Výchozí přístupová metoda pro nastavení atributu notes
    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Výchozí přístupová metoda pro předání atributu planted.
    public LocalDate getPlanted() {
        return planted;
    }

    // Výchozí přístupová metoda pro nastavení atributu planted.
    public void setPlanted(LocalDate planted) {
        this.planted = planted; }

    // Výchozí přístupová metoda pro předání atributu watering.
    public LocalDate getWatering() { return watering; }

    // Výchozí přístupová metoda pro nastavení atributu watering.
    public void setWatering(LocalDate watering) {
        this.watering = watering;
    }

    // Výchozí přístupová metoda pro předání atributu frekvencyOfWatering.
    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    // Výchozí přístupová metoda pro nastavení atributu frekvencyOfWatering.
    public void setFrequencyOfWatering(int frequencyOfWatering) {
        this.frequencyOfWatering = frequencyOfWatering;
    }

    // Metoda getWateringInfo(), která vrátí název květiny, datum poslední zálivky a datum doporučené další zálivky.
    // (Metoda vrátí textový řetězec, obsahující požadované informace)
    // OPRAVENO - Je to instanční metoda, takže ji stejně vždycky voláš na nějakou rostlinu. Stačilo by tedy nechat ji bez parametrů:
    public String getWateringInfo(){
        String      nameOfPlant     =   getName();
        LocalDate   lastWatering    =   getWatering();
        LocalDate   nextWatering    =   getWatering().plusDays(getFrequencyOfWatering());
        return  nameOfPlant + " " + lastWatering + " " + nextWatering;
    }
}

