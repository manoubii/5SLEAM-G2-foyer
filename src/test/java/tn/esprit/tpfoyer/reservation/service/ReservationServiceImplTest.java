package tn.esprit.tpfoyer.reservation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.repository.ReservationRepository;
import tn.esprit.tpfoyer.service.ReservationServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllReservations() {
        Reservation reservation = new Reservation("1", new Date(), true, null, new Date(), Reservation.TypeReservation.STANDARD);
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation));

        List<Reservation> reservations = reservationService.retrieveAllReservations();

        assertEquals(1, reservations.size());
        assertEquals("1", reservations.get(0).getIdReservation());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveReservation() {
        Reservation reservation = new Reservation("1", new Date(), true, null, new Date(), Reservation.TypeReservation.STANDARD);
        when(reservationRepository.findById("1")).thenReturn(java.util.Optional.of(reservation));

        Reservation foundReservation = reservationService.retrieveReservation("1");

        assertNotNull(foundReservation);
        assertEquals("1", foundReservation.getIdReservation());
        verify(reservationRepository, times(1)).findById("1");
    }

    @Test
    public void testAddReservation() {
        Reservation reservation = new Reservation("1", new Date(), true, null, new Date(), Reservation.TypeReservation.STANDARD);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation savedReservation = reservationService.addReservation(reservation);

        assertNotNull(savedReservation);
        assertEquals("1", savedReservation.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    public void testModifyReservation() {
        Reservation reservation = new Reservation("1", new Date(), true, null, new Date(), Reservation.TypeReservation.STANDARD);
        when(reservationRepository.save(reservation)).thenReturn(reservation);

        Reservation modifiedReservation = reservationService.modifyReservation(reservation);

        assertNotNull(modifiedReservation);
        assertEquals("1", modifiedReservation.getIdReservation());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    public void testRemoveReservation() {
        reservationService.removeReservation("1");
        verify(reservationRepository, times(1)).deleteById("1");
    }

    @Test
    public void testTrouverResSelonDateEtStatus() {
        Date date = new Date();
        Reservation reservation = new Reservation("1", date, true, null, new Date(), Reservation.TypeReservation.STANDARD);

        when(reservationRepository.findAllByAnneeUniversitaireBeforeAndEstValide(date, true))
                .thenReturn(Arrays.asList(reservation));

        List<Reservation> foundReservations = reservationService.trouverResSelonDateEtStatus(date, true);

        assertEquals(1, foundReservations.size());
        assertEquals("1", foundReservations.get(0).getIdReservation());
        verify(reservationRepository, times(1)).findAllByAnneeUniversitaireBeforeAndEstValide(date, true);
    }

    @Test
    public void testTrouverResSelonType() {
        Reservation reservation = new Reservation("1", new Date(), true, null, new Date(), Reservation.TypeReservation.STANDARD);

        when(reservationRepository.findAllByTypeReservation(Reservation.TypeReservation.STANDARD))
                .thenReturn(Arrays.asList(reservation));

        List<Reservation> foundReservations = reservationService.trouverResSelonType(Reservation.TypeReservation.STANDARD);

        assertEquals(1, foundReservations.size());
        assertEquals("1", foundReservations.get(0).getIdReservation());
        verify(reservationRepository, times(1)).findAllByTypeReservation(Reservation.TypeReservation.STANDARD);
    }

    @Test
    public void testTrouverResAvantDate() {
        Date date = new Date();
        Reservation reservation = new Reservation("1", new Date(), true, null, new Date(), Reservation.TypeReservation.STANDARD);

        when(reservationRepository.findAllByDateReservationBefore(date))
                .thenReturn(Arrays.asList(reservation));

        List<Reservation> foundReservations = reservationService.trouverResAvantDate(date);

        assertEquals(1, foundReservations.size());
        assertEquals("1", foundReservations.get(0).getIdReservation());
        verify(reservationRepository, times(1)).findAllByDateReservationBefore(date);
    }
}
