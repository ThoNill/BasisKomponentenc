package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.nill.basiskomponenten.betrag.Geld;
import org.nill.basiskomponenten.gemeinsam.BetragsBündel;
import org.nill.basiskomponenten.gemeinsam.ProzentBündelMap;

public class ProzentBündelTest {

    @Test
    public void ungleichVerteilt() {
        ProzentBündelMap<String> x = new ProzentBündelMap<>();
        x.put("a", 1 / 3.0);
        x.put("b", 1 / 3.0);
        x.put("c", 1 / 3.0);

        BetragsBündel<String> b = x.verteilen(Geld.createAmount(100));

        assertEquals(33.33, b.getBetrag("a").getNumber().doubleValue(), 0.0001d);
        assertEquals(33.33, b.getBetrag("b").getNumber().doubleValue(), 0.0001d);
        assertEquals(33.34, b.getBetrag("c").getNumber().doubleValue(), 0.0001d);
    }

    @Test
    public void gleichVerteilt() {
        ProzentBündelMap<String> x = new ProzentBündelMap<>();
        x.put("a", 1 / 2.0);
        x.put("b", 1 / 2.0);

        BetragsBündel<String> b = x.verteilen(Geld.createAmount(100));

        assertEquals(50.00, b.getBetrag("a").getNumber().doubleValue(), 0.0001d);
        assertEquals(50.00, b.getBetrag("b").getNumber().doubleValue(), 0.0001d);

    }

    @Test
    public void betragHolen() {
        ProzentBündelMap<String> x = new ProzentBündelMap<>();
        x.put("a", 1 / 2.0);
        x.put("b", 1 / 2.0);

        assertEquals(0.5, x.getBetrag("a"), 0.0001d);
        assertEquals(0.0, x.getBetrag("c"), 0.0001d);

    }

    @Test
    public void summe() {
        ProzentBündelMap<String> x = new ProzentBündelMap<>();
        x.put("a", 1 / 2.0);
        x.put("b", 1 / 2.0);

        assertEquals(1.0d, x.getSumme(), 0.0001d);
        assertEquals(0.5d, x.getSumme("a"), 0.0001d);
    }

    @Test
    public void fehlerVerteilt() {
        ProzentBündelMap<String> x = new ProzentBündelMap<>();
        try {
            BetragsBündel<String> b = x.verteilen(Geld.createAmount(100));
            fail("Ausnahme erwartet");
        } catch (IllegalArgumentException e) {

        } catch (Exception e) {
            fail("Unerwartete Ausnahme");
        }

    }

    @Test
    public void falschInitialisiert() {
        ProzentBündelMap<String> x = new ProzentBündelMap<>();
        try {
            x.put("a", -0.5);
            fail("Ausnahme erwartet");
        } catch (IllegalArgumentException e) {

        } catch (Exception e) {
            fail("Unerwartete Ausnahme");
        }

    }

}
