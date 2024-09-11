package com.diginamic.apiback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.diginamic.apiback.models.Absence;
import com.diginamic.apiback.models.User;
import com.diginamic.apiback.repository.AbsenceRepository;
import com.diginamic.apiback.services.AbsenceService;
import com.diginamic.apiback.services.TraitementNuitService;

@SpringBootTest
class ApiBackApplicationTests {

	@SpyBean
	private TraitementNuitService traitementNuitService;

	@Mock
	private AbsenceRepository absenceRepository;
	
	@InjectMocks
	private AbsenceService absenceService;

	@Test
	void testFindAll(){
		List<Absence> expectedAbsences = new ArrayList<>();
		expectedAbsences.add(new Absence());
		expectedAbsences.add(new Absence());
		when(absenceRepository.findAll()).thenReturn(expectedAbsences);

		List<Absence> actualAbsences = absenceRepository.findAll();

		assertEquals(expectedAbsences, actualAbsences);
		verify(absenceRepository, times(1)).findAll();
	}
	
	@Test
	void testFindAllForCurrentManager(){
		User user = new User();
		List<Absence> expectedAbsences = new ArrayList<>();
		expectedAbsences.add(new Absence());
		expectedAbsences.add(new Absence());
		when(absenceRepository.findByManagerId(user)).thenReturn(expectedAbsences);

		List<Absence> actualAbsences = absenceService.findAllForCurrentManager(user);
		
		assertEquals(expectedAbsences, actualAbsences);
		verify(absenceRepository, times(1)).findByManagerId(user);
	}

	@Test
	void contextLoads() {
		traitementNuitService.launchTraitementNuit();
	}

}
