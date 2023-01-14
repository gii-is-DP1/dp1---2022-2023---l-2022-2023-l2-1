package org.springframework.samples.petclinic.registeredUser;


import java.time.LocalTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.samples.petclinic.historico.Historico;
import org.springframework.samples.petclinic.historico.HistoricoService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisteredUserService {


    private RegisteredUserRepository registeredUserRepository;	
	
	@Autowired
	private UserService userService;

	@Autowired
	private HistoricoService historicoService;
	
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
		this.registeredUserRepository = registeredUserRepository;
    }

	@Transactional(readOnly = true)
	public RegisteredUser findRegisteredUserById(int id) throws DataAccessException {
		return registeredUserRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public RegisteredUser findRegisteredUserByUsername(User user) throws DataAccessException {
		return registeredUserRepository.findByUsername(user);
	}


	@Transactional(readOnly = true)
	public Collection<RegisteredUser> findRegisteredUser() throws DataAccessException {
		return registeredUserRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Collection<RegisteredUser> findRegisteredUserByName(String name) throws DataAccessException {
		return registeredUserRepository.findByName(name);
	}
    @Transactional
	public void saveRegisteredUser(RegisteredUser registeredUser) throws DataAccessException {
		//creating registeredUser
		registeredUserRepository.save(registeredUser);		
		//creating user
		userService.saveUser(registeredUser.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(registeredUser.getUser().getUsername(), "registeredUser");
		//crea el historico
		Historico hist = new Historico();
		hist.setRegisteredUserId(registeredUser.getId());
		hist.setMinasEncontradas(0);
		hist.setPartidasGanadas(0);
		hist.setPartidasTotales(0);
		hist.setPuntuacion(0);
		hist.setTiempoMedioPartida(LocalTime.of(0, 0, 0));
		hist.setTiempoTotalJuego(LocalTime.of(0, 0, 0));
		historicoService.saveHistorico(hist);
	}	
	@Transactional
	public void removeUser(Integer id){
		this.registeredUserRepository.deleteById(id);
	}
	@Transactional
    public void deleteUser(RegisteredUser user) throws DataAccessException {
        registeredUserRepository.delete(user);
    }
	@Transactional
	Page<RegisteredUser> findAllpageablePage(Pageable pageable){
		return registeredUserRepository.findAll(pageable);
	}
    @Transactional
    Iterable<RegisteredUser> findAllIterable(Sort sort){
		return registeredUserRepository.findAll(sort);
	}
	@Transactional
	Page<RegisteredUser> findByNameFromSubstring(String name, Pageable pageable){
		return registeredUserRepository.findByNameContaining(name, pageable);
	}


}
