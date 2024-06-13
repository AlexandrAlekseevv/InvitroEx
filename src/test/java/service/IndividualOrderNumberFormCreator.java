package service;

import config.ConfigLoader;
import models.IndividualOrderNumberForm;

public class IndividualOrderNumberFormCreator {
    public static final String TESTDATA_ORDER_NUMBER = "testdata.%s.order.number";
    public static final String TESTDATA_BIRTHDAY = "testdata.%s.order.birthday";
    public static final String TESTDATA_LASTNAME = "testdata.%s.order.lastname";

    public static IndividualOrderNumberForm withCredentialFromProperty(String userID) {
        return new IndividualOrderNumberForm(
                ConfigLoader.getTestData(String.format(TESTDATA_ORDER_NUMBER, userID)),
                ConfigLoader.getTestData((String.format(TESTDATA_BIRTHDAY, userID))),
                ConfigLoader.getTestData((String.format(TESTDATA_LASTNAME, userID)))
        );
    }
}
