package com.coviam.booking_service.service;

import com.coviam.booking_service.model.Booking;
import com.coviam.booking_service.model.BookingUserDetails;
import com.coviam.booking_service.repository.BookingRepository;
import com.coviam.booking_service.repository.BookingUserDetailsRepository;
import com.coviam.booking_service.util.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingUserDetailsRepository bookingUserDetailsRepository;

    @Autowired
    RandomGenerator randomGenerator;

    public Booking getBooking(String booking_reference){
        return bookingRepository.findByBookingReference(booking_reference);
    }

    public void createBooking(String customer_id, String booking_type, String booking_source, String booking_phone_number, String booking_email, String super_pnr, List<BookingUserDetails> user_details){
        Booking newBooking = new Booking(customer_id, randomGenerator.generateRandomString(),super_pnr, Booking.Status.pending,"",Booking.Status.pending,booking_type,booking_source,booking_phone_number,booking_email);
        newBooking.setBookingUserDetails(user_details);
        bookingRepository.save(newBooking);
    }

    public void updateBookingPaymentSuccessful(String booking_reference, String payment_id){
        Booking current_booking = this.getBooking(booking_reference);
        current_booking.setBooking_status(Booking.Status.successful);
        current_booking.setPayment_status(Booking.Status.successful);
        current_booking.setPayment_id(payment_id);
        bookingRepository.save(current_booking);
    }

    public void updateBookingPaymentError(String booking_reference, String payment_id){
        Booking current_booking = this.getBooking(booking_reference);
        current_booking.setBooking_status(Booking.Status.deferred);
        current_booking.setPayment_status(Booking.Status.deferred);
        current_booking.setPayment_id(payment_id);
        bookingRepository.save(current_booking);
    }

    public void updateBookingPaymentCancel(String booking_reference, String payment_id){
        Booking current_booking = this.getBooking(booking_reference);
        current_booking.setBooking_status(Booking.Status.cancelled);
        current_booking.setPayment_status(Booking.Status.cancelled);
        current_booking.setPayment_id(payment_id);
        bookingRepository.save(current_booking);
    }
}
