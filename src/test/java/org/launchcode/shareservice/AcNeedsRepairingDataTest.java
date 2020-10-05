package org.launchcode.shareservice;
import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.launchcode.shareservice.models.AcNeedsRepairing;
import org.launchcode.shareservice.models.AcNeedsRepairingData;
import org.launchcode.shareservice.models.State;
import org.launchcode.shareservice.models.ZipCode;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class AcNeedsRepairingDataTest {

    State state1= new State();

    ZipCode zipCode1=new ZipCode();

    private AcNeedsRepairing event = new AcNeedsRepairing(
            "David",
            "No cooling",
            "129 steamBoat Ln",
            "daviddong201612@gmail.com",
            "6366278615",
            "10/5/2020",
            "12:30",
            "Yes",
            state1,
            zipCode1);

    final AcNeedsRepairingData testData = new AcNeedsRepairingData();

    @Test
    public void getFieldValueTest() {
        final String theValue=testData.getFieldValue(event, "name");
        assertEquals("David",theValue);
    }


}
