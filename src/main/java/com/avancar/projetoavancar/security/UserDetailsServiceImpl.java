package com.avancar.projetoavancar.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.avancar.projetoavancar.model.Usuario;
import com.avancar.projetoavancar.repository.UsuarioRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		//Busca pelo usuario
		Optional<Usuario> usuario = userRepository.findByUsuario(userName);
		//Caso o usuario nao for encontrado, retorna not found
		usuario.orElseThrow(()-> new UsernameNotFoundException(userName + "Usuário não encontrado!"));
		
		return usuario.map(UserDetailsImpl::new).get();
	}

}
