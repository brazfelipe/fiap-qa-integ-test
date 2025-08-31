import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PrevisaoTempoTest {
	
	private static PrevisaoTempo previsaoTempo;

	@BeforeAll
	public static void setUp() throws IOException {
		
		previsaoTempo = new PrevisaoTempo();
		
	}
	
	@Test
	public void testPrevisaoPorCidade() throws JSONException, IOException {
		
		String cidade_teste = "São Paulo";
		String pais_teste = "BR";
		
		JSONObject previsao = previsaoTempo.getForecastByCityName(cidade_teste);
		
		String respostaHTTP = previsao.getString("cod");
		assertEquals("200", respostaHTTP);
    	
		String paisAPI = previsao.getJSONObject("city").getString("country");
    	String cidadeAPI = previsao.getJSONObject("city").getString("name");
    	
    	// assertAll agrupa os asserts e verifica todos do grupo, mesmo que algum deles falhe,
    	// que é diferente de simplesmente enfileirar asserts "normais" (que faria o código parar na primeira falha)
    	Assertions.assertAll("Verifica Cidade e País retornados pela API",
    		() -> assertEquals(cidade_teste, cidadeAPI),
    		() -> assertEquals(pais_teste, paisAPI)
    		);
	}
	
	@Test
	public void testPrevisaoPorCoordenadas() throws JSONException, IOException {
		
		String cidade_teste = "São Paulo";
		String pais_teste = "BR";
		double latitude = -23.547500;
		double longitude = -46.636100;
		
		JSONObject previsao = previsaoTempo.getForecastByCoord(latitude, longitude);
		
		String respostaHTTP = previsao.getString("cod");
		assertEquals("200", respostaHTTP);
		
		String paisAPI = previsao.getJSONObject("city").getString("country");
    	String cidadeAPI = previsao.getJSONObject("city").getString("name");
    	double latAPI = previsao.getJSONObject("city").getJSONObject("coord").getDouble("lat");
    	double lonAPI = previsao.getJSONObject("city").getJSONObject("coord").getDouble("lon");
    	
    	// assertAll agrupa os asserts e verifica todos do grupo, mesmo que algum deles falhe,
    	// que é diferente de simplesmente enfileirar asserts "normais" (que faria o código parar na primeira falha)
    	Assertions.assertAll("Verifica local retornado pela API",
    		() -> assertEquals(cidade_teste, cidadeAPI),
    		() -> assertEquals(pais_teste, paisAPI),
    		() -> assertEquals(latitude, latAPI),
    		() -> assertEquals(longitude, lonAPI)
    		);
    	
	}
	
}