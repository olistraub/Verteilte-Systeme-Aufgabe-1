/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoverleih.soap.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import webservice.Fahrzeug;
import webservice.Kunde;
import webservice.Leihvertrag;
import webservice.SoapService;
import webservice.SoapServiceService;

/**
 *
 * @author OL_SR
 */
public class AutoverleihSOAPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, DatatypeConfigurationException, ParseException  {
        // TODO code application logic here
        SoapServiceService soap = new SoapServiceService();
        SoapService service = soap.getSoapServicePort();
        
        System.out.println("         .----.\n" +
                            "      _.'__    `.\n" +
                            "  .--(#)(##)---/#\\\n" +
                            ".' @          /###\\\n" +
                            ":         ,   #####\n" +
                            " `-..__.-' _.-\\###/\n" +
                            "        `;_:    `\"'\n" +
                            "     .'\"\"\"\"\"`.\n" +
                            "    /,  JOE  ,\\\n" +
                            "   //  COOL!  \\\\\n" +
                            "   -Car Sharing-\n" +
                            "   `-._______.-'\n" +
                            "   ___`. | .'___\n" +
                            "  (______|______)");
        
        
        String input = "";
        BufferedReader fromKeyboard = new BufferedReader(new InputStreamReader(System.in));
        
        do{
            System.out.println("=========\n" +
                               "Hauptmenü\n" +
                                "=========");
            System.out.println("");
            
            System.out.println("  [K] Kunde anlegen\n" +
                                "  [F] Fahrzeug anlegen\n" +
                                "  [A] Fahrzeug ausleihen\n" +
                                "  [L] Leihverträge auflisten\n" +
                                "  [E] Ende");
           
            
            System.out.print("Deine Auswahl: ");
            
            input = fromKeyboard.readLine().toUpperCase();
            
            switch(input){
                case "K": createCustomer(service, fromKeyboard);
                        break;
                case "F": createCar(service, fromKeyboard);
                    break;
                case "A": rentCar(service, fromKeyboard);
                    break;
                case "L": listContractsForCustomer(service, fromKeyboard);
            }
            
             }while(!input.equals("E"));       
    }
    
    private static void createCustomer(SoapService service, BufferedReader keyboard) throws IOException{
        
        System.out.println("=============\n" +
                            "Kunde anlegen\n" +
                            "=============");
        System.out.println("");
        
        System.out.print("Vorname: ");
        String vorname = keyboard.readLine();
        
        System.out.print("Nachname: ");
        String nachname = keyboard.readLine();
        
        System.out.print("Straße: ");
        String Strasse = keyboard.readLine();
        
        System.out.print("Hausnummer: ");
        String hausnummer = keyboard.readLine();
        
        System.out.print("Postleitzahl: ");
        String plz = keyboard.readLine();
        
        System.out.print("Ort: ");
        String ort = keyboard.readLine();
        
        System.out.print("Land: ");
        String land = keyboard.readLine();
        
        Kunde newKunde = new Kunde();
        newKunde.setVorname(vorname);
        newKunde.setNachname(nachname);
        newKunde.setStrasse(Strasse);
        newKunde.setHausnummer(hausnummer);
        newKunde.setPostleitzahl(plz);
        newKunde.setOrt(ort);
        newKunde.setLand(land);
        
        Kunde kunde = service.registerKunde(newKunde);
        
       System.out.println("");
       System.out.println("Kundennummer "+ kunde.getId() +" wurde angelegt.");
    }
    
    private static void createCar(SoapService service, BufferedReader keyboard)throws IOException, DatatypeConfigurationException{
        System.out.println("================\n" +
                            "Fahrzeug anlegen\n" +
                            "================");
        
        System.out.print("Hersteller: ");
        String hersteller = keyboard.readLine();
        System.out.print("Modell: ");
        String modell = keyboard.readLine();
        System.out.print("Baujahr (yyyy): ");
        String baujahrInput = keyboard.readLine();
        
        //DatatypeFactory dtf = DatatypeFactory.newInstance();
        //XMLGregorianCalendar bjahr = dtf.newXMLGregorianCalendar(baujahrInput);
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date baujahr = null;
        
        try{
        // Workaound für Bug beim Datum
            baujahr = sdf.parse(baujahrInput);
        }catch(ParseException ex){
            System.out.println("Fehlerhafte Eingabe des Baujahrs");
            return;
        }
        
        Fahrzeug fahrzeug = new Fahrzeug();
        fahrzeug.setModell(modell);
        fahrzeug.setHersteller(hersteller);
       // fahrzeug.setBaujahr(bjahr);
        
        Fahrzeug car = service.registerFahrzeug(fahrzeug, baujahr.getTime());
                 
        System.out.println("");
        System.out.println("Fahrzeugnummer "+ car.getId() +" wurde angelegt.");
    }
    
    private static void rentCar(SoapService service, BufferedReader keyboard) throws IOException{
        System.out.println("==================\n" +
                            "Fahrzeug ausleihen\n" +
                            "==================");
        System.out.println("Folgende Fahrzeuge stehen zur Verfügung:");
        System.out.println("");
        
 
        for(Fahrzeug fahrzeug: service.listAllFahrzeug()){
            
            String bjahr = fahrzeug.getBaujahr() != null ? Integer.toString(fahrzeug.getBaujahr().getYear()) : "null";
            System.out.println(""+ fahrzeug.getHersteller() +" "+ fahrzeug.getModell()+ ", Baujahr "+ bjahr+", ID "+ fahrzeug.getId() );
        }
        
        System.out.println("");
        
        System.out.print("Kundennummer: ");
        String kundennummerInput = keyboard.readLine();
        System.out.print("Fahrzeug-ID: ");
        String idFahrzeugInput = keyboard.readLine();
        System.out.print("Abholung am (yyyy-mm-dd): ");
        String startDateInput = keyboard.readLine();
        System.out.print("Rückgabe am (yyyy-mm-dd): ");
        String endDateInput = keyboard.readLine();
        
        long kdnr;
        long fznr;
        
        try{
             kdnr = Long.parseLong(kundennummerInput);
        }catch(NumberFormatException ex){
            System.out.println("Die Kundennummer ist keine Zahl");
            return;
        }
        
        try{
             fznr = Long.parseLong(idFahrzeugInput);
        }catch(NumberFormatException ex){
            System.out.println("Die Fahrzeugnummer ist keine Zahl");
            return;
        }
        
        SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date endDate = null;
        Date startDate = null;
        
        try{
            startDate = sdf.parse(startDateInput);
            endDate = sdf.parse(endDateInput);
        }catch(ParseException ex){
            System.out.println("Ungültige Eingabe des Datums");               
        }
        
        Leihvertrag lvertrag = new Leihvertrag();
        Kunde kunde = new Kunde();
        kunde.setId(kdnr);
        
        Fahrzeug fahrzeug = new Fahrzeug();
        fahrzeug.setId(fznr);
        
        lvertrag.setKunde(kunde);
        lvertrag.setFahrzeug(fahrzeug);
        
        Leihvertrag vertrag = null;
        try{
          vertrag = service.createLeihvertrag(lvertrag, startDate.getTime(), endDate.getTime());
        }catch(Exception ex){
            System.out.println("Kundenummer oder Fahrzeugnummer wurde nicht gefunden!");
            return;
        }
 
        if(vertrag != null)
        System.out.println("Alles klar! Leihvertrag mit der ID "+vertrag.getId()+" wurde anglegegt.");
        else System.out.println("Leider ist dieses Fahrzeug zu diesem Zeitpunkt bereits ausgeliehen");
        
        
    }
    
    private static void listContractsForCustomer(SoapService service, BufferedReader keyboard) throws IOException{
        
        System.out.println("======================\n" +
                            "Leihverträge auflisten\n" +
                            "======================");
        System.out.println("");
        
        System.out.print("Eingabe Kundennummer: ");
        String kdnrInput = keyboard.readLine();
        long kdnr = 0;
        try{
            kdnr = Long.parseLong(kdnrInput);
        }catch(NumberFormatException ex){
            System.out.println("Die Kundenummer ist keine Zahl, ungültige Eingabe");
            return;
        }
        
        System.out.println("Folgende Leihverträge stehen für die Kundenummer "+ kdnr +" zur Verfügung: ");
        System.out.println("");
        
        for(Leihvertrag leihvertrag :service.getAllLeihvertragForKunde(kdnr)){
            SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
            Date startDate = leihvertrag.getBeginnDatum().toGregorianCalendar().getTime();
            Date endDate = leihvertrag.getEndeDatum().toGregorianCalendar().getTime();
    
            System.out.println(""+leihvertrag.getFahrzeug().getHersteller() +" "+ leihvertrag.getFahrzeug().getModell()+ ", ID "+ leihvertrag.getFahrzeug().getId()+", Von: "+ fmt.format(startDate)+", Bis: " + fmt.format(endDate));
        }

    }
    
}
