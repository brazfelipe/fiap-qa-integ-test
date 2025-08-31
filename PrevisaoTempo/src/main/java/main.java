import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class main {

	public static void main(String[] args) throws JSONException, IOException {
		
		PrevisaoTempo previsaoTempo = new PrevisaoTempo();
		
		JSONObject previsao = null;
		
		Scanner ler = new Scanner(System.in);
		int opcao;
		String menu = "Previsão do tempo para AMANHÃ\n\n"+
						"1 - Nome da cidade\n" +
						"2 - Coordenadas";
		
		System.out.println(menu);
		opcao = ler.nextInt();
		ler.nextLine();
		
		switch (opcao) {
			case 1:
				System.out.println("\nDigite o nome da cidade: ");
				String cidade = ler.nextLine();
				ler.close();
				System.out.println("\nBuscando informações...");

				previsao = previsaoTempo.getForecastByCityName(cidade);

			break;
			
			case 2:
				System.out.println("\nDigite a LATITUDE: ");
				double latitude = ler.nextDouble();
				System.out.println("Digite a LONGITUDE: ");
				double longitude = ler.nextDouble();
				ler.close();
				System.out.println("\nBuscando informações...");
				
				previsao = previsaoTempo.getForecastByCoord(latitude, longitude);
				
			break;
		}
		
		previsaoTempo.displayForecastForTomorrow(previsao);

	}

}
