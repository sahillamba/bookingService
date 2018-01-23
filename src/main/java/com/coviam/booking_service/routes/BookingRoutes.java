package com.coviam.booking_service.routes;


import com.coviam.booking_service.model.Booking;
import com.coviam.booking_service.model.BookingUserDetails;
import com.coviam.booking_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingRoutes {

    @Autowired
    public BookingService bookingService;


    @RequestMapping(
            value = "/createBooking",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void createBooking(@PathVariable("customer_id") String customer_id,@PathVariable("booking_type") String booking_type,@PathVariable("booking_source") String booking_source,@PathVariable("booking_phone_number") String booking_phone_number,@PathVariable("booking_email") String booking_email,@PathVariable("super_pnr") String super_pnr,@PathVariable("user_details") List<BookingUserDetails> user_details) {
         bookingService.createBooking(customer_id,booking_type,booking_source,booking_phone_number,booking_email,super_pnr,user_details);
    }


    @RequestMapping(
            value = "/get/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Booking getBooking(@PathVariable("id") String id) {
        return bookingService.getBooking(id);
    }

    @RequestMapping(
            value = "/updateBookingPaymentSuccessful",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void updateBookingPaymentSuccessful(@PathVariable("booking_reference") String booking_reference,@PathVariable("payment_id") String payment_id){
        bookingService.updateBookingPaymentSuccessful(booking_reference,payment_id);
    }

    @RequestMapping(
            value = "/updateBookingPaymentError",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void updateBookingPaymentError(@PathVariable("booking_reference") String booking_reference,@PathVariable("payment_id") String payment_id){
        bookingService.updateBookingPaymentError(booking_reference,payment_id);
    }

    @RequestMapping(
            value = "/updateBookingPaymentCancel",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void updateBookingPaymentCancel(@PathVariable("booking_reference") String booking_reference,@PathVariable("payment_id") String payment_id){
        bookingService.updateBookingPaymentCancel(booking_reference,payment_id);
    }
}
