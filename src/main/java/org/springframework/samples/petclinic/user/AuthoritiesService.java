/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.user;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class AuthoritiesService {

	private AuthoritiesRepository authoritiesRepository;
	private UserService userService;

	@Autowired
	public AuthoritiesService(AuthoritiesRepository authoritiesRepository,UserService userService) {
		this.authoritiesRepository = authoritiesRepository;
		this.userService = userService;
	}

	@Transactional
	public void saveAuthorities(Authorities authorities) throws DataAccessException {
		authoritiesRepository.save(authorities);
	}
	
	@Transactional
	public void saveAuthorities(String username, String role) throws DataAccessException {
		Authorities authority = new Authorities();
		Optional<User> user = userService.findUser(username);
		if(user.isPresent()) {
			authority.setUser(user.get());
			authority.setAuthority(role);
			//user.get().getAuthorities().add(authority);
			authoritiesRepository.save(authority);
		}else
			throw new DataAccessException("User '"+username+"' not found!") {};
	}


}
