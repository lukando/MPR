package model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testBazowePensjeSaPoprawne() {
        assertEquals(25000, Position.PREZES.getBazowaPensja());
        assertEquals(18000, Position.WICEPREZES.getBazowaPensja());
        assertEquals(12000, Position.MANAGER.getBazowaPensja());
        assertEquals(8000, Position.PROGRAMISTA.getBazowaPensja());
        assertEquals(3000, Position.STAZYSTA.getBazowaPensja());
    }
}