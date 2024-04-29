import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrackingSystem implements Comparable <TrackingSystem> {
    private static TrackingSystem instance;  // Variable para almacenar la instancia única
    List<Package> packages=new ArrayList<>();
    List<Package> packagesPorCiudad=new ArrayList<>();
    List<Package> arregloBurbuja=new ArrayList<>();
    List<Package> arregloInsercion=new ArrayList<>();

    private TrackingSystem() {
    }

    // Método estático para obtener la instancia única
    public static TrackingSystem getInstance() {
        if (instance == null) {  // Si la instancia no existe, créala
            instance = new TrackingSystem();
        }
        return instance;  // Devuelve la instancia única
    }
    // Método paraA agregar paquetes
    public void addPackage(Package package1){
        packages.add(package1);
        Collections.sort(packages);
    }
    // Método para eliminar paquetes

    public void removePackage(List<Package> listaRemover,String trackingNumberT){
        if (searchByTrackingNumberLineal(listaRemover,trackingNumberT)>=0){
            packages.remove(searchByTrackingNumberLineal(listaRemover,trackingNumberT));
        }
    }

    public int searchByTrackingNumberLineal(List<Package> listaBuscar, String trackingNumberT) {
        for (int j=0; j<listaBuscar.size(); j++){
            if (listaBuscar!= null && listaBuscar.size()>=0 && listaBuscar.get(j).getTrackingNumber().equals(trackingNumberT)){
                return j;
            }
        }
        return -1;
    }
    public Package searchByRecipientAddress(List<Package> listaBuscarPorRecipient, Address recipientAddr) {
        for (int i=0; i<listaBuscarPorRecipient.size(); i++){
            if (listaBuscarPorRecipient.get(i).getRecipientAddress().getStreet().equals(recipientAddr.getStreet()) && listaBuscarPorRecipient.get(i).getRecipientAddress().getCity().equals(recipientAddr.getCity()) && listaBuscarPorRecipient.get(i).getRecipientAddress().getState().equals(recipientAddr.getState()) && listaBuscarPorRecipient.get(i).getRecipientAddress().getZipCode().equals(recipientAddr.getZipCode())){
                return listaBuscarPorRecipient.get(i);
            }
        }
        return null;
    }
    public Package searchByAddress(List<Package> listaBuscarPorRecipient, Address recipientAddr) {
        for (int i=0; i<listaBuscarPorRecipient.size(); i++){
            if (listaBuscarPorRecipient.get(i).getSenderAddress().getStreet().equals(recipientAddr.getStreet()) && listaBuscarPorRecipient.get(i).getSenderAddress().getCity().equals(recipientAddr.getCity()) && listaBuscarPorRecipient.get(i).getSenderAddress().getState().equals(recipientAddr.getState()) && listaBuscarPorRecipient.get(i).getSenderAddress().getZipCode().equals(recipientAddr.getZipCode()) || listaBuscarPorRecipient.get(i).getRecipientAddress().getStreet().equals(recipientAddr.getStreet()) && listaBuscarPorRecipient.get(i).getRecipientAddress().getCity().equals(recipientAddr.getCity()) && listaBuscarPorRecipient.get(i).getRecipientAddress().getState().equals(recipientAddr.getState()) && listaBuscarPorRecipient.get(i).getRecipientAddress().getZipCode().equals(recipientAddr.getZipCode())){
                return listaBuscarPorRecipient.get(i);
            }
        }
        return null;
    }
    public  Package searchBinary(List<Package> listaBuscarPorTrackingBinario, int targetTrackingNumberT) {
        int izquierda=0, derecha= listaBuscarPorTrackingBinario.size() -1;
        while (izquierda<=derecha){
            int numMedio=(derecha+izquierda)/2;
            if(targetTrackingNumberT==Integer.parseInt(listaBuscarPorTrackingBinario.get(numMedio).getTrackingNumber())){
                return listaBuscarPorTrackingBinario.get(numMedio);
            }else if(Integer.parseInt(listaBuscarPorTrackingBinario.get(numMedio).getTrackingNumber())<targetTrackingNumberT){
                izquierda=numMedio+1;
            }else{
                derecha=numMedio-1;
            }
        }
        return null;
    }
    public List<Package> searchByCity (List<Package> listaBuscarPorCity, String city) {
        for (int a=0; a<listaBuscarPorCity.size(); a++){
            if (listaBuscarPorCity!=null && listaBuscarPorCity.size()>=0 && listaBuscarPorCity.get(a).getRecipientAddress().getCity().equals(city) || listaBuscarPorCity.get(a).getSenderAddress().getCity().equals(city)){

                packagesPorCiudad.add(listaBuscarPorCity.get(a));
            }
        }
        return packagesPorCiudad;
    }
    public void Burbuja(){
        arregloBurbuja = new ArrayList<>();
        for (Package packagesRetornar:packages){
            try {
                Package packageAux=packagesRetornar.deepClone();
                arregloBurbuja.add(packageAux);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        int n = arregloBurbuja.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arregloBurbuja.get(j).getSenderAddress().street.compareTo(arregloBurbuja.get(j+1).getSenderAddress().street) >0 ) {
                    String temp = arregloBurbuja.get(j).getSenderAddress().street;
                    arregloBurbuja.get(j).getSenderAddress().street = arregloBurbuja.get(j+1).getSenderAddress().street;
                    arregloBurbuja.get(j+1).getSenderAddress().street = temp;
                }
            }
        }
    }

    public List<Package> getArregloBurbuja() {
        return arregloBurbuja;
    }

    public void Insercion(){
        arregloInsercion = new ArrayList<>();
        for (Package packagesRetornar:packages){
            try {
                Package packageAux=packagesRetornar.deepClone();
                arregloInsercion.add(packageAux);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        int n = arregloInsercion.size();
        for (int i = 1; i < n; ++i) {
            String key = arregloInsercion.get(i).getSenderAddress().state;
            int j = i - 1;

            while (j >= 0 && arregloInsercion.get(j).getSenderAddress().state.compareTo(key) > 0) {
                arregloInsercion.get(j+1).getSenderAddress().state = arregloInsercion.get(j).getSenderAddress().state;
                j = j - 1;
            }
            arregloInsercion.get(j+1).senderAddress.state = key;
        }
    }

    public List<Package> getArregloInsercion() {
        return arregloInsercion;
    }

    @Override
    public int compareTo(TrackingSystem o) {
        return 0;
    }
}

