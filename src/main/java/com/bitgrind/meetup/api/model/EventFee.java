package com.bitgrind.meetup.api.model;

/**
 * Created by mrenouf on 1/5/15.
 */
public class EventFee {
    /**
     * Fee label, typically "Price"
     */
    String label;

    /**
     * Amount of the fee
     */
    double amount;

    /**
     * Currency accepted for fee
     */
    String currency;

    /**
     * Fee description, typically "per person"
     */
    String description;

    /**
     * Accepted method of payment. Can be one of "paypal", "amazon", or "cash"
     */
    String accepts;

    /**
     * "1" if payment is required to RSVP, "0" otherwise
     */
    boolean required;

}
