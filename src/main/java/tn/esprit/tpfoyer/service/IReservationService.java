package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {

    List<Reservation> retrieveAllReservations();
    Reservation retrieveReservation(String reservationId);
    Reservation addReservation(Reservation r);
    void removeReservation(String reservationId);
    Reservation modifyReservation(Reservation reservation);

    List<Reservation> trouverResSelonDateEtStatus(Date d, boolean b);
    List<Reservation> trouverResSelonType(Reservation.TypeReservation type);
    List<Reservation> trouverResAvantDate(Date date);
}
