
import com.github.matkubiak.tqs.IStockmarketService;
import com.github.matkubiak.tqs.Stock;
import com.github.matkubiak.tqs.StocksPortfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {

    private StocksPortfolio portfolio;
    private Stock stockA;
    private Stock stockB;
    private Stock invalidStock;

    @Mock
    IStockmarketService service;
    private final double appleStockValue = 5.5;
    private final double teslaStockValue = 4.0;

    public static class InvalidStockMatcher implements ArgumentMatcher<String> {
        @Override
        public boolean matches(String argument) {
            return argument != null && !(argument.equals("AAPL") || argument.equals("TSLA"));
        }
    }

    @BeforeEach
    void setup() {
        service = mock(IStockmarketService.class);
        portfolio = new StocksPortfolio(service);
        stockA = new Stock("AAPL", 1);
        stockB = new Stock("TSLA", 3);
        invalidStock = new Stock("Nonexistent_Stock", 2);

        when(service.lookUpPrice("AAPL")).thenReturn(appleStockValue);
        when(service.lookUpPrice("TSLA")).thenReturn(teslaStockValue);
        when(service.lookUpPrice(argThat(new InvalidStockMatcher()))).thenThrow(new RuntimeException("Data not found"));
    }

    @Test
    void testTotalValue() {
        assertEquals(0.0, portfolio.totalValue());

        portfolio.addStock(stockA);
        portfolio.addStock(stockB);
        assertEquals(appleStockValue + 3 * teslaStockValue, portfolio.totalValue());

        verify(service, times(2)).lookUpPrice(anyString());
        verify(service).lookUpPrice(stockA.getLabel());
        verify(service).lookUpPrice(stockB.getLabel());

        portfolio.addStock(invalidStock);
        assertThrows(RuntimeException.class, () -> portfolio.totalValue());
    }
}
