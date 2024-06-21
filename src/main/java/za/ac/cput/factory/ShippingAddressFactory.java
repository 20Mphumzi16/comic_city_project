package za.ac.cput.factory;

//Joshua Mokwebo
//Student No 222191562
//GitHubRepository:My_commic_city_project

import za.ac.cput.domain.ShippingAddress;
import za.ac.cput.util.Helper;

import java.time.LocalTime;



public class ShippingAddressFactory {

    public ShippingAddress buildShippingAddress (LocalTime time,  String street , String suburb, String postalCode, String city ) {
        if (Helper.isInvalidTime(time)|| Helper.isStringNullorEmpty(street) || Helper.isStringNullorEmpty(suburb) || Helper.isInvalidPostalCode(postalCode) || Helper.isStringNullorEmpty(city))
            return null;
        return new ShippingAddress.ShippingAddressBuilder().
                setPreffered_delivery_time(time).
                setSuburb(suburb).
                setCity(city).
                setPostalCode(postalCode).
                build();

    }

}
